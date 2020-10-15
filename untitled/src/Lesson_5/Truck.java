package Lesson_5;

public class Truck implements CalculatingFuelAndDrive {
    int id;
    int fuelTruck = 60;
    double consumptionTruck = 15.0;
    FuelStation fuelStation = new FuelStation ();

    @Override
    public void calculatingFuelAndDrive(int id, double fuelTruck, double consumptionTruck) {
        this.id = id;
        double remaining = fuelTruck;
        try {
            while (remaining > 0) {
                drive();
                Thread.sleep(3000);
                remaining = remaining - consumptionTruck;
                System.out.println("Truck " + this.id + "  Fuel remaining =" + remaining);
                if (remaining <= 15.0){
                    System.out.println("Truck " + this.id + "  go Fuel Station");
                    remaining = fuelStation.refueling(this.fuelTruck);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drive() {
        System.out.println("Track name " + this.id + " go...");
    }
}
