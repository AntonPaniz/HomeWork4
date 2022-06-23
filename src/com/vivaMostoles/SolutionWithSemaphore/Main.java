package src.com.vivaMostoles.SolutionWithSemaphore;

public class Main {
    public static void main(String[] args) {
      /*  private static int numberOfCallCenterManagers = 3;
        //Парковочное место занято - true, свободно - false
        private static final boolean[] CALLCENTER_NUMBER_OF_OPERATERS = new boolean[numberOfCallCenterManagers];
        //Устанавливаем флаг "справедливый", в таком случае метод
        //aсquire() будет раздавать разрешения в порядке очереди
        private static int numberOfCalls = 10;
        private static final Semaphore SEMAPHORE = new Semaphore(numberOfCalls, true);*/

        CallCreater.callCreater(19);
        CustomerSettings b = new CustomerSettings(8);
        b.run();
    }
}
