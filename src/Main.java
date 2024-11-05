public class Main {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();

        ConcreteObserver observer = new ConcreteObserver();
        TaskManager.getInstance().attach(observer);

        menu.displayMenu();
    }
}
