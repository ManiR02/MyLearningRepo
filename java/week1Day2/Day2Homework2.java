/*  Write Fibonacci series from 1 to 100.  eg., 1 1 2 3 5 8 13.*/

package week1Day2;

public class Day2Homework2 {

	public static void main(String[] args) {

		int a=1,b=100,c=0,tot;
		System.out.println("Fibonacci series from 1 to 100");
		while (a<=b)
		{
			System.out.println(a);
			tot=c+a;
			c=a;
			a=tot;
		}

	}

}
