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

package org.joda.time.contrib.jpox;

import org.jpox.ClassNameConstants;
import org.jpox.ObjectManager;
import org.jpox.store.mapped.expression.LogicSetExpression;
import org.jpox.store.mapped.expression.QueryExpression;
import org.jpox.store.mapped.expression.ScalarExpression;
import org.jpox.store.mapped.expression.SqlTemporalExpression;
import org.jpox.store.mapped.expression.SqlTimestampLiteral;
import org.jpox.store.mapped.mapping.SimpleDatastoreRepresentation;
import org.jpox.store.mapped.mapping.SingleFieldMapping;

import java.sql.Timestamp;

/**
 * Abstract SCO mapping for a java type that will be stored as a Timestamp type.
 */
public abstract class ObjectAsTimestampMapping extends SingleFieldMapping implements SimpleDatastoreRepresentation
{
    public ScalarExpression newLiteral( QueryExpression qs, Object value )
    {
        return new SqlTimestampLiteral(qs, this, (Timestamp) value);
    }

    public ScalarExpression newScalarExpression( QueryExpression qs, LogicSetExpression te )
    {
        return new SqlTemporalExpression(qs, this, te);
    }

    public abstract Class getJavaType();

    public String getJavaTypeForDatastoreMapping( int index )
    {
        return ClassNameConstants.JAVA_SQL_TIMESTAMP;
    }

    public void setObject( ObjectManager om, Object preparedStatement, int[] exprIndex, Object value )
    {
        getDataStoreMapping(0).setObject(preparedStatement, exprIndex[0], objectToTimestamp(value));
    }

    public Object getObject( ObjectManager om, Object resultSet, int[] exprIndex )
    {
        if (exprIndex == null)
        {
            return null;
        }

        Object datastoreValue = getDataStoreMapping(0).getObject(resultSet, exprIndex[0]);
        Object value = null;
        if (datastoreValue != null)
        {
            value = timestampToObject((Timestamp) datastoreValue);
        }
        return value;
    }

    /**
     * Method to set the datastore value based on the object value.
     *
     * @param object The object
     * @return The value to pass to the datastore
     */
    protected abstract Timestamp objectToTimestamp( Object object );

    /**
     * Method to extract the objects value from the datastore object.
     *
     * @param datastoreValue Value obtained from the datastore
     * @return The value of this object (derived from the datastore value)
     */
    protected abstract Object timestampToObject( Timestamp datastoreValue );
}
