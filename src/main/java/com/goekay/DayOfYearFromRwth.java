package com.goekay;

/**
 * Short and simple. Edge cases handled by library maintainers and Java Language developers.
 *
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @author Fabian Ohler <fabian.ohler1@rwth-aachen.de>
 * @since 24.05.2016
 */
public final class DayOfYearFromRwth {

    private DayOfYearFromRwth() { }

    // =================== Version 4 ====================

    public static int getDayOfYear4(final int year, final int month, final int day) {
        return new org.joda.time.LocalDate(year, month, day).getDayOfYear();
    }

    // =================== Version 5 ====================

    public static int getDayOfYear5(final int year, final int month, final int day) {
        return java.time.LocalDate.of(year, month, day).getDayOfYear();
    }
}
