import java.util.*;
import java.sql.*;  
public class Main {
    static Admin admin = new Admin();
    static Patient patient = new Patient();
    static Scanner obj = new Scanner(System.in);

    static String find;


    public static void main(String[] args) throws SQLException{
        int c1 = 1;
        System.out.println("Welcome...");
        while( c1 != -1) {
            System.out.println("Choose your role :\n 1. Doctor\n 2. Patient\n 3: exit");
            int choice = obj.nextInt();
            switch (choice) {
                case 1 -> doctorMenu();
                case 2 -> patientMenu();
                case 3 -> c1 = -1;
                default -> System.out.println("Enter valid choice");
            }
        }
    }
    public static void doctorMenu(){
        Doctor doc = new Doctor();
        System.out.println("Welcome to the Doctor Portal");
        System.out.println("Enter the following details to login");
        System.out.println("Your username : ");
        String username = obj.next();
        System.out.println("Your password : ");
        String pass = obj.next();

        boolean finished = false;
        while( !finished){
            if (doc.Login(username, pass)!="") {
                System.out.println("Do you want to :\n 1. View Patient Record\n 2. Give Diagnosis\n 3. View Token numbers\n 4. Exit");
                try {
                    int d_choice = obj.nextInt();
                    switch (d_choice) {
                        case 1 -> doc.viewRecord(doc.Login(username, pass));
                        case 2 -> doc.diagnosis(doc.Login(username, pass));
                        case 3 -> System.out.println(doc.Login(username, pass));
                        case 4 -> finished = true;
                        default -> System.out.println("Enter valid choice");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid choice");
                }
            }
            else {
                finished = true;
            }
        }

    }
    public static  void adminMenu(){
        System.out.println("Welcome to Admin portal...");
        System.out.println("Enter the following details to login");
        System.out.println("Your username : ");
        String username = obj.next();
        System.out.println("Your password : ");
        String pass = obj.next();
        if (admin.Login(username, pass)) {
            admin.createRecord(patient.getName(), patient.getAge(), patient.getGender(), patient.getSymptoms(), patient.getId());
        }
        else {
            adminMenu();
        }
    }
    public static void patientMenu(){

        boolean finished = false;
        while( !finished ) {
            System.out.println("You are now talking to the receptionist");
            System.out.println("Are you : \n 1. Returning patient\n 2. New visitor\n 3. Exit");
            int choice;
            try {
                choice = obj.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice");
                continue;
            }
            if (choice == 1) {
                System.out.println("Please enter your full name : ");
                find = obj.nextLine();
                find.toLowerCase();
                System.out.println("Do you want to : \n 1 : View your record\n 2: Visit the pharmacy\n 3 : Wait for consultation\n 4: Exit");
                int p_choice = obj.nextInt();
                if(p_choice == 1){
                    patient.viewRecord();
                    continue;
                }
                else if( p_choice == 2){
                    Pharmacy pharm = new Pharmacy();
                    pharm.PharmacyMenu();
                    continue;
                }
                else if( p_choice == 4 ){
                    break;
                }
            }
            else if (choice == 2) {
                System.out.println("Please enter your details as follows : ");
                Doctor doc = new Doctor();
                boolean valid = false;
                while (!valid) {
                    try {
                        obj.nextLine();
                        doc.displayAll();
                        System.out.println("Enter id of doctor: ");
                        int docId = obj.nextInt();
                        obj.nextLine();
                        patient.setId(docId);
                        System.out.println("Enter your name : ");
                        String name = obj.nextLine();
                        name.toLowerCase();
                        patient.setName(name);

                        System.out.println("Enter your age : ");
                        int age = obj.nextInt();
                        patient.setAge(age);

                        System.out.println("Enter your gender (M/F) : ");
                        String gender = obj.next();
                        patient.setGender(gender);

                        obj.nextLine();

                        System.out.println("Please enter your symptoms (in a line, separated by commas) : ");
                        String symptoms = obj.nextLine();
                        patient.setSymptoms(symptoms);

                    } catch (Exception e) {
                        System.out.println("Enter valid details");
                        continue;
                    }
                    valid = true;
                    System.out.println("Processing your details...");

                }

            } else if(choice==3){
                System.out.println("Exiting........!");
                break;
            }
            else {
                System.out.println("Enter a valid choice please");
                continue;
            }
            System.out.println("Please wait for your diagnosis by the doctor...");
            System.out.println("Entering the waiting room...");
            finished = true;
            System.out.println("\330[H\330[2J");
            adminMenu();
        }
    }
}
