package kutuphane;

public class Kitap {
    // 1. Kitap Düğüm Sınıfı (Her bir kitap bir düğümdür)
    private String kitapAdı;
    private String yazar;
    private int sayfa_sayısı;
    private Kitap next;
    
    public Kitap(String kitapAdı, String yazar, int sayfa_sayısı){
        this.kitapAdı = kitapAdı;
        this.yazar = yazar;
        this.sayfa_sayısı = sayfa_sayısı;
        this.next = null;
    }

    public String getKitapAdı() { return kitapAdı; }
    public void setKitapAdı(String kitapAdı) { this.kitapAdı = kitapAdı; }
    public String getYazar() { return yazar; }
    public void setYazar(String yazar) { this.yazar = yazar; }
    public int getSayfa_sayısı() { return sayfa_sayısı; }
    public void setSayfa_sayısı(int sayfa_sayısı) { this.sayfa_sayısı = sayfa_sayısı; }
    public Kitap getNext() { return next; }
    public void setNext(Kitap next) { this.next = next; }
    
    // 2. Kütüphane Yönetim Sistemi
    public static class Kutuphane {
        private Kitap head;
        private Kitap current;
        
        public Kutuphane(){
            this.head = null;
            this.current = null;
        }

        // 1. Başa Ekleme Metodu
        public void başaEkleme(String kitapAdı, String yazar, int sayfa_sayısı){
            Kitap newKitap = new Kitap(kitapAdı, yazar, sayfa_sayısı);
            
            if(head == null){
                head = newKitap;
            } else {
                newKitap.setNext(head);
                head = newKitap;
            }
            System.out.println("-> Başa eklendi: " + kitapAdı);
        }

        // 2. Sona Ekleme Metodu
        public void sonaEklme(String kitapAdı, String yazar, int sayfa_sayısı){
            Kitap newKitap = new Kitap(kitapAdı, yazar, sayfa_sayısı);
            
            if(head == null){
                head = newKitap;
            } else {
                Kitap temp = head;
                while(temp.getNext() != null){
                    temp = temp.getNext();
                }
                temp.setNext(newKitap);
            }
            System.out.println("-> Sona eklendi: " + kitapAdı);
        }

        // 3. Araya Ekleme Metodu
        public void arayaEkleme(String kitapAdı, String yazar, int sayfa_sayısı, int pozisyon) {
            if (head == null || pozisyon <= 1) {
                başaEkleme(kitapAdı, yazar, sayfa_sayısı);
                return;
            }

            Kitap newKitap = new Kitap(kitapAdı, yazar, sayfa_sayısı);
            Kitap temp = head;
            int i = 1;

            while (i < pozisyon - 1 && temp.getNext() != null) {
                temp = temp.getNext();
                i++;
            }

            newKitap.setNext(temp.getNext());
            temp.setNext(newKitap);
            
            System.out.println("-> " + pozisyon + ". sıraya eklendi: " + kitapAdı);
        }

        // 4. Baştan Silme Metodu
        public void baştanSilme(){
            if(head == null){
                System.out.println("Kütüphane zaten boş, silinecek kitap yok!");
                return;
            }
            
            Kitap temp = head; // Head'i kaybetmemek için tutuyoruz
            head = head.getNext(); // Head'i bir sonrakine kaydırıyoruz
            System.out.println("<- Baştaki kitap silindi: " + temp.getKitapAdı());
        }

        // 5. Sondan Silme Metodu
        public void sondanSilme(){
            if(head == null){
                System.out.println("Kütüphane zaten boş, silinecek kitap yok!");
                return;
            }
            
            // Eğer listede tek bir kitap varsa
            if(head.getNext() == null){
                System.out.println("<- Listedeki tek kitap silindi: " + head.getKitapAdı());
                head = null;
                return;
            }

            // Birden fazla kitap varsa sondan bir önceki düğüme kadar git
            Kitap temp = head;
            while(temp.getNext().getNext() != null){
                temp = temp.getNext();
            }
            
            System.out.println("<- Sondaki kitap silindi: " + temp.getNext().getKitapAdı());
            temp.setNext(null); // Son düğümün bağlantısını kopar
        }

        // 6. Aradan / Pozisyondan Silme Metodu
        public void aradanSilme(int pozisyon) {
            if (head == null) {
                System.out.println("Kütüphane boş, silinecek kitap yok!");
                return;
            }

            if (pozisyon <= 1) {
                baştanSilme();
                return;
            }

            Kitap temp = head;
            int i = 1;

            while (i < pozisyon - 1 && temp.getNext() != null) {
                temp = temp.getNext();
                i++;
            }

            if (temp.getNext() == null) {
                System.out.println("Girilen pozisyon liste sınırlarının dışında!");
                return;
            }

            Kitap silinecek = temp.getNext();
            System.out.println("<- " + pozisyon + ". sıradaki kitap silindi: " + silinecek.getKitapAdı());
            temp.setNext(silinecek.getNext());
        }

        // 7. Kütüphaneyi Ekrana Yazdırma (Listeleme) Metodu
        public void kutuphaneyiGoster() {
            if (head == null) {
                System.out.println("\n[Kütüphane şu an BOŞ]\n");
                return;
            }

            Kitap temp = head;
            int i = 1;
            System.out.println("\n--- GÜNCEL KÜTÜPHANE RAFİ ---");
            while (temp != null) {
                System.out.println(i + ". Kitap: " + temp.getKitapAdı() + " | Yazar: " + temp.getYazar() + " | Sayfa: " + temp.getSayfa_sayısı());
                temp = temp.getNext();
                i++;
            }
           
        }
    }
    
    public static void main(String[] args) {
        // Kütüphane nesnesi oluşturuyoruz
        Kutuphane kutuphanem = new Kutuphane();

        // Test 1: Ekleme İşlemleri
        kutuphanem.sonaEklme("Suç ve Ceza", "Dostoyevski", 670);
        kutuphanem.sonaEklme("Sefiller", "Victor Hugo", 1724);
        kutuphanem.başaEkleme("1984", "George Orwell", 352);
        kutuphanem.arayaEkleme("Simyacı", "Paulo Coelho", 184, 2); // 2. sıraya ekle

        // Rafı göster
        kutuphanem.kutuphaneyiGoster();

        // Test 2: Silme İşlemleri
        kutuphanem.aradanSilme(2); // 2. sıradakini sil
        kutuphanem.baştanSilme();    // Baştakini sil
        kutuphanem.sondanSilme();    // Sondakini sil

        // Son hali göster
        kutuphanem.kutuphaneyiGoster();
    }
}
