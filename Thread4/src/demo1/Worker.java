package demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jack on 7/29/15.
 *
 * using multiple locks and synchronized code blocks
 */
public class Worker {

    private Random random =new Random();


    //creating two lock to solve the double time problems
    //seperate locks to avoid any possible confusions
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    // why not adding lock on the list itself because the java
    // might opimise the list
    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    //using synchronization to avoid ArrayIndexOutOfBoundsException
    // but using double times
    // as there is only one intrinsic lock you will need double time

    public void stageone(){

        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(random.nextInt(100));
        }
    }

    public  void stagetwo(){

        synchronized (lock2) {

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }
    }

    public void process(){


        for (int i = 0; i < 1000; i++) {

            stageone();
            stagetwo();
        }
    }




    public void main() {
        System.out.println("Starting----");

        long start = System.currentTimeMillis();

//        process();

        //using thread

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        // with get ception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: 109
        // It's not only one steps to list.add  process. not a atamotic process
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();

        System.out.println("time take  "+(end - start));

        System.out.println("List1:" + list1.size() +"List2:"+list2.size());

    }


}
