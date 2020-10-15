package Lesson_5;

public class Car implements CalculatingFuelAndDrive{
    int id;
    int fuelCar = 20;
    double consumptionCar = 2.5;
    FuelStation fuelStation = new FuelStation ();

    @Override
    public void calculatingFuelAndDrive(int id, double fuelCar, double consumptionCar) {
        this.id = id;
        double remaining = fuelCar;
        try {
            while (remaining > 0) {
                drive();
                Thread.sleep(3000);
                remaining -= consumptionCar;
                System.out.println("Car " + this.id + "  Fuel remaining =" + remaining);
                if (remaining <= 2.5){
                    System.out.println("Car " + this.id + "  go Fuel Station");
                    remaining = fuelStation.refueling(this.fuelCar);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }
    }
    @Override
    public void drive() {
        System.out.println("Car name " + this.id + " go...");

    }
}
