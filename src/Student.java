import java.util.InputMismatchException;
import java.util.Scanner;

class Student{
    Scanner scan = new Scanner(System.in);
    String ID;
    String name;
    int age;
    String branch;
    String department;
    int roll;
    double cgpa;
    int year;
    String section;
    void enterDetails(){
        System.out.print("ENTER ID : ");
        try{
            ID = scan.nextLine();
        } catch(InputMismatchException e){
            System.out.println("Enter a valid ID");
        }
        System.out.print("ENTER NAME : ");
        try{
            name = scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Enter a valid name");
            scan.nextLine();
        }
        boolean isAgeValid = false;
        while(!isAgeValid){
            System.out.print("ENTER AGE : ");
            try{
                age = scan.nextInt();
                scan.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid age");
                scan.nextLine();
                continue;
            }
            isAgeValid = true;
        }
        System.out.print("ENTER BRANCH : ");
        try{
            branch = scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Enter a valid Branch");
            scan.nextLine();
        }
        System.out.print("ENTER SECTION : ");
        try{
            section = scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Enter a valid Section");
            scan.nextLine();
        }

        System.out.print("ENTER DEPARTMENT : ");
        try{
            department = scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Enter a valid Department");
            scan.nextLine();
        }
        boolean isRollValid = false;
        int entered_roll = 0;
        while(!isRollValid){
            System.out.print("ENTER ROLL NUMBER : ");
            try{
                roll = scan.nextInt();
                scan.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid roll number!");
                scan.nextLine();
                continue;
            }
            isRollValid = true;
        }

        boolean isYearValid = false;
        int entered_year = 0;
        while(!isYearValid){
            System.out.print("ENTER ACADEMIC YEAR : ");
            try{
                year = scan.nextInt();
                scan.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid academic year!");
                scan.nextLine();
                continue;
            }
            isYearValid = true;
        }

        boolean isCgpaValid = false;
        while(!isCgpaValid){
            System.out.print("ENTER cgpa : ");
            try{
                cgpa = scan.nextDouble();
                scan.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid CGPA");
                scan.nextLine();
                continue;
            }
            isCgpaValid = true;
        }
    }
}
