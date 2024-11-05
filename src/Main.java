public class Main {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();

        // Create and attach an observer
        ConcreteObserver observer = new ConcreteObserver();
        TaskManager.getInstance().attach(observer);

        menu.displayMenu();
    }
}
