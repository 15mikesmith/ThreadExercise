import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 15mik_000 on 11/20/2017.
 */

public class ThreadedPi {


    public static void main(String[] args) throws InterruptedException {

        
        //Argument 1 is num threads and Argument2 is the amount of iterations

        long threads = Long.parseLong(args[0]);
        long iterations = Long.parseLong(args[1]);

        AtomicInteger inCircle = new AtomicInteger();
        AtomicInteger overall = new AtomicInteger();


        Random r = new Random();


        Thread[] arr = new Thread[(int)threads];


        long startTime = System.nanoTime();
        for (int i = 0; i < threads ; i++) {
            arr[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < (iterations/threads); i++) {
                        double x = ThreadLocalRandom.current().nextDouble(0,1);
                        double y = ThreadLocalRandom.current().nextDouble(0,1);

                        if(Math.pow(x,2) + Math.pow(y,2) < 1){
                            inCircle.addAndGet(1);
                        }
                        overall.incrementAndGet();
                        //Calculation, increment count value
                    }
                }

            });
            arr[i].start();
        }



        for (int j = 0; j < arr.length; j++) {
            arr[j].join();
        }
        long endTime = System.nanoTime();

        System.out.println();
        System.out.println("Total = "+ overall.doubleValue());
        System.out.println("Inside = "+ inCircle.doubleValue());
        System.out.println("Ratio = "+ inCircle.doubleValue()/overall.doubleValue());
        System.out.println("Pi = "+ inCircle.doubleValue()/overall.doubleValue() * 4);

        //Time
        long duration = (endTime - startTime);
        double seconds = (double)duration / 1000000000.0;
        System.out.println();
        System.out.println("Time was: "+ seconds+" seconds");



    }

}
