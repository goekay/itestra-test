package com.goekay;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

/**
 * From: http://www.itestra.de/karriere/kampagnen-informatiker-gefragt/qualitaet/
 */
public final class DayOfYearFromItestra {

    private DayOfYearFromItestra() { }

    // =================== Version 1 ====================

    private static final int[] d = new int[] { 0, 0, 31, 59, 90, 120,
            151, 181, 212, 243, 273, 304, 334 };

    public static int getDayOfYear1(final int year, final int month,
                             final int day) {
        return d[month] + day
                + ((year & 3) == 0 && month > 2 ? 1 : 0);
    }

    // =================== Version 2 ====================

    public static int getDayOfYear2(final int year, final int month,
                             final int day) {
        final Calendar cal = new GregorianCalendar(year, month - 1,
                day);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    // =================== Version 3 ====================

    /**
     * Represents the months.
     */
    public enum Month {
        JAN(31), FEB(28) {
            @Override
            public int getDayCount(final int year) {
                return super.getDayCount(year)
                        + (isLeapYear(year) ? 1 : 0);
            }

            private boolean isLeapYear(final int year) {
                return year % 4 == 0
                        && (year % 100 != 0 || year % 400 == 0);
            }
        },
        MAR(31), APR(30), MAY(31), JUN(30), JUL(31), AUG(31), SEP(
                30), OCT(31), NOV(30), DEC(31);

        private Month(final int dayCount) {
            this.dayCount = dayCount;
        }

        /** Number of days in that month in non-leap-years */
        private final int dayCount;

        /**
         * @return Number of days in that month
         */
        public int getDayCount(final int year) {
            return dayCount;
        }
    }

    /**
     * Calculates which day in a year a date represents, with Jan 1st
     * being 1, Feb 2nd being 33 etc.
     *
     * @param year
     *            the year
     * @param month
     *            the month (1 - 12)
     * @param day
     *            the day in month (1 - 31)
     * @return the day in year
     * @throws IllegalArgumentException
     *             for illegal input value combinations
     */
    public static int getDayOfYear3(final int year, final int month,
                             final int day) {
        if (month < 1 || month > Month.values().length)
            throw new IllegalArgumentException(
                    "Illegal month " + month);
        if (day < 1
                || day > Month.values()[month - 1].getDayCount(year))
            throw new IllegalArgumentException("Illegal day " + day);
        final Stream<Month> streamOfPreviousMonths = Arrays
                .stream(Month.values(), 0, month - 1);
        final Integer daysTillMonth = streamOfPreviousMonths.reduce(
                Integer.valueOf(0),
                (sum, m) -> sum + m.getDayCount(year),
                (a, b) -> a + b);
        return daysTillMonth.intValue() + day;
    }
}
