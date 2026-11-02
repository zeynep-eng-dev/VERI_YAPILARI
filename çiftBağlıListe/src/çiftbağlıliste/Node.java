package çiftbağlıliste;

public class Node {
    int data;     // düğümün içindeki sayı
    Node next;    // sağdaki düğümü gösterir
    Node prev;    // soldaki düğümü gösterir

    public static class LinkedLis {
        Node head = null;    // listenin ilk düğümü
        Node _new = null;    // eklenecek yeni düğüm
        Node curr = null;    // listenin son düğümü
        Node temp = null;    // gezmek için yardımcı işaretçi 1
        Node temp2 = null;   // gezmek için yardımcı işaretçi 2

        // ---------- LİSTEYİ YAZDIR ----------
        public void yazdir() {
            temp = head;                         // baştan başla
            while (temp != null) {               // liste bitene kadar
                System.out.println(temp.data);   // sayıyı yaz
                temp = temp.next;                // sağdaki düğüme geç
            }

            System.out.println("<->");           // ileri ve geri yazdırmayı ayıran çizgi

            temp = curr;                         // bu sefer sondan başla
            while (temp != null) {
                System.out.println(temp.data);   // sayıyı yaz
                temp = temp.prev;                // soldaki düğüme geç
            }

            System.out.println("*********");     // yazdırma bitti işareti
        }

        // ---------- BAŞTAN SİL ----------
        public void bastanSil() {
            temp = head.next;    // ikinci düğümü sakla
            head = temp;         // head artık ikinci düğümü gösteriyor
            head.prev = null;    // yeni başın öncesinde kimse yok
        }

        // ---------- SONDAN SİL ----------
        public void sondanSil() {
            temp = head;                    // baştan başla
            while (temp.next != curr) {     // sondan bir önceki düğüme kadar git
                temp = temp.next;
            }
            curr = temp;                    // son artık sondan bir önceki düğüm
            curr.next = null;               // yeni sonun sağında kimse yok
        }

        // ---------- BELİRLİ SIRADAKİNİ SİL ----------
        public void aradakiSil(int index) {
            int count = 0;                  // sayaç
            temp2 = head;                   // silinecek düğümü bulmak için baştan başla

            while (count < index) {         // istenen sıraya gelene kadar
                temp = temp2;               // temp, temp2'nin bir gerisinde
                temp2 = temp2.next;         // temp2 bir ileri
                count++;                    // sayacı artır
            }
            // temp2 = silinecek düğüm, temp = ondan bir önceki

            temp.next = temp2.next;         // soldaki, ortadakini atlayıp sağdakine bağlanır
            temp2.next.prev = temp;         // sağdaki, ortadakini atlayıp soldakine bağlanır
        }

        // ---------- BAŞA EKLE ----------
        public void basaEkleme(int data) {
            _new = new Node();              // yeni düğümü oluştur
            _new.data = data;               // içine sayıyı koy

            if (head != null) {             // liste doluysa
                _new.next = head;           // yeni düğümün sağı eski baş olur
                head.prev = _new;           // eski başın solu yeni düğüm olur
            } else {                        // liste boşsa
                curr = _new;                // tek düğüm hem baş hem son olur
            }
            head = _new;                    // head artık yeni düğüm
        }

        // ---------- SONA EKLE ----------
        public void sonaEkleme(int data) {
            _new = new Node();              // yeni düğümü oluştur
            _new.data = data;               // içine sayıyı koy
            _new.next = null;               // sona eklendiği için sağında kimse yok

            if (head == null) {             // liste boşsa
                head = _new;                // yeni düğüm hem baş
                curr = _new;                // hem son olur
            } else {                        // liste doluysa
                curr.next = _new;           // eski son, yeniyi göstersin
                _new.prev = curr;           // yeni, eski sonu göstersin
                curr = _new;                // curr artık yeni son
            }
        }

        // ---------- BELİRLİ SIRAYA EKLE ----------
        public void belirliEkle(int data, int index) {
            int count = 0;                  // sayaç
            _new = new Node();              // yeni düğümü oluştur
            _new.data = data;               // içine sayıyı koy

            temp = head;                    // baştan başla
            while (count < index - 1) {     // ekleyeceğin yerin bir öncesine git
                temp = temp.next;
                count++;
            }

            _new.next = temp.next;          // 1) yeni → sağdaki
            _new.next.prev = _new;          // 2) sağdaki → yeni (geri ok)
            temp.next = _new;               // 3) soldaki → yeni
            _new.prev = temp;               // 4) yeni → soldaki (geri ok)
        }
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        LinkedLis liste = new LinkedLis();   // boş liste oluştur

        liste.basaEkleme(25);        // 25
        liste.sonaEkleme(10);        // 25 10
        liste.sonaEkleme(20);        // 25 10 20
        liste.sonaEkleme(5);         // 25 10 20 5
        liste.yazdir();

        liste.belirliEkle(40, 3);    // 25 10 20 40 5
        liste.yazdir();

        liste.sondanSil();           // 25 10 20 40
        liste.yazdir();

        liste.bastanSil();           // 10 20 40
        liste.yazdir();

        liste.aradakiSil(1);         // 10 40
        liste.yazdir();
    }
}
