package playlistt;

public class songNode {//SongNode adında dışarıdan erişilebilir bir sınıf başlatır. Her bir nesne bir şarkıyı ve bir sonraki şarkının adresini tutar.
    private String ŞarkıAdı;      
    private String sanatçı;    
    private double süre;  
    private songNode next;
    
    public songNode(String ŞarkıAdı, String sanatçı, double süre){
        this.sanatçı = sanatçı;
        this.ŞarkıAdı = ŞarkıAdı;
        this.süre = süre;
        this.next = null;//Listenin bir sonraki düğümünü (şarkısını) gösteren referanstır.
    }

    public String getŞarkıAdı() {
        return ŞarkıAdı;
    }

    public void setŞarkıAdı(String ŞarkıAdı) {
        this.ŞarkıAdı = ŞarkıAdı;
    }

    public String getSanatçı() {
        return sanatçı;
    }

    public void setSanatçı(String sanatçı) {
        this.sanatçı = sanatçı;
    }

    public double getSüre() {
        return süre;
    }

    public void setSüre(double süre) {
        this.süre = süre;
    }

    public songNode getNext() {
        return next;
    }

    public void setNext(songNode next) {
        this.next = next;
    }
    
    public static class playList{
        // play list adında ana yönetim sınıfımızı başlatır. Şarkı ekleme, silme, listeleme gibi tüm akıllı işlemler bu sınıfın içindeki metotlarla yürütülür.
        
        private songNode headSong;//: Listenin ilk şarkısını (başlangıç düğümünü) tutar. Liste boşsa null değerindedir.
        private songNode currentSong;//: O anda müzik çalarımızda çalmakta olan şarkıyı takip eder.
        private songNode currentNode;//Ekstra takip düğümü.
        
        public playList(){
            headSong = null;
            currentSong = null;
        }

        public songNode getHeadSong() {
            return headSong;
        }

        public void setHeadSong(songNode headSong) {
            this.headSong = headSong;
        }

        public songNode getCurrentSong() {
            return currentSong;
        }

        public void setCurrentSong(songNode currentSong) {
            this.currentSong = currentSong;
        }
        
        // listenin sonuna şarkı ekleme  
        public void addSong(String ŞarkıAdı, String sanatçı, double süre){
            songNode newSong = new songNode(ŞarkıAdı, sanatçı, süre);
            if(headSong == null){
                headSong = newSong;
            } else {
                //Eğer liste boş değilse, yeni şarkıyı eklemek için en son şarkıya (kuyruğa) kadar gitmemiz gerekir.
                songNode temp = headSong;//Listeyi kaybetmemek için başa (headSong) bakan geçici bir gezgin (temp) değişkeni oluşturuyoruz.
                while(temp.getNext() != null){//"Sonraki şarkı boş (null) olana kadar ilerle.
                    temp = temp.getNext();//: Döngü her döndüğünde gezginimizi bir sonraki şarkıya kaydırır.
                }
                temp.setNext(newSong);//Döngü bittiğinde temp artık listenin en sonundaki şarkıyı göstermektedir. Böylece zincirin en son halkası tamamlanmış oluyor!
            }
        }
        
        // şarkı Listesini ekrana yazdırma
        public void şarkıListesiniGöster(){//Listenin başından sonuna kadar tüm şarkıları sıra numarasıyla ekrana bastırır. Liste boşsa "Sarki listesi bos" uyarısı verir.
            if(headSong == null){
                System.out.println(" liste boş");
            } else {
                songNode temp = headSong;
                int i = 1;
                while(temp != null){
                    System.out.println(i + ". şarkı adı: " + temp.getŞarkıAdı() + " sanatçı adı: " + temp.getSanatçı() + " şarkı süresi: " + temp.süre);
                    temp = temp.getNext();
                    i++;
                }
            }
        }
        
        // belirli bir pozisyona şarkı ekleme
        public void şarkıEkleme(String ŞarkıAdı, String sanatçı, double süre, int pozisyon){
            songNode newSong = new songNode(ŞarkıAdı, sanatçı, süre);
            if(headSong == null || pozisyon <= 1){
                newSong.setNext(headSong);
                headSong = newSong;
            } else {
                songNode temp = headSong;
                int i = 1;
                while(i < pozisyon - 1 && temp.getNext() != null){// burda gideceğimiz elemandan bir öncekinden gidiyoruz
                    temp = temp.getNext();// gideceğimiz elemanı bulduk
                    i++;
                }
                if(temp.getNext() == null && i < pozisyon - 1){
                    System.out.println("pozisyon listenin dışındadır, şarkı sona eklendi.");
                }
                newSong.setNext(temp.getNext());
                temp.setNext(newSong);
            }
        }
        
        // şarkı silme
        public void şarkıSilme(String ŞarkıAdı){
            if(headSong == null){
                System.out.println("liste şuan boş");
            } else {
                songNode temp = headSong;
                int i = 1;//Şarkının listedeki sıra numarasını (kaçıncı sırada silindiğini) ekrana yazdırabilmek için bir sayaç (i) başlatıyoruz.
                if(temp.getŞarkıAdı().equalsIgnoreCase(ŞarkıAdı)){//Şu an üzerinde durduğumuz düğümün (head yani 1. şarkı) adı, aradığımız şarkı adı ile eşleşiyor mu diye bakar
                    headSong = temp.getNext();//: Eğer silinecek şarkı en baştaysa, listenin başını (headSong) bir sonraki şarkıya kaydırırız.
                    System.out.println("aradığınız şarkı listenin başındaydı ve silindi");
                    return;
                }
                while(temp.getNext() != null){//Son düğüme gelene kadar liste taranır.
                    if(temp.getNext().getŞarkıAdı().equalsIgnoreCase(ŞarkıAdı)){
                        //Silmek istediğimiz şarkı 2. vagonda (temp.getNext()). Biz 2. vagonu ortadan kaldırmak istiyoruz.
                        //O yüzden 1. vagona (temp) diyoruz ki: "Artık arkandaki 2. vagonu unut, direkt 2. vagonun arkasındakine (temp.getNext().getNext(), yani 3. vagona) bağlan!"
                        //1. vagon gidip 3. vagona tutunduğu an, ortadaki 2. vagonun bağlantısı havada kalır
                        temp.setNext(temp.getNext().getNext());
                        
                        System.out.println("Aradiginiz sarki listenin " + (i + 1) + ". sirasinda idi ve silindi");
                        return;
                    }
                    temp = temp.getNext();
                    i++;
                }
                System.out.println("aradığınız şarkı listede bulunamadı");
            }
        }
        
        // Şuandaki şarkıyı gösterme
        public void şuanÇalnŞarkı(){
            if(headSong == null){
                System.out.println("liste şuan boş");
                return;
            }
            if(currentSong == null){
                System.out.println("henüz şarkı çalınamadı. İlk şarkı çalınıyor");
                currentSong = headSong;
            }
            System.out.println("Su anda caliyor:");
            System.out.println("Sarki : " + currentSong.getŞarkıAdı());
            System.out.println("Sanatci : " + currentSong.getSanatçı());
            System.out.println("Sure: " + currentSong.getSüre());
        }
        
        //headSong listenin kapısıdır (hep ilk elemanda sabit kalır, listeyi kaybetmememizi sağlar)
        //temp geçici bir gezgindir (arama yaparken, silerken veya eklerken ortalıkta koşturur, işi bitince kaybolur)
        //Kullanıcının o an hangi şarkıda olduğunu, müzik çaların kumandasında ileri tuşuna (nextSong()) basınca hangi şarkıya geçmesi gerektiğini hafızada tutan hafıza kumandasıdır.
        
        // sonraki şarkıyı gösterme
        public void sonrakıSrakıGösterme() {
            if (headSong == null) {
                System.out.println("Liste bos, calinacak sarki yok.");
                return;
            }

            if (currentSong == null) {
                currentSong = headSong;
                System.out.println("Calma baslatildi : " + currentSong.getŞarkıAdı() + " - " + currentSong.getSanatçı());
                return;
            }

            if (currentSong.getNext() == null) {
                System.out.println("Listenin sonuna geldiniz. Basa donuluyor...");
                currentSong = headSong;
            } else {
                currentSong = currentSong.getNext();
            }

            System.out.println("Su anda caliyor: " + currentSong.getŞarkıAdı() + " - " + currentSong.getSanatçı());
        }
        
        // sanatçı arama
        public void findArtist(String artist) {
            boolean findFlag = false;
            
            if(headSong == null) {
                System.out.println("Liste bos. Sarkici bulunamaz");
            } else {
                songNode temp = headSong;
                int i = 1;
                while(temp != null) {
                    if(temp.getSanatçı().equalsIgnoreCase(artist)) {
                        System.out.println("Aranan sanatci listende " + i + ". sirada");
                        findFlag = true;
                    }
                    temp = temp.getNext();
                    i++;
                }
                
                if(!findFlag) {
                    System.out.println("Listede aradiginiz sanatci yok");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
