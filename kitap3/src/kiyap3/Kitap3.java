package kiyap3;

public class Kitap3 {
    private String ogrencıAdı;
    private int öğrenciNo;
    private int ogrencıSınıfı;
    private Kitap3 next; // işaretçi

    public Kitap3(String ogrencıAdı, int öğrenciNo, int ogrencıSınıfı) {
        this.ogrencıAdı = ogrencıAdı;
        this.ogrencıSınıfı = ogrencıSınıfı;
        this.öğrenciNo = öğrenciNo;
        this.next = null;
    }

    public String getOgrencıAdı() {
        return ogrencıAdı;
    }

    public void setOgrencıAdı(String ogrencıAdı) {
        this.ogrencıAdı = ogrencıAdı;
    }

    public int getÖğrenciNo() {
        return öğrenciNo;
    }

    public void setÖğrenciNo(int öğrenciNo) {
        this.öğrenciNo = öğrenciNo;
    }

    public int getOgrencıSınıfı() {
        return ogrencıSınıfı;
    }

    public void setOgrencıSınıfı(int ogrencıSınıfı) {
        this.ogrencıSınıfı = ogrencıSınıfı;
    }

    public Kitap3 getNext() {
        return next;
    }

    public void setNext(Kitap3 next) {
        this.next = next;
    }

    public static class Okul {
        Kitap3 head = null; // Başa eklemede ilk head boştur

        public void başaEkleme(String ogrencıAdı, int öğrenciNo, int ogrencıSınıfı) {
            Kitap3 yenı = new Kitap3(ogrencıAdı, öğrenciNo, ogrencıSınıfı);
            
            yenı.next = head;
            head = yenı;
            
            System.out.println("Öğrenci adı: " + ogrencıAdı + ", Numarası: " + öğrenciNo + ", Sınıfı: " + ogrencıSınıfı + " başa eklenmiştir.");
        }

        // Araya eleman ekleme
        public void arayaElemanEkleme(String ogrencıAdı, int öğrenciNo, int ogrencıSınıfı, int pozisyon) {
            if (pozisyon == 0) {
                başaEkleme(ogrencıAdı, öğrenciNo, ogrencıSınıfı);
                return;
            }

            Kitap3 yenı = new Kitap3(ogrencıAdı, öğrenciNo, ogrencıSınıfı);
            Kitap3 current = head;

            // Pozisyondan bir önceki düğüme kadar git
            for (int i = 0; i < pozisyon - 1; i++) {
                if (current != null) {
                    current = current.next;
                } else {
                    break;
                }
            }

            if (current != null) {
                yenı.setNext(current.getNext());// yenı düğümü  içindeki ile değişitie artık biliyor
                current.setNext(yenı);// güncelleme yap
                System.out.println(pozisyon + ". pozisyona eklenmiştir.");
            } else {
                System.out.println("Hata: Belirtilen pozisyon listenin sınırlarını aşıyor!");
            }
        }

        public void bilgiYazdırma() {
            Kitap3 current = head;

            if (current == null) {
                System.out.println("Liste boş!");
                return;
            }
            while (current != null) {
                System.out.println("Ad: " + current.getOgrencıAdı() + " | Sınıf: " + current.getOgrencıSınıfı() + " | No: " + current.getÖğrenciNo());
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        Okul okul = new Okul();
        okul.başaEkleme("Ali", 101, 9);
        okul.başaEkleme("Ayşe", 102, 10);
        
        System.out.println("\n--- Liste ---");
        okul.bilgiYazdırma();
    }
}
