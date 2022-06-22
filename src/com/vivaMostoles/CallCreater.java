package src.com.vivaMostoles;

import com.vivaMostoles.CallCenter;

public class CallCreater extends InterruptedException {
    public static void callCreater(int numberOfCalls){
     try {
         for (int i = 1; i <= numberOfCalls; i++) {
             new Thread(new CustomerSettings(i)).start();
             Thread.sleep(400);
         }
     } catch (InterruptedException e){
    }}}
