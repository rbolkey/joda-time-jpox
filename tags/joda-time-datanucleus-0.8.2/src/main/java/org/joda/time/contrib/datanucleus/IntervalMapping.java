/**
 * Copyright (C) 2008 Richard Bolkey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joda.time.contrib.datanucleus;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ObjectManager;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.mapped.DatastoreAdapter;
import org.datanucleus.store.mapped.DatastoreContainerObject;
import org.datanucleus.store.mapped.expression.LogicSetExpression;
import org.datanucleus.store.mapped.expression.QueryExpression;
import org.datanucleus.store.mapped.expression.ScalarExpression;
import org.datanucleus.store.mapped.mapping.SingleFieldMultiMapping;
import org.joda.time.Interval;

import java.sql.Timestamp;

/**
 * Maps an {@link org.joda.time.Interval} to a pair of TIMESTAMP columns representing the start and end of the
 * interval.
 *
 * @author Richard Bolkey
 * @see org.joda.time.Interval#getStartMillis()
 * @see org.joda.time.Interval#getEndMillis()
 */
public class IntervalMapping extends SingleFieldMultiMapping
{

    private static final Interval sampleValue = new Interval(0, 0);

    public void initialize( DatastoreAdapter datastoreAdapter, AbstractMemberMetaData abstractMemberMetaData,
                            DatastoreContainerObject datastoreContainerObject, ClassLoaderResolver classLoaderResolver )
    {
        super.initialize(datastoreAdapter, abstractMemberMetaData, datastoreContainerObject, classLoaderResolver);

        addDatastoreField(ClassNameConstants.JAVA_SQL_TIMESTAMP); // start
        addDatastoreField(ClassNameConstants.JAVA_SQL_TIMESTAMP); // end
    }

    public Class getJavaType()
    {
        return Interval.class;
    }

    public Object getSampleValue( ClassLoaderResolver classLoaderResolver )
    {
        return sampleValue;
    }

    public Object getObject( ObjectManager objectManager, Object resultSet, int[] exprIndex )
    {
        // check for null entries
        if (getDataStoreMapping(0).getObject(resultSet, exprIndex[0]) == null)
        {
            return null;
        }

        Timestamp start = (Timestamp) getDataStoreMapping(0).getObject(resultSet, exprIndex[0]);
        Timestamp end = (Timestamp) getDataStoreMapping(1).getObject(resultSet, exprIndex[1]);
        return new Interval(start.getTime(), end.getTime());
    }

    public void setObject( ObjectManager objectManager, Object preparedStatement, int[] exprIndex, Object value )
    {
        Interval interval = (Interval) value;

        if (interval == null)
        {
            getDataStoreMapping(0).setObject(preparedStatement, exprIndex[0], null);
            getDataStoreMapping(1).setObject(preparedStatement, exprIndex[1], null);
        }
        else
        {
            getDataStoreMapping(0).setObject(preparedStatement, exprIndex[0], new Timestamp(interval.getStartMillis()));
            getDataStoreMapping(1).setObject(preparedStatement, exprIndex[1], new Timestamp(interval.getEndMillis()));
        }
    }

    public ScalarExpression newLiteral( QueryExpression queryExpression, Object o )
    {
        return null;
    }

    public ScalarExpression newScalarExpression( QueryExpression queryExpression,
                                                 LogicSetExpression logicSetExpression )
    {
        return null;
    }
}
