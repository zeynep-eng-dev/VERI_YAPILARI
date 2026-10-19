
package kitap;


public class Kitap {
    private String ogrencıAdı;
    private int öğrenciNo;
    private int ogrencıSınıfı;
    private Kitap next;// işarteçi;
    
    public Kitap(String ogrencıAdı,int öğrenciNo,int ogrencıSınıfı){
        this.ogrencıAdı=ogrencıAdı;
        this.ogrencıSınıfı=ogrencıSınıfı;
        this.öğrenciNo=öğrenciNo;
        this.next=null;
        
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

    public Kitap getNext() {
        return next;
    }

    public void setNext(Kitap next) {
        this.next = next;
    }
    
    public static class Okul{
        Kitap head=null; //  burda başa eklemede ilk head boştur
        public void başaEkleme(String ogrencıAdı,int öğrenciNo,int ogrencıSınıfı){
            Kitap yenı=new Kitap(ogrencıAdı,öğrenciNo,ogrencıSınıfı);
            
            yenı.next=head;
            head=yenı;
            
            System.out.println("öğrenci ad:"+ogrencıAdı+"öğrenci numarası:"+öğrenciNo+"öğrenci sınıfı"+ogrencıSınıfı+"gıbı özellikler başa eklenmiştir");
            
        }
        public void bilgiYazdırma(){
            Kitap current=head;
            
            if(current==null){
                System.out.println(" liste boş");
                return;
            }
            while(current!=null){
                System.out.println(current.getOgrencıAdı()+""+current.getOgrencıSınıfı()+""+current.getÖğrenciNo());
                current = current.next;
            }
        }
        
    }
    

 
    public static void main(String[] args) {
        Okul  yeni=new Okul();
        yeni.başaEkleme("zeynep", 12, 3);
        yeni.bilgiYazdırma();
      
    }
    
}
