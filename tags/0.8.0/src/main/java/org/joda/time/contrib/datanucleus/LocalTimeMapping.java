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
import org.datanucleus.store.mapped.mapping.ObjectAsIntegerMapping;
import org.joda.time.LocalTime;

/**
 * Maps a {@link org.joda.time.LocalTime} to a INTEGER field representing the number of milliseconds in the day.
 *
 * @see org.joda.time.LocalTime#getMillisOfDay()
 * @see org.joda.time.LocalTime#fromMillisOfDay(long)
 */
public class LocalTimeMapping extends ObjectAsIntegerMapping
{

    public Class getJavaType()
    {
        return LocalTime.class;
    }

    public Object getSampleValue( ClassLoaderResolver classLoaderResolver )
    {
        return LocalTime.MIDNIGHT;
    }

    protected Number objectToNumber( Object object )
    {
        Number num;
        if (object instanceof LocalTime)
        {
            num = Integer.valueOf(((LocalTime) object).getMillisOfDay());
        }
        else
        {
            num = (Number) object;
        }

        return num;
    }

    protected Object numberToObject( Number value )
    {
        return LocalTime.fromMillisOfDay(value.longValue());
    }
}
