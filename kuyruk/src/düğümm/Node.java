package düğümm; // Bu sınıfın "course4_queue2" paketine (klasörüne) ait olduğunu belirtir.

// 1. Düğüm (Node) Sınıfı: Bağlı listenin temel yapı taşını temsil eder.
class Dugum {
    int veri;          // Düğümün içerisinde saklanacak olan tamsayı değer (veri).
    Dugum sonraki;     // Listede bir sonraki düğümün hafızadaki adresini (referansını) tutan işaretçi.
}

// 2. DİNAMİK BAĞLANTILI LİSTEYE SAHİP DAİRESEL KUYRUK (Circular Queue)
class Kuyruk3 {
    Dugum front = null; // Kuyruğun başını (ilk çıkacak elemanı) tutan referans. Başlangıçta boştur.
    Dugum rear = null;  // Kuyruğun sonunu (en son eklenen elemanı) tutan referans. Başlangıçta boştur.
    Dugum curr = null;  // Listede gezinmek (traverse etmek) için kullanacağımız geçici gezgin düğüm.
    
    int BOYUT = 5;      // Kuyruğun alabileceği maksimum eleman sınırını belirler.
    int sayi = 0;       // Kuyrukta şu an anlık olarak kaç tane eleman olduğunu takip eder.
    
    // Kuyruktaki tüm elemanları ekrana yazdıran metot
    public void Print() {
        // Eğer kuyruk tamamen boşsa ekrana BOŞ yaz ve metottan çık
        if (front == null) {
            System.out.println("BOŞ");
            return;
        }
        
        curr = front; // Gezgini kuyruğun başına konumlandır
        
        // Dairesel yapıyı ekrana yazdırmak için do-while döngüsü kullanıyoruz
        do {
            System.out.printf("%4d", curr.veri); // Mevcut düğümün verisini formatlı şekilde yazdır
            curr = curr.sonraki;                  // Gezgini bir sonraki düğüme ilerlet
        } while (curr != front);                  // Gezgin tekrar başa (front) dönene kadar dönmeye devam et
        
        System.out.println();                     // Alt satıra geç
        System.out.println("------------");       // Görsel ayraç çizgi
    }
    
    // Kuyruğa yeni eleman ekleme (Enqueue) metodu
    public void Enqueue(int veri) {
        // Durum 1: Eğer kuyrukta hiç eleman yoksa (ilk eleman eklenecekse)
        if (front == null) {
            Dugum _yeni = new Dugum(); // Bellekte yeni bir düğüm nesnesi oluştur
            _yeni.veri = veri;         // Parametre olarak gelen veriyi bu düğüme ata
            
            front = _yeni;             // İlk eleman olduğu için front burayı gösterir
            rear = _yeni;              // İlk eleman aynı zamanda son elemandır, rear burayı gösterir
            rear.sonraki = front;      // Dairesel bağ kuralı gereği son eleman başı işaret eder (halka oluşur)
            
            sayi++;                    // Kuyruktaki eleman sayısını 1 artır
            Print();                   // Kuyruğun son halini ekrana yazdır
        } 
        else {
            // Durum 2: Kuyrukta zaten elemanlar var, kapasite doldu mu diye kontrol et
            if (sayi < BOYUT) {                          
                Dugum _yeni = new Dugum(); // Yeni düğüm oluştur
                _yeni.veri = veri;         // Veriyi içine yerleştir
                
                rear.sonraki = _yeni;      // Mevcut son elemanın sonrasına yeni düğümü bağla
                rear = _yeni;              // Artık yeni eklenen düğümümüz yeni "rear" (arka) oldu
                rear.sonraki = front;      // Dairesel yapının bozulmaması için yeni arka elemanı başa bağla
                sayi++;                    // Eleman sayısını 1 artır
                
                Print();                   // Kuyruğun güncel halini ekrana yazdır
            } 
            else {
                // Eğer eleman sayısı BOYUT'a ulaştıysa kuyruk doludur
                System.out.println("DOLU");
            }
        }
    }
    
    // Kuyruktan eleman çıkarma (Dequeue) metodu
    public void Dequeue() {
        // Eğer kuyruk zaten boşsa çıkarılacak eleman yoktur
        if (front == null) {
            System.out.println("BOŞ");
        } 
        else {    
            // Eğer kuyrukta birden fazla eleman varsa
            if (sayi > 1) {
                front = front.sonraki; // Baştaki elemanı bir sonraki elemana kaydır (eski baş düşer)
                rear.sonraki = front;  // Son düğümün işaretçisini güncel başa (front) sabitle
                sayi--;                // Eleman sayısını 1 azalt
                Print();               // Güncel kuyruğu ekrana yazdır
            } 
            else {
                // Eğer kuyrukta sadece 1 eleman kaldıysa ve o da çıkarılıyorsa kuyruk tamamen sıfırlanır
                front = null;
                rear = null;
                sayi = 0;
                System.out.println("BOŞ");
            }
        }
    }
}

// 3. ANA SINIF (Programın çalıştırıldığı main metodu)
public class Ana {
    public static void main(String[] args) {
        Kuyruk3 q3 = new Kuyruk3(); // Kuyruk3 sınıfından q3 adında bir nesne türet
        
        q3.Enqueue(60); // Kuyruğa 60 ekle
        q3.Enqueue(40); // Kuyruğa 40 ekle
        q3.Enqueue(50); // Kuyruğa 50 ekle
        q3.Enqueue(30); // Kuyruğa 30 ekle
        q3.Enqueue(100); // Kuyruğa 100 ekle (Kuyruk şu an tamamen doldu, boyut = 5)
        q3.Enqueue(25); // Kapasite dolu olduğu için ekrana "DOLU" yazacaktır
        
        q3.Dequeue(); // Kuyruğun başından bir eleman çıkar (60 çıkar)
        q3.Dequeue(); // Kuyruğun başından bir eleman çıkar (40 çıkar)
        q3.Dequeue(); // Kuyruğun başından bir eleman çıkar (50 çıkar)
        
        q3.Enqueue(500); // Boşalan yerlere yeni eleman olarak 500 ekle
        
        q3.Dequeue(); // Baştan bir eleman daha çıkar
        q3.Dequeue(); // Baştan bir eleman daha çıkar
        q3.Dequeue(); // Baştan bir eleman daha çıkar
        
        q3.Enqueue(600); // Tekrar kuyruğa 600 ekle
    }
}
