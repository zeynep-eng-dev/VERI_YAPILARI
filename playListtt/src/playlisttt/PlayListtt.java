package playlistt;

class SongNode {
    private String şarkıAdı;
    private String sanatçı;
    private double süre;
    private SongNode next;

    public SongNode(String şarkıAdı, String sanatçı, double süre) {
        this.şarkıAdı = şarkıAdı;
        this.sanatçı = sanatçı;
        this.süre = süre;
        this.next = null;
    }

    public String getŞarkıAdı() {
        return şarkıAdı; }
    public void setŞarkıAdı(String şarkıAdı) {
        this.şarkıAdı = şarkıAdı; }

    public String getSanatçı() {
        return sanatçı; }
    public void setSanatçı(String sanatçı) { 
        this.sanatçı = sanatçı; }

    public double getSüre() { 
        return süre; }
    public void setSüre(double süre) { 
        this.süre = süre; }

    public SongNode getNext() {
        return next; }
    public void setNext(SongNode next) {
        this.next = next; }
}

class PlayList {
    private SongNode headSong;
    private SongNode currentSong;

    public PlayList() {
        headSong = null;
        currentSong = null;
    }

    public SongNode getHeadSong() { return headSong; }
    public void setHeadSong(SongNode headSong) { this.headSong = headSong; }

    public SongNode getCurrentSong() { return currentSong; }
    public void setCurrentSong(SongNode currentSong) { this.currentSong = currentSong; }

    // Listenin sonuna şarkı ekleme
    public void addSong(String şarkıAdı, String sanatçı, double süre) {
        SongNode newSong = new SongNode(şarkıAdı, sanatçı, süre);
        if (headSong == null) {
            headSong = newSong;
        } else {
            SongNode temp = headSong;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newSong);
        }
    }

    // Şarkı Listesini ekrana yazdırma
    public void şarkıListesiniGöster() {
        if (headSong == null) {
            System.out.println("Liste boş.");
        } else {
            SongNode temp = headSong;
            int i = 1;
            while (temp != null) {
                System.out.println(i + ". Şarkı: " + temp.getŞarkıAdı() + " | Sanatçı: " + temp.getSanatçı() + " | Süre: " + temp.getSüre() + " dk");
                temp = temp.getNext();
                i++;
            }
        }
    }

    // Belirli bir pozisyona şarkı ekleme
    public void şarkıEkleme(String şarkıAdı, String sanatçı, double süre, int pozisyon) {
        SongNode newSong = new SongNode(şarkıAdı, sanatçı, süre);
        if (headSong == null || pozisyon <= 1) {
            newSong.setNext(headSong);
            headSong = newSong;
        } else {
            SongNode temp = headSong;
            int i = 1;
            while (i < pozisyon - 1 && temp.getNext() != null) {
                temp = temp.getNext();
                i++;
            }
            if (temp.getNext() == null && i < pozisyon - 1) {
                System.out.println("Pozisyon listenin dışındadır, şarkı sona eklendi.");
            }
            newSong.setNext(temp.getNext());
            temp.setNext(newSong);
        }
    }

    // Şarkı silme
    public void şarkıSilme(String şarkıAdı) {
        if (headSong == null) {
            System.out.println("Liste şu an boş.");
            return;
        }
        
        SongNode temp = headSong;
        int i = 1;

        if (temp.getŞarkıAdı().equalsIgnoreCase(şarkıAdı)) {
            headSong = temp.getNext();
            System.out.println("Aradığınız şarkı (" + şarkıAdı + ") listenin başındaydı ve silindi.");
            return;
        }

        while (temp.getNext() != null) {
            if (temp.getNext().getŞarkıAdı().equalsIgnoreCase(şarkıAdı)) {
                temp.setNext(temp.getNext().getNext());
                System.out.println("Aradığınız şarkı (" + şarkıAdı + ") listenin " + (i + 1) + ". sırasındaydı ve silindi.");
                return;
            }
            temp = temp.getNext();
            i++;
        }
        System.out.println("Aradığınız şarkı (" + şarkıAdı + ") listede bulunamadı.");
    }

    // Şu anki şarkıyı gösterme
    public void şuanÇalanŞarkı() {
        if (headSong == null) {
            System.out.println("Liste şu an boş.");
            return;
        }
        if (currentSong == null) {
            System.out.println("Henüz şarkı çalınmadı. İlk şarkıdan başlanıyor...");
            currentSong = headSong;
        }
        System.out.println("Şu anda çalıyor: " + currentSong.getŞarkıAdı() + " - " + currentSong.getSanatçı() + " (" + currentSong.getSüre() + " dk)");
    }

    // Sonraki şarkıya geçme
    public void sonrakiŞarkıGösterme() {
        if (headSong == null) {
            System.out.println("Liste boş, çalınacak şarkı yok.");
            return;
        }

        if (currentSong == null) {
            currentSong = headSong;
            System.out.println("Çalma başlatıldı: " + currentSong.getŞarkıAdı() + " - " + currentSong.getSanatçı());
            return;
        }

        if (currentSong.getNext() == null) {
            System.out.println("Listenin sonuna geldiniz. Başa dönülüyor...");
            currentSong = headSong;
        } else {
            currentSong = currentSong.getNext();
        }

        System.out.println("Şu anda çalıyor: " + currentSong.getŞarkıAdı() + " - " + currentSong.getSanatçı());
    }

    // Sanatçı arama
    public void findArtist(String artist) {
        boolean findFlag = false;

        if (headSong == null) {
            System.out.println("Liste boş. Şarkıcı bulunamaz.");
        } else {
            SongNode temp = headSong;
            int i = 1;
            while (temp != null) {
                if (temp.getSanatçı().equalsIgnoreCase(artist)) {
                    System.out.println("Aranan sanatçı (" + artist + ") listenin " + i + ". sırasında.");
                    findFlag = true;
                }
                temp = temp.getNext();
                i++;
            }

            if (!findFlag) {
                System.out.println("Listede aradığınız sanatçı (" + artist + ") bulunamadı.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PlayList listem = new PlayList();

        System.out.println("--- 1. ŞARKI EKLEME ---");
        listem.addSong("Bohemian Rhapsody", "Queen", 5.55);
        listem.addSong("Hotel California", "Eagles", 6.30);
        listem.addSong("Shape of You", "Ed Sheeran", 3.53);
        listem.şarkıListesiniGöster();

        System.out.println("\n--- 2. ARAYA ŞARKI EKLEME ---");
        listem.şarkıEkleme("Imagine", "John Lennon", 3.03, 2);
        listem.şarkıListesiniGöster();

        System.out.println("\n--- 3. MÜZİK ÇALAR TESTİ ---");
        listem.şuanÇalanŞarkı();
        listem.sonrakiŞarkıGösterme();

        System.out.println("\n--- 4. SANATÇI ARAMA ---");
        listem.findArtist("Queen");

        System.out.println("\n--- 5. ŞARKI SİLME ---");
        listem.şarkıSilme("Hotel California");
        System.out.println("\nGüncel Liste:");
        listem.şarkıListesiniGöster();
    }
}
