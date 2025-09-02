package org.example.service;

import java.sql.*;

public class CargoService {

    private static final String URL = "jdbc:postgresql://localhost:5432/kargotakip";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1234"; // PostgreSQL şifren

    public static void listCargosByUserId(int userId) {
        String sql = "SELECT * FROM kargo WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n--- Kargolar ---");
            boolean hasResult = false;

            while (rs.next()) {
                hasResult = true;
                int kargoId = rs.getInt("kargo_id");
                String tipi = rs.getString("kargo_tipi");
                String adres = rs.getString("adres");

                System.out.println("Kargo ID: " + kargoId);
                System.out.println("Tipi    : " + tipi);
                System.out.println("Adres   : " + adres);
                System.out.println("------------------");
            }

            if (!hasResult) {
                System.out.println("Bu kullanıcıya ait kargo bulunamadı.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public static void createCargo(int userId, String tipi, String adres) {
    String sql = "INSERT INTO kargo (user_id, kargo_tipi, adres) VALUES (?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, userId);
        stmt.setString(2, tipi);
        stmt.setString(3, adres);

        int affectedRows = stmt.executeUpdate();

        if (affectedRows > 0) {
            System.out.println("Kargo başarıyla eklendi.");
        } else {
            System.out.println("Kargo eklenemedi.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public static void deleteCargoById(int kargoId, int userId) {
        String sql = "DELETE FROM kargo WHERE kargo_id = ? AND user_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kargoId);
            stmt.setInt(2, userId);

            int affected = stmt.executeUpdate();

            if (affected > 0) {
                System.out.println("Kargo silindi.");
            } else {
                System.out.println("Kargo bulunamadı veya size ait değil.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateCargo(int kargoId, int userId, String yeniTip, String yeniAdres) {
        String sql = "UPDATE kargo SET kargo_tipi = ?, adres = ? WHERE kargo_id = ? AND user_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, yeniTip);
            stmt.setString(2, yeniAdres);
            stmt.setInt(3, kargoId);
            stmt.setInt(4, userId);

            int affected = stmt.executeUpdate();

            if (affected > 0) {
                System.out.println("Kargo güncellendi.");
            } else {
                System.out.println("Kargo bulunamadı veya size ait değil.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

