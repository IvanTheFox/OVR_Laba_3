import java.time.Year;
import java.util.Random;

/**
 * Класс, представляющий машину, содержащий её описание.
 */
public class Car {
    String model;
    int price;
    int mileage;
    int birthYear;
    String registryNumber;

    Random randomizer = new Random();
    RegistryNumberManager regNumManager;

    static String defaultModel = "Nissan Juke";
    static int firstCarBirthYear = 1886;
    static final int firstNissanJukeYear = 2010;  

    public Car() {
        model = defaultModel;
        price = 0;
        mileage = 0;
        birthYear = Year.now().getValue();
        registryNumber = "A000AA";
    }
    /**
     * Конструктор для создания машины, привязанной к заданному менеджеру регистрационных номеров, с произвольным регистрационным номером
     * @param _regnumManager - менеджер регистрационных номеров, к которому привязывается машина
     */
    public Car(RegistryNumberManager _regNumManager) {
        model = defaultModel;
        price = randomizer.nextInt(100000,10000000);
        mileage = randomizer.nextInt(100000);
        birthYear = randomizer.nextInt(firstNissanJukeYear,Year.now().getValue()+1);
        regNumManager = _regNumManager;
        registryNumber = regNumManager.generateRegistryNumber();
    }
    /**
     * Конструктор для создания машины с заданными свойтсвами
     * @param _regNumManager - менеджер регистрационных номеров, к которому привязывается машина
     * @param _model - модель машины
     * @param _price - цена машины
     * @param _birthYear - год выпуска машины
     * @param _registryNumber - регистрационный номер машины
     */
    public Car(RegistryNumberManager _regNumManager, String _model, int _price, int _mileage, int _birthYear, String _registryNumber) {
        // Привязка к менеджеру регистрационных номеров
        regNumManager = _regNumManager;
        // Регистрационный номер
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
        // Модель
        if (_model.equals("")) {
            _model = defaultModel;
            System.out.println("Model name can not be empty! Using " + defaultModel + " instead for " + registryNumber);
        } else {
            model = _model;
        }
        // Цена
        if (_price >= 0) {
            price = _price;
        } else {
            price = 0;
            System.out.println("Price can not be negative! Using 0 instead for " + registryNumber);
        }
        // Пробег
        if (_mileage >= 0) {
            mileage = _mileage;
        } else {
            mileage = 0;
            System.out.println("Mileage can not be negative! Using 0 instead for " + registryNumber);
        }
        // Год выпуска
        if (_birthYear < firstCarBirthYear) {
            birthYear = firstCarBirthYear;
            System.out.println("Cars did not exist then! Using " + String.valueOf(firstCarBirthYear) + " instead for " + registryNumber);
        } else if (_birthYear > Year.now().getValue()) {
            birthYear = Year.now().getValue();
            System.out.println("That year has not come yet! Using " + String.valueOf(Year.now().getValue()) + " instead for " + registryNumber);
        } else {
            birthYear=_birthYear;
        }
    }
    /**
     * Задаёт новую модель машины
     * @param value - новая модель машины
     * @return - успех операции
     */
    public boolean setModel(String value) {
        if (value.equals("")) {
            return false;
        }
        model = value; 
        return true;
    }
    /**
     * Задаёт новую цену машины
     * @param value - новая цена машины
     * @return - успех операции
     */
    public boolean setPrice(int value) {
        if (value < 0) {
            return false;
        }
        price = value;
        return true;
    }
    /**
     * Задаёт новый пробег машины
     * @param value - новый пробег машины
     * @return - успех операции
     */
    public boolean setMileage(int value) {
        if (value < 0) {
            return false;
        }
        mileage = value;
        return true;
    }
    /**
     * Задаёт новую дату выпуска машины
     * @param value - новая дата выпуска машины
     * @return - успех операции
     */
    public boolean setBirthYear(int value) {
        if (value < firstCarBirthYear || value > Year.now().getValue()) {
            return false;
        }
        birthYear = value;
        return true;
    }
    /**
     * Задаёт новый регистрационный номер машины
     * @param value - новый регистрационный номер машины
     * @return - успех операции
     */
    public boolean setRegistryNumber(String value) {
        if (!regNumManager.isRegistryNumber(value) || regNumManager.isRegistryNumberBusy(value)) {
            return false;
        }
        registryNumber = value;
        return true;
    }
    /**
     * Выводит информацию о машине в консоль
     */
    public void printInfo() {
        System.out.printf("%s %d yr.\n\tPrice: %d\n\tMileage: %d\n\tRegistry number: %s\n", model, birthYear, price, mileage, registryNumber);
    }
}