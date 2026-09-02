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

        // DATABASE INTEGRATION DONE

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

    boolean checkRoll(int year, String branch, String section, int r){

        // DATABASE INTEGRATION DONE

        AtomicBoolean isAssigned = new AtomicBoolean(false);
        String sql = "select ClassRoll from StudentDetails where Section = ? and Year = ? and Branch = ?";
        try(PreparedStatement query = BaseConnect.prepareStatement(sql);){
            query.setString(1, section);
            query.setInt(2, year);
            query.setString(3, branch);
            ResultSet table = query.executeQuery(sql);
            while(table.next()){
                if(table.getInt(1) == r){
                    System.out.println("ROLL NUMBER ALREADY ASSIGNED!!");
                    isAssigned.set(true);
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

        // DATABASE INTEGRATION DONE

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

        // DATABASE INTEGRATION DONE

        try(Statement query = BaseConnect.createStatement();){
            ResultSet table = query.executeQuery("select * from StudentDetails");
            if(table.next()){
                do{
                    System.out.println("ID : " + table.getString(1));
                    System.out.println("ROLL : " + table.getInt(2));
                    System.out.println("NAME : " + table.getString(3));
                    System.out.println("AGE : " + table.getInt(4));
                    System.out.println("ACADEMIC YEAR : " + table.getInt(5));
                    System.out.println("BRANCH : " + table.getString(6));
                    System.out.println("SECTION : " + table.getString(7));
                    System.out.println("DEPARTMENT : " + table.getString(8));
                    System.out.println("CGPA : " + table.getDouble(9));
                } while(table.next());
            }
            else {
                System.out.println("No Record Found!!");
            }
        } catch(SQLException q){
            System.out.println("Display Query Failed!!");
        }
    }

    void removeStudent() {

        // DATABASE INTEGRATION DONE

        String sql = "DELETE FROM StudentDetails where ID = ?";
        try(PreparedStatement query = BaseConnect.prepareStatement(sql);){
            String student_ID = "";
            Scanner scan = new Scanner(System.in);
            System.out.print("ENTER ID OF THE STUDENT : ");
            student_ID = scan.nextLine();
            query.setString(1, student_ID);
            int action = query.executeUpdate();
            if(action > 0){
                System.out.println("Student Record Deleted Successfully");
            }
            else{
                System.out.println("Student Not Found!!");
            }
        } catch(SQLException q){
            System.out.println("removeStudent -> Query Creation Failed");
        }
    }

    void searchStudent(){

        // DATABASE INTEGRATION DONE

        String sql = "Select * from StudentDetails where ID = ?";
        try(PreparedStatement query = BaseConnect.prepareStatement(sql);){
            String student_ID = "";
            Scanner scan = new Scanner(System.in);
            System.out.print("ENTER ID OF STUDENT : ");
            student_ID = scan.nextLine();
            query.setString(1, student_ID);
            ResultSet table = query.executeQuery();
            if(table.next()){
                do{
                    System.out.println("ID : " + table.getString(1));
                    System.out.println("ROLL : " + table.getInt(2));
                    System.out.println("NAME : " + table.getString(3));
                    System.out.println("AGE : " + table.getInt(4));
                    System.out.println("ACADEMIC YEAR : " + table.getInt(5));
                    System.out.println("BRANCH : " + table.getString(6));
                    System.out.println("SECTION : " + table.getString(7));
                    System.out.println("DEPARTMENT : " + table.getString(8));
                    System.out.println("CGPA : " + table.getDouble(9));
                } while(table.next());
            }
            else{
                System.out.println("Student Record Not Found!!");
            }
        } catch(SQLException q){
            System.out.println("searchStudent -> Query Creation Failed");
        }
    }

//    void update(){
////       ----------------------- UNDER PROCESS -----------------------
//        String sql = "select * from StudentDetails where ID = ?";
//        try(PreparedStatement query = BaseConnect.prepareStatement(sql)){
//            Scanner scan = new Scanner(System.in);
//            System.out.print("ENTER ID OF STUDENT : ");
//            String student_ID = scan.nextLine();
//            query.setString(1, student_ID);
//            ResultSet table = query.executeQuery();
//                    int edit_choice = 0;
//                    while(edit_choice != 10){
//                        System.out.println("------ EDIT ------");
//                        System.out.println("1. ID");
//                        System.out.println("2. ROLL");
//                        System.out.println("3. NAME");
//                        System.out.println("4. AGE");
//                        System.out.println("5. YEAR");
//                        System.out.println("6. BRANCH");
//                        System.out.println("7. SECTION");
//                        System.out.println("8. DEPARTMENT");
//                        System.out.println("9. CGPA");
//                        System.out.println("10. EXIT");
//                        System.out.print("CHOOSE : ");
//                        edit_choice = scan.nextInt();
//                        scan.nextLine();
//                        switch(edit_choice){
//                            case 1:
//                                System.out.println("CURRENT ID : " + table.getString(1));
//                                System.out.println("ENTER NEW ID : ");
//                                String newID = scan.nextLine();
//                                if(!checkId(newID)){
//                                    table.updateString(1, newID);
//                                }
//                                else{
//                                    System.out.println("ID Already Assigned!!");
//                                }
//
//                            case 2:
//                                System.out.println("CURRENT ROLL NUMBER : " + table.getInt(2));
//                                System.out.print("ENTER NEW ROLL NUMBER : ");
//                                int new_roll = scan.nextInt();
//                                scan.nextLine();
//                                if(!checkRoll(table.getInt(5), table.getString(6), table.getString(7), new_roll)){
////                                    String sql = "";
////                                    PreparedStatement query = BaseConnect.prepareStatement(sql);
//                                }
//                                boolean roll_exists = false;
//                                for(Student std : Students){
//                                    if(std.roll == new_roll){
//                                        roll_exists = true;
//                                        break;
//                                    }
//                                }
//                                if(!roll_exists){
//                                    student.roll = new_roll;
//                                    System.out.println("Updated ROLL NUMBER : " + student.ID);
//                                    System.out.println("ROLL NUMBER UPDATED SUCCESFULLY");
//                                    break;
//                                }
//                                else{
//                                    System.out.println("THE ENTERED ROLL NUMBER IS ALREADY ASSIGNED!!");
//                                    break;
//                                }
//
//
//                            case 3:
//                                System.out.println("Current Name : " + table.getString());
//                                System.out.print("ENTER NEW NAME : ");
//                                student.name = scan.nextLine();
//                                System.out.println("Updated Name : " + student.name);
//                                System.out.println("NAME UPDATED SUCCESFULLY");
//                                break;
//                            case 4:
//                                System.out.println("Current Age : " + student.age);
//                                System.out.print("ENTER NEW AGE : ");
//                                student.age = scan.nextInt();
//                                scan.nextLine();
//                                System.out.println("Updated Age : " + student.age);
//                                System.out.println("AGE UPDATED SUCCESFULLY");
//                                break;
//                            case 3:
//                                System.out.println("Current Branch : " + student.branch);
//                                System.out.print("ENTER NEW BRANCH : ");
//                                student.branch = scan.nextLine();
//                                System.out.println("Updated Branch : " + student.branch);
//                                System.out.println("Branch UPDATED SUCCESFULLY");
//                                break;
//                            case 9:
//                                System.out.println("Current CGPA : " + student.cgpa);
//                                System.out.print("ENTER NEW CGPA : ");
//                                student.cgpa = scan.nextDouble();
//                                scan.nextLine();
//                                System.out.println("Updated CGPA : " + student.cgpa);
//                                System.out.println("CGPA UPDATED SUCCESFULLY");
//                                break;
//                            case 6:
//                                System.out.println("Student Details Edited Successfully");
//                                break;
//                            default:
//                                System.out.println("Choose from 1/2/3/4/5/6");
//                                break;
//                        }
//                    }
//                    return;
//                    System.out.println("Student Not Found");
//        } catch (SQLException e){
//            System.out.println("update -> Query Creation Failed");
//        }
//    }
}