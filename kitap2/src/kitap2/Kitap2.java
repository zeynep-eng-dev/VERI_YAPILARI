package kitap2;

public class Kitap2 {
    private String ogrencıAdı;
    private int öğrenciNo;
    private int ogrencıSınıfı;
    private Kitap2 next; // İşaretçi
    
    public Kitap2(String ogrencıAdı, int öğrenciNo, int ogrencıSınıfı) {
        this.ogrencıAdı = ogrencıAdı;
        this.ogrencıSınıfı = ogrencıSınıfı;
        this.öğrenciNo = öğrenciNo;
        this.next = null;
    }

    public String getOgrencıAdı() { return ogrencıAdı; }
    public void setOgrencıAdı(String ogrencıAdı) { this.ogrencıAdı = ogrencıAdı; }

    public int getÖğrenciNo() { return öğrenciNo; }
    public void setÖğrenciNo(int öğrenciNo) { this.öğrenciNo = öğrenciNo; }

    public int getOgrencıSınıfı() { return ogrencıSınıfı; }
    public void setOgrencıSınıfı(int ogrencıSınıfı) { this.ogrencıSınıfı = ogrencıSınıfı; }

    public Kitap2 getNext() { return next; }
    public void setNext(Kitap2 next) { this.next = next; }
     
    public static class Okul {
        Kitap2 head = null; // Liste henüz boş

        public void sonaEkleme(String ogrencıAdı, int öğrenciNo, int ogrencıSınıfı) {
            Kitap2 yenı = new Kitap2(ogrencıAdı, öğrenciNo, ogrencıSınıfı);
            
            // 1. Durum: Liste boşsa head doğrudan yeni düğüm olur
            if (head == null) {
                head = yenı;
                System.out.println("Liste boştu, ilk eleman olarak eklendi: " + ogrencıAdı);
                return;
            }
            
            // 2. Durum: Liste doluysa son düğümü bulmak için ilerle
            Kitap2 current = head;
            while (current.getNext() != null) {
                current = current.getNext(); // Sadece ilerliyoruz
            }
            
            // Döngü bittiğinde 'current' artık en son düğümdedir.
            current.setNext(yenı); // Yeni düğümü son elemanın arkasına bağlıyoruz
            System.out.println("Sona ekleme işlemi yapıldı: " + ogrencıAdı);
        }

        public void yazdırma() {
            Kitap2 current = head;
            
            if (head == null) {
                System.out.println("Liste boş!");
                return;
            }
            
            System.out.println("\n--- ÖĞRENCİ LİSTESİ ---");
            while (current != null) {
                System.out.println("Ad: " + current.getOgrencıAdı() + 
                                   " | No: " + current.getÖğrenciNo() + 
                                   " | Sınıf: " + current.getOgrencıSınıfı());
                current = current.getNext(); // Sonraki düğüme geç
            }
        }
    }

    public static void main(String[] args) {
        Okul yenilik = new Okul();
        
        yenilik.sonaEkleme("Meryem", 12, 5);
        yenilik.sonaEkleme("Zeynep", 101, 3);
        yenilik.sonaEkleme("Ahmet", 102, 4);
        
        yenilik.yazdırma();
    }
}