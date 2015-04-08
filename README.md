# joda-time-jpox

DataNucleus/JPOX support for Joda Time. **This is an archive of my Google Code repository.**

## Overview

[Joda-Time](http://joda-time.sourceforge.net/index.html) is a quality alternative and improvement on the
JDK Date and Calendar classes.  [DataNucleus](http://www.datanucleus.org/) is a JDO/JPA compliant persistence engine.

This project supplies class mapping for Joda-Time classes to be persisted using !DataNucleus/JPOX.

## Requirements

  * JDK 1.3+
  * !DataNucleus 1.0.0+ or JPOX 1.2.2+
  * Joda-Time 1.5.0+

## Releases

*0.8.2* - Updated to DataNucleus 1.1.x releases.

*0.8.1* - Fix for including types back into the default fetch group.

*0.8.0* - Updated dependency from JPOX 1.2.2 to DataNucleus 1.0.0 Final.

*0.7.0* `[`Initial Release`]`- Contains mappings for DateTime, DateTimeZone, Duration, Interval, LocalDate, LocalTime, and Period.

## Maven Repository

```
<repository>
    <id>joda-time-datanucleus</id>
    <name>Joda Time Mapping for Datanucleus</name>
    <url>http://joda-time-jpox.googlecode.com/svn/repository</url>
    <snapshots>
        <enabled>false</enabled>
    </snapshots>
    <releases>
        <enabled>true</enabled>
    </releases>
</repository>

<dependencies>
  <dependency>
    <groupId>com.google.code.joda-time-jpox</groupId>
    <artifactId>joda-time-datanucleus</artifactId>
    <version>0.8.2</version>
  </dependency>
</dependencies>
```

## Available Types

The main types that can be persisted

| *Class* | *SQL Column Type* | *Description* |
|---------|-------------------|---------------|
| DateTime | TIMESTAMP | |
| DateTimeZone | VARCHAR | |
| Duration | VARCHAR | The format is PTnS where n is the value. |
| Interval | TIMESTAMP, TIMESTAMP | Two columns for start and end of interval. |
| LocalDate | TIMESTAMP | |
| LocalTime | INTEGER | Number of milliseconds |
| Period | VARCHAR | The format is PnYnMnWnDTnHnMnS where n is the value |
