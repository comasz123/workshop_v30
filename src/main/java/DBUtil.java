import java.sql.*;
import java.util.Arrays;

public class DBUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop?useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "coderslab";
    private static final String DB_PASSWORD = "Lama";


    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void insert(Connection conn, String query, String... params) {
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUsersTable(Connection conn, String query) {
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDBIfNotExist(Connection connection, String database) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(database)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createTableIfNotExist(Connection connection, String table) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(table)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User[] loadUsers(Connection conn, String query, String... columnNames) throws SQLException {
        User[] users = new User[0];

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                String[] array = new String[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    array[i] = resultSet.getString(columnNames[i]);
                }
                users = Arrays.copyOf(users, users.length + 1);
                users[users.length - 1] = new User(Integer.parseInt(array[0]), array[1], array[2], array[3]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void printUserName(Connection conn, String query, int id) {

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == true) {
                String name = resultSet.getString(1);
                System.out.println("Usunięto użytkownika:");
                System.out.println(name);
            } else {
                System.out.println("Użytkownik o podanym id nie istnieje");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int getRowIDValue(Connection conn, String query, int rowNumber) {
        int result = -1;

        try (PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, rowNumber - 1);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void editUser(Connection conn, String query, String newName, String newEmail, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(query.replace("newName", newName).replace("newEmail", newEmail))) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void remove(Connection conn, String query, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double returnAVG(Connection conn, String querry) {
        double result = 0;
        try (PreparedStatement statement =
                     conn.prepareStatement(querry)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getDouble(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static int returnNumberOfUsers(Connection conn, String query) {
        int result = -1;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static boolean doYouHaveSuchUser(Connection connect, String selectUserName, int id) {
        boolean result = false;

        try (PreparedStatement statement =
                     connect.prepareStatement(selectUserName)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public static String getPassword(Connection connect, String query, int id) {
        String result = null;

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getString(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
