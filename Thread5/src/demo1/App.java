package demo1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by jack on 7/29/15.
 * Thread pools
 */

class Processor implements Runnable{

    private int id;

    public Processor(int id){

        this.id =id;
    }

    public void run() {

        System.out.println("Starting:"+id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Completed:"+id);

    }
}
public class App {

    public static void main(String[] args) {

        //using only 2 workers to cycling to do the 5 tasks avoid the
        //overheads(things like initilize and arrange space ) time for
        //creating a new thread
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //like having a number of workers . have several tasks .
        //when a worker finish a task then start a new task

        //executor service has his own thread tell the thread using
        //submit
        for (int i = 0; i < 5; i++) {
            executor.submit(new Processor(i));
        }

        //not immediatily until all the threads finish
        executor.shutdown();

        System.out.println("all task submitted!");

        try {
            executor.awaitTermination(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" all tasks finished!");

    }
}
