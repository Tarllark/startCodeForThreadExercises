package day2.webscraper;

public class Tester {
  
  public static void main(String[] args) throws InterruptedException {
    System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
    
    parallelThreads();
    Thread.sleep(5000);
    sequential();
    Thread.sleep(5000);
    sequentialThreads();
  }
  
  //Runtime tests
  public static void parallelThreads() throws InterruptedException{
      long start = System.nanoTime();
    TagCounter tc1 = new TagCounter("http://www.fck.dk");
    tc1.start();
    TagCounter tc2= new TagCounter("http://www.google.com");
    tc2.start();
    TagCounter tc3= new TagCounter("http://politiken.dk/");
    tc3.start();
    long end = System.nanoTime();
      System.out.println("Runtime for parallel: " + (end-start));
  }
  public static void sequentialThreads() throws InterruptedException{
      long start = System.nanoTime();
    TagCounter tc1 = new TagCounter("http://www.fck.dk");
    tc1.start();
    tc1.join();
    TagCounter tc2= new TagCounter("http://www.google.com");
    tc2.start();
    tc2.join();
    TagCounter tc3= new TagCounter("http://politiken.dk/");
    tc3.start();
    long end = System.nanoTime();
      System.out.println("Runtime for sequential threads: " + (end-start));
  }
  public static void sequential(){
      long start = System.nanoTime();
    TagCounter tc1 = new TagCounter("http://www.fck.dk");
    tc1.run();
    TagCounter tc2= new TagCounter("http://www.google.com");
    tc2.run();
    TagCounter tc3= new TagCounter("http://politiken.dk/");
    tc3.run();
    System.out.println("Title: "+tc1.getTitle());
    System.out.println("Div's: "+tc1.getDivCount());
    System.out.println("Body's: "+tc1.getBodyCount());
    System.out.println("Title: "+tc2.getTitle());
    System.out.println("Div's: "+tc2.getDivCount());
    System.out.println("Body's: "+tc2.getBodyCount());
    System.out.println("Title: "+tc3.getTitle());
    System.out.println("Div's: "+tc3.getDivCount());
    System.out.println("Body's: "+tc3.getBodyCount());
    long end = System.nanoTime();
      System.out.println("Runtime for sequential: " + (end-start));
  }
}
