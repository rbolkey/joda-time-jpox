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
import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;

/**
 * Maps a {@link org.joda.time.Period} to a VARCHAR field.  The format is PnYnMnWnDTnHnMnS where n is the value.
 *
 * @author Richard Bolkey
 * @see Period#toString(org.joda.time.format.PeriodFormatter)
 * @see org.joda.time.format.ISOPeriodFormat#standard()
 * @see Period#Period(Object)
 */
public class PeriodMapping extends ObjectAsStringMapping
{

    public Class getJavaType()
    {
        return Period.class;
    }

    public Object getSampleValue( ClassLoaderResolver classLoaderResolver )
    {
        return Period.ZERO;
    }

    protected String objectToString( Object object )
    {
        String period;
        if (object instanceof Period)
        {
            period = ((Period) object).toString(ISOPeriodFormat.standard());
        }
        else
        {
            period = (String) object;
        }
        return period;
    }

    protected Object stringToObject( String s )
    {
        return new Period(s);
    }
}
