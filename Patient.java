import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Patient {
    Scanner obj = new Scanner(System.in);
    private String name, symptoms, gender;
    private int age,docId;

    public int getAge() {
        return age;
    }
    
    public int getId() {
        return docId;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void setId(int id) {
        this.docId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    void viewRecord() {
        System.out.println("Enter the patient name : ");
        String find;
        try {
            find = obj.nextLine();
            find.toLowerCase();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/patients", "bgv", "Vaibhav@2002");
                Statement stmt = con.createStatement();
                ResultSet newrs = stmt.executeQuery("SELECT token,name,age,gender,diagnosis FROM patients WHERE name='" + find + "';");
                while (newrs.next()) {
                    String leftAlignFormat = "| %-4d | %-25s | %-4d | %-6s | %-48s |%n";
                    System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                    System.out.format("|   ID   |         NAME            | AGE  | Gender |                  Diagnosis                       +%n");
                    System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                    System.out.format(leftAlignFormat, newrs.getInt(1) ,newrs.getString(2),newrs.getInt(3),newrs.getString(4),newrs.getString(5));
                    System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                }
                con.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        catch (Exception e) {
            System.out.println("Invalid choice");
        }
    }
}
