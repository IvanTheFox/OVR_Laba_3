public class App {
    public static void main (String[] args) {
        UserInterface UI = new UserInterface();
        boolean flag=true;
        while(flag){
            flag = UI.initializeMain();
        }
    }
}