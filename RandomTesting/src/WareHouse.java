import java.util.LinkedList;

public class WareHouse {

    public LinkedList<Product> wareHouse=new LinkedList <Product>();

    public synchronized void pushProduct(){
        for(int i=0;;i++){
            push(new Product(i));
        }
    }

    public synchronized void popProduct(){
        for(int j=0;;j++){
            pop();
        }
    }


    private void push(Product product){
        if(wareHouse.size()==10){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            wareHouse.addFirst(product);
        System.out.println(product.toString()+" 被放入仓库");
        notify();

    }


    private void pop(){
        if(wareHouse.size()==0){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Product product=wareHouse.removeFirst();
        System.out.println(product.toString()+" 被取出来了");
        notify();
    }
}
