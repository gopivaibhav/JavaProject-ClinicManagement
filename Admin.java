import java.sql.*;  
public class Admin {
    private final String username;
    private final String password;
    Admin(){
        this.username = "admin";
        this.password = "123";
    }
    public boolean Login(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            System.out.println("Logging in...");
            return true;
        }
        else {
            System.out.println("Invalid username or password");
            return false;
        }
    }
    public void createRecord( String name, int age, String gender, String symptoms, int docId){
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/patients","bgv","Vaibhav@2002");  
            Statement stmt=con.createStatement(); 
            String query = "INSERT INTO patients(token, name, age, gender,symptoms) VALUES (?"+",'"+name+"',"+age+",'"+gender+"','"+symptoms+"')"; 
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet rs=stmt.executeQuery("SELECT token from patients;");  
            int t=0;
            while(rs.next())  
            t=rs.getInt(1);
            int token=t+1;
            pst.setInt(1, token);
            pst.executeUpdate();
            System.out.println("-----Patient Record-----");
            System.out.println("\n name: " + name + "\n token no :" + token);
            ResultSet newrs=stmt.executeQuery("SELECT tokens from doctors WHERE id="+docId+";");
            String tokenIds="";
            while(newrs.next()) {
                tokenIds=newrs.getString(1);
            }
            tokenIds+=","+token;
            String newquery="UPDATE doctors SET tokens='"+tokenIds+"' WHERE id="+docId+";";
            PreparedStatement newpst=con.prepareStatement(newquery);
            newpst.executeUpdate();
            con.close();  
        }catch(Exception e){ 
            System.out.println(e);
        }
    }
}
