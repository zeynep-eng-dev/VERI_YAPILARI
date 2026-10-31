package playlist2;

public class carNode {
    private String brand; // marka
    private String model;
    private int horsePower; // beygir gücü
    private carNode next; // sonraki araba düğümü
    
    public carNode(String brand, String model, int horsePower) {
        this.brand = brand;
        this.horsePower = horsePower;
        this.model = model;
        this.next = null; // ilk oluşan düğümün nexti null'dur
    }

    public String getBrand() {
        return brand; }
    public void setBrand(String brand)
    { this.brand = brand; }

    public String getModel() { 
        return model; }
    public void setModel(String model) {
        this.model = model; }

    public int getHorsePower() {
        return horsePower; }
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower; }

    public carNode getNext() {
        return next; }
    public void setNext(carNode next) { this.next = next; }
    
    
    public static class CarList {
        private carNode head;
        private carNode current; // Şu an incelenen / seçili olan araba
        
        public CarList() {
            head = null;
            current = null;
        }

        public carNode getHead() { 
            return head; }
        public void setHead(carNode head) { 
            this.head = head; }

        public carNode getCurrent() {
            return current; }
        public void setCurrent(carNode current) {
            this.current = current; }
        
        // 1. BAŞA EKLEME
        public void başaEkleme(String brand, String model, int horsePower) {
            carNode newCar = new carNode(brand, model, horsePower);
            if (head == null) {
                head = newCar;
                System.out.println(brand + " " + model + " liste boştu, başa eklendi.");
            } else {
                newCar.setNext(head); // Yeni arabanın next'ini eski head'e bağlıyoruz
                head = newCar;        // Head'i yeni araba yapıyoruz
                System.out.println(brand + " " + model + " başa eklenmiştir.");
            } 
        }
          
        // 2. SONA EKLEME
        public void sonaEkleme(String brand, String model, int horsePower) {
            carNode newCar = new carNode(brand, model, horsePower);
            if (head == null) {
                head = newCar;
                System.out.println(brand + " " + model + " liste boş olduğu için başa eklenmiş oldu.");
            } else {
                carNode temp = head; 
                while (temp.getNext() != null) { // Listenin sonuna kadar ilerliyoruz
                    temp = temp.getNext();
                } 
                temp.setNext(newCar); // En sondaki düğümün next'ine yeni arabayı bağlıyoruz
                System.out.println(brand + " " + model + " listenin sonuna eklendi.");
            }
        }
          
        // 3. ARAYA / İNDEKSE GÖRE EKLEME
        public void arayaEkleme(String brand, String model, int horsePower, int pozisyon) {
            carNode newCar = new carNode(brand, model, horsePower);
           
            if (head == null || pozisyon <= 1) {
                newCar.setNext(head);
                head = newCar;
            } else {
                carNode temp = head;
                int i = 1;
                while (temp.getNext() != null && i < pozisyon - 1) {
                    temp = temp.getNext();
                    i++;
                }
                if (temp.getNext() == null && i < pozisyon - 1) {
                    System.out.println("Pozisyon listenin dışındaydı, araba sona eklendi.");
                }
                newCar.setNext(temp.getNext());
                temp.setNext(newCar);
            }
        }
           
        // 4. BAŞTAN ELEMAN SİLME
        public void baştanElemanSilme() {
            if (head == null) {
                System.out.println("Galeri zaten boş, silinecek araba yok.");
                return;
            } else {
                System.out.println("Baştaki araba (" + head.getBrand() + ") başarıyla silindi.");
                head = head.getNext(); // Head'i bir sonraki düğüme kaydırıyoruz
            }
        }
           
        // 5. SONDAN ELEMAN SİLME
        public void sondanSilme() {
            if (head == null) {
                System.out.println("Galeri boş, silinecek araba yok.");
                return;
            } 
            if (head.getNext() == null) { // Listede tek bir eleman varsa
                System.out.println("Listede tek araba vardı (" + head.getBrand() + ") silindi, galeri boşaldı.");
                head = null;
                return;
            }
            
            // Listede birden fazla eleman varsa, sondan bir önceki düğümü buluyoruz
            carNode temp = head;
            while (temp.getNext().getNext() != null) {
                temp = temp.getNext();
            }
            System.out.println("Sondaki araba silindi: " + temp.getNext().getBrand());
            temp.setNext(null); // Sondaki düğümün bağlantısını koparıyoruz
        }

        // 6. ARADAN / İNDEKSE GÖRE ELEMAN SİLME
        public void aradanElemanSilme(int pozisyon) {
            if (head == null) {
                System.out.println("Galeri boş, silinecek eleman yok.");
                return;
            } 
            if (pozisyon == 1) {
                System.out.println("1. sıradaki eleman (" + head.getBrand() + ") silinmiştir.");
                head = head.getNext();
                return;
            } 
            
            int i = 1;
            carNode temp = head;
            while (i < pozisyon - 1 && temp.getNext() != null) {
                temp = temp.getNext();
                i++;
            }
            
            if (temp.getNext() == null) {
                System.out.println("Hata: Belirtilen pozisyon listede yok!");
                return;  
            }
            
            System.out.println("Silinecek araba: " + temp.getNext().getBrand());
            temp.setNext(temp.getNext().getNext()); // Aradaki düğümü atlayarak siliyoruz
        }

        // 7. TÜM LİSTEYİ GÖSTERME
        public void showCars() {
            if (head == null) {
                System.out.println("Araba galerisi boş!\n");
                return;
            }
            carNode temp = head;
            int i = 1;
            while (temp != null) {
                System.out.println(i + ". Araba -> Marka: " + temp.getBrand() + 
                                   ", Model: " + temp.getModel() + 
                                   ", HP: " + temp.getHorsePower());
                temp = temp.getNext();
                i++;
            }
            System.out.println();
        }

        // 8. ŞU AN İNCELENEN ARABAYI GÖSTERME (currentInfo)
        public void currentCarInfo() {
            if (head == null) {
                System.out.println("Galeri boş.");
                return;
            }
            if (current == null) {
                current = head; // Eğer henüz seçim yapılmadıysa ilk arabayı seçer
                System.out.println("Henüz bir araba seçilmemişti. İlk araba gösteriliyor:");
            } else {
                System.out.println("Şu anda incelenen araba:");
            }
            System.out.println("-> " + current.getBrand() + " " + current.getModel() + " (" + current.getHorsePower() + " HP)");
        }

        // 9. SONRAKİ ARABAYA GEÇME (nextCar)
        public void nextCar() {
            if (head == null) {
                System.out.println("Galeri boş!");
                return;
            }
            if (current == null) {
                current = head;
            } else if (current.getNext() == null) {
                System.out.println("Galerinin sonuna geldiniz. Başa dönülüyor...");
                current = head; // Listenin sonuna gelindiyse başa döner
            } else {
                current = current.getNext(); // Sonraki düğüme geçer
            }
            System.out.println("-> Sonraki araca geçildi: " + current.getBrand() + " " + current.getModel());
        }

        // 10. MARKAYA GÖRE ARAMA VE YAZDIRMA
        public void findBrand(String targetBrand) {
            if (head == null) {
                System.out.println("Galeri boş, arama yapılamaz.");
                return;
            }
            carNode temp = head;
            int i = 1;
            boolean found = false;
            
            while (temp != null) {
                if (temp.getBrand().equalsIgnoreCase(targetBrand)) {
                    System.out.println("Aradığın marka (" + targetBrand + ") galeride " + i + ". sırada bulundu! Model: " + temp.getModel() + ", HP: " + temp.getHorsePower());
                    found = true;
                }
                temp = temp.getNext();
                i++;
            }
            
            if (!found) {
                System.out.println("Aradığın marka (" + targetBrand + ") galeride bulunamadı.");
            }
        }
    }
     

    // MAIN SINIFI (Test Alanı)

    public static void main(String[] args) {
        CarList galeri = new CarList();

        System.out.println("--- 1. ELEMAN EKLEME TESTİ ---");
        galeri.sonaEkleme("BMW", "M4", 510);
        galeri.sonaEkleme("Audi", "RS6", 600);
        galeri.başaEkleme("Porsche", "911 GT3", 502);
        
        System.out.println("\n--- GÜNCEL GALERİ ---");
        galeri.showCars(); // Sıralama: Porsche -> BMW -> Audi

        System.out.println("\n--- 2. MARKA ARAMA TESTİ ---");
        galeri.findBrand("Audi");
        galeri.findBrand("Tofaş"); // Olmayan marka testi

        System.out.println("\n--- 3. İNCELEME VE SONRAKİ ARACA GEÇME TESTİ ---");
        galeri.currentCarInfo(); // Porsche'yi gösterir
        galeri.nextCar();        // BMW'ye geçer
        galeri.nextCar();        // Audi'ye geçer
        galeri.nextCar();        // Başa (Porsche) geri döner

        System.out.println("\n--- 4. SİLME İŞLEMLERİ TESTİ ---");
        galeri.baştanElemanSilme(); // Porsche silinir
        galeri.showCars();
    }
}
