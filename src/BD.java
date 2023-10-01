import java.sql.*;

public class BD {

    private String url = "jdbc:mysql://localhost:3306/jdbcdemo";
    private String username = "root";
    private String password = "";

    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement deleteAllStatement;
    private PreparedStatement selectStatement;

    public BD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            insertStatement = connection.prepareStatement("INSERT INTO testtext (name, description) VALUES (?, ?)");
            deleteStatement = connection.prepareStatement("DELETE FROM testtext WHERE name = ?");
            deleteAllStatement = connection.prepareStatement("DELETE FROM testtext");
            selectStatement = connection.prepareStatement("SELECT * FROM testtext");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addObj(String name, String description) {
        try {
            insertStatement.setString(1, name);
            insertStatement.setString(2, description);
            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("The entry was added successfully.");
            } else {
                System.out.println("Failed to add entry.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showDB() {
        try {
            ResultSet resultSet = selectStatement.executeQuery();
            int i = 1;
            while (resultSet.next()) {
                i++;
                System.out.println(i + ": " + resultSet.getString("name") + "\n" + resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteElement(String name) {
        try {
            deleteStatement.setString(1, name);
            int rowsAffected = deleteStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("The entry was successfully deleted.");
            } else {
                System.out.println("An entry with the specified name was not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllDB() {
        try {
            int rowsAffected = deleteAllStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("All entries were successfully deleted.");
            } else {
                System.out.println("The database is already empty.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BD database = new BD();

        database.addObj("Tidy The room", "Kitchen");
        database.addObj("Find a new job", "Job.eu");
        database.addObj("Meet with Michal", "Cafe L.");
        database.addObj("Call to MUM", "!!!");

        database.showDB();
        database.deleteElement("Tidy The room");
        database.deleteAllDB();
        database.showDB();

        database.closeConnection();
    }
}

