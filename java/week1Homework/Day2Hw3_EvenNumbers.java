//Write a java program to print even numbers between 100 to 200
package week1Homework;

public class Day2Hw3_EvenNumbers {

	//Created new method
	public void evenNumbers()
	{
		int i;
		System.out.println("Even number between 100 to 200");
		for(i=100;i<=200;i++)
		{
			if (i%2==0)
			{
				System.out.println(i);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Day2Hw3_EvenNumbers evenCheck = new Day2Hw3_EvenNumbers(); //Object created for Class 
		evenCheck.evenNumbers(); //Invoked that method using object  

	}

}
