import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;
import java.util.concurrent.atomic.AtomicBoolean;

import com.sun.jdi.connect.Connector;
class StudentRecord{
    ArrayList<Student> Students = new ArrayList<>();
    Connection BaseConnect;
    public StudentRecord(Connection Connector) {
        this.BaseConnect = Connector;
    }

    boolean checkId(String Id){
        AtomicBoolean isAssigned = new AtomicBoolean(false);
        try(Statement query = BaseConnect.createStatement();){
            ResultSet table = query.executeQuery("select ID from StudentDetails");
            while(table.next()){
                String ID = table.getString(1);
                if(ID.equals(Id)){
                    isAssigned.set(true);
                    break;
                }
            }
        } catch(SQLException q){
            System.out.println("Column Selection Query Failed!!");
        }
        finally{
            return isAssigned.get();
        }
    }

    void addStudent(){
        Student S = new Student();
        S.enterDetails();
        if(!checkId(S.ID)){
            Students.add(S);
            String sql = "INSERT INTO StudentDetails(ID, ClassRoll, Name, Age, Year, Branch, Section, Department, CGPA) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement query = BaseConnect.prepareStatement(sql);){
                query.setString(1, S.ID);
                query.setInt(2, S.roll);
                query.setString(3, S.name);
                query.setInt(4, S.age);
                query.setInt(5, S.year);
                query.setString(6, S.branch);
                query.setString(7, S.section);
                query.setString(8, S.department);
                query.setDouble(9, S.cgpa);
                query.executeUpdate();
            } catch(SQLException q){
                System.out.println("Insertion Query Failed");
                q.printStackTrace();
            }
        }
        else{
            System.out.println("ID Already Assigned");
        }
    }

    void display(){
        if(Students.isEmpty()){
            System.out.println("No stduents avaliable");
            return;
        }
        for(Student student : Students){
            System.out.println("------------------------------------");
            System.out.println("NAME : " + student.name);
            System.out.println("AGE : " + student.age);
            System.out.println("BRANCH : " + student.branch);
            System.out.println("ROLL : " + student.roll);
            System.out.println("CGPA : " + student.cgpa);
            System.out.println("------------------------------------");
        }
    }

    void removeStudent(){
        int student_roll = 0;
        Scanner scan = new Scanner(System.in);
        System.out.print("ENTER ROLL NUMBER OF THE STUDENT : ");
        try{
            student_roll = scan.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Enter a valid ROLL NUMBER!!");
            return;
        }
        for(Student student : Students){
            if(student.roll == student_roll){
                Students.remove(student);
                return;
            }
        }
        System.out.println("Student Not Found");
    }

    void searchStudent(){
        int student_roll = 0;
        Scanner scan = new Scanner(System.in);
        System.out.print("ENTER ROLL NUMBER OF STUDENT : ");
        try{
            student_roll = scan.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Enter a valid ROLL NUMBER!!");
            scan.next();
            return;
        }
        for(Student student : Students){
            if(student_roll == student.roll){
                System.out.println("------------------------------------");
                System.out.println("NAME : " + student.name);
                System.out.println("AGE : " + student.age);
                System.out.println("Branch : " + student.branch);
                System.out.println("ROLL : " + student.roll);
                System.out.println("CGPA : " + student.cgpa);
                System.out.println("------------------------------------");
                return;
            }
        }
        System.out.println("No student found");
    }

    void update(){
        Scanner scan = new Scanner(System.in);
        System.out.print("ENTER ROLL NUMBER OF STUDENT : ");
        int student_roll = scan.nextInt();
        for(Student student : Students){
            if(student.roll == student_roll){
                int edit_choice = 0;
                while(edit_choice != 6){
                    System.out.println("------ EDIT ------");
                    System.out.println("1. NAME");
                    System.out.println("2. AGE");
                    System.out.println("3. BRANCH");
                    System.out.println("4. CGPA");
                    System.out.println("5. ROLL");
                    System.out.println("6. EXIT");
                    System.out.print("CHOOSE : ");
                    edit_choice = scan.nextInt();
                    scan.nextLine();
                    switch(edit_choice){
                        case 1:
                            System.out.println("Current Name : " + student.name);
                            System.out.print("ENTER NEW NAME : ");
                            student.name = scan.nextLine();
                            System.out.println("Updated Name : " + student.name);
                            System.out.println("NAME UPDATED SUCCESFULLY");
                            break;
                        case 2:
                            System.out.println("Current Age : " + student.age);
                            System.out.print("ENTER NEW AGE : ");
                            student.age = scan.nextInt();
                            scan.nextLine();
                            System.out.println("Updated Age : " + student.age);
                            System.out.println("AGE UPDATED SUCCESFULLY");
                            break;
                        case 3:
                            System.out.println("Current Branch : " + student.branch);
                            System.out.print("ENTER NEW BRANCH : ");
                            student.branch = scan.nextLine();
                            System.out.println("Updated Branch : " + student.branch);
                            System.out.println("Branch UPDATED SUCCESFULLY");
                            break;
                        case 4:
                            System.out.println("Current CGPA : " + student.cgpa);
                            System.out.print("ENTER NEW CGPA : ");
                            student.cgpa = scan.nextDouble();
                            scan.nextLine();
                            System.out.println("Updated CGPA : " + student.cgpa);
                            System.out.println("CGPA UPDATED SUCCESFULLY");
                            break;
                        case 5:
                            System.out.println("Current ROLL NUMBER : " + student.ID);
                            System.out.print("ENTER NEW ROLL NUMBER : ");
                            int new_roll = scan.nextInt();
                            scan.nextLine();
                            boolean roll_exists = false;
                            for(Student std : Students){
                                if(std.roll == new_roll){
                                    roll_exists = true;
                                    break;
                                }
                            }
                            if(!roll_exists){
                                student.roll = new_roll;
                                System.out.println("Updated ROLL NUMBER : " + student.ID);
                                System.out.println("ROLL NUMBER UPDATED SUCCESFULLY");
                                break;
                            }
                            else{
                                System.out.println("THE ENTERED ROLL NUMBER IS ALREADY ASSIGNED!!");
                                break;
                            }
                        case 6:
                            System.out.println("Student Details Edited Successfully");
                            break;
                        default:
                            System.out.println("Choose from 1/2/3/4/5/6");
                            break;
                    }
                }
                return;
            }
        }
        System.out.println("Student Not Found");
    }

}