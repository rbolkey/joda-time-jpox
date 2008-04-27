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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

/**
 * Tests the mappings by persisting and retrieving {@link JodaTimeEntity}.
 */
public class PersistenceTest
{
    private final PersistenceManagerFactory _pmf;

    public PersistenceTest()
    {
        _pmf = JDOHelper.getPersistenceManagerFactory("jpox.properties");
    }

    @Test
    public void testPersistence()
    {
        DateTime dateTime = new DateTime(2008, 3, 14, 15, 9, 26, 0);
        DateTimeZone dateTimeZone = DateTimeZone.forID("America/Chicago");
        Duration duration = new Duration(4200, 6200);
        Interval interval = new Interval(0, 42000);
        LocalDate localDate = new LocalDate(2008, 3, 14);
        LocalTime localTime = new LocalTime(15, 9, 26);
        Period period = new Period(50, 23, 15, 333);

        // Do the persistence
        PersistenceManager pm = _pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();

            JodaTimeEntity entity =
                    new JodaTimeEntity(dateTime, dateTimeZone, duration, interval, localDate, localTime, period);
            entity.setId(1);

            pm.makePersistent(entity);

            tx.commit();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }

        // Do the retrieval
        pm = _pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();

            JodaTimeEntity entity = pm.getObjectById(JodaTimeEntity.class, 1L);

            assertEquals(entity.getDateTime(), dateTime);
            assertEquals(entity.getDateTimeZone(), dateTimeZone);
            assertEquals(entity.getDuration(), duration);
            assertEquals(entity.getInterval(), interval);
            assertEquals(entity.getLocalDate(), localDate);
            assertEquals(entity.getLocalTime(), localTime);
            assertEquals(entity.getPeriod(), period);

            pm.deletePersistent(entity);

            tx.commit();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }
}
