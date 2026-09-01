import java.util.InputMismatchException;
import java.util.Scanner;

class Student{
    Scanner scan = new Scanner(System.in);
    String name;
    int age;
    String clas;
    int roll;
    double cgpa;
    void enterDetails(){
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
        System.out.print("ENTER CLASS : ");
        try{
            clas = scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Enter a valid Class");
            scan.nextLine();
        }
        boolean isRollValid = false;
        int entered_roll = 0;
        while(!isRollValid){
            System.out.print("ENTER ROLL : ");
            try{
                roll = scan.nextInt();
                scan.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid roll number");
                scan.nextLine();
                continue;
            }
            isRollValid = true;
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
