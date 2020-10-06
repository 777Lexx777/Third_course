package Lesson_2_MySQL.Lesson_2_ChatMySQL;

//import Lesson_2_MySQL.Box;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Set;

public class AuthenticationService {
//    private Set<Client> clients = Set.of(
//            new Client(1, "l1", "l1","l1"),
//            new Client(2, "q1", "q1","q1"),
//            new Client(3, "w1", "w1","w1")
//    );
//
    public AuthenticationService() { }
//
//        public Client findByLoginAndPassword(String login, String password) {
//        for (Client c : clients) {
//            if (c.getLogin().equals(login) && c.getPassword().equals(password)) {
//                return c;
//            }
//        }
//        return null;
//    }
    public Client findByLoginAndPassword(String login, String password) {

        Connection connection = DBConnectionFactory.getConnection();
        try { PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM client WHERE login = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) { return new Client(
                            (int) rs.getLong("id"),
                            rs.getString("nameUser"),
                            rs.getString("login"),
                            rs.getString("password")
                );
            }
            System.out.println(rs);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("SWW", e);
        } finally {
            closeConnection(connection);
        }
    }
    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public class Client {
        private final int id;
        private final String nameUser;
        private final String password;
        private final String login;

        public Client(int id, String nameUser,String password , String login ) {
            this.id = id;
            this.nameUser = nameUser;
            this.password = password;
            this.login = login;
        }
        public int getId() {
            return id;
        }
        public String getNameUser() { return nameUser; }
        public String getPassword() { return password; }
        public String getLogin() { return login; }
//
//    public int getNumber() { return number; }
//    public int getAuthentication() {return authentication;}
//      public String getName() { return name; }
//    public int getAge() {return age;}
//    public int getWeight() {return weight;}

        @Override
        public String toString() {
            return "Box{" + "id=" + id + ", nameUser='" + nameUser + '\'' +
                    ", password='" + password + '\'' + ", login='" + login + '\'' + '}';
        }

    }
}
