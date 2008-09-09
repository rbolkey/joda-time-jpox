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
import org.datanucleus.store.mapped.mapping.ObjectAsStringMapping;
import org.joda.time.DateTimeZone;

/**
 * Maps a {@link org.joda.time.DateTimeZone} to a VARCHAR column.
 *
 * @author Richard Bolkey
 * @see org.joda.time.DateTimeZone#getID()
 * @see org.joda.time.DateTimeZone#forID(String)
 */
public class DateTimeZoneMapping extends ObjectAsStringMapping
{

    public Object getSampleValue( ClassLoaderResolver classLoaderResolver )
    {
        return DateTimeZone.UTC;
    }

    public Class getJavaType()
    {
        return DateTimeZone.class;
    }

    /**
     * Method to return the default length of this type in the datastore. Timezones require 30 chars.
     *
     * @param index The index position
     * @return The default length
     */
    public int getDefaultLength( int index )
    {
        return 30;
    }

    protected String objectToString( Object object )
    {
        String locale;
        if (object instanceof DateTimeZone)
        {
            locale = ((DateTimeZone) object).getID();
        }
        else
        {
            locale = (String) object;
        }

        return locale;
    }

    protected Object stringToObject( String s )
    {
        return DateTimeZone.forID(s);
    }

}
