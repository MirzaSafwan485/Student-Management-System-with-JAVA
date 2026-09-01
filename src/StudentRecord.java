import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class StudentRecord{
    ArrayList<Student> Students = new ArrayList<>();

    void addStudent(){
        Student S = new Student();
        S.enterDetails();
        for(Student student : Students){
            if(S.roll == student.roll){
                System.out.println("STUDENT ROLL NUMBER ALREADY EXISTS");
                return;
            }
        }
        Students.add(S);
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
            System.out.println("CLASS : " + student.clas);
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
            System.out.println("Enter a valid roll number!!");
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
            System.out.println("Enter a valid roll number!!");
            scan.next();
            return;
        }
        for(Student student : Students){
            if(student_roll == student.roll){
                System.out.println("------------------------------------");
                System.out.println("NAME : " + student.name);
                System.out.println("AGE : " + student.age);
                System.out.println("CLASS : " + student.clas);
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
        System.out.print("ENTER ROLL NO OF STUDENT : ");
        int student_roll = scan.nextInt();
        for(Student student : Students){
            if(student.roll == student_roll){
                int edit_choice = 0;
                while(edit_choice != 6){
                    System.out.println("------ EDIT ------");
                    System.out.println("1. NAME");
                    System.out.println("2. AGE");
                    System.out.println("3. CLASS");
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
                            System.out.println("Current Class : " + student.clas);
                            System.out.print("ENTER NEW CLASS : ");
                            student.clas = scan.nextLine();
                            System.out.println("Updated Class : " + student.clas);
                            System.out.println("CLASS UPDATED SUCCESFULLY");
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
                            System.out.println("Current Roll : " + student.roll);
                            System.out.print("ENTER NEW ROLL : ");
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
                                System.out.println("Updated Roll : " + student.roll);
                                System.out.println("ROLL UPDATED SUCCESFULLY");
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