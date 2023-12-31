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
    private static final class JDBCConnectionHolder {
        private static final JDBCConnection JDBC_CONNECTION = new JDBCConnection();
    }

    public static JDBCConnection getJDBCConnection() {
        return JDBCConnectionHolder.JDBC_CONNECTION;
    }

    private static final class ConnectionHolder {
        private static final Connection CONNECTION = JDBCConnection
                .getJDBCConnection().getInitConnection();
    }

    public static Connection getConnection() {
        return ConnectionHolder.CONNECTION;
    }

    private JDBCConnection() {
    }

    private Connection getInitConnection() {
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "0521");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public User getUserByEmail(Connection connection, String email) {
        String query = "select id, password, role from user where email = ?";
        try (PreparedStatement ppstmt = connection.prepareStatement(query)) {
            ppstmt.setString(1, email);
            ResultSet rs = ppstmt.executeQuery();
            if (rs.next()) {
                String id = "u" + rs.getInt("id");
                String password = rs.getString("password");
                String role = rs.getString("role");
                return new User(id, email, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User getUserById(Connection connection, int id) {
        String query = "select email, password, role from user where id = ?";
        try (PreparedStatement ppstmt = connection.prepareStatement(query)) {
            ppstmt.setInt(1, id);
            ResultSet rs = ppstmt.executeQuery();
            if (rs.next()) {
                String sId = "u" + id;
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                return new User(sId, email, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUser(Connection connection) {
        String query = "select id, email, password, role from user";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement ppstmt = connection.prepareStatement(query)) {
            readGetUserResult(userList, ppstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public int getUserCount(Connection connection) {
        String query = "select count(*) from user";
        int count = 0;
        try (PreparedStatement ppstmt = connection.prepareStatement(query)) {
            ResultSet rs = ppstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<User> getAPageUser(Connection connection, int page) {
        int beginIndex = (page - 1) * 10;
        String query = "select id, email, password, role from user limit ?, 10";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement ppstmt = connection.prepareStatement(query)) {
            ppstmt.setInt(1, beginIndex);
            readGetUserResult(userList, ppstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private void readGetUserResult(List<User> userList, PreparedStatement ppstmt) throws SQLException {
        ResultSet rs = ppstmt.executeQuery();
        while (rs.next()) {
            String id = "u" + rs.getInt("id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            userList.add(new User(id, email, password, role));
        }
    }

    public void addUser(Connection connection, User user) {
        String insertSql = "insert into user(email, password) values(?, ?);";
        try (PreparedStatement ppstmt = connection.prepareStatement(insertSql)) {
            ppstmt.setString(1, user.getEmail());
            ppstmt.setString(2, user.getPassword());
            ppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserLog(Connection connection, UserLog userLog) {
        String insertSql = "insert into `user-log`(`user-id`, time, behavior) values(?, ?, ?);";
        try (PreparedStatement ppstmt = connection.prepareStatement(insertSql)) {
            ppstmt.setInt(1, userLog.getUserId());
            ppstmt.setString(2, userLog.getTime());
            ppstmt.setString(3, userLog.getBehavior());
            ppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Connection connection, int id) {
        String insertSql = "delete from user where id = ?;";
        try (PreparedStatement ppstmt = connection.prepareStatement(insertSql)) {
            ppstmt.setInt(1, id);
            ppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Connection connection, int id, String email, String password) {
        String insertSql = "update user set email = ? , password = ? where id = ?;";
        try (PreparedStatement ppstmt = connection.prepareStatement(insertSql)) {
            ppstmt.setString(1, email);
            ppstmt.setString(2, password);
            ppstmt.setInt(3, id);
            ppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
