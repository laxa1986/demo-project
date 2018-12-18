package com.alex4.demoproject;

import java.sql.*;
import java.util.Date;

public class Calendar {
    public static void main(String[] args) throws Exception {
//        createTable();
//        bookTheMeeting();
        selectAll();
    }

    private static void createTable() throws SQLException {
        var con = getConnection();

        Statement ddlStatement = con.createStatement();
        ddlStatement.executeUpdate("CREATE TABLE meeting ( " +
                "    st TIMESTAMP, " +
                "    en TIMESTAMP, " +
                "    userid NUMBER " +
                ")");
    }

    private static void bookTheMeeting() throws SQLException {
        var meeting = new Meeting(new Date(0), new Date(1), 1);
        tryBook(meeting);
    }

    private static void selectAll() throws SQLException {
        try (var conn = getConnection()) {
            var st = conn.prepareStatement(MEETINGS_QUERY);
            try (var rs = st.executeQuery()) {
                rs.next();
                Timestamp ts = rs.getTimestamp(2);
                var date = rs.getDate(2);
                var time = rs.getTime(2);
                System.out.println("ts: " + ts + "\ndate: " + " " + date + "\ntime: " + time);
            }
        }
    }

    private static final String MEETINGS_QUERY = "" +
            "SELECT * FROM meeting";

    private static final String CONFLICTS_QUERY = "" +
            "SELECT count(*) " +
            "FROM meeting " +
            "WHERE ? < meeting.st AND meeting.st < ? OR ? < meeting.en AND meeting.en < ? OR ? = meeting.st AND meeting.en = ?";

    private static final String BOOK_QUERY = "" +
            "INSERT INTO meeting (st, en, userid) VALUES (?, ?, ?)";

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        var sid = "ORCLCDB";
        var user = "sys as SYSDBA";
        var password = "Oradoc_db1";
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:"+sid, user, password);
    }

    public static boolean tryBook(Meeting meeting) throws SQLException {
        var start = new Timestamp(meeting.getStart().getTime());
        var end = new Timestamp(meeting.getEnd().getTime());

        try (var connection = getConnection()) {
            // this is to ensure that once non conflict identified, the conflict can't appear till the end of transaction
//            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            // this is to ensure both statements select and insert will be done as part of one transaction
            connection.setAutoCommit(false);

            var st1 = connection.prepareStatement(CONFLICTS_QUERY);
            st1.setTimestamp(1, start);
            st1.setTimestamp(2, end);
            st1.setTimestamp(3, start);
            st1.setTimestamp(4, end);
            st1.setTimestamp(5, start);
            st1.setTimestamp(6, end);

            try (ResultSet rs = st1.executeQuery()) {
                rs.next();
                int conflictCount = rs.getInt(1);
                if (conflictCount > 0) {
                    return false;
                }
            }

            var st2 = connection.prepareStatement(BOOK_QUERY);
            st2.setTimestamp(1, start);
            st2.setTimestamp(2, end);
            st2.setInt(3, meeting.getUserId());
            st2.executeUpdate();

            try {
                connection.commit();
            } catch (SQLException e) {
                // analyse exception, if it is due to race condition (another user booked faster)
                // then it is kind of expected exception rather then runtime error, handle it as cannot book
                return false;
            }
        }

        return true;
    }
}

// SQL: Table meeting (st long, et long, userid integer)

class Meeting {
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