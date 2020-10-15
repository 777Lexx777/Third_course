package Lesson_5;

public class Bus implements CalculatingFuelAndDrive {
    int id;
    int fuelBus = 40;
    double consumptionBus = 7.5;
    FuelStation fuelStation = new FuelStation ();

    @Override
    public void calculatingFuelAndDrive(int id, double fuelBus, double consumptionBus) {
        this.id = id;
        double remaining = fuelBus;
        try {
            while (remaining > 0) {
                drive();
                Thread.sleep(3000);
                remaining = remaining - consumptionBus;
                System.out.println("Bus " + this.id + "  Fuel remaining =" + remaining);
                if (remaining <= 7.5){
                    System.out.println("Bus " + this.id + "  go Fuel Station");
                    remaining = fuelStation.refueling(this.fuelBus);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drive() {
        System.out.println("Bus name " + this.id + " go...");
    }
}
