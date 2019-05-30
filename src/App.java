import abstractClass.AbstractAlgorithm;
import algorithms.Bruteforce;
import algorithms.Greedy;
import algorithms.RandomSolution;
import cryptography.CryptoException;
import cryptography.SymmetricCryptography;
import knapSack.Instance;
import knapSack.Item;
import knapSack.Solution;

import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    // private static String key = "Mary has one cat";
    private static String key = new String();
    private static String fileName = "message";
    private static File encryptedFile = new File("document.encrypted");
    private static File decryptedFile = new File("document.decrypted");
    private static Instance instance = new Instance();
    private static List<Item> listItems = new ArrayList<>();
    private static int capacity = 10;
    private static AbstractAlgorithm alg ;
    private static Solution solution;

    public static void encryptFile() throws IOException {
        Scanner scaner = new Scanner(System.in);
        String message;

        System.out.println("-------------------Encrypt file-------------------");
        System.out.println("");
        System.out.println("Message : ");
        message = scaner.nextLine();
        System.out.println("Key : ");
        setKey(scaner.nextLine());

        System.out.println("Message was encrypted to file: " + fileName + ".txt");

        File inputFile = new File(fileName + ".txt");
        PrintWriter record = new PrintWriter(fileName + ".txt");
        record.println(message);
        record.close();

        try {
            SymmetricCryptography.encrypt(key, inputFile, encryptedFile);

            if (inputFile.exists()){
                inputFile.delete();
            }
        }
        catch (CryptoException ex) {
            System.out.println(ex.getMessage()); ex.printStackTrace();
        }
        scaner.nextLine();
    }

    public static void decryptFile() throws IOException, BadPaddingException {
        Scanner scaner = new Scanner(System.in);
        String message;
        String _key;

        System.out.println("-------------------Decrypt file-------------------");
        System.out.println("");
        System.out.println("Key : ");
        _key = scaner.nextLine();


        if(_key.getBytes().length == 16) {
            setKey(_key);

            try {
                SymmetricCryptography.decrypt(key, encryptedFile, decryptedFile);

                if(SymmetricCryptography.decrypted){
                    BufferedReader reader = new BufferedReader(new FileReader("document.decrypted"));
                    message = reader.readLine();
                    reader.close();
                    System.out.println("Message was decrypt."); System.out.println("Text: " + message);

                }
            }
            catch (CryptoException ex)
            {
                System.out.println(ex.getMessage()); ex.printStackTrace();
            }

        }else { System.out.println("Wrong key.");}

        scaner.nextLine();
    }

    public static void cryptoFile() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException {
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
                    if (decryptedFile.exists()){
                        decryptedFile.delete();
                    }
                    break;
                default:
                    System.out.println("Error!");
            }
        }
    }

    public static void printInstance() {

        if(listItems.isEmpty()) System.out.println("Empty list.");
        else {
            System.out.println("Items : ");
            for(int i=0 ; i <listItems.size() ; i++){
                System.out.print("( "+ listItems.get(i).weight + ", " + listItems.get(i).profit + " ) ");
            }
        }
    }

    public static void printSolution(){
        System.out.print("[ ");
        for(int i=0 ; i < solution.knapSack.size() ; i++){
            if(i==solution.knapSack.size()-1) System.out.print(solution.knapSack.get(i));
            else System.out.print(solution.knapSack.get(i)+ ", ");
        }
        System.out.print(" ]");
        System.out.println("");
    }

    public static void solveProblem() {
        Scanner scaner = new Scanner(System.in);
        String choice;
        boolean stop = false;

        while (!stop) {
            System.out.println("---------------------Solve problem---------------------");
            System.out.println("");
            System.out.println("1. - Brute force");
            System.out.println("2. - Random solution");
            System.out.println("3. - Greedy");
            System.out.println("0. - Back");
            System.out.print("Choice: ");
            choice = scaner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("---------------------Brute force---------------------");
                    System.out.println("");
                    if(!listItems.isEmpty() && capacity >0){
                        alg = new Bruteforce();
                        instance = new Instance(listItems, capacity);
                        solution = alg.algorithm(instance);
                        System.out.println("Solution: ");
                        printSolution();
                    }else {System.out.println("ERROR");}
                    break;
                case "2":
                    System.out.println("---------------------Random Solution---------------------");
                    System.out.println("");
                    if(!listItems.isEmpty() && capacity >0){
                        alg = new RandomSolution();
                        instance = new Instance(listItems, capacity);
                        solution = alg.algorithm(instance);
                        System.out.println("Solution: ");
                        printSolution();
                    }else {System.out.println("ERROR");}
                    break;

                case "3":
                    System.out.println("---------------------Greedy---------------------");
                    System.out.println("");
                    if(!listItems.isEmpty() && capacity >0){
                        alg = new Greedy();
                        instance = new Instance(listItems, capacity);
                        solution = alg.algorithm(instance);
                        System.out.println("Solution: ");
                        printSolution();
                    }else {System.out.println("ERROR");}
                    break;
                case "0":
                    stop = true;
                    break;
                default:
                    System.out.println("Error!");
            }
        }
    }

    public static void knapsackProblem(){
        Scanner scaner = new Scanner(System.in);
        String choice;
        float profit;
        int weight;
        boolean stop = false;

        while (!stop) {
            System.out.println("---------------------Knapsack---------------------");
            System.out.println("");
            System.out.println("Capacity: " + capacity );
            printInstance();
            System.out.println("");
            System.out.println("1. - Add item");
            System.out.println("2. - Change capacity");
            System.out.println("3. - Solve problem");
            System.out.println("0. - Back");
            System.out.print("Choice: ");
            choice = scaner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("---------------------Add item---------------------");
                    System.out.println("");
                    System.out.println("Profit: ");
                    profit =  Integer.parseInt(scaner.nextLine());
                    System.out.println("Weight: ");
                    weight = Integer.parseInt(scaner.nextLine());
                    listItems.add(new Item(profit, weight));
                    break;
                case "2":
                    System.out.println("---------------------Capacity---------------------");
                    System.out.println("");
                    System.out.println("New capacity: ");
                    capacity = Integer.parseInt(scaner.nextLine());
                    break;
                case "3":
                    solveProblem();
                    break;
                case "0":
                    stop = true;
                    listItems.clear();
                default:
                    System.out.println("Error!");
            }
        }

    }

    public static void menu() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException {
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

    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException {
        menu();
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        App.key = key;
    }
}
