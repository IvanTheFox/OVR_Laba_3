import java.time.Year;
import java.util.Arrays;

public class CarArray {
    Car[] cars;

    public CarArray() {
        cars = new Car[10];
    }

    public void getCheapest() {
        int cheapestIndex = 0;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].price < cars[cheapestIndex].price) {
                cheapestIndex = i;
            }
        }

        cars[cheapestIndex].printInfo();
    }

    public void getSmallestMileage() {
        int smallestMileageIndex = -1;
        for (int i = 0; i < cars.length; i++) {
            if (Year.now().getValue() - cars[i].birthYear > 3) {
                if (smallestMileageIndex == -1 || cars[smallestMileageIndex].mileage > cars[i].mileage) {
                    smallestMileageIndex = i;
                }
            }
        }

        if (smallestMileageIndex == -1) {
            System.out.println("No cars older than 3 years!");
        } else {
            cars[smallestMileageIndex].printInfo();
        }
    }

    public void sortByYear() {
        Arrays.sort(cars, (c1, c2) -> Integer.compare(c1.birthYear, c2.birthYear));
        for (Car car : cars) {
            car.printInfo();
        }
    }

    public void getByRegistryNumber(String registryNumber) {
        boolean carFound = false;
        for (Car car : cars) {
            if (registryNumber.equals(registryNumber)) {
                carFound = true;
                car.printInfo();
                break;
            }
        }

        if (!carFound) {
            System.out.print("Couldn't find a car with that registry number!");
        }
    }

    public void setField(String registryNumber, String fieldName, String value) {
        // Looking for a car with provided registry number
        boolean carFound = false;
        Car car = new Car();
        for (Car c : cars) {
            if (registryNumber.equals(c.registryNumber)) {
                car = c;
                carFound = true;
                break;
            }
        }
        if (!carFound) {
            System.out.print("Couldn't find a car with that registry number!");
        }
        
        // Trying to convert value to int
        int intValue = 0;
        boolean convertionToIntSuccess = false;
        try {
            intValue = Integer.parseInt(value);
            convertionToIntSuccess = true;
        } catch (Exception e) {}

        // Setting the field
        if (fieldName.equals("Model")) {
            boolean operationSuccess = car.setModel(value);
            if (!operationSuccess) {
                System.out.println("Model can not be empty!");
            } else {
                car.printInfo();
            }
        } else if (fieldName.equals("Price")) {
            if (!convertionToIntSuccess) {
                System.out.println("Failed to convert value to number!");
            } else {
                boolean operationSuccess = car.setPrice(intValue);
                if (!operationSuccess) {
                    System.out.println("Wrong price format!");
                } else {
                    car.printInfo();
                }
            }
        } else if (fieldName.equals("Mileage")) {
            if (!convertionToIntSuccess) {
                System.out.println("Failed to convert value to number!");
            } else {
                boolean operationSuccess = car.setMileage(intValue);
                if (!operationSuccess) {
                    System.out.println("Wrong mileage format!");
                } else {
                    car.printInfo();
                }
            }
        } else if (fieldName.equals("BirthYear")) {
            if (!convertionToIntSuccess) {
                System.out.println("Failed to convert value to number!");
            } else {
                boolean operationSuccess = car.setBirthYear(intValue);
                if (!operationSuccess) {
                    System.out.println("Wrong birth year format!");
                } else {
                    car.printInfo();
                }
            }
        } else if (fieldName.equals("RegistryNumber")) {
            boolean operationSuccess = car.setRegistryNumber(value);
            if (!operationSuccess) {
                if (car.regNumManager.isRegistryNumber(value)) {
                    System.out.println("This registry number is busy!");
                } else {
                    System.out.println("This registry number is invalid!");
                }
            } else {
                car.printInfo();
            }
        } else {
            System.out.println("Couldn't find such field!");
        }
    }
}