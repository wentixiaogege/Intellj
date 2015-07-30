package demo3;

/**
 * Created by jack on 7/29/15.
 */
public class App {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //simulate
                for (int i = 0; i < 10; i++) {

                    System.out.println("Hello "+i);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
    }
}