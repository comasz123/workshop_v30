import java.sql.Connection;

public class UserDao {
    public static final String INSERT_INTO_USERS =
            "INSERT INTO users(name, password, email) VALUE (?,?,?);";
    public static String SELECT_USERS = "SELECT * FROM users;";
    public static String COUNT_USERS = "SELECT COUNT(id) FROM users;";
    private static String DELETE_QUERY = "DELETE FROM users where id = ?;";
    private static final String EDIT_QUERY =
            "UPDATE users SET name = 'newName', email='newEmail' WHERE id=?;";
    public static String SELECT_USER_NAME = "SELECT name FROM users WHERE id = ?;";
    public static String SELECT_ID_FROM_ROW_NR = "SELECT id FROM users LIMIT ?,1;";
    public static String CREATE_DATABASE_IF_NOT_EXIST =
            "CREATE DATABASE IF NOT EXISTS workshop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;";
    public static String CREATE_TABLE_IF_NOT_EXISTS =
            "CREATE TABLE IF NOT EXISTS users (id int auto_increment UNIQUE, name VARCHAR(100), password VARCHAR(60), email VARCHAR(50) UNIQUE, PRIMARY KEY (id));";
    public static String RETRIEVE_PASSWORD = "SELECT password FROM users WHERE id = ?;";
    public static String DROP_TABLE = "DROP TABLE users;";

    public void delete_table(){
        try (Connection connect = DBUtil.connect()) {
            DBUtil.deleteUsersTable(connect, DROP_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void add(String name, String password, String email) {

        try (Connection connect = DBUtil.connect()) {
            DBUtil.insert(connect, INSERT_INTO_USERS, name, password, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void redoUsers(User[] users){
        try (Connection connect = DBUtil.connect()){
            DBUtil.createTableIfNotExist(connect, CREATE_TABLE_IF_NOT_EXISTS);
            for (User user:users) {
                DBUtil.insert(connect, INSERT_INTO_USERS, user.getName(), user.getPassword(), user.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void createDBIfNotExist() {
        try (Connection connect = DBUtil.connect()) {
            DBUtil.createDBIfNotExist(connect, CREATE_DATABASE_IF_NOT_EXIST);
            DBUtil.createTableIfNotExist(connect, CREATE_TABLE_IF_NOT_EXISTS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User[] findAll() {
        User[] users = new User[0];

        try (Connection connect = DBUtil.connect()) {
            users = DBUtil.loadUsers(connect, SELECT_USERS, "id", "name", "password", "email");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void displayName(int id) {
        try (Connection connect = DBUtil.connect()) {
            DBUtil.printUserName(connect, SELECT_USER_NAME, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connect = DBUtil.connect()) {
            DBUtil.remove(connect, DELETE_QUERY, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void countUsers() {

        try (Connection connect = DBUtil.connect()) {
            User.numberOfUSERS = DBUtil.returnNumberOfUsers(connect, COUNT_USERS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int lastIdValue() {
        int rowID = -1;
        try (Connection connect = DBUtil.connect()) {
            rowID = DBUtil.getRowIDValue(connect, SELECT_ID_FROM_ROW_NR, User.numberOfUSERS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowID;

    }

    public void edit(int id, String name, String email) {
        try (Connection connect = DBUtil.connect()) {
            DBUtil.editUser(connect, EDIT_QUERY, name, email, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean haveSuchUser(int id) {
        boolean result = false;
        try (Connection connect = DBUtil.connect()) {
            result = DBUtil.doYouHaveSuchUser(connect, SELECT_USER_NAME, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public String returnPassword (int id){
        String result = null;

        try (Connection connect = DBUtil.connect()) {
            result = DBUtil.getPassword(connect, RETRIEVE_PASSWORD, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
