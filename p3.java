import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Main {
    private static int budget, totalExpenses = 0, food = 0, medical = 0, electricity = 0, clothes = 0, others = 0;
    private static String username, password;

    private static HashMap<String, ArrayList<Integer>> tracker = new HashMap<>();
    private static HashMap<String, String> userdata = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Sign up  2. Login  3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    usersignup(sc);
                    break;
                case 2:
                    userlogin(sc);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void usersignup(Scanner sc) {
        System.out.print("Enter user name: ");
        String username = sc.nextLine();
        System.out.print("Enter the password: ");
        String password = sc.nextLine();

        if (userdata.containsKey(username)) {
            System.out.println("Username already exists. You can try logging in now with the valid username and password.");
            return;
        }

        userdata.put(username, password);
        System.out.println("User registration successful. Now you can proceed with login.");
    }

    public static void userlogin(Scanner sc) {
        System.out.print("Enter user name: ");
        String username = sc.nextLine();
        System.out.print("Enter the password: ");
        String password = sc.nextLine();

        if (userdata.containsKey(username) && userdata.get(username).equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            initializeTracker();
            System.out.print("Enter your budget per month: ");
            budget = sc.nextInt();
            sc.nextLine();
            while (true) {
                System.out.println("1. Food 2. Medical 3. Electricity 4. Clothes 5. Others 6. Exit");
                System.out.print("Enter your expense category from the above: ");
                int choice1 = sc.nextInt();
                if (choice1 == 6) {
                    budgettracking();
                    System.out.println("Your investment in food: " + food);
                    System.out.println("Your investment in medicine: " + medical);
                    System.out.println("Your investment in electricity: " + electricity);
                    System.out.println("Your investment in clothes: " + clothes);
                    System.out.println("Your investment in others: " + others);
                    String filePath = "C:\\example.txt";
                    File file = new File(filePath);

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write("Username: " + username + "\n");
                        writer.write("Password: " + password + "\n");
                        writer.write("Food: " + food + "\n");
                        writer.write("Medical: " + medical + "\n");
                        writer.write("Electricity: " + electricity + "\n");
                        writer.write("Clothes: " + clothes + "\n");
                        writer.write("Others: " + others + "\n");
                        System.out.println("File written successfully to " + file.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (file.exists()) {
                        System.out.println("File exists: " + file.getAbsolutePath());
                    } else {
                        System.out.println("File does not exist: " + file.getAbsolutePath());
                    }
                    break;
                }
                System.out.print("Enter your expense in rupees: ");
                int money = sc.nextInt();
                sc.nextLine();
                switch (choice1) {
                    case 1:
                        tracker.get("Food").add(money);
                        break;
                    case 2:
                        tracker.get("Medical").add(money);
                        break;
                    case 3:
                        tracker.get("Electricity").add(money);
                        break;
                    case 4:
                        tracker.get("Clothes").add(money);
                        break;
                    case 5:
                        tracker.get("Others").add(money);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void initializeTracker() {
        tracker.put("Food", new ArrayList<>());
        tracker.put("Medical", new ArrayList<>());
        tracker.put("Electricity", new ArrayList<>());
        tracker.put("Clothes", new ArrayList<>());
        tracker.put("Others", new ArrayList<>());
    }

    public static void budgettracking() {
        for (int f : tracker.get("Food")) {
            food += f;
        }
        for (int m : tracker.get("Medical")) {
            medical += m;
        }
        for (int e : tracker.get("Electricity")) {
            electricity += e;
        }
        for (int c : tracker.get("Clothes")) {
            clothes += c;
        }
        for (int o : tracker.get("Others")) {
            others += o;
        }
        totalExpenses = food + medical + electricity + clothes + others;
        if (totalExpenses > budget) {
            System.out.println("Alert: Your total expenses have exceeded your budget!");
            System.out.println("You crossed your budget. It's time to save in the next month with a total of: " + (totalExpenses - budget));
        } else {
            System.out.println("Your total expenses are within the budget.");
        }
        System.out.println("Total expenses so far: " + totalExpenses);
    }
}
