import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;
import com.sun.jdi.connect.Connector;
public class Main {
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Students";
            String user = "root";
            String password = "Safwan@723926";
            try(Connection connect = DriverManager.getConnection(url, user, password);){
                Scanner scan = new Scanner(System.in);
                StudentRecord record = new StudentRecord(connect);
                int choice = 0;
                while(choice != 6){
                    System.out.println("---------- CHOOSE ----------");
                    System.out.println("1. ADD STUDENT");
                    System.out.println("2. SHOW STUDENTS");
                    System.out.println("3. REMOVE STUDENT");
                    System.out.println("4. SEARCH STUDENT");
                    System.out.println("5. EDIT STUDENT");
                    System.out.println("6. EXIT");
                    System.out.print("CHOOSE : ");
                    try{
                        choice = scan.nextInt();
                    }
                    catch(InputMismatchException e){
                        System.out.println("INVALID CHOICE ENTERED!! CHOOSE BETWEEN 1 to 6");
                        scan.nextLine();
                        continue;
                    }
                    switch(choice){
                        case 1:
                            record.addStudent();
                            break;
                        case 2:
                            record.display();
                            break;
                        case 3:
                            record.removeStudent();
                            break;
                        case 4:
                            record.searchStudent();
                            break;
                        case 5:
//                            record.update();
                            break;
                        case 6:
                            System.out.println("OPERATIONS SUCCESFULL");
                            break;
                        default:
                            System.out.println("INVALID CHOICE ENTERED!! CHOOSE BETWEEN 1 to 6");
                            break;
                    }
                }
            } catch(java.sql.SQLException e){
                System.out.println("Connection to DataBase Failed!!");
            }
        }
        catch(ClassNotFoundException e){
            System.out.println("Connection Failed!!");
        }
    }
}