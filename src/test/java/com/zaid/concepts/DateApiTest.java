package com.zaid.concepts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class DateApiTest {
  private ZoneId zone1, zone2;

  @Before
  public void setup() {
    zone1 = ZoneId.of("Europe/Berlin");
    zone2 = ZoneId.of("Brazil/East");
  }

  @Test
  public void testClockAndDate() {
    Clock clock = Clock.systemDefaultZone();
    long millis = clock.millis();
    Instant instant = clock.instant();
    Date legacyDate = Date.from(instant);
    System.out.println("testClockAndDate: " + millis + "---" + legacyDate);
  }

  @Test
  public void testLocalTime() {
    LocalTime now1 = LocalTime.now(zone1);
    LocalTime now2 = LocalTime.now(zone2);
    long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
    long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
    LocalTime late = LocalTime.of(23, 59, 59);
    DateTimeFormatter germanFormatter =
        DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);
    LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
    assertFalse(now1.isBefore(now2));
    assertEquals(hoursBetween, -2);
    assertEquals(minutesBetween, -179);
    assertEquals(late.toString(), "23:59:59");
    assertEquals(leetTime.toString(), "13:37");
  }

  @Test
  public void testLocalDate() {
    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
    LocalDate yesterday = tomorrow.minusDays(2);
    LocalDate spainDay = LocalDate.of(2016, Month.OCTOBER, 12);
    DayOfWeek dayOfWeek = spainDay.getDayOfWeek();
    DateTimeFormatter germanFormatter =
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
    LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
    System.out.println("yesterday: " + yesterday + ", dayOfWeek: " + dayOfWeek + ", xmas: " + xmas);
  }

  @Test
  public void testLocalDateTime() {
    LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
    DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
    Month month = sylvester.getMonth();
    long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
    Instant instant = sylvester.atZone(ZoneId.systemDefault()).toInstant();
    Date legacyDate = Date.from(instant);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm");
    LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
    String string = formatter.format(parsed);
    System.out.println(string);
    System.out
        .println("Month: " + month + ", dayOfWeek: " + dayOfWeek + ", minuteOfDay: " + minuteOfDay);
    System.out.println("legacyDate: " + legacyDate + ", format date:" + string);
  }

}
