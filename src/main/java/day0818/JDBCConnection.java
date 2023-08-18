package day0818;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    CREATE TABLE `user` (
        `id` int(11) NOT NULL AUTO_INCREMENT,
        `name` varchar(255) DEFAULT NULL,
        `balance` int(11) DEFAULT NULL,
        PRIMARY KEY (`id`)
    );
 */
public class JDBCConnection {
    public Connection getConnection() {
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "0521");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected to database");
        return conn;
    }

    public User getUser(Connection connection, String email) {
        String query = "select email, password, role from user where email = ?";
        try (PreparedStatement ppstmt = connection.prepareStatement(query)) {
            ppstmt.setString(1, email);
            ResultSet rs = ppstmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                String role = rs.getString("role");
                return new User(email, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUser(Connection connection){
        String query = "select email, password, role from user";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement ppstmt = connection.prepareStatement(query)) {
            ResultSet rs = ppstmt.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                userList.add(new User(email, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void add(Connection connection, User user) {
        String insertSql = "insert into user(email, password) values(?, ?);";
        try (PreparedStatement ppstmt = connection.prepareStatement(insertSql)) {
            ppstmt.setString(1, user.getEmail());
            ppstmt.setString(2, user.getPassword());
            ppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBCConnection jdbcTest = new JDBCConnection();
        Connection connection = jdbcTest.getConnection();
/*        jdbcTest.add(connection);
        jdbcTest.testPreparedStatement(connection);*/
    }
}
