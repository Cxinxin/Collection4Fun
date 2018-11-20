
public class Productor implements Runnable{

    //生产者

    private WareHouse warehouse = null;


    public Productor(WareHouse wareHouse) {
        this.warehouse=wareHouse;
    }

    @Override
    public void run() {
        warehouse.pushProduct();
    }
}
