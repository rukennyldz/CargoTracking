package org.example.service;

import java.sql.*;
import org.example.service.RegisterService;  // import buraya gelmeli

public class LoginService {

    private static final String URL = "jdbc:postgresql://localhost:5432/kargotakip";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    // Kullanıcı girişini kontrol eden metod
    public static boolean login(String username, String password) {
        String sql = "SELECT user_id FROM users WHERE user_name = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, RegisterService.md5Hash(password));  // şifre hashleniyor

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // Eğer kullanıcı varsa true döner

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kullanıcının user_id'sini dönen metod (geçerliyse)
    public static int getUserIdIfValid(String username, String password) {
        String sql = "SELECT user_id FROM users WHERE user_name = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, RegisterService.md5Hash(password));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;  // kullanıcı bulunamazsa -1 döner
    }
}

