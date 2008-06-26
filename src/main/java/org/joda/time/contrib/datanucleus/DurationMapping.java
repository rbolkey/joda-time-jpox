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
import org.joda.time.Duration;

/**
 * Maps a {@link org.joda.time.Duration} to a VARCHAR field. The format is PTnS where n is the value.
 *
 * @author Richard Bolkey
 * @see org.joda.time.Duration#toString()
 * @see org.joda.time.Duration#Duration(Object)
 */
public class DurationMapping extends ObjectAsStringMapping
{

    public Class getJavaType()
    {
        return Duration.class;
    }

    public Object getSampleValue( ClassLoaderResolver classLoaderResolver )
    {
        return Duration.ZERO;
    }

    protected String objectToString( Object object )
    {
        String duration;
        if (object instanceof Duration)
        {
            duration = object.toString();
        }
        else
        {
            duration = (String) object;
        }

        return duration;
    }

    protected Object stringToObject( String value )
    {
        return new Duration(value);
    }
}
