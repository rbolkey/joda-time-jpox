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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;

/**
 * Test persistence class using joda time types.
 */
public class JodaTimeEntity
{
    private long id;

    private DateTime dateTime;

    private DateTimeZone dateTimeZone;

    private Duration duration;

    private Interval interval;

    private LocalDate localDate;

    private LocalTime localTime;

    private Period period;

    public JodaTimeEntity()
    {
        dateTime = new DateTime();
        dateTimeZone = DateTimeZone.forOffsetHours(-6);
        duration = Duration.ZERO;
        interval = new Interval(0, 42);
        localDate = new LocalDate();
        localTime = new LocalTime();
        period = Period.ZERO;
    }

    public JodaTimeEntity( DateTime dateTime, DateTimeZone dateTimeZone, Duration duration, Interval interval,
                           LocalDate localDate, LocalTime localTime, Period period )
    {
        this.dateTime = dateTime;
        this.dateTimeZone = dateTimeZone;
        this.duration = duration;
        this.interval = interval;
        this.localDate = localDate;
        this.localTime = localTime;
        this.period = period;
    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public DateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime( DateTime dateTime )
    {
        this.dateTime = dateTime;
    }

    public DateTimeZone getDateTimeZone()
    {
        return dateTimeZone;
    }

    public void setDateTimeZone( DateTimeZone dateTimeZone )
    {
        this.dateTimeZone = dateTimeZone;
    }

    public Duration getDuration()
    {
        return duration;
    }

    public void setDuration( Duration duration )
    {
        this.duration = duration;
    }

    public Interval getInterval()
    {
        return interval;
    }

    public void setInterval( Interval interval )
    {
        this.interval = interval;
    }

    public LocalDate getLocalDate()
    {
        return localDate;
    }

    public void setLocalDate( LocalDate localDate )
    {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime()
    {
        return localTime;
    }

    public void setLocalTime( LocalTime localTime )
    {
        this.localTime = localTime;
    }

    public Period getPeriod()
    {
        return period;
    }

    public void setPeriod( Period period )
    {
        this.period = period;
    }
}
