/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day2.deadlock;

import java.lang.management.*;

/**
 *
 * @author Tarllark
 */
public class DeadlockDetection extends Thread{
    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
  public void run() {
    while (true) {
      long[] threadIds = bean.findDeadlockedThreads();
      if(threadIds != null){
          ThreadInfo[] threadInfos = bean.getThreadInfo(threadIds);
          for(ThreadInfo thread : threadInfos)
              System.out.println(thread.toString().trim());
      }
    }
  }

}
