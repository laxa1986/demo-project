package com.alex4.demoproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

// SQL: Table meeting (st long, et long, userid integer)

@Getter
@AllArgsConstructor
public class Meeting {
    private final Date start;
    private final Date end;
    private final Integer userId;
}