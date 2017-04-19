package week1Day2;

import java.util.Scanner;

public class SumofTwoNumbers {


	public void sum2Numbers(int n1, int n2){
		int total;
		total = n1+n2;
		System.out.println("Addition of two numbers is " + total);
	}

	public void mul2Numbers(int n1, int n2){
		int total;
		total = n1*n2;
		System.out.println("Multiply of two numbers is " + total);
	}

	public void div2Numbers(int n1, int n2)
	{
		int total;
		total=n1/n2;
		System.out.println("Division of two numbers is "+total);
		
	}
	public void sub2Numbers(int n1, int n2){
		int total;
		total = n1-n2;
		System.out.println("Subraction of two numbers is " + total);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a,b;
		Scanner numberInput = new Scanner(System.in);
		System.out.println("Enter the First Number : ");
		a=numberInput.nextInt();
		System.out.println("Enter the Second Number : ");
		b=numberInput.nextInt();
		SumofTwoNumbers sumnum1 = new SumofTwoNumbers();
		sumnum1.sum2Numbers(a,b);
		sumnum1.sub2Numbers(a,b);
		sumnum1.mul2Numbers(a,b);
		sumnum1.div2Numbers(a,b);

	}

}
