import java.util.*;

public class BooleDevre {
    void booleFonk() throws Exception {
        DosyaOku dosyaOku = new DosyaOku();
        String satir = dosyaOku.dosyadanOku("src//boole.txt");

        String fonksiyon = satir.split("=")[1].trim();
        String[] terimler = fonksiyon.split("\\+");

        Set<String> degiller = new HashSet<>();
        for (String terim : terimler) {
            for (int i = 0; i < terim.length() - 1; i++) {
                if (terim.charAt(i + 1) == '\'') {
                    degiller.add(String.valueOf(terim.charAt(i)));
                }
            }
        }

        int veKapiSayisi = 0;
        for (String terim : terimler) {
            int harfAdedi = 0;
            for (int i = 0; i < terim.length(); i++) {
                char c = terim.charAt(i);
                if (Character.isLetter(c)) {
                    harfAdedi++;
                }
            }
            if (harfAdedi > 1) {
                veKapiSayisi++;
            }
        }

        int seviye = 1;
        if (degiller.size() > 0 && veKapiSayisi > 0) {
            seviye = 3;
        } else if (degiller.size() > 0 || veKapiSayisi > 0) {
            seviye = 2;
        }

        System.out.println("Devre " + seviye + " seviyelidir.");

        Map<String, DegilKapi> degilKapiMap = new HashMap<>();
        int sayac = 1;
        if (degiller.size() > 0) {
            System.out.println("1. Seviye İçin:");
            System.out.println("    Kapı türü: DEĞİL, " + degiller.size() + " tane kapı var");
            for (String d : degiller) {
                System.out.println("    " + sayac++ + ". Kapının girişi: " + d);
                DegilKapi dk = new DegilKapi(d);
                dk.girisler.add(d);
                degilKapiMap.put(d, dk);
            }
        }

        List<Object> veyaGirisleri = new ArrayList<>();
        List<VeKapi> veKapiListesi = new ArrayList<>();
        sayac = 1;
        if (veKapiSayisi > 0) {
            if (seviye == 3) {
                System.out.println("2. Seviye İçin:");
            } else if (seviye == 2) {
                System.out.println("1. Seviye İçin:");
            }
            System.out.println("    Kapı türü: VE, " + veKapiSayisi + " tane kapı var");
        }

        for (String terim : terimler) {
            List<Object> girisler = new ArrayList<>();
            for (int i = 0; i < terim.length(); i++) {
                char c = terim.charAt(i);
                if (c == '\'') continue;
                if (i + 1 < terim.length() && terim.charAt(i + 1) == '\'') {
                    girisler.add(degilKapiMap.get(String.valueOf(c)));
                    i++;
                } else if (Character.isLetter(c)) {
                    girisler.add(String.valueOf(c));
                }
            }

            if (girisler.size() == 1) {
                veyaGirisleri.add(girisler.get(0));
            } else {
                VeKapi vk = new VeKapi(girisler);
                veyaGirisleri.add(vk);
                veKapiListesi.add(vk);
                if (veKapiSayisi > 0) {
                    System.out.print("    " + sayac + ". Kapı " + girisler.size() + "-girişli ve girişleri: ");
                    for (Object g : girisler) {
                        if (g instanceof String)
                            System.out.print(g + ", ");
                        else
                            System.out.print((new ArrayList<>(degilKapiMap.values()).indexOf(g) + 1) + ". DEĞİL kapısı, ");
                    }
                    System.out.println();
                }
                sayac++;
            }
        }

        if (veyaGirisleri.size() > 1) {
            if (seviye == 3) {
                System.out.println("3. Seviye İçin:");
            } else if (seviye == 2) {
                System.out.println("2. Seviye İçin:");
            } else {
                System.out.println("1. Seviye İçin:");
            }
            System.out.println("    Kapı türü: VEYA, 1 tane kapı var");
            System.out.print("    1. Kapı " + veyaGirisleri.size() + "-girişli ve girişleri: ");
            for (Object g : veyaGirisleri) {
                if (g instanceof String) {
                    System.out.print(g + ", ");
                } else {
                    int index = veKapiListesi.indexOf(g) + 1;
                    System.out.print(index + ". VE kapısı, ");
                }
            }
            System.out.println();
        }

        Set<String> degiskenler = new HashSet<>();
        for (String terim : terimler) {
            for (char c : terim.toCharArray()) {
                if (Character.isLetter(c)) {
                    degiskenler.add(String.valueOf(c));
                }
            }
        }

        Scanner input = new Scanner(System.in);
        Map<String, Integer> girisDegerleri = new HashMap<>();
        for (String d : degiskenler) {
            System.out.println(d + " değişkenin değerini giriniz:");
            girisDegerleri.put(d, input.nextInt());
        }

        VeyaKapi cikis = new VeyaKapi(veyaGirisleri);
        System.out.println("Devrenin Sonucu: " + cikis.hesapla(girisDegerleri));
    }
}
