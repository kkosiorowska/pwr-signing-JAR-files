import java.util.Scanner;

public class App {

    public static void encryptFile() {

    }

    public static void decryptFile() {

    }

    public static void cryptoFile() {
        Scanner scaner = new Scanner(System.in);
        String choice;
        boolean finish = false;

        while (!finish) {
            System.out.println("-------------Cryptographic file system-------------");
            System.out.println("");
            System.out.println("1. - Encrypt");
            System.out.println("2. - Decrypt");
            System.out.println("0. - Back to main menu: ");
            System.out.print("Choice: ");
            choice = scaner.nextLine();
            switch (choice) {
                case "1":
                    encryptFile();
                    break;
                case "2":
                    decryptFile();
                    break;
                case "0":
                    finish = true;
                    break;
                default:
                    System.out.println("Error!");
            }
        }
    }

    public static void knapsackProblem(){

    }

    public static void menu() {
        Scanner scaner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("-----------------------MENU-----------------------");
            System.out.println("");
            System.out.println("1. - Encrypt and decrypt text file");
            System.out.println("2. - Knapsack problem");;
            System.out.println("0. - Exit");
            System.out.print("Choice: ");
            choice = scaner.nextLine();
            switch (choice) {
                case "1":
                    cryptoFile();
                    break;
                case "2":
                    knapsackProblem();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Error!");
            }
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
