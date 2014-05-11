package com.springapp.mvc;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by Александр on 28.04.14.
 */
public class DatabaseWorker {
    private static Connection connection = null;

    private static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/Egorchev";
                return connection = DriverManager.getConnection(url, "postgres", "postgres");
            } catch (Exception e) {
                System.out.println("Database connection error");
                e.printStackTrace();
                return null;
            }
        } else
            return connection;
    }


    public static int isValid(String login, String password) {

        try {
            String sql = "SELECT password, id FROM users WHERE login=?;";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                if (set.getString(1).equals(password)) {
                    return set.getInt(2);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static AboutUser getInfo(int id) {
        AboutUser aboutUser = new AboutUser();

        try {
            String sql = "SELECT * FROM users WHERE id=?;";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                aboutUser.id = set.getInt(1);
                aboutUser.username = set.getString(2);
                aboutUser.password = set.getString(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aboutUser;
    }

    public static MyLinkedList getUzas() {
        try {
            MyLinkedList uzas = new MyLinkedList();
            String sql = "SELECT * FROM users;";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                AboutUser user = new AboutUser();
                user.setId(set.getInt(1));
                user.setUsername(set.getString(2));
                user.setPassword(set.getString(3));
                user.setImageName(set.getString(4));
                uzas.add(user);
            }
            return uzas;
        } catch (SQLException e) {
            e.printStackTrace();
            return new MyLinkedList();
        }
    }

    public static void registrated(AboutUser user) {
        Connection conc = getConnection();
        String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
        String sql1 = "INSERT INTO about_user (login) VALUES (?)";
        try {
            PreparedStatement preparedStatement = conc.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = conc.prepareStatement(sql1);
            preparedStatement1.setString(1, user.getUsername());
            preparedStatement1.executeUpdate();
            System.out.println("added user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void AddImageOnDataBase(String name, String login) {
        Connection conc = getConnection();
        String sql = "UPDATE users SET avatar ='" + name + "' WHERE login='" + login + "'";
        //  System.out.println(sql);
        try {
            PreparedStatement preparedStatement = conc.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("added image path");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static String GettingImagePage(String login) {
        Connection conc = getConnection();
        try {
            PreparedStatement preparedStatement = conc.prepareStatement("SELECT avatar FROM users WHERE login='" + login + "'");
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            return set.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return "empty.jpg";
        }
    }

    public static LinkedList<StructureMessage> getMessagesFromDB(String from, String to) {
        Connection conc = getConnection();
        LinkedList<StructureMessage> list = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = conc.prepareStatement("SELECT fromlogin,date,message FROM messages " +
                    "WHERE fromlogin IN ('" + from + "', '" + to + "') AND tologin IN ('" + from + "', '" + to + "') ORDER BY date DESC");
//            System.out.println("SELECT fromlogin,date,message FROM messages " +
//                    "WHERE fromlogin IN ('" + from + "', '" + to + "') AND tologin IN ('" + from + "', '" + to + "')");
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                StructureMessage message = new StructureMessage();
                message.setFrom(set.getString(1));
                message.setDate(set.getString(2));
                message.setMessage(set.getString(3));
                list.add(message);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void AddMessageToDB(String from, String to, String message) {
        Connection conc = getConnection();
        String sql = "INSERT INTO messages (fromlogin, tologin, message) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conc.prepareStatement(sql);
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            preparedStatement.setString(3, message);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static InfoUser getInfoAboutUser(String login){
        Connection conc = getConnection();
        try {
            PreparedStatement preparedStatement = conc.prepareStatement("SELECT * FROM about_user where login ='"+login+"'");
//            System.out.println("SELECT * FROM about_user where login ='"+login+"'");

            ResultSet set = preparedStatement.executeQuery();
            InfoUser infoUser = new InfoUser();
            set.next();
            infoUser.setLogin(login);
            infoUser.setFirstname(set.getString("firstname"));
            infoUser.setLastname(set.getString("lastname"));
            infoUser.setBdate(set.getString("bdate"));
            infoUser.setHobby(set.getString("hobby"));
            return infoUser;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void SetInfoAboutUser (String login, String firstname, String lastname, String bdate, String hobby){
        Connection conc = getConnection();
        try {
            PreparedStatement preparedStatement = conc.prepareStatement("UPDATE about_user SET " +
                    "firstname='"+firstname+"', lastname='"+lastname+"', bdate='" +bdate +"', " +
                    "hobby='"+hobby+"' where login='"+login+"'");
            preparedStatement.executeUpdate();
            System.out.println("UPDATE about_user SET " +
                    "firstname='"+firstname+"', lastname='"+lastname+"', bdate='" +bdate +"', " +
                    "hobby='"+hobby+"' where login='"+login+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getInfoAboutUser("test");
    }


}
