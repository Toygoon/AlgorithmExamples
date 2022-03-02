package example;

public class Date implements Comparable<Date> {
    private final int month;
    private final int day;
    private final int year;

    /**
     * Initialize a new date from the month, day, and year.
     * @param month : month value, 1 to 12
     * @param day   : date of the day
     * @param year  : year
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /* Compare two dates chronologically. */
    @Override
    public int compareTo(Date o) {
        if (this.year < o.year || this.month < o.month || this.day < o.day)
            return -1;
        if (this.year > o.year || this.month > o.month || this.day > o.day)
            return 1;
        return 0;
    }
}
