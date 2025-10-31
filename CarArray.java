import java.time.Year;
import java.util.ArrayList;

/**
 * Класс, представляющий список машин
 */
public class CarArray {
    ArrayList<Car> cars;
    static final int startLength = 10;

    /**
     * Конструктор по умолчанию
     */
    public CarArray(){
        cars = new ArrayList<Car>();
    }
    /**
     * Конструктор, привязывающий менеджер регистрационных номеров к списку
     * @param _Manager
     */
    public CarArray(RegistryNumberManager _Manager) {
        cars = new ArrayList<Car>();
        for (int i=0;i<startLength;i++){
            cars.add(new Car(_Manager));
        }
    }

    /**
     * Получение самой дешёвой машины
     */
    public void getCheapest() {
        int cheapestIndex = 0;
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).price < cars.get(cheapestIndex).price) {
                cheapestIndex = i;
            }
        }
        cars.get(cheapestIndex).printInfo();
    }
    /**
     * Получение машины с самым маленьким пробегом, старее 3 лет
     */
    public void getSmallestMileage() {
        int smallestMileageIndex = -1;
        for (int i = 0; i < cars.size(); i++) {
            if (Year.now().getValue() - cars.get(i).birthYear > 3) {
                if (smallestMileageIndex == -1 || cars.get(smallestMileageIndex).mileage > cars.get(i).mileage) {
                    smallestMileageIndex = i;
                }
            }
        }

        if (smallestMileageIndex == -1) {
            System.out.println("No cars older than 3 years!");
        } else {
            cars.get(smallestMileageIndex).printInfo();
        }
    }
    /**
     * Получение машины по регистрационному номеру
     * @param registryNumber
     */
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

    /**
     * Сортировка машин по году выпуска
     */
    public void sortByYear() {
        cars.sort((c1, c2) -> Integer.compare(c2.birthYear, c1.birthYear));
        printAll();
    }

    /**
     * Изменение свойтв машины из списка
     * @param registryNumber - регистрационный номер машины
     * @param fieldName - название свойства
     * @param value - значение свойства
     */
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
                    System.out.println("This registry number is taken!");
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
    
    /**
     * Вывод информации о всех машинах в списке
     */
    public void printAll(){
        for (Car car : cars){
            car.printInfo();
        }
    }
}