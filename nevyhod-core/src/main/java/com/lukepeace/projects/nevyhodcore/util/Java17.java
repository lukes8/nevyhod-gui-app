package com.lukepeace.projects.nevyhodcore.util;


public class Java17 {

    static String formatValue(Object o) {

        if (o instanceof String s) {
            return s;
        }
        if (o instanceof Long l) {
            return String.format("Long value: %d", l);
        }
        if (o instanceof Double d) {
            return String.format("Double value: %f", d);
        }
        return "not-recognized";
    }

    static String checkValue(String s) {
        return switch (s) {
            case "Foo", "Bar" -> "great";
            case "Green", "Hi" -> "green";
            default -> "default";
        };
    }

    static String checkValue2(String o) {
        String s = """
                'The time has come,' the Walrus said,
                'To talk of many things:
                Of shoes -- and ships -- and sealing-wax --
                Of cabbages -- and kings --
                And why the sea is boiling hot --
                And whether pigs have wings.'
                """;
        return s;
    }

    static String checkNumber(int nbr) {
        return switch (nbr) {
            case 0, 1, 2 -> "smaller than 3";
            case 3 -> "3";
            case 4, 5, 6 -> "bigger than 3";
            default -> {
                yield "out of range";
            }
        };
    }

    enum DayOfWeek {
        MONDAY, THUESDAY, WEDNESDAY, SATURDAY, SUNDAY, FRIDAY, THURSDAY
    }
    enum TypeOfDay {
        WORK_WEEK, WEEKEND, HOLIDAY
    }

    static TypeOfDay detectDayType(DayOfWeek day) {
        return switch (day) {
            case MONDAY, THUESDAY, WEDNESDAY -> TypeOfDay.WORK_WEEK;
            case SATURDAY, SUNDAY -> TypeOfDay.WEEKEND;
            default -> TypeOfDay.HOLIDAY;
        };
    }
}
