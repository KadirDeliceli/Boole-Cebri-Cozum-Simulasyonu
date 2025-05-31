import java.util.*;

abstract class Kapi {
    List<Object> girisler = new ArrayList<>();
    abstract int hesapla(Map<String, Integer> degiskenler);
}

class DegilKapi extends Kapi {
    String giris;

    public DegilKapi(String giris) {
        this.giris = giris;
    }

    @Override
    int hesapla(Map<String, Integer> degiskenler) {
        Object giris = girisler.get(0);
        int deger;
        if (giris instanceof String) {
            deger = degiskenler.get((String) giris);
        } else {
            deger = ((Kapi) giris).hesapla(degiskenler);
        }

        if (deger == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return giris;
    }
}

class VeKapi extends Kapi {
    public VeKapi(List<Object> girisler) {
        this.girisler = girisler;
    }

    @Override
    int hesapla(Map<String, Integer> degiskenler) {
        for (Object giris : girisler) {
            int deger;
            if (giris instanceof String) {
                deger = degiskenler.get((String) giris);
            } else {
                deger = ((Kapi) giris).hesapla(degiskenler);
            }

            if (deger == 0) {
                return 0;
            }
        }
        return 1;
    }
}

class VeyaKapi extends Kapi {
    public VeyaKapi(List<Object> girisler) {
        this.girisler = girisler;
    }

    @Override
    int hesapla(Map<String, Integer> degiskenler) {
        for (Object giris : girisler) {
            int deger;
            if (giris instanceof String) {
                deger = degiskenler.get((String) giris);
            } else {
                deger = ((Kapi) giris).hesapla(degiskenler);
            }

            if (deger == 1) {
                return 1;
            }
        }
        return 0;
    }
}
