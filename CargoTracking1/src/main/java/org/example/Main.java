package org.example;

import org.example.service.LoginService;
import org.example.service.RegisterService;
import org.example.service.CargoService;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Uygulama başlarken bir kereye mahsus kullanıcıları topluca eklemek istersen:
        // Yalnızca 1 kere çağır, tekrar çalıştırmalarda yorum satırına al!
        // RegisterService.registerBatchUsers();

        System.out.println("1 - Register");
        System.out.println("2 - Login");
        System.out.print("Seçiminiz: ");
        int secim = scanner.nextInt();
        scanner.nextLine();  // dummy read

        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();

        System.out.print("Şifre: ");
        String password = scanner.nextLine();
        int userId = -1;
        boolean loginSuccess = false;
        if (secim == 1) {
            boolean success = RegisterService.registerUser(username, password);
            if (success) {
                System.out.println("✅ Kayıt başarılı.");
            } else {
                System.out.println("❌ Kayıt başarısız.");
            }
        } else if (secim == 2) {
            loginSuccess = LoginService.login(username, password);
            if (loginSuccess) {
                userId = LoginService.getUserIdIfValid(username, password);
                System.out.println("✅ Giriş başarılı.");

            } else {
                System.out.println("❌ Giriş başarısız.");
                return;
            }


            while (true) {
                System.out.println("\n--- Kargo Menüsü ---");
                System.out.println("1 - Kargoları Listele");
                System.out.println("2 - Yeni Kargo Ekle");
                System.out.println("3 - Kargo Sil");
                System.out.println("4 - Kargo Güncelle");
                System.out.println("5 - Çıkış");
                System.out.print("Seçiminiz: ");
                int sec = scanner.nextInt();
                scanner.nextLine(); // dummy read

                if (sec == 1) {
                    CargoService.listCargosByUserId(userId);
                } else if (sec == 2) {
                    System.out.print("Kargo Tipi: ");
                    String tip = scanner.nextLine();
                    System.out.print("Adres: ");
                    String adres = scanner.nextLine();
                    CargoService.createCargo(userId, tip, adres);
                } else if (sec == 3) {
                    System.out.print("Silinecek Kargo ID: ");
                    int kargoId = scanner.nextInt();
                    scanner.nextLine();
                    CargoService.deleteCargoById(kargoId, userId);
                } else if (sec == 4) {
                    System.out.print("Güncellenecek Kargo ID: ");
                    int kargoId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Yeni Kargo Tipi: ");
                    String yeniTip = scanner.nextLine();
                    System.out.print("Yeni Adres: ");
                    String yeniAdres = scanner.nextLine();
                    CargoService.updateCargo(kargoId, userId, yeniTip, yeniAdres);
                } else if (sec == 5) {
                    System.out.println("Çıkılıyor...");
                    break;
                } else {
                    System.out.println("Geçersiz seçim.");
                }
            }
        }

        scanner.close();
    }
}










