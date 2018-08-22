package day3.futures;

import day3.futures.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tester {

  
  public static void main(String[] args) throws InterruptedException {
    //This represent the Queue in the exercise-figure. Observe the size of the Queue
    ArrayBlockingQueue<Integer> numbers = new ArrayBlockingQueue(5);
    
    ExecutorService es = Executors.newCachedThreadPool();
    //Create and start four producers (P1-P4 in the exercise-figure)
    /*es.execute(new RandomNumberProducer(numbers));
    es.execute(new RandomNumberProducer(numbers));
    es.execute(new RandomNumberProducer(numbers));
    es.execute(new RandomNumberProducer(numbers));
     */
    ArrayList<Future> futures = new ArrayList();
    for(int i = 0; i<5; i++)
    futures.add(es.submit(new RandomNumberProducer()));
    
    for(Future future : futures){
          try {
              System.out.println(future.get());
        } catch (ExecutionException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Create and start single consumer (C1 in the exercise-figure)
    /*RandomNumberConsumer consumer = new RandomNumberConsumer(numbers);
    es.execute(consumer);    
    */
    es.shutdown();
    es.awaitTermination(10, TimeUnit.SECONDS);
    /*
    System.out.println("Total of all random numbers: " + consumer.getSumTotal());
    System.out.println("Number of random numbers below 50: " + consumer.getBelow50().size());
    System.out.println("Number of random numbers >= 50: " + consumer.getAboveOr50().size());
  */
    }
}

/* answers to a)
    1) The advantage of using multithreading for creating numerous RNG is first of all the speed at which it can be done due to each thread being able to run "simultaneously". The second advantage when using pseudo-random generators that take their seed from a "random" place is the different seeds used by each generator resolving in a wider range of randomness.
    2) The amount of threads that should be linked to the amounts of cores on the PC, though this can be avoided through hyper threading allowing a higher amount of threads to be run by using idle staging of pipelines in the CPU
    3) Depends on the situation in which it is implemented. If I need all of the generated items I would use a put() to allow insertion when space is available, otherwise I would use offer() to either stop the process when the list is filled or otherwise continue.
    4) Again this is dependent on the use of the program. In general I would use poll() and assign x amount of time to finish the process, if the time limit is reached I would assume there to be either a bug, in worst case scenario a infinite loop somewhere.
*/