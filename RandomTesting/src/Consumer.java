public class Consumer implements Runnable {



    private WareHouse warehouse = null;


    public Consumer(WareHouse wareHouse) {
        this.warehouse=wareHouse;
    }
    @Override
    public void run() {
        warehouse.popProduct();
    }
}
