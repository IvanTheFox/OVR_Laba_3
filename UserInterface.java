import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    String command;
    ArrayList<String> args;

    public UserInterface() {
        command = "";
        args = new ArrayList<String>();
    }

    private void parseCommand() {

    }

    private String getNextString(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        try {
            if (scanner.hasNext()) {
                return scanner.next();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            return "";
        }
    }

    public void awaitCommand(String text) {
        String input = getNextString(text);
        if (input.equals("")) {
            System.out.println("Input is empty!");
            return;
        }

        String[] keywords = input.split(" ");
        
    }
}