package com.geekbrains.server.authorization;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;

public class AuthServiceImpl implements AuthService {
    private final Map<String, UserData> users;
    private static Connection connection;
    private static Statement stmt;

    public AuthServiceImpl() {
        users = new HashMap<>();
    }

    @Override
    public void start() {
        try {
            connect();
            // загружаем зарегистрированных пользователей из БД
            try (ResultSet rs = stmt.executeQuery("select * from chat_users order by login_str")) {
                while (rs.next()) {
                    users.put(rs.getString("login_str"),
                            new UserData(rs.getString("login_str"),
                                    rs.getString("password_str"),
                                    rs.getString("nick_name")));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Сервис аутентификации инициализирован");
    }

    @Override
    public synchronized String getNickNameByLoginAndPassword(String login, String password) {
        UserData user = users.get(login);
        // Ищем пользователя по логину и паролю, если нашли то возвращаем никнэйм
        if (user != null && user.getPassword().equals(password)) {
            return user.getNickName();
        }
        return null;
    }

    @Override
    public void end() {
        try {
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Сервис аутентификации отключен");
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
        stmt = connection.createStatement();
    }

    private static void disconnect() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
