package com.spring.biz.common;

// JDBCUtil 클래스는 JDBC(Java Database Connectivity)를 사용하여 데이터베이스 연결과 관련된 유틸리티 클래스입니다.
// 이 클래스를 사용함으로써 데이터베이스 연결과 관련된 공통 로직을 중복코드 없이 처리할 수 있습니다.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

	private static final String driverName = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String userName = "NPNC";
	private static final String password = "1234";                           // 데이터베이스 비밀번호

    // 데이터베이스 연결을 수행하는 메서드
    public static Connection connect() {
        try {
            Class.forName(driverName); // MySQL 드라이버 클래스를 동적으로 로드
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        Connection conn = null;
        try {
            // 지정된 URL, 사용자 이름, 비밀번호로 데이터베이스와 연결
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }

    // 데이터베이스 연결 해제 메서드 (PreparedStatement, Connection을 매개변수로 받는 버전)
    public static void disconnect(PreparedStatement stmt, Connection conn) {
        try {
            stmt.close(); // PreparedStatement를 먼저 닫습니다.
            conn.close(); // Connection을 닫습니다.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 데이터베이스 연결 해제 메서드 (ResultSet, PreparedStatement, Connection을 매개변수로 받는 버전)
    public static void disconnect(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            rs.close();   // ResultSet을 먼저 닫습니다.
            stmt.close(); // PreparedStatement를 닫습니다.
            conn.close(); // Connection을 닫습니다.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
