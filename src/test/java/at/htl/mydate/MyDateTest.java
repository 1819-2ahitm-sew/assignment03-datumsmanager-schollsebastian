package at.htl.mydate;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class MyDateTest {

    @Test
    void smokeTest() {
        MyDate date = new MyDate("29.9.2018");
        assertThat(date.getWeekday(), is(Weekday.SATURDAY));
    }

    @Test
    void constructWithStringOk() {
        MyDate date = new MyDate("29.9.2018");
        assertThat(date.getYear(),is(2018));
        assertThat(date.getMonth(),is(9));
        assertThat(date.getDay(),is(29));
        assertThat(date.getWeekday(),is(Weekday.SATURDAY));
    }

    @Test
    void constructWithStringFaildYear() {
        MyDate date = new MyDate("29.9.2018000");
        assertThat(date.getYear(),is(0));
        assertThat(date.getMonth(),is(0));
        assertThat(date.getDay(),is(0));
        assertThat(date.getWeekday(),is(nullValue()));
    }

    @Test
    void constructWithStringFaildMonth() {
        MyDate date = new MyDate("29.27.2018");
        assertThat(date.getYear(),is(0));
        assertThat(date.getMonth(),is(0));
        assertThat(date.getDay(),is(0));
        assertThat(date.getWeekday(),is(nullValue()));
    }

    @Test
    void constructWithStringFaildDay() {
        MyDate date = new MyDate("35.9.2018000");
        assertThat(date.getYear(),is(0));
        assertThat(date.getMonth(),is(0));
        assertThat(date.getDay(),is(0));
        assertThat(date.getWeekday(),is(nullValue()));
    }

    @Test
    void isYoungerThanYear() {
        MyDate younger = new MyDate("2018.1.1");
        MyDate older = new MyDate("2016.1.1");

        assertThat("Die Bestimmung des Jahres ist falsch, wenn das andere Datum älter ist",younger.isYoungerThan(older),is(true));
        assertThat("Die Bestimmung des Jahres ist falsch, wenn das andere Datum jünger ist",older.isYoungerThan(younger),is(false));
    }

    @Test
    void isYoungerThanMonth() {
        MyDate younger = new MyDate("2018.7.1");
        MyDate older = new MyDate("2018.2.1");

        assertThat("Die Bestimmung des Monats ist falsch, wenn das andere Datum älter ist",younger.isYoungerThan(older),is(true));
        assertThat("Die Bestimmung des Monats ist falsch, wenn das andere Datum jünger ist",older.isYoungerThan(younger),is(false));
    }

    @Test
    void isYoungerThanDay() {
        MyDate younger = new MyDate("2018.7.11");
        MyDate older = new MyDate("2018.7.1");

        assertThat("Die Bestimmung des Tages ist falsch, wenn das andere Datum älter ist",younger.isYoungerThan(older),is(true));
        assertThat("Die Bestimmung des Tages ist falsch, wenn das andere Datum jünger ist",older.isYoungerThan(younger),is(false));
    }

    @Test
    void isYoungerThanDayEqual() {
        MyDate first = new MyDate("2018.7.11");
        MyDate second = new MyDate("2018.7.11");

        assertThat("Die Bestimmung des Tages ist falsch, wenn das andere Datum gleich ist",first.isYoungerThan(second),is(false));
        assertThat("Die Bestimmung des Tages ist falsch, wenn das andere Datum gleich ist",second.isYoungerThan(first),is(false));
    }

    @Test
    void formatDate() {
        MyDate date = new MyDate("2018.9.29");
        assertThat(date.formatDate(), is("Samstag, 29. September 2018"));
    }
}