import java.io.File;
import java.util.Scanner;

public class DosyaOku {
    String dosyadanOku(String dosyaAdi) {
        String satir = "";
        try {
            File dosyam = new File(dosyaAdi);
            Scanner scanner = new Scanner(dosyam);
            satir = scanner.nextLine().trim();
            scanner.close();

            System.out.println("boole.txt dosyası okundu.");

        } catch (Exception e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
        return satir;
    }
}
