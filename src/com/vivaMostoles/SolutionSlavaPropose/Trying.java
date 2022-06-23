package src.com.vivaMostoles.SolutionSlavaPropose;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Trying {
    public static void main(String[] args) {

        ExecutorService numberOfOperaters = Executors.newFixedThreadPool(3);
    Callable<Integer> task1 = () -> {
        System.out.println(Thread.currentThread().getId());
        System.out.println("I'm Callable task 1.");
        return 100;
    };

    Callable<Integer> task2 = () -> {
        System.out.println(Thread.currentThread().getId());
        System.out.println("I'm Callable task 2.");
        return 200;
    };

    Callable<Integer> task3 = () -> {
        System.out.println(Thread.currentThread().getId());
        System.out.println("I'm Callable task 3.");
        return 300;
    };
        Callable<Integer> task4 = () -> {
            System.out.println(Thread.currentThread().getId());
            System.out.println("I'm Callable task 4.");
            return 3004;
        };
        Callable<Integer> task5 = () -> {
            System.out.println(Thread.currentThread().getId());
            System.out.println("I'm Callable task 5.");
            return 3005;
        };
        Callable<Integer> task6 = () -> {
            System.out.println(Thread.currentThread().getId());
            System.out.println("I'm Callable task 6.");
            return 3006;
        };
    List<Callable<Integer>> tasks = List.of(task1, task2, task3, task4, task5, task6);



        try {

      //  otherTask("Before Future Result");

        // block until future returned a result,
        // timeout if the future takes more than 5 seconds to return the result
        //Integer result = futureTask1.get(3, TimeUnit.SECONDS);

        List<Future<Integer>> futures = numberOfOperaters.invokeAll(tasks);
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }

        otherTask("After Future Result");


    } catch (InterruptedException e) {// thread was interrupted
        e.printStackTrace();
    }
        catch (ExecutionException e) {// thread threw an exception
        e.printStackTrace();
        System.err.println("Execution error");
    }
//        catch (TimeoutException e) {// timeout before the future task is complete
//            e.printStackTrace();
//            System.err.println("time out");
//        }
        finally {
        // shut down the executor manually

            numberOfOperaters.shutdown();
    }

}

    private static void otherTask(String name) {
        System.out.println("I'm other task! " + name);
    }
}



