import java.time.Year;

public class Car {
    String model;
    int price;
    int mileage;
    int birthYear;
    String registryNumber;

    RegistryNumberManager regNumManager;

    static String defaultModel = "Nissan Jhuk";
    static int firstCarBirthYear = 1886;

    public Car() {
        model = "Nissan Jhuk";
        price = 0;
        mileage = 0;
        birthYear = Year.now().getValue();
        registryNumber = "A000AA";
    }
    public Car(RegistryNumberManager _regnumManager) {
        model = "Nissan Jhuk";
        price = 0;
        mileage = 0;
        birthYear = Year.now().getValue();
        regNumManager = _regnumManager;
        registryNumber = regNumManager.generateRegistryNumber();
    }
    public Car(RegistryNumberManager _regNumManager, String _model, int _price, int _mileage, int _birthYear, String _registryNumber) {
        regNumManager = _regNumManager;
        // Setting the registry number
        if (regNumManager.isRegistryNumber(_registryNumber)) {
            if (regNumManager.isRegistryNumberBusy(_registryNumber)) {
                registryNumber = regNumManager.generateRegistryNumber();
                System.out.println("This registry number is taken! Using " + registryNumber + " instead for " + _registryNumber);
            } else {
                registryNumber = _registryNumber;
            }
        } else {
            registryNumber = regNumManager.generateRegistryNumber();
            System.out.println("This registry's format is wrong! Using " + registryNumber + " instead for " + _registryNumber);
        }
        // Setting the model
        if (_model.equals("")) {
            _model = defaultModel;
            System.out.println("Model name can not be empty! Using " + defaultModel + " instead for " + registryNumber);
        } else {
            model = _model;
        }
        // Setting the price
        if (_price >= 0) {
            price = _price;
        } else {
            price = 0;
            System.out.println("Price can not be negative! Using 0 instead for " + registryNumber);
        }
        // Setting the mileage
        if (_mileage >= 0) {
            mileage = _mileage;
        } else {
            mileage = 0;
            System.out.println("Mileage can not be negative! Using 0 instead for " + registryNumber);
        }
        // Setting the birth year
        if (_birthYear < firstCarBirthYear) {
            birthYear = firstCarBirthYear;
            System.out.println("Cars did not exist then! Using " + String.valueOf(firstCarBirthYear) + " instead for " + registryNumber);
        } else if (_birthYear > Year.now().getValue()) {
            birthYear = Year.now().getValue();
            System.out.println("That year has not come yet! Using " + String.valueOf(Year.now().getValue()) + " instead for " + registryNumber);
        }
    }

    public boolean setModel(String value) {
        if (value.equals("")) {
            return false;
        }
        model = value; 
        return true;
    }
    public boolean setPrice(int value) {
        if (value < 0) {
            return false;
        }
        price = value;
        return true;
    }
    public boolean setMileage(int value) {
        if (value < 0) {
            return false;
        }
        mileage = value;
        return true;
    }
    public boolean setBirthYear(int value) {
        if (value < firstCarBirthYear || value > Year.now().getValue()) {
            return false;
        }
        birthYear = value;
        return true;
    }
    public boolean setRegistryNumber(String value) {
        if (!regNumManager.isRegistryNumber(value) || regNumManager.isRegistryNumberBusy(value)) {
            return false;
        }
        registryNumber = value;
        return true;
    }

    public void printInfo() {
        System.out.printf("%s %d yr.\n\tPrice: %d\n\tMileage: %d\n\tRegistry number: %s\n", model, birthYear, price, mileage, registryNumber);
    }
}