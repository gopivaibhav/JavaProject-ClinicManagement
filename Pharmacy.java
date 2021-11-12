import java.util.Scanner;
import java.sql.*; 
public class Pharmacy {
   static int Order_no;
    static int  receipt_number;
    static String customerName;
    static String date;
    static int[] Quantity = new int[10];
    static int count1,count2;
    static int[] menu = new int[10];
    static int[] quantity = new int[10];
    static double[] amount = new double[10];
    static double[] Medicine = new double[]{2.00, 3.00, 1.00, 4.00, 1.00, 5.00, 7.00, 4.00, 3.00, 5.00};
    static double total;
    static Scanner sc = new Scanner(System.in);
    public Pharmacy(){
        count1=-1;
        count2=-1;
    }
    public static void print_order() {
        String leftAlignFormat = "| %-4d | %-15s | %-4d | %-4d |%n";
        System.out.format("+------+-----------------+------+------+%n");
        System.out.format("| ID   |      NAME       | Quan | PRICE|%n");
        System.out.format("+------+-----------------+------+------+%n");
            int totalPrice=0;
            for (int i = 0; i <= count1; i++) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/patients", "bgv", "Vaibhav@2002");
                    Statement stmt = con.createStatement();
                    ResultSet newrs = stmt.executeQuery("SELECT id,name,price FROM pharmacy WHERE id="+menu[i]+";");
                    while (newrs.next()) {
                        System.out.format(leftAlignFormat, newrs.getInt(1) ,newrs.getString(2),quantity[i],newrs.getInt(3)*quantity[i]);
                    totalPrice+=newrs.getInt(3)*quantity[i];
                    }
                    con.close();
                }
                catch (Exception e) {
                    System.out.println(e);
                }
        
            }
            System.out.format("+------+-----------------+------+------+%n");
            System.out.println("\n\nTotal Cost ---> "+totalPrice);
    }
    public static void take_order() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/patients", "bgv", "Vaibhav@2002");
            Statement stmt = con.createStatement();
            ResultSet newrs = stmt.executeQuery("SELECT id,name,price FROM pharmacy;");
            String leftAlignFormat = "| %-4d | %-15s | %-4d |%n";
            System.out.format("+------+-----------------+------+%n");
            System.out.format("| ID   |      NAME       | PRICE|%n");
            System.out.format("+------+-----------------+------+%n");
            while (newrs.next()) {
                System.out.format(leftAlignFormat, newrs.getInt(1) ,newrs.getString(2),newrs.getInt(3));
                System.out.format("+------+-----------------+------+%n");
            }
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    
        System.out.println("Please enter medicine ID : ");
        menu[++count1] = sc.nextInt();
        System.out.println("Please enter the Quantity: ");
        quantity[++count2] = sc.nextInt();

        System.out.println("===========================================================================");
        System.out.println("Order Taken Successfully");
        System.out.println("===========================================================================");
        System.out.println("Go to Reciept Menu to Pay The Bill");
        System.out.println("===========================================================================");
        // sc.close();
        // Pharmacy.PharmacyMenu();
    }

    public void PharmacyMenu() {
        int menu;

        do {
            System.out.println("\t\t\t    Pharmacy Management System \n");
            System.out.println("\t\t==================================================\n\n");
            System.out.println("\t\t--------------------------------------------------\n");
            System.out.println("\t\t||\t1. Add Medicine to your  order \t\t ||\n");
            System.out.println("\t\t||\t2. Print the Receipt and Make Payment \t ||\n");
            System.out.println("\t\t||\t3. Exit\t\t\t\t\t ||\n");
            System.out.println("\t\t--------------------------------------------------\n");
            System.out.println("Enter choice: ");
            menu = sc.nextInt();
            switch (menu) {
                case 1: {
                    take_order();    //function add
                    continue;
                }    //end case 1
                case 2:{
                    print_order();
                    continue;
                }
                case 3:{
                    break;
                }
                default: {
                    System.out.println("You enter invalid input re-enter the input");
                    continue;
                }

            }
            
        } while (3 != menu);
    }
    
}