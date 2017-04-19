/*Prime number program*/

package week1Day1;
import java.util.Scanner;
public class Day1Homework3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int i,j,count=0;
		@SuppressWarnings("resource")
		Scanner a = new Scanner(System.in);
		System.out.print("Enter a Number : ");
        j = a.nextInt();
		 for(i=2; i<j; i++)
	        {
	            if(j%i == 0)
	            {
	                count++;
	                break;
	            }
	        }
	        if(count == 0)
	        {
	            System.out.print("This is a Prime Number");
	        }
	        else
	        {
	            System.out.print("This is not a Prime Number");
	        }
		
	}

}
