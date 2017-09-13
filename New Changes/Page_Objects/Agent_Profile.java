package Page_Objects;

import java.io.File;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import utils.ReadExcel;

import wrappers.FunctionLibrary;

public class Agent_Profile extends FunctionLibrary{
	
public synchronized String Agent_Profile_Page(String locator){
	
	String multiLanguageExcelPath = System.getProperty("user.dir")+property.getProperty("resourceMgmtfilekeyPath");

	String languageName = property.getProperty("Language_Required");
	
	ReadExcel excel=new ReadExcel(multiLanguageExcelPath);
	
	String[] xpathsplittext = null;
	String resourceFileName = null;
	String filePathLocation = null;
	NodeList nodeList=null;
	
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();
		hs.put("menu_Dropdown", "id#dropdownMenu1");
		hs.put("Agent_Profile", "id#agentProfile");
		hs.put("Agent_Full_Name", "id#agentFullName");
		hs.put("Agent_User_Name", "id#agentUserName");
		hs.put("Phone", "id#agentPhone");
		hs.put("Email", "id#agentEMail");
		hs.put("Status", "id#agentStatus");
		hs.put("Last_Login", "xpath#.//*[@id='myProfileForm']//strong[contains(text(),'HomeResources>>LastLogin')]/../../div[2]");
		hs.put("Access_Level", "id#agentAccess");
		hs.put("Old_Password", "id#agentOldPassword");
		hs.put("New_Password", "id#agentNewPassword");
		hs.put("Retype_New_Password", "id#agentConfirmNewPassword");
		hs.put("Update", "id#btnSubmitChangePass");
		hs.put("Reset", "id#btnResetChangePass");
		hs.put("Alert_Message", "id#lblresult");
		hs.put("Change_Password_Message", "id#chgPwdMessage");
		hs.put("Upload_Profile_Image", "id#myProfileImg");
		hs.put("Remove_Profile_Image", "xpath#.//*[@id='btnRemoveProfileImg']/span[@class='glyphicon glyphicon-remove']");
		hs.put("Agent_Confirm_Password_Alert", "id#agentConfirmNewPassword-error");
		hs.put("Upload_Profile_Message", "xpath#//div[@id='myModel']//*//*//div[@class='modal-header']");
		hs.put("Close_Button", "xpath#//div[@class='modal-footer']//button[@id='ErrmsgClose']");
			
			String xpathValue = hs.get(locator);
			
			if(xpathValue.contains("text()=")){
			
				System.out.println("text()");	
					
				Pattern pattern = Pattern.compile("xpath(.*)text(.*)='([\\sa-zA-Z>]+)']");
				
				Matcher matcher = pattern.matcher(xpathValue);
				
				while(matcher.find()){
					
					String splittedXpath = matcher.group(3);					
					
					System.out.println("splittedXpath3 :"+splittedXpath);
					
					xpathsplittext = splittedXpath.split(">>");
					
					resourceFileName = xpathsplittext[0] + excel.RetrieveAutomationKeyFromExcel("Extension_Language", "Resx_Extension", languageName);
					
					filePathLocation=property.getProperty("resourcesFilePath_GBR");
					String resourceFileToGetValue=filePathLocation+"\\"+resourceFileName;
					log.info("File path is "+resourceFileToGetValue);
					File file=new File("//\\"+resourceFileToGetValue);

					String commonAttribute=property.getProperty("attributeCommonValue");
					String fullAttributewithName=commonAttribute+"[@name='"+ xpathsplittext[1] +"']/value";
					log.info("Attribute to Retrieve Node value is : "+fullAttributewithName);

					DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
					DocumentBuilder builder=factory.newDocumentBuilder();
					Document document=builder.parse(file);
					document.getDocumentElement().normalize();
					XPath xpath=XPathFactory.newInstance().newXPath();
					nodeList=(NodeList)xpath.compile(fullAttributewithName).evaluate(document,XPathConstants.NODESET);

					xpathValue =  xpathValue.replaceAll(splittedXpath, nodeList.item(0).getTextContent());
					
				
					} 
				
			} else if(xpathValue.contains("contains(text()")){
				
				System.out.println("contains(text()");
				
				Pattern pattern = Pattern.compile("(.*)contains(.*)text(.*),'(.*)'");
				
				Matcher matcher = pattern.matcher(xpathValue);
				
				while(matcher.find()){
					
					String splittedXpath = matcher.group(4);
					
					System.out.println("splittedXpath :"+splittedXpath);
					
					xpathsplittext = splittedXpath.split(">>");
					
					resourceFileName = xpathsplittext[0] + excel.RetrieveAutomationKeyFromExcel("Extension_Language", "Resx_Extension", languageName);
					
					filePathLocation=property.getProperty("resourcesFilePath_GBR");
					String resourceFileToGetValue=filePathLocation+"\\"+resourceFileName;
					log.info("File path is "+resourceFileToGetValue);
					File file=new File("//\\"+resourceFileToGetValue);

					String commonAttribute=property.getProperty("attributeCommonValue");
					String fullAttributewithName=commonAttribute+"[@name='"+ xpathsplittext[1] +"']/value";
					log.info("Attribute to Retrieve Node value is : "+fullAttributewithName);

					DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
					DocumentBuilder builder=factory.newDocumentBuilder();
					Document document=builder.parse(file);
					document.getDocumentElement().normalize();
					XPath xpath=XPathFactory.newInstance().newXPath();
					nodeList=(NodeList)xpath.compile(fullAttributewithName).evaluate(document,XPathConstants.NODESET);

					xpathValue = xpathValue.replaceAll(splittedXpath, nodeList.item(0).getTextContent());
					
		
					}
				
				}
		
		return xpathValue;

	}catch(Exception e){
		log.info("Error occurred in POM classes :"+e);
	}
	return null;
}

}
