import java.io.IOException;

public class ExceptionTypeTest {

    public void doSomething() throws ArithmeticException{
        System.out.println("you should throw ArithmeticException,but seems is runtime excpetion,u may get away of this");
    }

    public void doThrowIOException() throws IOException{
        System.out.println("haha IOEcption, no way u can get away of it");
    }
    public static void main(){
        ExceptionTypeTest ett = new ExceptionTypeTest();
        ett.doSomething();
        try {
            ett.doThrowIOException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
