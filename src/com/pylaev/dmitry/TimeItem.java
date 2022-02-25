package com.pylaev.dmitry;

import com.digdes.school.DatesToCronConvertException;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class TimeItem {
    private final int year;
    private final int month;
    private final int dayOfMonth;
    private final int hour;
    private final int minute;
    private final DayOfWeek dayOfWeek;

    public int getYear ( ) {
        return year;
    }
    public String getMonth ( ) {
        return String.valueOf(month + 1);
    }
    public String getDayOfMonth ( ) {
        return String.valueOf(dayOfMonth + 1);
    }
    public String getHour ( ) {
        return String.valueOf(hour);
    }
    public String getMinute ( ) {
        return String.valueOf(minute);
    }
    public String getDayOfWeek ( ) {
        return String.valueOf(dayOfWeek);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeItem)) return false;
        TimeItem timeItem = (TimeItem) o;
        return getYear() == timeItem.getYear()
                && getMonth().equals(timeItem.getMonth())
                && getDayOfMonth().equals(timeItem.getDayOfMonth())
                && getHour().equals(timeItem.getHour())
                && getMinute().equals(timeItem.getMinute())
                && getDayOfWeek().equals(timeItem.getDayOfWeek());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYear(), getMonth(), getDayOfMonth(), getHour(), getMinute(), getDayOfWeek());
    }

    public TimeItem (String str) throws DatesToCronConvertException {
        try {
            this.year = Integer.parseInt(str.substring(0, str.indexOf("-")));
            this.month = Integer.parseInt(str.substring(str.indexOf("-") + 1, str.lastIndexOf("-"))) - 1;
            this.dayOfMonth = Integer.parseInt(str.substring(str.lastIndexOf("-") + 1, str.lastIndexOf("-") + 3)) - 1;
            this.hour = Integer.parseInt(str.substring(str.indexOf("T") + 1, str.indexOf("T") + 3));
            this.minute = Integer.parseInt(str.substring(str.indexOf(":") + 1, str.indexOf(":") + 3));

            Calendar calendar = new GregorianCalendar(year, month , dayOfMonth);

            this.dayOfWeek = DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK));
        } catch (NumberFormatException e) {
            throw new DatesToCronConvertException();
        }
    }

    @Override
    public String toString ( ) {
        return "TimeItem{" +
                "year=" + year +
                ", month=" + month +
                ", dayOfMonth=" + dayOfMonth +
                ", hour=" + hour +
                ", minute=" + minute +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
