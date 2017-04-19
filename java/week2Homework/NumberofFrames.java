/*Week2 : Homework 5*/
/*layout.jquery-dev.com Number of Frames Test case through Wrapper Class*/

package week2Homework;

import org.junit.Test;

import wrappers.GenericWrappers;


public class NumberofFrames extends GenericWrappers{
@Test
	public void NumberofFrames1() throws InterruptedException {
		// TODO Auto-generated method stub
		invokeApp("chrome", "http://layout.jquery-dev.com/demos/iframes_many.html");
		getNumberofFrames("iframe"); //New method is included in Generic Wrappers
		closeBrowser();
	}

}
