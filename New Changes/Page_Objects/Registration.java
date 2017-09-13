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

public class Registration extends FunctionLibrary {

	public synchronized String Registration_SWE_Page(String locator){
		
		String multiLanguageExcelPath = System.getProperty("user.dir")+property.getProperty("resourceMgmtfilekeyPath");

		String languageName = property.getProperty("Language_Required");
		
		ReadExcel excel=new ReadExcel(multiLanguageExcelPath);
		
		String[] xpathsplittext = null;
		String resourceFileName = null;
		String filePathLocation = null;
		NodeList nodeList=null;
		String Resxfilevalue = null;
		String[] SplitResxfilevalue = null;
		String finalresxfilevalue = null;
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();

		hs.put("Text_Parameter", "id#subsParameter");
		hs.put("Btn_Register", "id#btnNewRegister");
		hs.put("Alert_Message_Register", "id#spnPortinSubscriberMsg");
		hs.put("Btn_FindAddress", "xpath#//a[@id='btnFindAddress']//span[@class='glyphicon glyphicon-search']");
		hs.put("Alert_Message_Email", "id#Email-error");
		hs.put("Restricted_Email_Alert", "id#SWEErrorMsg");
		hs.put("Puk_Code_Alert", "id#PUKCode-error");
		hs.put("Btn_Next", "id#btnSWEnext");
		hs.put("Error_Message", "id#lblError");
		hs.put("Title", "id#SWEtitle");
		hs.put("PUK_Code", "id#PUKCode");
		hs.put("Text_Email", "id#Email");
		hs.put("First_Name", "id#FirstName");
		hs.put("Gender", "id#SWEGender");
		
		hs.put("Load_Type", "id#loadType");
		hs.put("Load_Parameter", "id#loadParameter");
		hs.put("Load_Subscriber", "id#btnLoadSubscriber");
		hs.put("Subscriber_View", "xpath#//div[@class='su-expand-icon sub-view']");
		hs.put("Edit_Registration", "id#editPreRegxn");		
		
		hs.put("lastName", "id#LastName");
		hs.put("DOB", "id#txtSWEDOB");
		hs.put("country", "id#Country");
		hs.put("city", "id#CityName");
		hs.put("state", "id#txtSWEState_1");
		hs.put("houseName", "id#txtSWEHouseName_1");
		hs.put("apartmentNo", "id#apartmentNo");
		hs.put("houseNo", "id#HouseNo");
		
		hs.put("street", "id#Street");
		hs.put("postCode", "id#PostCode");
		hs.put("landlineNo", "id#LandlineNo");
		hs.put("MSISDN", "id#Msisdn");
		hs.put("language", "id#SWELang");
		hs.put("aboutUS", "id#SWEaboutus");
		hs.put("chkSMS", "id#cbxMarketingSMS");
		hs.put("findAddress", "xpath#//a[@id='btnFindAddress']/span");
		hs.put("addressPopUp", "xpath#//div[@id='dvAddressList']/div");
		hs.put("selectAddress", "id#lstAddressResult");
		hs.put("acceptAddress", "xpath#//a[@id='btnAcceptAddress']/span");
		hs.put("resetAddress", "xpath#//a[@id='btnResetAddress']/span");
		
		
		//Confirmation message details
		hs.put("confirm_Title", "id#lblSWETitle");
		hs.put("confirm_FirstName", "id#lblSWEFirstName");
		hs.put("confirm_LastName", "id#lblSWELastName");
		hs.put("confirm_DOB", "id#lblSWEDOB");
		hs.put("confirm_Gender", "id#lblSWEGender");
		hs.put("confirm_Country", "id#lblSWECountry1");
		hs.put("confirm_City", "id#lblSWECity");
		hs.put("confirm_AppartmentNo", "id#lblSWEAppartmentNo");
		hs.put("confirm_HouseNo", "id#lblSWEHouseNo");
		hs.put("confirm_Street", "id#lblSWEStreet");
		hs.put("confirm_PostCode", "id#lblSWECode");
		
		hs.put("confirm_State", "id#lblSWEState");
		hs.put("confirm_HouseName", "id#lblSWEHouseName");
		
		hs.put("confirm_Email", "id#lblSWEEmail");
		hs.put("confirm_ContactNo", "id#lblSWEContactno");
		hs.put("confirm_PUK_Number", "id#lblSWEPuk");
		hs.put("confirm_MSISDN", "id#lblSWEMSISDN");
		hs.put("confirm_HearAb", "id#lblSWEAboutus");
		hs.put("confirm_Language", "id#lblSWEPrefLang");
		hs.put("confirmMsg", "xpath#//label[@id='btnCircleSWEReg' and contains(text(), 'success')]");
		hs.put("confirmPUKMsg", "xpath#//label[@id='btnCircleSWEReg' and contains(text(), 'Please check')]");
		hs.put("highlightValMsg", "xpath#//label[@id='btnCircleSWEReg' and contains(text(), 'Highlighted')]");
		hs.put("restrictIDMsg", "id#SWEErrorMsg");
		
		hs.put("submit", "id#btnSWEsubmit");
		hs.put("backButton", "id#btnSWEback");
		hs.put("cancelButton", "id#btnSWEcancel");
		
		//Validation message alert POM's
		
		hs.put("val_Title", "xpath#//select[@id='SWEtitle' and contains(@aria-required, 'true')]");
		hs.put("val_FirstName", "xpath#//input[@id='FirstName' and contains(@aria-required, 'true')]");
		hs.put("val_LastName", "xpath#//input[@id='LastName' and contains(@aria-required, 'true')]");
		hs.put("val_DOB", "xpath#//input[@id='txtSWEDOB' and contains(@aria-required, 'true')]");
		hs.put("val_Gender", "xpath#//select[@id='SWEGender' and contains(@aria-required, 'true')]");
		hs.put("val_City", "xpath#//input[@id='CityName' and contains(@aria-required, 'true')]");
		hs.put("val_Street", "xpath#//input[@id='Street' and contains(@aria-required, 'true')]");
		hs.put("val_PostCode", "xpath#//input[@id='PostCode' and contains(@aria-required, 'true')]");
		hs.put("val_HouseNo", "xpath#//input[@id='HouseNo' and contains(@aria-required, 'true')]");
		hs.put("val_State", "xpath#//input[@id='txtSWEState_1' and contains(@aria-required, 'true')]");
		hs.put("val_ContactNo", "xpath#//input[@id='LandlineNo' and contains(@aria-required, 'true')]");
		hs.put("val_PUK", "xpath#//input[@id='PUKCode' and contains(@aria-required, 'true')]");
		hs.put("val_Language", "xpath#//select[@id='SWELang' and contains(@aria-required, 'true')]");
		hs.put("val_HearAb", "xpath#//select[@id='SWEaboutus' and contains(@aria-required, 'true')]");
		
		hs.put("gafValCity", "xpath#//input[@id='CityName' and contains(@class, 'error')]");
		hs.put("gafValStreet", "xpath#//input[@id='Street' and contains(@class, 'error')]");
		hs.put("gafValPostCode", "xpath#//input[@id='PostCode' and contains(@class, 'error')]");
		
		//POM for View Registration SWE
		
		hs.put("view_Title", "xpath#//div[@class='form-group col-md-1 label1']");
		hs.put("view_FirstName", "xpath#//div[@class='form-group col-md-1 label1']/following-sibling::div[1]");
		hs.put("view_LastName", "xpath#//div[@class='form-group col-md-1 label1']/following-sibling::div[2]");
		hs.put("view_DOB", "xpath#//strong[contains(text(), 'RegistrationResources>>DateOfBirth')]/parent::label/parent::div/following-sibling::div");
		hs.put("view_Gender", "xpath#//strong[contains(text(), 'RegistrationResources>>Gender')]/parent::label/parent::div/following-sibling::div");
		hs.put("view_Country", "xpath#//strong[contains(text(), 'RegistrationResources>>Country')]/parent::label/parent::div/following-sibling::div");
		hs.put("view_AccNo", "xpath#//strong[contains(text(), 'RegistrationResources>>AccountNo')]/parent::label/parent::div/following-sibling::div");
		hs.put("view_Email", "xpath#//strong[text()='RegistrationResources>>EMail']/ancestor::div/following-sibling::div[@class='col-md-3 b-margin label1']");
		hs.put("view_Contact", "xpath#//strong[text()='RegistrationResources>>LandlineNumber']/ancestor::div/following-sibling::div[@class='col-md-3 label1']");
		hs.put("view_City", "xpath#//div[@class='col-md-2 col-md-offset-2 b-margin']");
		hs.put("view_AppNo", "xpath#(//div[@class='col-md-2 col-md-offset-2 b-margin label1'])[1]");
		hs.put("view_StreetAdd", "xpath#(//div[@class='col-md-3 col-md-offset-2 b-margin label1'])[1]");
		hs.put("view_PostCode", "xpath#(//div[@class='col-md-2 b-margin label1'])[1]");
		hs.put("view_HouseNo", "xpath#(//div[@class='col-md-2 col-md-offset-2 b-margin label1'])[2]");
		hs.put("view_State", "xpath#(//div[@class='col-md-3 col-md-offset-2 b-margin label1'])[2]");
		hs.put("view_MSISDN", "xpath#//strong[text()='RegistrationResources>>MSISDN']/ancestor::div/following-sibling::div[@class='col-md-3 b-margin label1']");
		hs.put("view_IMSI1", "xpath#(//strong[text()='RegistrationResources>>IMSI1']/ancestor::div/following-sibling::div[@class='col-md-3 label1'])[1]");
		hs.put("view_IMSI2", "xpath#(//strong[text()='RegistrationResources>>IMSI1']/ancestor::div/following-sibling::div[@class='col-md-3 label1'])[2]");
		hs.put("view_ICCID", "xpath#//strong[text()='RegistrationResources>>ICCID']/ancestor::div/following-sibling::div[@class='col-md-3 label1']");
		hs.put("view_Language", "xpath#(//strong[text()='RegistrationResources>>PreferredLanguage']/ancestor::div/following-sibling::div[@class='form-group col-md-3 label1'])[1]");
		hs.put("view_HearAbUs", "xpath#(//strong[text()='RegistrationResources>>PreferredLanguage']/ancestor::div/following-sibling::div[@class='form-group col-md-3 label1'])[2]");
		hs.put("view_PUKCode", "xpath#//strong[text()='RegistrationResources>>lblPUK']/ancestor::div/following-sibling::div[@class='col-md-3 label1']");
		hs.put("view_CancelButton", "xpath#(//div[@class='modal-content']//following-sibling::div[@class='modal-footer']//button)[1]");
		
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

				Resxfilevalue = nodeList.item(0).getTextContent();
				
				if(Resxfilevalue.contains("'")){
					SplitResxfilevalue = Resxfilevalue.split("'");
					
					for(int m = 1; m <= SplitResxfilevalue.length -1; m++){
						if(m < 2){
							finalresxfilevalue = SplitResxfilevalue[m-1] +"',\"'\",'"+ SplitResxfilevalue[m];
						} else{
							finalresxfilevalue = finalresxfilevalue + "',\"'\",'"+ SplitResxfilevalue[m];
						}
					}
					
					xpathValue =  xpathValue.replaceAll("'"+ splittedXpath +"'", "concat('"+ finalresxfilevalue +"')");
				} else{
					xpathValue = xpathValue.replaceAll(splittedXpath, Resxfilevalue);
				}
				
			
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

				Resxfilevalue = nodeList.item(0).getTextContent();
				
				if(Resxfilevalue.contains("'")){
					SplitResxfilevalue = Resxfilevalue.split("'");
					
					for(int m = 1; m <= SplitResxfilevalue.length -1; m++){
						if(m < 2){
							finalresxfilevalue = SplitResxfilevalue[m-1] +"',\"'\",'"+ SplitResxfilevalue[m];
						} else{
							finalresxfilevalue = finalresxfilevalue + "',\"'\",'"+ SplitResxfilevalue[m];
						}
					}
					
					xpathValue =  xpathValue.replaceAll("'"+ splittedXpath +"'", "concat('"+ finalresxfilevalue +"')");
				} else{
					xpathValue = xpathValue.replaceAll(splittedXpath, Resxfilevalue);
				}
				
	
				}
			
			} else if(xpathValue.contains("title=")) {
				
				if(xpathValue.contains(">>")){
			
				System.out.println("title()");	
					
				Pattern pattern = Pattern.compile("xpath(.*)title(.*)='([\\sa-zA-Z>]+)']");
				
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

					Resxfilevalue = nodeList.item(0).getTextContent();
					
					if(Resxfilevalue.contains("'")){
						SplitResxfilevalue = Resxfilevalue.split("'");
						
						for(int m = 1; m <= SplitResxfilevalue.length -1; m++){
							if(m < 2){
								finalresxfilevalue = SplitResxfilevalue[m-1] +"',\"'\",'"+ SplitResxfilevalue[m];
							} else{
								finalresxfilevalue = finalresxfilevalue + "',\"'\",'"+ SplitResxfilevalue[m];
							}
						}
						
						xpathValue =  xpathValue.replaceAll("'"+ splittedXpath +"'", "concat('"+ finalresxfilevalue +"')");
					} else{
						xpathValue = xpathValue.replaceAll(splittedXpath, Resxfilevalue);
					}

					} 
				
				}
			} else if(xpathValue.contains("normalize-space()=")){
				
				System.out.println("normalize-space()");	
					
				Pattern pattern = Pattern.compile("xpath(.*)text(.*).*=.*'([\\sa-zA-Z0-9_>]+)']");
				
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

					Resxfilevalue = nodeList.item(0).getTextContent();
					
					if(Resxfilevalue.contains("'")){
						SplitResxfilevalue = Resxfilevalue.split("'");
						
						for(int m = 1; m <= SplitResxfilevalue.length -1; m++){
							if(m < 2){
								finalresxfilevalue = SplitResxfilevalue[m-1] +"',\"'\",'"+ SplitResxfilevalue[m];
							} else{
								finalresxfilevalue = finalresxfilevalue + "',\"'\",'"+ SplitResxfilevalue[m];
							}
						}
						
						xpathValue =  xpathValue.replaceAll("'"+ splittedXpath +"'", "concat('"+ finalresxfilevalue +"')");
					} else{
						xpathValue = xpathValue.replaceAll(splittedXpath, Resxfilevalue);
					}
					
				
					} 
				
			}
	
	return xpathValue;
		
		}catch(Exception e){
			
			System.out.println("Error occurred in POM classes :"+e);
			
		}
		return null;
	}
	
	public synchronized String Registration_AUT_Page(String locator){
		
		String multiLanguageExcelPath = System.getProperty("user.dir")+property.getProperty("resourceMgmtfilekeyPath");

		String languageName = property.getProperty("Language_Required");
		
		ReadExcel excel=new ReadExcel(multiLanguageExcelPath);
		
		String[] xpathsplittext = null;
		String resourceFileName = null;
		String filePathLocation = null;
		NodeList nodeList=null;
		String Resxfilevalue = null;
		String[] SplitResxfilevalue = null;
		String finalresxfilevalue = null;
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();

		hs.put("Text_Parameter", "id#subsParameter");
		hs.put("Btn_Register", "id#btnNewRegister");
		hs.put("Alert_Message_Register", "id#spnPortinSubscriberMsg");
		hs.put("Btn_FindAddress", "xpath#//a[@id='btnFindAddress']//span[@class='glyphicon glyphicon-search']");
		hs.put("Alert_Message_Email", "id#txtEmail1-error");
		hs.put("Restricted_Email_Alert", "id#restrictErrorMsg");
		hs.put("Btn_Next", "id#Next");
		hs.put("Error_Message", "id#lblError");
		hs.put("Title", "id#ddlTitle");
		hs.put("First_Name", "id#txtfirstName");
		hs.put("Last_Name", "id#txtlastName");
		hs.put("Academic_Title", "id#Academic_Title");
		hs.put("Date_Of_Birth", "id#txtdateofbirth");
		hs.put("Country", "id#ddCountry");
		hs.put("State", "id#txtState_1");
		hs.put("Post_Code", "id#txtPostCode_1");
		hs.put("City", "id#txtCity_1");
		hs.put("Street", "id#txtStreet_1");
		hs.put("House_Number", "id#House_Number");
		hs.put("House_Name", "id#House_Name");
		hs.put("Apartment_Number", "id#ApartmentNo");
		hs.put("Text_Email", "id#txtEmail1");
		hs.put("Contact_Number", "id#txtContactNumber_1");
		hs.put("Qualification", "id#Qualification");
		hs.put("Occupation", "id#Occupation");
		hs.put("MSISDN", "id#MSISDN_Number");
		hs.put("PUK_Code", "id#PUK_Number");
		hs.put("Country_Call_Most", "id#call_most");
		hs.put("Hear_About_Us", "id#hear_about");
		hs.put("Secret_Question", "id#Secret_Question");
		hs.put("Secret_Answer", "id#Secret_Answer");
		hs.put("IVR_Language", "id#IVR_Language");
		hs.put("Phone_Book", "id#Phone_Book");
		hs.put("Terms_Conditions", "id#Terms_Conditions");
		hs.put("Marketing_SMS", "id#cbxMarketingSMS");
		
		hs.put("list_City", "id#ddCity");
		hs.put("list_Street", "id#ddStreet");
		hs.put("GAF_House_Number", "id#ddHouseno");
		hs.put("GAF_Country", "id#txtCountry_1");
		
		hs.put("Confirm_Title", "id#step2Title");
		hs.put("Confirm_First_Name", "id#step2FirstName");
		hs.put("Confirm_Last_Name", "id#step2LastName");
		hs.put("Confirm_Academic_Title", "id#step2Academic_Title");
		hs.put("Confirm_Date_Of_Birth", "id#step2dob");
		hs.put("Confirm_Country", "id#step2txtCountry_1");
		hs.put("Confirm_State", "id#step2ddlState");
		hs.put("Confirm_Post_Code", "id#step2txtPostCode_1");
		hs.put("Confirm_City", "id#step2txtCity_1");
		hs.put("Confirm_Street", "id#step2txtStreet_1");
		hs.put("Confirm_House_Number", "id#step2House_Number");
		hs.put("Confirm_House_Name", "id#step2House_Name");
		hs.put("Confirm_Apartment_Number", "id#step2ApartmentNo");
		hs.put("Confirm_Email", "id#step2txtEmail1");
		hs.put("Confirm_Contact_Number", "id#step2txtContactNumber_1");
		hs.put("Confirm_Qualification", "id#step2Qualification");
		hs.put("Confirm_Occupation", "id#step2occupy");
		hs.put("Confirm_MSISDN", "id#step2MSISDN_Number");
		hs.put("Confirm_PUK_Code", "id#step2PUK_Number");
		hs.put("Confirm_Call_Country_Most", "id#step2call_most");
		hs.put("Confirm_Hear_About_Us", "id#step2hear_about");
		hs.put("Confirm_Secret_Question", "id#step2Secret_Question");
		hs.put("Confirm_Secret_Answer", "id#step2Secret_Answer");
		hs.put("Confirm_IVR_Language", "id#step2IVR_Language");
		hs.put("Confirm_Phone_Book", "id#step2Phone_Book");
		hs.put("Btn_Back", "id#Back");
		hs.put("Btn_Submit", "id#Submit");
		hs.put("Loading_Image", "xpath#.//label[@id='spinIcon'] and contains(@class, 'glyphicon-rotate')]");
		hs.put("Confirm_Message", "id#step1error");
		
		hs.put("Load_Type", "id#loadType");
		hs.put("Load_Parameter", "id#loadParameter");
		hs.put("Load_Subscriber", "id#btnLoadSubscriber");
		hs.put("Subscriber_View", "xpath#//div[@class='su-expand-icon sub-view']");
		
		// View Registration
		hs.put("View_Registration", "id#viewPreRegxn");
		
		hs.put("View_Puk_Code", "xpath#//label[contains(text(),'RegistrationResources>>lblPUK')]/parent::div/parent::div/div[4]");
		hs.put("View_IMSI_1", "xpath#//label[contains(text(),'RegistrationResources>>IMSI1')]/parent::div/parent::div/div[2]");
		hs.put("View_IMSI_2", "xpath#//label[contains(text(),'RegistrationResources>>IMSI2')]/parent::div/parent::div/div[4]");
		hs.put("View_MSISDN", "xpath#//label[text()[normalize-space() = 'RegistrationResources>>LycaMobile']]/parent::div/parent::div/div[2]");
		hs.put("View_ICCID", "xpath#//label[text()[normalize-space() = 'RegistrationResources>>ICCID']]/parent::div/parent::div/div[2]");
		
		hs.put("View_Title", "xpath#//label[contains(text(),'RegistrationResources>>Title')]/parent::div/parent::div/div[2]");
		hs.put("View_First_Name", "xpath#//label[contains(text(),'RegistrationResources>>Title')]/parent::div/parent::div/div[3]");
		hs.put("View_Last_Name", "xpath#//label[contains(text(),'RegistrationResources>>Title')]/parent::div/parent::div/div[4]");
		hs.put("View_Academic_Title", "xpath#//label[contains(text(),'RegistrationResources>>Academic')]/parent::div/parent::div/div[2]");
		hs.put("View_Date_Of_Birth", "xpath#//label[contains(text(),'RegistrationResources>>DateOfBirth')]/parent::div/parent::div/div[2]");
		hs.put("View_Contact_Number", "xpath#//label[contains(text(),'RegistrationResources>>ContactNumber')]/parent::div/parent::div/div[4]");
		hs.put("View_Email", "xpath#//label[contains(text(),'RegistrationResources>>EMail')]/parent::div/parent::div/div[2]");
		hs.put("View_Account_Number", "xpath#//label[contains(text(),'RegistrationResources>>AccountNo')]/parent::div/parent::div/div[2]");
		hs.put("View_Country", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[7]/div[1]");
		hs.put("View_State", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[8]/div[1]");
		hs.put("View_Post_Code", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[8]/div[2]");
		hs.put("View_City", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[9]/div[1]");
		hs.put("View_Street", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[9]/div[2]");
		hs.put("View_House_Number", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[10]/div[1]");
		hs.put("View_Apartment_Number", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[12]/div[1]");
		hs.put("View_Qualification", "xpath#//label[contains(text(),'RegistrationResources>>Qualification')]/parent::div/parent::div/div[2]");
		hs.put("View_Occupation", "xpath#//label[contains(text(),'RegistrationResources>>Occupation')]/parent::div/parent::div/div[4]");
		hs.put("View_Country_Call_Most", "xpath#//label[contains(text(),'RegistrationResources>>Call_most')]/parent::div/parent::div/div[2]");
		hs.put("View_Hear_About_Us", "xpath#//label[contains(text(),'RegistrationResources>>Hearaboutus')]/parent::div/parent::div/div[4]");
		hs.put("View_Secret_Question", "xpath#//label[contains(text(),'RegistrationResources>>Secret_Question')]/parent::div/parent::div/div[2]");
		hs.put("View_Secret_Answer", "xpath#//label[contains(text(),'RegistrationResources>>Secret_Answer')]/parent::div/parent::div/div[4]");
		hs.put("View_IVR_Language", "xpath#//label[contains(text(),'RegistrationResources>>IVRLanguage')]/parent::div/parent::div/div[2]");
		hs.put("View_Phone_Book", "xpath#//label[contains(text(),'RegistrationResources>>Phone_Book')]/parent::div/parent::div/div[4]");
		hs.put("View_SMS_Marketing", "id#cbxMarketingSMS");
		hs.put("View_Cancel", "xpath#.//*[@class='modal-footer']/button[@data-dismiss='modal' and contains(@class, 'crm-btn')]");
		hs.put("Loading_Image_Subscriber_View", "xpath#.//*[@class='spnRegDiv glyphicon-rotate']");
		hs.put("View_Edit_Reg_CloseBtn", "xpath#//div[@class='close-icon close-view-icon']");
		
		// Edit Registration
		hs.put("Edit_Registration", "id#editPreRegxn");
		hs.put("Edit_Cancel", "id#Cancel");
		hs.put("Edit_Account_Number", "id#txtAccountNo");
		hs.put("Edit_MSISDN", "id#MSISDN_Number");
		hs.put("Edit_PUK_Code", "id#PUK_Number");
		hs.put("Edit_IMSI_1", "id#IMSI1");
		hs.put("Edit_IMSI_2", "id#IMS2");
		hs.put("Edit_ICCID", "id#ICCID");
		
		hs.put("close_button", "xpath#//div[@class='close-icon close-view-icon']");
		
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

				Resxfilevalue = nodeList.item(0).getTextContent();
				
				if(Resxfilevalue.contains("'")){
					SplitResxfilevalue = Resxfilevalue.split("'");
					
					for(int m = 1; m <= SplitResxfilevalue.length -1; m++){
						if(m < 2){
							finalresxfilevalue = SplitResxfilevalue[m-1] +"',\"'\",'"+ SplitResxfilevalue[m];
						} else{
							finalresxfilevalue = finalresxfilevalue + "',\"'\",'"+ SplitResxfilevalue[m];
						}
					}
					
					xpathValue =  xpathValue.replaceAll("'"+ splittedXpath +"'", "concat('"+ finalresxfilevalue +"')");
				} else{
					xpathValue = xpathValue.replaceAll(splittedXpath, Resxfilevalue);
				}
				
			
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

				Resxfilevalue = nodeList.item(0).getTextContent();
				
				if(Resxfilevalue.contains("'")){
					SplitResxfilevalue = Resxfilevalue.split("'");
					
					for(int m = 1; m <= SplitResxfilevalue.length -1; m++){
						if(m < 2){
							finalresxfilevalue = SplitResxfilevalue[m-1] +"',\"'\",'"+ SplitResxfilevalue[m];
						} else{
							finalresxfilevalue = finalresxfilevalue + "',\"'\",'"+ SplitResxfilevalue[m];
						}
					}
					
					xpathValue =  xpathValue.replaceAll("'"+ splittedXpath +"'", "concat('"+ finalresxfilevalue +"')");
				} else{
					xpathValue = xpathValue.replaceAll(splittedXpath, Resxfilevalue);
				}
				
	
				}
			
			} else if(xpathValue.contains("title=")) {
				
				if(xpathValue.contains(">>")){
			
				System.out.println("title()");	
					
				Pattern pattern = Pattern.compile("xpath(.*)title(.*)='([\\sa-zA-Z>]+)']");
				
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

					Resxfilevalue = nodeList.item(0).getTextContent();
					
					if(Resxfilevalue.contains("'")){
						SplitResxfilevalue = Resxfilevalue.split("'");
						
						for(int m = 1; m <= SplitResxfilevalue.length -1; m++){
							if(m < 2){
								finalresxfilevalue = SplitResxfilevalue[m-1] +"',\"'\",'"+ SplitResxfilevalue[m];
							} else{
								finalresxfilevalue = finalresxfilevalue + "',\"'\",'"+ SplitResxfilevalue[m];
							}
						}
						
						xpathValue =  xpathValue.replaceAll("'"+ splittedXpath +"'", "concat('"+ finalresxfilevalue +"')");
					} else{
						xpathValue = xpathValue.replaceAll(splittedXpath, Resxfilevalue);
					}

					} 
				
				}
			} else if(xpathValue.contains("normalize-space()")){
				
				System.out.println("normalize-space()");	
					
				Pattern pattern = Pattern.compile("xpath(.*)text(.*).*=.*'([\\sa-zA-Z0-9_>]+)']");
				
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

					Resxfilevalue = nodeList.item(0).getTextContent();
					
					if(Resxfilevalue.contains("'")){
						SplitResxfilevalue = Resxfilevalue.split("'");
						
						for(int m = 1; m <= SplitResxfilevalue.length -1; m++){
							if(m < 2){
								finalresxfilevalue = SplitResxfilevalue[m-1] +"',\"'\",'"+ SplitResxfilevalue[m];
							} else{
								finalresxfilevalue = finalresxfilevalue + "',\"'\",'"+ SplitResxfilevalue[m];
							}
						}
						
						xpathValue =  xpathValue.replaceAll("'"+ splittedXpath +"'", "concat('"+ finalresxfilevalue +"')");
					} else{
						xpathValue = xpathValue.replaceAll(splittedXpath, Resxfilevalue);
					}
					
				
					} 
				
			}
	
		return xpathValue;
		
		}catch(Exception e){
			
			System.out.println("Error occurred in POM classes :"+e);
			
		}
		return null;
	}

	
	public synchronized String Registration_MKD_Page(String locator){
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();

		hs.put("newRegBtn", "id#btnNewRegister");
		hs.put("selectPrepaid", "id#ddlPrePost");
		hs.put("mobileRadBtn", "id#rdmobileno");
		hs.put("pukRadBtn", "id#rdPukcode");
		hs.put("txtBoxICCID", "id#txtmkdiccid");
		hs.put("mobileNo", "id#txtmkdmobileno");
		hs.put("pukCodeTxt", "id#txtmkdpukcode");
		hs.put("selSecretQues", "id#ddlMKDSQ");
		hs.put("secretAns", "id#txtmkdsecretnswer");
		hs.put("indivRadBtn", "id#rdindividual");
		hs.put("compRadBtn", "id#rdCompany");
		hs.put("selTitle", "id#ddMKDtitle");
		hs.put("firstName", "id#txtfirstName");
		hs.put("lastName", "id#txtlastName");
		hs.put("DOB", "id#txtmkdindividualdob");
		hs.put("DOBCompany", "id#txtmkdcompanydob");
		hs.put("DOBErrorMsg", "id#txtmkdindividualdob-error");
		hs.put("DOBCompanyErrorMsg", "id#txtmkdcompanydob-error");
		hs.put("txtEMBG", "id#txtmkdEMBG");
		hs.put("txtEMBGCompany", "id#txtmkdcompanyEDB");
		hs.put("selProfession", "id#ddlMKDProfession");
		hs.put("selProfCompany", "id#ddlMKDCompanyProfession");
		hs.put("contact", "id#txtmkdindividualcontact");
		hs.put("contactCompany", "id#txtmkdcompanycontact");
		hs.put("email", "id#txtmkdindividualemail");
		hs.put("confirmEmail", "id#txtmkdindividualconfirmemail");
		hs.put("emailCompany", "id#txtmkdcompanymail");
		hs.put("confirmEmailCompany", "id#txtmkdcompanyconfirmmail");
		hs.put("selCity", "id#ddlmkdcity");
		hs.put("selMunicipality", "id#ddlmkdmunicipality");
		hs.put("selPlace", "id#ddlmkdplace");
		hs.put("addressTxt", "id#txtmkdaddress");
		hs.put("postCode", "id#txtmkdpostcode");
		hs.put("selLanguage", "id#ddlMKDLanguage");
		hs.put("hearAbUs", "id#ddlMKDhearabt");
		hs.put("mostCallCountry", "id#ddlMKDcall");
		hs.put("teleDirNone", "id#rdNone");
		hs.put("teleDirDA", "id#rdDA");
		hs.put("teleDirNE", "id#rdNE");
		hs.put("termsCond", "id#chkterms");
		hs.put("referFriend", "id#chkreferfriend");
		hs.put("referFrndNumber", "id#txtmkdfrndno");
		
		hs.put("companyName", "id#txtmkdcompanyname");
		hs.put("selectFunction", "id#ddlMKDfunction");
		
		hs.put("confirmRegType", "id#lblmkdregistrationtype");
		hs.put("confirmICCID", "id#lblmkdiccid");
		hs.put("confirmMobNo", "id#lblvalmkdmblpuk");
		hs.put("confirmSecQues", "id#lblmkdsecques");
		hs.put("confirmSecAns", "id#lblmkdsecans");
		hs.put("confirmTOC", "id#lblmkdindividualcompany");
		hs.put("confirmTitle", "id#lblmkdtitle");
		hs.put("confirmFirst", "id#lblmkdfirstname");
		hs.put("confirmLast", "id#lblmkdlastname");
		hs.put("confirmDOB", "id#lblmkddob");
		hs.put("confirmProf", "id#lblmkdprofession");
		hs.put("confirmEMBG", "id#lblmkdembg");
		hs.put("confirmContact", "id#lblmkdcontact");
		hs.put("confirmEmail1", "id#lblmkdEmail");
		hs.put("confirmEmail2", "id#lblmkdconfirmEmail");
		hs.put("confirmCity", "id#lblmkdcity");
		hs.put("confirmMunic", "id#lblmkdmunicipality");
		hs.put("confirmPlace", "id#lblmkdplace");
		hs.put("confirmAddress", "id#lblmkdaddress");
		hs.put("confirmPost", "id#lblmkdpostcode");
		hs.put("confirmLang", "id#lblmkdlanguage");
		hs.put("confirmHearAb", "id#lblmkdhereaboutus");
		hs.put("confirmCtryCall", "id#lblmkdcountrycall");
		hs.put("confirmTelDir", "id#lblmkdtelephnedirect");
		hs.put("confirmTermsCond", "id#chklblterms");
		hs.put("confirmReferFriend", "id#chklblreferfriend");
		hs.put("confirmFriendNo", "id#lblmkdreferfriend");
		hs.put("confirmCompany", "id#lblcompanyname");
		hs.put("confirmFunction", "id#lblfunction");
		
		hs.put("nxtButton", "id#btnMKDNext");
		hs.put("submit", "id#btnMKDSubmit");
		hs.put("editSubmit", "id#btnMKDUpdate");
		hs.put("confirmMsg", "xpath#//label[@id='btnCircleMKD' and contains(@class, 'true')]");
		hs.put("validationMsg", "xpath#//label[@id='btnCircleMKD' and contains(@class, 'false')]");
		
		//View Registration
		hs.put("expand_RegIcon", "xpath#//div[@class='su-expand-icon sub-view']");
		hs.put("view_RegBtn", "id#viewPreRegxn");
		
		//Edit Registration
		hs.put("edit_RegBtn", "id#editPreRegxn");
		
		hs.put("viewRegType", "id#lblmkdregistrationtype");
		hs.put("viewICCID", "id#lblmkdiccid");
		hs.put("viewMobilePUKCode", "id#lblvalmkdmblpuk");
		hs.put("viewSecQues", "id#lblmkdsecques");
		hs.put("viewSecAns", "id#lblmkdsecans");
		hs.put("viewIndivComp", "id#lblmkdindividualcompany");
		hs.put("viewTitle", "id#lblmkdtitle");
		hs.put("viewFirstName", "id#lblmkdfirstname");
		hs.put("viewLastName", "id#lblmkdlastname");
		hs.put("viewDOB", "id#lblmkddob");
		hs.put("viewEMBQ", "id#lblmkdembg");
		hs.put("viewProfession", "id#lblmkdprofession");
		hs.put("viewContact", "id#lblmkdcontact");
		hs.put("viewEmail1", "id#lblmkdEmail");
		hs.put("viewEmail2", "id#lblmkdconfirmEmail");
		hs.put("viewCity", "id#lblmkdcity");
		hs.put("viewMunicipality", "id#lblmkdmunicipality");
		hs.put("viewPlace", "id#lblmkdplace");
		hs.put("viewAddress", "id#lblmkdaddress");
		hs.put("viewPostcode", "id#lblmkdpostcode");
		hs.put("viewLanguage", "id#lblmkdlanguage");
		hs.put("viewHearAbUs", "id#lblmkdhereaboutus");
		hs.put("viewCountryCall", "id#lblmkdcountrycall");
		hs.put("viewTeleDir", "id#lblmkdtelephnedirect");
		hs.put("viewReferFriend", "id#chklblreferfriend");
		hs.put("viewTFriendMSISDN", "id#lblmkdreferfriend");
		hs.put("viewTermsCond", "id#chklblterms");
		hs.put("viewCompany", "id#lblcompanyname");
		hs.put("viewFunction", "id#lblfunction");
		
		hs.put("view_CancelButton", "xpath#(//div[@class='modal-content']//following-sibling::div[@class='modal-footer']//button)[1]");
		
		//Disable Fields
		hs.put("disableIndiv", "css#input[disabled=''][id='rdindividual']");
		hs.put("disableCompType", "css#input[disabled=''][id='rdCompany']");
		hs.put("disableTitle", "css#select[disabled=''][id='ddMKDtitle']");
		hs.put("disableFirstName", "css#input[disabled=''][id='txtfirstName']");
		hs.put("disableLastName", "css#input[disabled=''][id='txtlastName']");
		hs.put("disableInDOB", "css#input[disabled=''][id='txtmkdindividualdob']");
		hs.put("disableCompanyDOB", "css#input[disabled=''][id='txtmkdcompanydob']");
		hs.put("disableCity", "css#select[disabled=''][id='ddlmkdcity']");
		hs.put("disableMunicipility", "css#select[disabled=''][id='ddlmkdmunicipality']");
		hs.put("disablePlace", "css#select[disabled=''][id='ddlmkdplace']");
		hs.put("disableAddress", "css#input[disabled=''][id='txtmkdaddress']");
		hs.put("disablePostCode", "css#input[disabled=''][id='txtmkdpostcode']");
		
		return hs.get(locator);
		
		}catch(Exception e){
			
			System.out.println("Error occurred in POM classes :"+e);
			
		}
		return null;
	}
	
	
	
	public synchronized String Registration_SWI_Page(String locator){
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();

		
		hs.put("title", "id#ddtitle");
		hs.put("firstName", "id#CustomerDetailSWI_Firstname");
		hs.put("lastName", "id#CustomerDetailSWI_Lastname");
		hs.put("careOf", "id#CustomerDetailSWI_CareOf");
		hs.put("DOB", "id#CustomerDetailSWI_Dateofbirth");
		hs.put("genderDD", "id#ddgender");
		hs.put("residentYes", "id#Yes");
		hs.put("residentNo", "id#No");
		hs.put("street", "id#txtStreet");
		hs.put("houseNo", "id#txtHouseno");
		hs.put("postCode", "id#txtCode");
		hs.put("findAddress", "xpath#//a[@id='btnFindAddress']/span");
		hs.put("findAddressNotVisible", "xpath#//a[@id='btnFindAddress' and not (contains(@style,'none'))]//span");
		hs.put("resetAddress", "xpath#//a[@id='btnResetAddress']/span");
		hs.put("cityDD", "id#ddCity");
		hs.put("cityTextBox", "id#txtCity");
		hs.put("country", "id#CustomerDetailSWI_Country");
		hs.put("nationalityDD", "id#ddNationality");
		hs.put("email", "id#CustomerDetailSWI_Email");
		hs.put("confirmEmail", "id#CustomerDetailSWI_ConformEmail");
		hs.put("languageDD", "id#ddLanguage");
		hs.put("privateNo", "id#CustomerDetailSWI_Privatephonenumber");
		hs.put("businessNo", "id#CustomerDetailSWI_BusPhoneNumber");
		hs.put("faxNo", "id#CustomerDetailSWI_FaxNumber");
		hs.put("msisdnRadBtn", "xpath#(//input[@class='mobile-number'])[1]");
		hs.put("pukCodeRadBtn", "xpath#(//input[@class='puk-code'])[1]");
		hs.put("msisdnTxtBox", "xpath#//input[@id='CustomerDetailSWI_Msisdn' and contains(@placeholder, 'MSISDN')]");
		hs.put("pukCodeTxtBox", "id#CustomerDetailSWI_Pukcode");
		hs.put("simNo", "id#Sim15digit");
		hs.put("4DigitSim", "xpath#//input[@id='CustomerDetailSWI_Simnumber' and contains(@placeholder, '4')]");
		hs.put("idDD", "id#ddIDType");
		hs.put("idNoTxtBox", "id#CustomerDetailSWI_IdNumber");
		hs.put("nationalityID_DD", "id#ddNationalityID");
		hs.put("registeredBy", "id#CustomerDetailSWI_RegisteredBy");
		hs.put("promotedBy", "id#CustomerDetailSWI_Promotedby");
		hs.put("retailerID", "id#CustomerDetailSWI_RetID");
		hs.put("marketingSMSChkBox", "id#cbxSmsMarketing");
		hs.put("adultChkbox", "id#cbxAdult");
		hs.put("addressChkBox", "id#cbxAddressVerified");		
		hs.put("identifyChkBox", "id#cbxIdentificationChecked");
		hs.put("photoChkBox", "id#cbxSamePhoto");
		hs.put("termsCondChkBox", "id#cbxTermsCondition");
		hs.put("nextBtn", "id#Next");
		
		//Confirmation Page
		hs.put("confirmTitle", "id#step2ddtitle");
		hs.put("confirmFirstName", "id#step2Firstname");
		hs.put("confirmLastName", "id#step2Lastname");
		hs.put("confirmCareOf", "id#step2CareOf");
		hs.put("confirmDOB", "id#step2Dateofbirth");
		hs.put("confirmGender", "id#step2ddgender");
		hs.put("confirmAccountNo", "id#step2Accountnumber");
		hs.put("confirmResidentYes", "id#rbtnYes");
		hs.put("confirmResidentNo", "id#rbtnNo");
		hs.put("confirmStreet", "id#step2Street");
		hs.put("confirmHouseNo", "id#step2Houseno");
		hs.put("confirmPostCode", "id#step2Postcode");
		hs.put("confirmCity", "id#step2Cityname");
		hs.put("confirmCountry", "id#step2Country");
		hs.put("confirmNationality", "id#step2ddNationality");
		hs.put("confirmEmail1", "id#step2Email");
		hs.put("confirmEmail2", "id#step2ConformEmail");
		hs.put("confirmLanguage", "id#step2ddLanguage");
		hs.put("confirmPrivateNo", "id#step2Privatephonenumber");
		hs.put("confirmBusinessNo", "id#step2BusPhoneNumber");
		hs.put("confirmFaxNo", "id#step2FaxNumber");
		hs.put("confirmMSISDNRadBtn", "xpath#//input[@id='step2radioMsisdn' and contains(@checked, 'checked')]");
		hs.put("confirmPukCodeRadBtn", "xpath#//input[@id='step2radioPuk' and contains(@checked, 'checked')]");
		hs.put("confirmMSISDN", "id#step2Msisdn");
		hs.put("confirmSimNo", "id#step2Simnumber");
		hs.put("confirmIDType", "id#step2ddIDType");
		hs.put("confirmIDNo", "id#step2IdNumber");
		hs.put("confirmNationalityID", "id#step2ddNationalityID");
		hs.put("confirmRegisteredBy", "id#step2RegisteredBy");
		hs.put("confirmPromotedBy", "id#step2Promotedby");
		hs.put("confirmRetailerID", "id#step2RetID");
		hs.put("custDocumentChkBox", "id#cbxAgreement");
		
		//Edit Registration fields
		hs.put("editConfirmMSISDN", "id#step2Msisdn2");
		hs.put("viewPukCode", "id#step2Pukcode2");
		hs.put("confirmIMSI1", "id#step2IMSI");
		hs.put("confirmIMSI2", "id#step2IMSI2");
		hs.put("editConfirmICCID", "id#step2Simnumber2");
		
		//View Registration
		hs.put("viewSMSChkbox", "xpath#//input[@id='step2cbxSmsMarketing' and contains(@disabled, 'disabled')]");
		hs.put("viewAddressChkbox", "xpath#//input[@id='step2cbxAddressVerified' and contains(@disabled, 'disabled')]");
		hs.put("viewIdentifyChkbox", "xpath#//input[@id='step2cbxIdentificationChecked' and contains(@disabled, 'disabled')]");
		hs.put("viewPhotoChkbox", "xpath#//input[@id='step2cbxSamePhoto' and contains(@disabled, 'disabled')]");
		hs.put("viewSigDocChkbox", "xpath#//input[@id='step2cbxAgreement' and contains(@disabled, 'disabled')]");
		
		// Validation text elements
		hs.put("highlightMsg", "xpath#//span[@id='SwissErrorMsg' and contains(@style, 'Red')]");
		hs.put("valTitle", "xpath#//select[@id='ddtitle' and contains(@aria-required, 'true')]");
		hs.put("valFirstName", "xpath#//input[@id='CustomerDetailSWI_Firstname' and contains(@aria-required, 'true')]");
		hs.put("valLastName", "xpath#//input[@id='CustomerDetailSWI_Lastname' and contains(@aria-required, 'true')]");
		hs.put("valDOB", "xpath#//input[@id='CustomerDetailSWI_Dateofbirth' and contains(@aria-required, 'true')]");
		hs.put("valGenderDD", "xpath#//select[@id='ddgender' and contains(@aria-required, 'true')]");
		hs.put("valStreet", "xpath#//input[@id='txtStreet' and contains(@aria-required, 'true')]");
		hs.put("valHouseNo", "xpath#//input[@id='txtHouseno' and contains(@aria-required, 'true')]");
		hs.put("valPostCode", "xpath#//input[@id='txtCode' and contains(@aria-required, 'true')]");
		hs.put("valCityDD", "xpath#//select[@id='ddCity' and contains(@aria-required, 'true')]");
		hs.put("valNationalityDD", "xpath#//select[@id='ddNationality' and contains(@aria-required, 'true')]");
		hs.put("valLanguageDD", "xpath#//select[@id='ddLanguage' and contains(@aria-required, 'true')]");
		hs.put("val4DigitSim", "xpath#//input[@id='CustomerDetailSWI_Simnumber' and contains(@aria-required, 'true')]");
		hs.put("valIdDD", "xpath#//select[@id='ddIDType' and contains(@aria-required, 'true')]");
		hs.put("valIdNoTxtBox", "xpath#//input[@id='CustomerDetailSWI_IdNumber' and contains(@aria-required, 'true')]");
		hs.put("valRetailerID", "xpath#//input[@id='CustomerDetailSWI_RetID' and contains(@aria-required, 'true')]");
		hs.put("submit", "id#Submit");
		
		//Confirmation and Validation messages
		hs.put("confirmMsg", "xpath#//div[@id='remove-five' and contains(@style, 'rgb')]");
		hs.put("restrictEmailMsg", "xpath#//div[@class='email' and contains(@style, 'block')]");
		hs.put("restrictConEmailMsg", "xpath#//div[@class='Confirmemail']");
		hs.put("validEmailMsg", "id#CustomerDetailSWI_Email-error");
		hs.put("validConEmailMsg", "id#CustomerDetailSWI_ConformEmail-error");
		hs.put("validSimNoMsg", "id#CustomerDetailSWI_Simnumber-error");
		
		//Old Customers
		hs.put("retrieveButton", "id#btnRetervie");
		hs.put("oldCustomerBtn", "xpath#//*[@id='pstepSWI1']/div/div[3]/div[3]/label");
		hs.put("valCusDOB", "xpath#//input[@id='frgrefDOB' and contains(@class, 'error')]");
		hs.put("valCusDocTypeDD", "xpath#//select[@id='ddlDocType' and contains(@class, 'error')]");
		hs.put("valCusDocType", "xpath#//input[@id='txtDocType' and contains(@class, 'error')]");
		hs.put("custMandatoryMessage", "xpath#//div[@id='errorMsgForgot' and contains(@style, 'Red')]");
		hs.put("noRecValMsg", "xpath#//div[@id='errorMsgForgot' and contains(text(), 'No')]");
		
		hs.put("CusDOB", "id#frgrefDOB");
		hs.put("CusDocTypeDD", "id#ddlDocType");
		hs.put("CusDocType", "id#txtDocType");
		
		//New & Old Customer
		hs.put("newCustomerButton", "id#new");
		hs.put("oldCustomerButton", "id#old");
		
		return hs.get(locator);
		
		}catch(Exception e){
			
			System.out.println("Error occurred in POM classes :"+e);
			
		}
		return null;
	}
	
	
	
	public synchronized String Registration_NLD_Page(String locator){
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();

		hs.put("title", "id#NLDtitle");
		hs.put("firstName", "id#FirstName");
		hs.put("lastName", "id#LastName");
		hs.put("DOB", "id#txtNLDDOB");
		hs.put("country", "id#Country");
		hs.put("cityTextBox", "id#city");
		hs.put("street", "id#Street");
		hs.put("postCode", "id#Code");
		hs.put("findAddress", "xpath#//a[@id='btnFindAddressNLD']/span");
		hs.put("addressList", "id#lstAddressResult");
		hs.put("acceptAddress", "xpath#//a[@id='btnAcceptAddress']/span");
		hs.put("resetAddress", "xpath#//a[@id='btnResetAddress']/span");
		hs.put("houseNo", "id#HouseNo");
		hs.put("houseNoExt", "id#HouseNoExt");
		hs.put("email", "id#Email1");
		hs.put("landlineNo", "id#ContactNo");
		hs.put("ICCIDTxtbox", "id#ICCID");
		hs.put("pukCodeTxtBox", "id#PukCode");
		hs.put("languageDD", "id#NLDLang");
		hs.put("hearAboutUs", "id#NLDaboutus");
		hs.put("marketingSMSChkBox", "id#cbxMarketingSMS");
		hs.put("nextBtn", "id#btnNLDnext");
		hs.put("cancelBtn", "id#btnNLDcancel");
		
		//Edit Registration
		hs.put("accountNo", "id#AccNo");
		hs.put("IMSI_1", "id#IMSI_1");
		hs.put("IMSI_2", "id#IMSI_2");
		hs.put("MSISDN", "id#MSISDN");
		
		
		//Confirmation Page
		hs.put("confirmTitle", "id#lblNLDTitle");
		hs.put("confirmFirstName", "id#lblNLDFirstName");
		hs.put("confirmLastName", "id#lblNLDLastName");
		hs.put("confirmDOB", "id#lblNLDDOB");
		hs.put("confirmCountry", "id#lblNLDCountry1");
		hs.put("confirmAccountNo", "id#lblNLDAccNo");
		hs.put("confirmCity", "id#lblNLDCity");
		hs.put("confirmStreet", "id#lblNLDStreet");
		hs.put("confirmPostCode", "id#lblNLDCode");
		hs.put("confirmHouseNo", "id#lblNLDHouseNo");
		hs.put("confirmHouseNoExt", "id#lblNLDHouseNoExt");		
		hs.put("confirmEmail1", "id#lblNLDEmail");
		hs.put("confirmLandlineNo", "id#lblNLDContactno");
		hs.put("confirmMSISDN", "id#lblNLDMSISDN");
		hs.put("confirmICCID", "id#lblNLDICCID");
		hs.put("confirmIMSI_1", "id#lblNLDIMSI1");
		hs.put("confirmIMSI_2", "id#lblNLDIMSI2");
		hs.put("confirmPukCode", "id#lblNLDPuk");
		hs.put("confirmLanguage", "id#lblNLDPrefLang");
		hs.put("confirmHearAbUs", "id#lblNLDAboutus");
		
		hs.put("submit", "id#btnNLDsubmit");
		hs.put("backButton", "id#btnNLDback");
		
		hs.put("valTitle", "xpath#//select[@id='NLDtitle' and contains(@aria-required, 'true')]");
		hs.put("valFirstName", "xpath#//input[@id='FirstName' and contains(@aria-required, 'true')]");
		hs.put("valLastName", "xpath#//input[@id='LastName' and contains(@aria-required, 'true')]");
		hs.put("valDOB", "xpath#//input[@id='txtNLDDOB' and contains(@aria-required, 'true')]");
		hs.put("valCityTextBox", "xpath#//input[@id='city' and contains(@aria-required, 'true')]");
		hs.put("valCityFind", "xpath#//input[@id='city' and contains(@class, 'form-control mediumtext error')]");
		hs.put("valStreet", "xpath#//input[@id='Street' and contains(@aria-required, 'true')]");
		hs.put("valStreetFind", "xpath#//input[@id='Street' and contains(@class, 'form-control mediumtext error')]");
		hs.put("valPostCode", "xpath#//input[@id='Code' and contains(@aria-required, 'true')]");
		hs.put("valPostCodeFind", "xpath#//input[@id='Code' and contains(@class, 'form-control smalltext error')]");
		hs.put("valEmail", "xpath#//input[@id='Email1' and contains(@aria-required, 'true')]");
		hs.put("valLandlineNo", "xpath#//input[@id='ContactNo' and contains(@aria-required, 'true')]");
		hs.put("valICCIDTxt", "xpath#//input[@id='ICCID' and contains(@aria-required, 'true')]");
		hs.put("valpukCodeTxt", "xpath#//input[@id='PukCode' and contains(@aria-required, 'true')]");
		hs.put("valLanguageDD", "xpath#//select[@id='NLDLang' and contains(@aria-required, 'true')]");
		hs.put("valHearAboutUs", "xpath#//select[@id='NLDaboutus' and contains(@aria-required, 'true')]");
		
		//Confirmation and Validation messages
		hs.put("confirmMsg", "xpath#//label[@id='btnCircleNLDReg' and contains(@style, '128')]");
		hs.put("mandatoryMsg", "xpath#//label[@id='btnCircleNLDReg' and contains(@class, 'false')]");
		hs.put("emailValidMsg", "id#Email1-error");
		hs.put("emailRestrictMsg", "xpath#//div[@id='NLDErrorMsg' and contains(@style, 'red')]");
		
		return hs.get(locator);
		
		}catch(Exception e){
			
			System.out.println("Error occurred in POM classes :"+e);
			
		}
		return null;
	}
	
	
	public synchronized String Registration_DEN_Page(String locator){
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();

		hs.put("titleDD", "id#ddTitle");
		hs.put("firstName", "id#txtFirstName");
		hs.put("lastName", "id#txtLastName");
		hs.put("DOB", "id#txtDOB");
		hs.put("genderDD", "id#ddGender");
		hs.put("country", "id#txtCountry");
		hs.put("flatExistYes", "id#yes");
		hs.put("flatExistNo", "id#no");
		hs.put("flatNoTxt", "id#txtFlatNumber");
		hs.put("flatNameDD", "id#ddFlatName");
		hs.put("city", "id#txtCity");
		hs.put("postCode", "id#txtCode");
		hs.put("findAddress", "xpath#//a[@id='btnFindAddress']/span");
		hs.put("findAddressNotVisible", "xpath#//a[@id='btnFindAddress' and not (contains(@style, 'none'))]/span");
		hs.put("addressPopUp", "xpath#//div[@id='dvAddressList']/div");
		hs.put("selectAddress", "id#lstAddressResult");
		hs.put("acceptAddress", "xpath#//a[@id='btnAcceptAddress']/span");
		hs.put("resetAddress", "xpath#//a[@id='btnResetAddress']/span");
		hs.put("street", "id#Street");
		hs.put("apartmentNo", "id#apartmentNo");
		hs.put("houseNo", "id#HouseNo");
		hs.put("email", "id#HouseNo");
		hs.put("landlineNo", "id#LandlineNo");
		hs.put("MSISDN", "id#Msisdn");
		hs.put("pukCode", "id#HouseNo");
		hs.put("languageDD", "id#SWELang");
		hs.put("hearAboutUs", "id#HouseNo");
		hs.put("nxtButton", "id#btnMKDNext");
		
		hs.put("submit", "id#btnMKDSubmit");
		hs.put("confirmMsg", "xpath#//label[@id='btnCircleMKD' and contains(@class, 'true')]");
		
		
		
		return hs.get(locator);
		
		}catch(Exception e){
			
			System.out.println("Error occurred in POM classes :"+e);
			
		}
		return null;
	}
	
}
