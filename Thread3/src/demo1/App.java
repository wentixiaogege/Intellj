package demo1;

/**
 * Created by jack on 7/29/15.
 * using synchronized
 */
public class App {

    private int count =0;


    //using synchronized
    //every objects in java has a monitor lock   or an intrinsic(固有的) lock
    //like a mutex
    //the lock is app object
    public synchronized void increment(){

        count++;
    }


    public static void main(String[] args) {

        App app = new App();

        app.dowork();

    }

    public void dowork() {
        Thread t1 = new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i < 10000 ; i++) {
                    increment();

                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000 ; i++) {
                    increment();

                }
            }
        });

        t1.start();
        t2.start();

        // this answer is not right because in the main thread the other thread do
//        System.out.println("count is "+count);

        try {

            // this will work but not always be 20000   ,does not work why
            // is  count++ is like a atomic operation
            // but count ++ is  count =count +1  using 3 steps;
            // some increments has been skept

             t1.join();

             t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("count is "+count);


    }
}
