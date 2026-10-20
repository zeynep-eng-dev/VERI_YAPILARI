package kitap5;

public class Kitap5 {
    private String ogrencıAdı;
    private int ogrencıNo;
    private int ogrencıSınıfı;
    private Kitap5 next;

    public Kitap5(String ogrencıAdı, int ogrencıNo, int ogrencıSınıfı) {
        this.ogrencıAdı = ogrencıAdı;
        this.ogrencıNo = ogrencıNo;
        this.ogrencıSınıfı = ogrencıSınıfı;
        this.next = null;
    }

    public String getOgrencıAdı() {
        return ogrencıAdı;
    }

    public void setOgrencıAdı(String ogrencıAdı) {
        this.ogrencıAdı = ogrencıAdı;
    }

    public int getOgrencıNo() {
        return ogrencıNo;
    }

    public void setOgrencıNo(int ogrencıNo) {
        this.ogrencıNo = ogrencıNo;
    }

    public int getOgrencıSınıfı() {
        return ogrencıSınıfı;
    }

    public void setOgrencıSınıfı(int ogrencıSınıfı) {
        this.ogrencıSınıfı = ogrencıSınıfı;
    }

    public Kitap5 getNext() {
        return next;
    }

    public void setNext(Kitap5 next) {
        this.next = next;
    }
    
    public static class OkulSistemı {
        Kitap5 head = null;

        // Başa eleman ekleme (Test edebilmen için ekledim)
        public void başaEkle(String ogrencıAdı, int ogrencıNo, int ogrencıSınıfı) {
            Kitap5 yeni = new Kitap5(ogrencıAdı, ogrencıNo, ogrencıSınıfı);
            yeni.next = head;
            head = yeni;
        }

        // Baştan silme
        public void baştanSilme() {
            if (head == null) {
                System.out.println("Liste boş, silinecek eleman yok!");
                return;
            }
            System.out.println(head.getOgrencıAdı() + " adlı üye baştan silinmiştir.");
            head = head.next; // Head'i bir sonraki düğüme kaydırıyoruz
        }

        // Aradan silme
        public void aradanSilme(int pozisyon) {
            if (head == null) {
                System.out.println("Liste zaten boş!");
                return;
            }

            if (pozisyon == 0) {
                baştanSilme();
                return;
            }

            Kitap5 current = head;
            for (int i = 0; i < pozisyon - 1; i++) {
                if (current != null) {
                    current = current.next;
                } else {
                    break;
                }
            }

            if (current == null || current.next == null) {
                System.out.println("Hata: Belirtilen pozisyon listenin sınırlarını aşıyor!");
                return;
            }

            System.out.println(current.next.getOgrencıAdı() + " adlı üye silindi.");
            current.next = current.next.next; // Aradan atlama (bağlantıyı koparma)
        }

        // Sondan çıkarma
        public void sondanÇıkarma() {
            if (head == null) {
                System.out.println("Liste zaten boş!");
                return;
            }

            // Eğer listede tek eleman varsa
            if (head.next == null) {
                System.out.println(head.getOgrencıAdı() + " silindi, liste boşaldı.");
                head = null;
                return;
            }

            Kitap5 current = head;
            // Sondan bir önceki düğümü bulana kadar ilerle
            while (current.next.next != null) {
                current = current.next;
            }

            System.out.println(current.next.getOgrencıAdı() + " adlı üye sondan silindi.");
            current.next = null; // Son elemanı kopar
        }

        // Listeyi yazdırma (Tüm listeyi gezer)
        public void yazdırma() {
            Kitap5 current = head;
            if (current == null) {
                System.out.println("Liste boş!");
                return;
            }
            System.out.print("Liste: ");
            while (current != null) {
                System.out.print(current.getOgrencıAdı() + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        OkulSistemı okul = new OkulSistemı();
        okul.başaEkle("Ahmet", 1, 9);
        okul.başaEkle("Mehmet", 2, 10);
        okul.başaEkle("Ayşe", 3, 11);

        okul.yazdırma(); // Ayşe -> Mehmet -> Ahmet -> null

        okul.baştanSilme(); // Ayşe silinir
        okul.yazdırma();
    }
}
