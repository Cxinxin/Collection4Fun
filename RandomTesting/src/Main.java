
public class Main {

    public static void main(String[] args) {
        WareHouse wareHouse=new WareHouse();
        new Thread(new Productor(wareHouse)).start();
        new Thread(new Consumer(wareHouse)).start();

        //https://my.oschina.net/githubhty/blog/874082


    }

}
