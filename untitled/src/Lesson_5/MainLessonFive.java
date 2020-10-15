package Lesson_5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
1.Создать классы Car, Truck, Bus. Каждый обладает объемом топлива и расходом
(Car - 20\2.5, Truck - 60\15, Bus - 40\7.5) и
уникальных значением для определения среди разных транспортных средств.
2.Для каждого транспортного средства оставшиеся в баке количество топлива вычисляется раз в 3 сек.
3.Создать до 10 экземпляров разных транспортных средств и запустить их в работу
4.Создать класс FuelStation. Одновременно может заправлять 3 автомобиля,
остальные должны пока не освободится место. Заправка занимает 5 сек,
зачем транспортное средство освобождает место
5.* Транспортные средства должны выстраиваться в очередь, если нет свобожных мест для заправки и
начинать заправку в строгом порядке своей очередь
6.* Транспортные средства после заправки возвращаются на дорогу и продолжают свое движение
 */
public class MainLessonFive {

    public static void main(String[] args) {
         ExecutorService executorService = Executors.newFixedThreadPool(10);

        Bus bus = new Bus();
        Car car = new Car();
        Truck truck = new Truck();

        for (int k = 1 ; k < 5; k++) {
            int id = k;
            executorService.execute(new Runnable() {
                @Override
                public void run() { new  Bus().calculatingFuelAndDrive(id, bus.fuelBus, bus.consumptionBus);
                }
            });
        }
        for (int i = 1 ; i < 4; i++) {
            int id = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() { new Car().calculatingFuelAndDrive(id, car.fuelCar, car.consumptionCar);
                }
            });
        }
        for (int j = 1 ; j < 4; j++) {
            int id = j;
            executorService.execute(new Runnable() {
                @Override
                public void run() { new  Truck().calculatingFuelAndDrive(id, truck.fuelTruck, truck.consumptionTruck);
                }
            });
        }
        executorService.shutdown();
    }
}
