package com.vivaMostoles;
import src.com.vivaMostoles.CallCreater;
import src.com.vivaMostoles.CustomerSettings;

import java.util.concurrent.Semaphore;
public class CallCenter {
    private static int numberOfCallCenterManagers = 3;
    //Парковочное место занято - true, свободно - false
    private static final boolean[] CALLCENTER_NUMBER_OF_OPERATERS = new boolean[numberOfCallCenterManagers];
    //Устанавливаем флаг "справедливый", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    private static int numberOfCalls = 10;
    public static final Semaphore SEMAPHORE = new Semaphore(numberOfCalls, true);

  //  public static void main(String[] args) throws InterruptedException{
     /*   for (int i = 1; i <= numberOfCalls; i++) {
            new Thread(new Customer(i)).start();
            Thread.sleep(500);
        }*/
    //    CallCreater.callCreater(10);
    }

  /*  public static class Customer implements Runnable {
        private int customerNumber;

        public Customer(int customerNumber) {
            this.customerNumber = customerNumber;
        }

    CustomerSettings toCheck = new CustomerSettings(numberOfCalls);
        @Override
        public void run() {
            System.out.printf("Client № %d calling.\n", toCheck );
            try {
                //acquire() запрашивает доступ к следующему за вызовом этого метода блоку кода,
                //если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
                //пока семафор не разрешит доступ
                SEMAPHORE.acquire();

                int operatorNumber = -1;

                //Ищем свободное место и паркуемся
                synchronized (CALLCENTER_NUMBER_OF_OPERATERS){
                    for (int i = 1; i <= numberOfCallCenterManagers; i++)
                        if (!CALLCENTER_NUMBER_OF_OPERATERS[i]) {      //Если место свободно
                            CALLCENTER_NUMBER_OF_OPERATERS[i] = true;  //занимаем его
                            operatorNumber = i;         //Наличие свободного места, гарантирует семафор
                            System.out.printf("CLient №%d is talking with operator %d.\n", customerNumber, i);
                            break;
                        }
                }

                Thread.sleep(1000);       //Уходим за покупками, к примеру

                synchronized (CALLCENTER_NUMBER_OF_OPERATERS) {
                    CALLCENTER_NUMBER_OF_OPERATERS[operatorNumber] = false;//Освобождаем место
                }
                //release(), напротив, освобождает ресурс
                SEMAPHORE.release();
                System.out.printf("Client №%d has finished conversation.\n", toCheck);
            } catch (InterruptedException e) {
            }
        }*/


