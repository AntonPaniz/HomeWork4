package com.vivaMostoles;
import java.util.concurrent.Semaphore;
public class CallCenter {
    private static int numberOfCallCenterManagers;
    //Парковочное место занято - true, свободно - false
    private static final boolean[] CALLCENTER_NUMBER_OF_OPERATERS = new boolean[numberOfCallCenterManagers];
    //Устанавливаем флаг "справедливый", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    private static int numberOfCalls;
    private static final Semaphore SEMAPHORE = new Semaphore(numberOfCalls, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 25; i++) {
            new Thread(new Customer(i)).start();
            Thread.sleep(500);
        }
    }
    public static class Customer implements Runnable {
        private int customerNumber;

        public Customer(int customerNumber) {
            this.customerNumber = customerNumber;
        }
        @Override
        public void run() {
            System.out.printf("Клиент №%d позвонил в колл-центр.\n", customerNumber);
            try {
                //acquire() запрашивает доступ к следующему за вызовом этого метода блоку кода,
                //если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
                //пока семафор не разрешит доступ
                SEMAPHORE.acquire();

                int operatorNumber = -1;

                //Ищем свободное место и паркуемся
                synchronized (CALLCENTER_NUMBER_OF_OPERATERS){
                    for (int i = 1; i <= 5; i++)
                        if (!CALLCENTER_NUMBER_OF_OPERATERS[i]) {      //Если место свободно
                            CALLCENTER_NUMBER_OF_OPERATERS[i] = true;  //занимаем его
                            operatorNumber = i;         //Наличие свободного места, гарантирует семафор
                            System.out.printf("Клиент №%d разговаривает с оператором %d.\n", customerNumber, i);
                            break;
                        }
                }

                Thread.sleep(3000);       //Уходим за покупками, к примеру

                synchronized (CALLCENTER_NUMBER_OF_OPERATERS) {
                    CALLCENTER_NUMBER_OF_OPERATERS[operatorNumber] = false;//Освобождаем место
                }
                //release(), напротив, освобождает ресурс
                SEMAPHORE.release();
                System.out.printf("Клиент №%d закончил разговор.\n", customerNumber);
            } catch (InterruptedException e) {
            }
        }
    }
}
