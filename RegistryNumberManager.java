import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegistryNumberManager {
    String defaultRegistry = "A000AA";
    String registryRegex = "[ABEKMHOPCTYX]\\d\\d[ABEKMHOPCTYX]{2}";
    ArrayList<String> busyRegistryNumbers = new ArrayList<String>();

    public boolean isRegistryNumber(String value) {
        return Pattern.matches(registryRegex, value);
    }

    public boolean isRegistryNumberBusy(String value) {
        if (!registryRegex.matches(value)) {
            return false;
        }
        boolean isBusy = false;
        for (String rn : busyRegistryNumbers) {
            if (rn.equals(value)) {
                isBusy = true;
                break;
            }
        }
        return isBusy;
    }

    public String generateRegistryNumber(boolean addToRecords) {
        String[] letters = {"A", "B", "E", "K", "M", "H", "O", "P", "C", "T", "Y", "X"};
        int[] bases = {letters.length, 10, 10, 10, letters.length, letters.length};
        int[] numRegistry = {0, 0, 0, 0, 0, 0};
        String rn = letters[numRegistry[0]] + 
            String.valueOf(numRegistry[1]) + 
            String.valueOf(numRegistry[2]) + 
            String.valueOf(numRegistry[3]) + 
            letters[numRegistry[4]] + 
            letters[numRegistry[5]];

        while (isRegistryNumberBusy(rn)) {
            for (int i = 0; i < numRegistry.length; i++) {
                numRegistry[i]++;
            }
            for (int i = 0; i < numRegistry.length - 1; i++) {
                numRegistry[i + 1] += numRegistry[i] / bases[i];
                numRegistry[i] %= bases[i];
            }

            rn = letters[numRegistry[0]] + 
                String.valueOf(numRegistry[1]) + 
                String.valueOf(numRegistry[2]) + 
                String.valueOf(numRegistry[3]) + 
                letters[numRegistry[4]] + 
                letters[numRegistry[5]];
        }

        return rn;
    }
}
