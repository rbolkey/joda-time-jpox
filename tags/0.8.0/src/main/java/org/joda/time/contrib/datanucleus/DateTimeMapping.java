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
import org.joda.time.DateTime;

import java.sql.Timestamp;

/**
 * Maps a {@link org.joda.time.DateTime} to a TIMESTAMP column.
 *
 * @author Richard Bolkey
 * @see org.joda.time.DateTime#getMillis()
 */
public class DateTimeMapping extends ObjectAsTimestampMapping
{
    private static final DateTime mappingSampleValue = new DateTime();

    public Object getSampleValue( ClassLoaderResolver clr )
    {
        return mappingSampleValue;
    }

    /**
     * Method to return the default length of this type in the datastore. {@link DateTime} requires 24 characters
     * ("yyyy-MM-ddTHH:mm:ss.SSSZ")
     *
     * @param index The index position
     * @return The default length
     */
    public int getDefaultLength( int index )
    {
        return 24;
    }

    public Class getJavaType()
    {
        return DateTime.class;
    }

    protected Timestamp objectToTimestamp( Object object )
    {
        Timestamp time;
        if (object instanceof DateTime)
        {
            time = new Timestamp(((DateTime) object).getMillis());
        }
        else
        {
            time = (Timestamp) object;
        }

        return time;
    }

    protected Object timestampToObject( Timestamp datastoreValue )
    {
        return new DateTime(datastoreValue.getTime());
    }
}
