package Lesson_5;

import java.util.concurrent.Semaphore;

public class FuelStation {
    //Одновременно может заправлять 3 автомобиля
    Semaphore semaphore = new Semaphore(3);

    public double refueling( double fuel){
        try {
            semaphore.acquire();
            Thread.sleep(5000);
            return fuel;

        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }finally {
            semaphore.release();
        }
    }
}
