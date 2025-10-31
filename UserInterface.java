import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, представляющий интерфейс пользователя
 */
public class UserInterface {
    String command;
    ArrayList<String> args;
    CarArray cars;
    RegistryNumberManager manager;

    /**
     * Коструктор по умолчанию
     */
    public UserInterface() {
        command = "";
        args = new ArrayList<String>();
        manager=new RegistryNumberManager();
        cars=new CarArray(manager);
    }

    /**
     * Обработка введённой пользователем комманды
     * @param command - комманда, введённая пользователем
     * @return - ввёл ли пользователь комманду выхода из программы
     */
    private boolean parseCommand(int command) {
        switch (command) {
            case 1:
                cars.printAll();
                return true;
            case 2:
                setNewCar();
                return true;
            case 3:
                cars.getCheapest();
                return true;
            case 4:
                cars.getSmallestMileage();
                return true;
            case 5:
                cars.sortByYear();
                return true;
            case 6:
                initializeCommand6();
                return true;
            case 7:
                System.out.println("Выход...\n");
                return false;
            default:
                System.out.println("Выберите указанную на экране команду!\n");
                return true;
        }
    }

    /**
     * Дальнейшая обработка комманды взаимодействия с машинами
     * @param command
     * @param number
     * @return
     */
    private boolean parseCommand6(int command, String number) {
        switch(command){
            case 1:
                cars.getByRegistryNumber(number);
                return true;
            case 2:
                setField(number);
                return true;
            case 3:
                return false;
            default:
                System.out.println("Выберите указанную на экране команду!\n");
                return true;
        }
    }

    /**
     * Меню комманды взаимодействия с машинами
     */
    private void initializeCommand6(){
        while (true){
            String number = getNextString("Введите регистрационный номер: ");
            if (manager.isRegistryNumber(number)){
                boolean continue6 = true;
                while (continue6){
                    try{
                        String input = getNextString("[1] Вывести информацию о выбранной машине\n"+
                        "[2] Изменить свойство машины\n"+
                        "[3] Отмена\n");
                        if (input.equals("")) {
                            System.out.println("Ввод пустой!");
                        }
                        continue6=parseCommand6(Integer.parseInt(input),number);
                    } catch (Exception e){System.out.println("Неправильный ввод");}
                }
                break;
            } else {
                System.out.println("Вы ввели не регистрационный номер!\n");
            }
        }
    }

    /**
     * Функция инициализации интерфейса пользователя
     * @return - ввёл ли пользователь комманду выхода из программы
     */
    public boolean initializeMain() {
        try{
            System.out.println("Бригада №3: Медведев Михаил, Шепелев Иван\n"+
            "Приложение демонстрирует использование возможностей иерархии классов,\nпостроенной в предметной области 'Транспортные средства'\n");
            String intro = 
            "Введите число, чтобы выполнить одно из действий:\n"+
            "[1] Вывести информацию о всех машинах\n"+
            "[2] Добавить машину\n"+
            "[3] Вывести самый дешевый автомобиль\n"+
            "[4] Вывести самый маленький пробег среди машин старше 3 лет\n"+
            "[5] Упорядочить массив по убыванию года выпуска\n"+
            "[6] Найти машину по регистрационному номеру\n"+
            "[7] Выйти\n";
            String input = getNextString(intro);
            if (input.equals("")) {
                System.out.println("Ввод пустой!");
                return true;
            }
            return parseCommand(Integer.parseInt(input));
        } catch (Exception e){
            System.out.println("Введите номер команды!\n");
            return true;
        }
    }
    
    /**
     * Получение ввода пользователя
     * @param text - текст перед полем ввода
     * @return - введённая пользователем строка
     */
    private String getNextString(String text) {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        try {
            if (scanner.hasNext()) {
                input = scanner.next();
            } else {
                throw new Exception();
            }        
            return input;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Изменение своства машины
     * @param number - регистрационный номер машины
     */
    private void setField(String number){
        try {
            String[] input = getNextString("Введите название свойства, которое хотите изменить, и через пробел - новое значение:").split(":");
            if (input.length!=2){
                System.out.println("Ввод неправильный!\n");
                return;
            }
            cars.setField(number, input[0], input[1]);
        } catch(Exception e) {}
    }

    /**
     * Создание новой машины
     */
    private void setNewCar(){
        try{
            String[] input = getNextString("Введите модель, цену, пробег, год производства и регистрационный номер машины:\n").split(":");
            if (input.length!=5){
                System.out.println("Ввод неправильный!\n");
                return;
            }
            cars.cars.add(new Car(manager, input[0], Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), input[4]));
        } catch(Exception e) {
            System.out.println("Ввод неправильный!\n");
        }
    }
}