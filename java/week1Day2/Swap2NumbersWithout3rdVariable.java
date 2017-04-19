/*Swapping two numbers without using third variable*/

package week1Day2;

import java.util.Scanner;

public class Swap2NumbersWithout3rdVariable {

	public void swapNumbers(int num1,int num2){
		num1=num1+num2;
		num2=num1-num2;
		num1=num1-num2;
		System.out.println("After Swap First Number is " + num1);
		System.out.println("After Swap First Number is " + num2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a,b;

		System.out.println("Swapping two numbers without third variable");

		Scanner numInput = new Scanner(System.in);
		System.out.println("Enter a first Number : ");
		a=numInput.nextInt();
		System.out.println("Enter a second Number : ");
		b=numInput.nextInt();
		Swap2NumbersWithout3rdVariable swapnum = new Swap2NumbersWithout3rdVariable();
		swapnum.swapNumbers(a, b);

	}

}
