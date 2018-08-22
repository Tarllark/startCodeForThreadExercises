package day3.futures;

import day3.futures.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RandomNumberConsumer implements Runnable {

  ArrayBlockingQueue<Integer> numbersProduced;

  public RandomNumberConsumer(ArrayBlockingQueue<Integer> numbersProduced) {
    this.numbersProduced = numbersProduced;
  }
  //Should eventually hold the sum of all random number consumed
  int sumTotal = 0;
  List<Integer> below50 = new ArrayList();
  List<Integer> aboveOr50 = new ArrayList();

  @Override
  public void run() {
    //In this exercise, we start four threads, each producing 100 numbers, so we know how much to consume
    while (!numbersProduced.isEmpty()) {
        try {
            //Todo: Fetch numbers from numbersProduced and do what you have to do
            int cur;
            if(((cur = numbersProduced.poll(1000, TimeUnit.MILLISECONDS)) < 50) ? below50.add(cur) : aboveOr50.add(cur));
        } catch (InterruptedException ex) {
            Logger.getLogger(RandomNumberConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }

  
  public int getSumTotal() {
    return sumTotal;
  }

  public List<Integer> getBelow50() {
    return below50;
  }

  public List<Integer> getAboveOr50() {
    return aboveOr50;
  }
  
}
