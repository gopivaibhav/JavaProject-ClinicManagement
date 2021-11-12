import java.sql.*;  
import java.util.Scanner;
public class Doctor {
    
    Scanner obj = new Scanner(System.in);
    public String Login(String username, String password){
        String tokenIds="";
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/patients","bgv","Vaibhav@2002");  
            Statement stmt=con.createStatement(); 
            ResultSet newrs=stmt.executeQuery("SELECT password,tokens FROM doctors WHERE name='"+username+"';");  
            while(newrs.next())  {
                if(newrs.getString(1).equals(password)){
                    tokenIds=newrs.getString(2);
                }else{
                    System.out.println("Invalid Password");
                }
                
            }
            con.close();  
        }catch(Exception e){ 
            System.out.println(e);
        }
        return tokenIds;
    }
    void viewRecord(String tokens){
            try{  
                Class.forName("com.mysql.cj.jdbc.Driver");  
                Connection con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/patients","bgv","Vaibhav@2002");  
                Statement stmt=con.createStatement(); 
                String[] arrOfStr = tokens.split(",", 15);
                // System.out.println(arrOfStr[0]+arrOfStr[1]+arrOfStr[2]);
                String query="SELECT token,name,age,gender,diagnosis FROM patients WHERE token="+arrOfStr[0];
                for (String ele : arrOfStr) {
                    query+=" OR token="+ele;
                }
                query+=";";
                ResultSet newrs = stmt.executeQuery(query);
                String leftAlignFormat = "| %-4d | %-25s | %-4d | %-6s | %-48s |%n";
                System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                System.out.format("|   ID   |         NAME            | AGE  | Gender |                  Diagnosis                       +%n");
                System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                while (newrs.next()) {
                    System.out.format(leftAlignFormat, newrs.getInt(1) ,newrs.getString(2),newrs.getInt(3),newrs.getString(4),newrs.getString(5));
                }
                System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                System.out.println("Enter the patient id : ");
                int find;
                find = obj.nextInt();
                ResultSet rs=stmt.executeQuery("SELECT token,name,age,gender,symptoms FROM patients WHERE token="+find+";");  
                while (rs.next()) {
                    String alignFormat = "| %-4d | %-25s | %-4d | %-6s | %-48s |%n";
                    System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                    System.out.format("|   ID   |         NAME            | AGE  | Gender |                  Symptoms                        +%n");
                    System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                    System.out.format(alignFormat, rs.getInt(1) ,rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                    System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                }
                con.close();  
            }catch(Exception e){ 
                System.out.println(e);
            }
    }

    void diagnosis(String tokens){
            try{  
                Class.forName("com.mysql.cj.jdbc.Driver");  
                Connection con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/patients","bgv","Vaibhav@2002");  
                Statement stmt = con.createStatement();
                String[] arrOfStr = tokens.split(",", 15);
                // System.out.println(arrOfStr[0]+arrOfStr[1]+arrOfStr[2]);
                String query="SELECT token,name,age,gender,diagnosis FROM patients WHERE token="+arrOfStr[0];
                for (String ele : arrOfStr) {
                    query+=" OR token="+ele;
                }
                query+=";";
                ResultSet newrs = stmt.executeQuery(query);
                String leftAlignFormat = "| %-4d | %-25s | %-4d | %-6s | %-48s |%n";
                System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                System.out.format("|   ID   |         NAME            | AGE  | Gender |                  Diagnosis                       +%n");
                System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                while (newrs.next()) {
                    System.out.format(leftAlignFormat, newrs.getInt(1) ,newrs.getString(2),newrs.getInt(3),newrs.getString(4),newrs.getString(5));
                }
                System.out.format("+--------+-------------------------+------+--------+--------------------------------------------------+%n");
                System.out.println("Enter the patient id : ");
                int find;
                find = obj.nextInt();
                obj.nextLine();
                System.out.println("Enter your diagnosis:");
                String diagnosis = obj.nextLine();
                String query2="UPDATE patients SET diagnosis='"+diagnosis+"' WHERE token="+find+";";
                PreparedStatement pst=con.prepareStatement(query2);
                pst.executeUpdate();
                con.close();  
            }catch(Exception e){ 
                System.out.println(e);
            }
        
        // System.out.println("Enter the prescribed medicines and quantity, each new medicine separated by commas");
        // String prescription = obj.nextLine();
    }
    void displayAll(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/patients", "bgv", "Vaibhav@2002");
            Statement stmt = con.createStatement();
            ResultSet newrs = stmt.executeQuery("SELECT id,name FROM doctors;");
            while (newrs.next()) {
                System.out.print(newrs.getInt(1));
                System.out.println("  "+newrs.getString(2));
            }
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
