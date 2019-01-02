package com.alex4.demoproject.model;

import java.util.Date;

// SQL: Table meeting (st long, et long, userid integer)
public class Meeting {
    private final Date start;
    private final Date end;
    private final Integer userId;

    public Meeting(Date start, Date end, Integer userId) {
        this.start = start;
        this.end = end;
        this.userId = userId;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Integer getUserId() {
        return userId;
    }
}
