import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegistryNumberManager {
    String defaultRegistry;
    String registryRegex;
    ArrayList<String> busyRegistryNumbers;
    

    public RegistryNumberManager(){
        defaultRegistry = "A000AA";
        registryRegex = "[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}";
        busyRegistryNumbers = new ArrayList<String>();
    }

    public boolean isRegistryNumber(String value) {
        return Pattern.matches(registryRegex, value);
    }

    public boolean isRegistryNumberBusy(String value) {
        //??
        boolean isBusy = false;
        for (String rn : busyRegistryNumbers) {
            if (rn.equals(value)) {
                isBusy = true;
                break;
            }
        }
        return isBusy;
    }

    public String generateRegistryNumber() {
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
                letters[numRegistry[5]];
        }
        busyRegistryNumbers.add(rn);
        return rn;
        
    }
}
