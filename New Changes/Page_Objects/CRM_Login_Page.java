package Page_Objects;

import java.util.Hashtable;


public class CRM_Login_Page{

	public synchronized String Login_Page(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();		
			hs.put("txtbox_UserName", "id#UserName");
			hs.put("txtbox_Password", "id#Password");
			hs.put("btn_LogIn", "id#Login");
			hs.put("Globe_Icon", "xpath#//span[@class='dropdown-toggle glyphicon glyphicon-globe']");
			hs.put("Language_List", "xpath#//ul[@class='dropdown-menu dropdown-menu-right']/li/a");
			hs.put("btn_LogIn", "id#Login");
			return hs.get(locator);
		}catch(Exception e){
			System.out.println("Error occurred in POM classes :"+e);
			return null;
		}

	}

}
