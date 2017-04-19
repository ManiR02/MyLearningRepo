/*Write a Program to verify the given input is Prime or not */ 

package week1Day2;

import java.util.Scanner;

public class Day2Homework1 {

	public static void main(String[] args) {
		int i,num,count=0;
		System.out.println("Enter a number : ");

		//Input from user
		Scanner numinput = new Scanner(System.in);
		num= numinput.nextInt();

		//For loop to find prime number
		for (i=2;i<num;i++)
		{
			if (num%i==0)
			{
				count++; 
				break;
			}
		}

		if (count== 0) //Remainder is zero
		{
			System.out.println("Given number " + num + " is a Prime");
		}
		else //Remainder is non zero
		{
			System.out.println("Given number " + num + " is not a Prime");
		}
	}
}