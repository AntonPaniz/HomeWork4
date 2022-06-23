package src.com.vivaMostoles.SolutionWithSemaphore;

import java.util.concurrent.Semaphore;


public class CustomerSettings implements Runnable {
    private static int numberOfCallCenterManagers = 19;

    //Парковочное место занято - true, свободно - false
    public static final boolean[] CALLCENTER_NUMBER_OF_OPERATERS = new boolean[numberOfCallCenterManagers];
    //Устанавливаем флаг "справедливый", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    private static int numberOfCalls = 90;
    private static final Semaphore SEMAPHORE = new Semaphore(numberOfCallCenterManagers, true);
    private int customerNumber = 93;

    public CustomerSettings(int customerNumber) {
        this.customerNumber = customerNumber;
    }


    @Override
    public void run() {
        System.out.printf("Client  %d calling.\n", customerNumber);
        try {
            //acquire() запрашивает доступ к следующему за вызовом этого метода блоку кода,
            //если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
            //пока семафор не разрешит доступ
            SEMAPHORE.acquire();

            int operatorNumber = -1;

            //Ищем свободное место и паркуемся
            synchronized (CALLCENTER_NUMBER_OF_OPERATERS) {
                for (int i = 1; i <= numberOfCallCenterManagers; i++)
                    if (!CALLCENTER_NUMBER_OF_OPERATERS[i]) {      //Если место свободно
                        CALLCENTER_NUMBER_OF_OPERATERS[i] = true;  //занимаем его
                        operatorNumber = i;         //Наличие свободного места, гарантирует семафор
                        System.out.printf("CLient %d is talking with operator %d.\n", customerNumber, i);
                        break;
                    }
            }

            Thread.sleep(5000);       //Уходим за покупками, к примеру

            synchronized (CALLCENTER_NUMBER_OF_OPERATERS) {
                CALLCENTER_NUMBER_OF_OPERATERS[operatorNumber] = false;//Освобождаем место
            }
            //release(), напротив, освобождает ресурс
            SEMAPHORE.release();
            System.out.printf("Client  %d has finished conversation.\n", customerNumber);
        } catch (InterruptedException e) {
        }
    }
}
