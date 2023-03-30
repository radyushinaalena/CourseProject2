import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            menu:
            while (true) {
                System.out.println("Выберите пункт меню:");
                TaskService.printMenu();
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    scanner.nextLine();
                    switch (menu) {
                        case 0:
                            break menu;
                        case 1:
                            TaskService.addTask(scanner);
                            break;
                        case 2:
                            TaskService.deleteTask(scanner);
                            break;
                        case 3:
                            TaskService.printTask();
                            break;
                    }
                }
            }
        }
    }
}