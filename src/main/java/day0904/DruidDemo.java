package day0904;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import day0904.mybatis.po.User;
import day0904.mybatis.po.UserLog;
import day0905.IUserDau;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DruidDemo implements IUserDau {

    private static DataSource dataSource;

    private DruidDemo(){
    }

    private static final class DruidDemoHolder {
        private static final DruidDemo DRUID_DEMO = new DruidDemo();
    }

    public static DruidDemo getDruidDemo() {
        return DruidDemoHolder.DRUID_DEMO;
    }


    static {
        Properties prop = new Properties();
        //Connection connection = null;
        try (InputStream inputStream = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties")){
            prop.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(prop);
            //从池子中获得连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "select id, password, role from user where email = ?";
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(query)) {
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

    @Override
    public User getUserById(int id) {
        String query = "select email, password, role from user where id = ?";
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(query)) {
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

    @Override
    public List<User> getAllUser() {
        String query = "select id, email, password, role from user";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(query)) {
            readGetUserResult(userList, ppstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int getUserCount() {
        String query = "select count(*) from user";
        int count = 0;
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(query)) {
            ResultSet rs = ppstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<User> getAPageUser(int page) {
        String query = "select id, email, password, role from user limit ?, 10";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(query)) {
            ppstmt.setInt(1, page);
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

    @Override
    public void addUser(User user) {
        String insertSql = "insert into user(email, password) values(?, ?);";
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(insertSql)) {
            ppstmt.setString(1, user.getEmail());
            ppstmt.setString(2, user.getPassword());
            ppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUserLog(UserLog userLog) {
        String insertSql = "insert into `user-log`(`user-id`, time, behavior) values(?, ?, ?);";
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(insertSql)) {
            ppstmt.setInt(1, userLog.getUserId());
            ppstmt.setString(2, userLog.getTime());
            ppstmt.setString(3, userLog.getBehavior());
            ppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String insertSql = "delete from user where id = ?;";
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(insertSql)) {
            ppstmt.setInt(1, id);
            ppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(int id, String email, String password) {
        String insertSql = "update user set email = ? , password = ? where id = ?;";
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(insertSql)) {
            ppstmt.setString(1, email);
            ppstmt.setString(2, password);
            ppstmt.setInt(3, id);
            ppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsersByFussyEmailSearch(String email, int page) {
        List<User> userList = new ArrayList<>();
        String insertSql = "select id, email, password, role from user where email like ? limit ?, 10;";
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(insertSql)) {
            ppstmt.setString(1, "%" + email + "%");
            ppstmt.setInt(2, page);
            ResultSet rs = ppstmt.executeQuery();
            while (rs.next()) {
                String id = "u" + rs.getInt("id");
                email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                userList.add(new User(id, email, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int getUserCountsByFussyEmailSearch(String email) {
        int count = 0;
        String insertSql = "select count(*) from user where email like ?;";
        try (PreparedStatement ppstmt = dataSource.getConnection().prepareStatement(insertSql)) {
            ppstmt.setString(1, "%" + email + "%");
            ResultSet rs = ppstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
