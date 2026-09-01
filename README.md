Multiple Equation Solver

Overview

A Java-based Multiple Equation Solver that solves systems of linearequations using matrix operations.
The project implements the mathematical logic manually rather thanrelying on a built-in matrix or linear-algebra library.

Features
Accepts systems of linear equations from the user.
Supports matrices of different sizes.
Calculates the determinant of a matrix.
Generates the cofactor matrix.
Calculates the adjoint matrix.
Calculates the inverse matrix.
Multiplies the inverse coefficient matrix with the result matrix.
Produces the values of the unknown variables.
Displays solutions with controlled decimal precision.

Method : 
For a system represented as:
AX = B
the solver follows the matrix-based approach:
X = A⁻¹B

The program obtains the inverse of the coefficient matrix throughdeterminant, cofactor, and adjoint calculations before multiplying it bythe result matrix.

Technologies Used

Java
ArrayList
Scanner
Recursion
Matrix operations
Object-Oriented Programming
Concepts Practiced
Classes and Objects
Methods
ArrayList<ArrayList<Double>>
Nested loops
Recursion
Matrix manipulation
Determinants
Cofactor matrices
Adjoint matrices
Matrix inversion
Matrix multiplication
Console-based input/output

How to Run
1. Clone the repository.
2. Open the project in a Java-supported IDE or terminal.
3. Compile the Java source file(s).
4. Run the main class.
5. Enter the size of the coefficient matrix.
6. Enter the coefficients and result values when prompted.
7 .The program calculates and displays the values of the unknown variables.

Example

For a system such as:

x + y + z = 6

2x + 3y + z = 11

x + 2y + 3z = 14

the program represents the system using:

Coefficient Matrix × Solution Matrix = Result Matrix
and solves it using the inverse-matrix method.

Project Purpose : 
This project was created to combine Java programming with linear algebraand to implement the complete matrix-based equation-solving process fromscratch.

Author

Mirza Safwan Baig
