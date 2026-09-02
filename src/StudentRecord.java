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

    void update(){
//       ----------------------- UNDER PROCESS -----------------------
        String sql = "select * from StudentDetails where ID = ?";
        try(PreparedStatement query = BaseConnect.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)){
            Scanner scan = new Scanner(System.in);
            System.out.print("ENTER ID OF STUDENT : ");
            String student_ID = scan.nextLine();
            query.setString(1, student_ID);
            ResultSet table = query.executeQuery();
            if(table.next()){
                int edit_choice = 0;
                while(edit_choice != 10){
                    System.out.println("------ EDIT ------");
                    System.out.println("1. ID");
                    System.out.println("2. ROLL");
                    System.out.println("3. NAME");
                    System.out.println("4. AGE");
                    System.out.println("5. YEAR");
                    System.out.println("6. BRANCH");
                    System.out.println("7. SECTION");
                    System.out.println("8. DEPARTMENT");
                    System.out.println("9. CGPA");
                    System.out.println("10. EXIT");
                    System.out.print("CHOOSE : ");
                    edit_choice = scan.nextInt();
                    scan.nextLine();
                    switch(edit_choice){
                        case 1:
                            System.out.println("CURRENT ID : " + table.getString(1));
                            System.out.print("ENTER NEW ID : ");
                            String newID = scan.nextLine();
                            if(!checkId(newID)){
                                table.updateString(1, newID);
                            }
                            else{
                                System.out.println("ID Already Assigned!!");
                            }
                            break;

                        case 2:
                            System.out.println("CURRENT ROLL NUMBER : " + table.getInt(2));
                            System.out.print("ENTER NEW ROLL NUMBER : ");
                            int new_roll = scan.nextInt();
                            scan.nextLine();
                            if(!checkRoll(table.getInt(5), table.getString(6), table.getString(7), new_roll)){
                                table.updateInt(2, new_roll);
                                table.updateRow();
                            }
                            else{
                                System.out.println("ROLL NUMBER IS ALREADY ASSIGNED!!");
                            }
                            System.out.println("UPDATED ROLL : " + table.getInt(2));
                            System.out.println("ROLL UPDATED SUCCESFULLY");
                            break;


                        case 3:
                            System.out.println("CURRENT NAME : " + table.getString(3));
                            System.out.print("ENTER NEW NAME : ");
                            String name = scan.nextLine();
                            table.updateString(3, name);
                            table.updateRow();
                            System.out.println("UPDATED NAME : " + table.getString(3));
                            System.out.println("NAME UPDATED SUCCESFULLY");
                            break;


                        case 4:
                            System.out.println("CURRENT AGE : " + table.getInt(4));
                            System.out.print("ENTER NEW AGE : ");
                            int newAge = scan.nextInt();
                            scan.nextLine();
                            table.updateInt(4, newAge);
                            table.updateRow();
                            System.out.println("UPDATED AGE : " + table.getInt(4));
                            System.out.println("AGE UPDATED SUCCESFULLY");
                            break;


                        case 5:
                            System.out.println("CURRENT ACADEMIC YEAR : " + table.getInt(5));
                            System.out.print("ENTER NEW ACADEMIC YEAR : ");
                            int newYear = scan.nextInt();
                            scan.nextLine();
                            table.updateInt(5, newYear);
                            table.updateRow();
                            System.out.println("UPDATED ACADEMIC YEAR : " + table.getInt(5));
                            System.out.println("ACADEMIC YEAR UPDATED SUCCESFULLY");
                            break;

                        case 6:
                            System.out.println("CURRENT BRANCH : " + table.getString(6));
                            System.out.print("ENTER NEW BRANCH : ");
                            String newBranch = scan.nextLine();
                            table.updateString(6, newBranch);
                            table.updateRow();
                            System.out.println("UPDATED BRANCH : " + table.getString(6));
                            System.out.println("Branch UPDATED SUCCESFULLY");
                            break;

                        case 7:
                            System.out.println("CURRENT SECTION : " + table.getString(7));
                            System.out.print("ENTER NEW SECTION : ");
                            String newSection = scan.nextLine();
                            table.updateString(7, newSection);
                            table.updateRow();
                            System.out.println("UPDATED SECTION : " + table.getString(7));
                            System.out.println("SECTION UPDATED SUCCESSFULLY");
                            break;

                        case 8:
                            System.out.println("CURRENT DEPARTMENT : " + table.getString(8));
                            System.out.print("ENTER NEW DEPARTMENT : ");
                            String newDepartment = scan.nextLine();
                            table.updateString(8, newDepartment);
                            table.updateRow();
                            System.out.println("UPDATED DEPARTMENT : " + table.getString(8));
                            System.out.println("DEPARTMENT UPDATED SUCCESSFULLY");
                            break;

                        case 9:
                            System.out.println("CURRENT CGPA : " + table.getDouble(9));
                            System.out.print("ENTER NEW CGPA : ");
                            double newCGPA = scan.nextDouble();
                            scan.nextLine();
                            table.updateDouble(9, newCGPA);
                            table.updateRow();
                            System.out.println("UPDATED CGPA : " + table.getDouble(9));
                            System.out.println("CGPA UPDATED SUCCESFULLY");
                            break;

                        case 10:
                            System.out.println("Student Details Edited Successfully");
                            break;


                        default:
                            System.out.println("Choose from 1/2/3/4/5/6");
                            break;
                    }
                }
            }
            else{
                System.out.println("Student Not Found");
                return;
            }
        } catch (SQLException e){
            System.out.println("update -> Query Creation Failed");
            e.printStackTrace();
        }
    }
}