import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Класс, представляющий менеджер регистрационных номеров машин
 */
public class RegistryNumberManager {
    String defaultRegistry;
    String registryRegex;
    ArrayList<String> busyRegistryNumbers;

    static final int[] regionNumbers = {};
    
    /**
     * Конструктор по умолчанию
     */
    public RegistryNumberManager() {
        defaultRegistry = "A001AA001";
        registryRegex = "[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}\\d{3}";
        busyRegistryNumbers = new ArrayList<String>();
    }

    /**
     * Проверка формата регистрационного номера
     * @param value - проверямый регистрационный номер
     * @return - соответствие регистрационного номера формату
     */
    public boolean isRegistryNumber(String value) {
        return Pattern.matches(registryRegex, value);
    }
    /**
     * Проверка, занят ли регистрационный номер
     * @param value - проверяемый регистрационный номер
     * @return - статус занятости
     */
    public boolean isRegistryNumberBusy(String value) {
        boolean isBusy = false;
        for (String rn : busyRegistryNumbers) {
            if (rn.equals(value)) {
                isBusy = true;
                break;
            }
        }
        return isBusy;
    }
    /**
     * Генерирует новый свободный регистрационный номер
     * @return - новый регистрационный номер
     */
    public String generateRegistryNumber() {
        String[] letters = {"A", "B", "E", "K", "M", "H", "O", "P", "C", "T", "Y", "X"};
        int[] bases = {letters.length, 10, 10, 10, letters.length, letters.length, 10, 10, 10};
        int[] numRegistry = {0, 0, 0, 1, 0, 0, 0, 0, 1};
        String rn = letters[numRegistry[0]] + 
            String.valueOf(numRegistry[1]) + 
            String.valueOf(numRegistry[2]) + 
            String.valueOf(numRegistry[3]) + 
            letters[numRegistry[4]] + 
            letters[numRegistry[5]] +
            String.valueOf(numRegistry[6]) + 
            String.valueOf(numRegistry[7]) + 
            String.valueOf(numRegistry[8]);

        while (isRegistryNumberBusy(rn)) {
            numRegistry[0]++;
            for (int i = 0; i < numRegistry.length - 1; i++) {
                numRegistry[i + 1] += numRegistry[i] / bases[i];
                numRegistry[i] %= bases[i];
            }
            rn = letters[numRegistry[0]] + 
                String.valueOf(numRegistry[1]) + 
                String.valueOf(numRegistry[2]) + 
                String.valueOf(numRegistry[3]) + 
                letters[numRegistry[4]] + 
                letters[numRegistry[5]] +
            String.valueOf(numRegistry[6]) + 
            String.valueOf(numRegistry[7]) + 
            String.valueOf(numRegistry[8]);
        }
        busyRegistryNumbers.add(rn);
        return rn;
        
    }
}
