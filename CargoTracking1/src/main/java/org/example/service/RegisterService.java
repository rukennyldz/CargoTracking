package org.example.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterService {

    // Veritabanı bilgileri
    private static final String URL = "jdbc:postgresql://localhost:5432/kargotakip";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    // Kullanıcıları toplu ekleme
    public static void registerBatchUsers() {
        String[][] users = {
                {"ayse_kaya", "123456"},
                {"admin", "1234"},
                {"john_doe", "pass123"},
                {"jane_smith", "abc123"},
                {"murat_aydin", "test456"},
                {"veli_demir", "qwerty"},
                {"mehmet_can", "can123"},
                {"zeynep_gul", "123456"},
                {"ali_veli", "veliali"},
                {"test_user", "testtest"},
        };

        String sql = "INSERT INTO users (user_name, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (String[] user : users) {
                stmt.setString(1, user[0]);
                stmt.setString(2, md5Hash(user[1]));
                stmt.addBatch();
            }

            stmt.executeBatch();
            System.out.println("Toplu kullanıcı ekleme başarılı!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tek bir kullanıcıyı kaydetme
    public static boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (user_name, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, md5Hash(password));
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // MD5 hash işlemi
    public static String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashedBytes = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algoritması bulunamadı!", e);
        }
    }
}



