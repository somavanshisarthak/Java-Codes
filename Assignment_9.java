//Author: Sarthak Somavanshi
// Base class
abstract class Car {
    protected String model;
    protected String accessories;

    public abstract void assemble();
    public abstract void paint();
    public abstract void test();

    @Override
    public String toString() {
        return "Model: " + model + ", Accessories: " + accessories;
    }
}

// Hatchback class
class Hatchback extends Car {
    public Hatchback() {
        model = "Hatchback";
        accessories = "Basic Accessories";
    }

    @Override
    public void assemble() {
        System.out.println("Assembling Hatchback.");
    }

    @Override
    public void paint() {
        System.out.println("Painting Hatchback.");
    }

    @Override
    public void test() {
        System.out.println("Testing Hatchback.");
    }
}

// Sedan class
class Sedan extends Car {
    public Sedan() {
        model = "Sedan";
        accessories = "Luxury Accessories";
    }

    @Override
    public void assemble() {
        System.out.println("Assembling Sedan.");
    }

    @Override
    public void paint() {
        System.out.println("Painting Sedan.");
    }

    @Override
    public void test() {
        System.out.println("Testing Sedan.");
    }
}

// SUV class
class SUV extends Car {
    public SUV() {
        model = "SUV";
        accessories = "Off-Road Accessories";
    }

    @Override
    public void assemble() {
        System.out.println("Assembling SUV.");
    }

    @Override
    public void paint() {
        System.out.println("Painting SUV.");
    }

    @Override
    public void test() {
        System.out.println("Testing SUV.");
    }
}

// Factory class
class CarFactory {
    public Car createCar(String type) {
        Car car = null;

        switch (type.toLowerCase()) {
            case "hatchback":
                car = new Hatchback();
                break;
            case "sedan":
                car = new Sedan();
                break;
            case "suv":
                car = new SUV();
                break;
            default:
                System.out.println("Unknown car type.");
                break;
        }

        if (car != null) {
            car.assemble();
            car.paint();
            car.test();
        }

        return car;
    }
}

// Test class
public class TestFactoryPattern {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();

        System.out.println("Creating a Hatchback:");
        Car hatchback = carFactory.createCar("hatchback");
        System.out.println(hatchback);

        System.out.println("\nCreating a Sedan:");
        Car sedan = carFactory.createCar("sedan");
        System.out.println(sedan);

        System.out.println("\nCreating an SUV:");
        Car suv = carFactory.createCar("suv");
        System.out.println(suv);
    }
}