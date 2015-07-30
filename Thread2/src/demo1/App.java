package demo1;

import java.util.Scanner;

/**
 * Created by jack on 7/29/15.
 *
 * thread synchronization  dealing with threads cashing problems
 */
class Processor extends Thread{

//    private boolean running = true;
    private volatile boolean running = true;

    //used for preventing threads cashing variables when ther're not changed
    public void run(){
        while(running){

            System.out.println("hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void shutdown(){
        running = false;
    }

}


public class App {

    public static void main(String[] args) {

        Processor procs1 = new Processor();

        procs1.start();

        System.out.println("press sth to shutdown");
        Scanner scanner = new Scanner(System.in);


        scanner.hasNextLine();

        procs1.shutdown();

    }
}
