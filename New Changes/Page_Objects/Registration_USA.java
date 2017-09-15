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

public class Registration_USA extends FunctionLibrary {

	public synchronized String registration_USA_Page(String locator){

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
			hs.put("newSubscriber_txtBox", "id#subsParameter");
			hs.put("register_Button", "id#btnNewRegister");
			hs.put("normal_Reg_ChkBox", "xpath#//input[@id='radio-button1-normal' and contains(@checked, checked)]");
			hs.put("title_Dropdown", "xpath#//select[@id='ddlTitle']");
			hs.put("firstName_TxtBox", "id#txtfirstName");
			hs.put("lastName_TxtBox", "id#txtlastName");
			hs.put("email_TxtBox", "id#txtEmail1");
			hs.put("confirmEmail_TxtBox", "id#txtConfirmEmail1");
			hs.put("contactNo_TxtBox", "id#txtContactNumber_1");
			hs.put("country_TxtBox", "id#txtCountry_1");
			hs.put("postCode_TxtBox", "id#txtPostCode_1");
			hs.put("findAddress_Btn", "xpath#//a[@id='btnFindAddress']//span[@class='glyphicon glyphicon-search']");
			hs.put("resetAddress_Btn", "xpath#//span[@class='glyphicon glyphicon-refresh']");
			hs.put("houseNo_TxtBox", "id#House_Number");
			hs.put("street_TxtBox", "id#txtStreet_1");
			hs.put("city_TxtBox", "id#txtCity_1");
			hs.put("state_TxtBox", "id#txtState_1");
			hs.put("PUK_Number", "id#PUK_Number");
			hs.put("MSISDN_TxtBox", "id#MSISDN_Number");
			hs.put("callMost_DD", "id#call_most");
			hs.put("hearAb_DD", "id#hear_about");
			hs.put("secretQues_DD", "id#Secret_Question");
			hs.put("secretAns_TxtBox", "id#Secret_Answer");
			hs.put("language_Dropdown", "xpath#//*[@id='Language']");
			hs.put("terms_Checkbox", "id#Terms_Conditions");
			hs.put("SMS_Checkbox", "id#cbxMarketingSMS");
			hs.put("cancel_Btn", "id#Cancel");
			hs.put("next_Btn", "id#Next");
			//Confirmation message details
			hs.put("confirm_Title", "id#step2_normal_title");
			hs.put("confirm_FirstName", "id#step2_normal_firstName");
			hs.put("confirm_LastName", "id#step2_normal_lastName");
			hs.put("confirm_DOB", "id#step2_normal_dob");
			hs.put("confirm_Email1", "id#step2_normal_email1");
			hs.put("confirm_Email2", "id#step2_normal_email1_confirm");
			hs.put("confirm_ContactNo", "id#step2_normal_contactNumber");
			hs.put("confirm_Country", "id#step2_normal_Country_1");
			hs.put("confirm_PostCode", "id#step2_normal_postCode_1");
			hs.put("confirm_HouseNo", "id#step2_normal_houseNumber");
			hs.put("confirm_Street", "id#step2_normal_street_1");
			hs.put("confirm_City", "id#step2_normal_city1");
			hs.put("confirm_State", "id#step2_normal_state1");
			hs.put("confirm_PUK_Number", "id#step2_normal_pukNumber");
			hs.put("confirm_MSISDN", "id#step2_normal_MSISDNNumber");
			hs.put("confirm_CallMost", "id#step2_normal_callMost");
			hs.put("confirm_HearAb", "id#step2_normal_hearAbout");
			hs.put("confirm_SecretQues", "id#step2_normal_secretQuestion");
			hs.put("confirm_SecretAns", "id#step2_normal_secretAnswer");
			hs.put("confirm_Language", "id#step2_normal_language");
			hs.put("confirm_SMS_Checkbox", "id#step2_cbxMarketingSMS");
			//Buttons in Registration
			hs.put("back_Button", "id#Back");
			hs.put("submit_Button", "id#Submit");
			//Load Icon
			hs.put("load_Icon", "id#spinIcon");
			hs.put("regPostCode_Load", "id#spnLoadMessage1");
			//Confirmation & error message
			hs.put("confirmation_Msg", "xpath#//span[@id='step1error' and contains(@style, 'color')]");
			hs.put("personal_Info", "xpath#//div[text()='RegistrationResources>>PersonalInformation']");
			hs.put("restrict_Message", "id#restrictErrorMsg");
			hs.put("PUKCode_ValidMsg", "id#PUK_Number-error");
			hs.put("MSISDN_ValidMsg", "id#MSISDN_Number-error");
			hs.put("invalidEmail_ConfMsg", "id#txtEmail1-error");
			hs.put("invalidConfirmEmail_ConfMsg", "id#txtConfirmEmail1-error");
			//View Registration
			hs.put("expand_RegIcon", "xpath#//div[@class='su-expand-icon sub-view']");
			hs.put("view_RegBtn", "id#viewPreRegxn");
			hs.put("viewReg_LoadIcon", "xpath#//span[@class='glyphicon-rotate']");
			hs.put("title_Text", "xpath#//label[@class='bold' and contains(text(), 'RegistrationResources>>Title')]");
			hs.put("view_Title", "xpath#//label[text()='RegistrationResources>>Title']/ancestor::div/following-sibling::div[@class='form-group col-md-1']");
			hs.put("view_FirstName", "xpath#//label[text()='RegistrationResources>>Title']/ancestor::div/following-sibling::div[@class='form-group col-md-2'][1]");
			hs.put("view_LastName", "xpath#//label[text()='RegistrationResources>>Title']/ancestor::div/following-sibling::div[@class='form-group col-md-2'][2]");
			hs.put("view_DOB", "xpath#//text()[normalize-space()='RegistrationResources>>DateOfBirth']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_Email", "xpath#//label[text()='RegistrationResources>>EMail']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_ConEmail", "xpath#//label[text()='RegistrationResources>>Confirm']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_Contact", "xpath#//label[text()='RegistrationResources>>Contact']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_AccNo", "xpath#//label[text()='RegistrationResources>>AccountNo']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_Country", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[1]");
			hs.put("view_PostCode", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[2]");
			hs.put("view_HouseNo", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[3]");
			hs.put("view_StreetAdd", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[4]");
			hs.put("view_City", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[5]//div[@class='col-md-3 col-md-offset-2 b-margin']");
			hs.put("view_State", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[5]//div/following-sibling::div");
			hs.put("view_PUKCode", "xpath#//label[text()='RegistrationResources>>lblPUK']/ancestor::div/following-sibling::div[@class='col-md-3'][1]");
			hs.put("view_MSISDN", "xpath#//label[text()='RegistrationResources>>MSISDN']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_IMSI1", "xpath#//label[text()='RegistrationResources>>IMSI1']/ancestor::div/following-sibling::div[@class='col-md-3'][1]");
			hs.put("view_IMSI2", "xpath#//label[text()='RegistrationResources>>IMSI2']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_ICCID", "xpath#//label[text()='RegistrationResources>>ICCID']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_MostCallCtry", "xpath#//label[text()='RegistrationResources>>Call_most']/ancestor::div/following-sibling::div[@class='form-group col-md-3'][1]");
			hs.put("view_HearAbUs", "xpath#//label[text()='RegistrationResources>>Hearaboutus']/ancestor::div/following-sibling::div[@class='form-group col-md-3']");
			hs.put("view_SecQues", "xpath#//label[text()='RegistrationResources>>Secret_Question']/ancestor::div/following-sibling::div[@class='form-group col-md-3']");
			hs.put("view_SecAns", "xpath#//label[text()='RegistrationResources>>Secret_Answer']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_Language", "xpath#//label[text()='RegistrationResources>>PreferredLanguage']/ancestor::div/following-sibling::div[@class='form-group col-md-3']");
			hs.put("view_CloseButton", "xpath#//h4[@class='modal-title' and contains(text(), 'United States of America')]/preceding-sibling::button[@class='close']");
			//Validation alert red boxes
			hs.put("val_HighMsg", "id#lblError");
			hs.put("val_Title", "xpath#//select[@id='ddlTitle' and contains(@aria-required, 'true')]");
			hs.put("val_FirstName", "xpath#//input[@id='txtfirstName' and contains(@aria-required, 'true')]");
			hs.put("val_LastName", "xpath#//input[@id='txtlastName' and contains(@aria-required, 'true')]");
			hs.put("val_DOB", "xpath#//input[@id='txtdateofbirth' and contains(@aria-required, 'true')]");
			hs.put("val_Email", "xpath#//input[@id='txtEmail1' and contains(@aria-required, 'true')]");
			hs.put("val_ConfirmEmail", "xpath#//input[@id='txtConfirmEmail1' and contains(@aria-required, 'true')]");
			hs.put("val_ContactNo", "xpath#//input[@id='txtContactNumber_1' and contains(@aria-required, 'true')]");
			hs.put("val_PostCode", "xpath#//input[@id='txtPostCode_1' and contains(@aria-required, 'true')]");
			hs.put("val_HouseNo", "xpath#//input[@id='House_Number' and contains(@aria-required, 'true')]");
			hs.put("val_Street", "xpath#//input[@id='txtStreet_1' and contains(@aria-required, 'true')]");
			hs.put("val_City", "xpath#//input[@id='txtCity_1' and contains(@aria-required, 'true')]");
			hs.put("val_State", "xpath#//select[@id='txtState_1' and contains(@aria-required, 'true')]");
			hs.put("val_PUK", "xpath#//input[@id='PUK_Number' and contains(@aria-required, 'true')]");
			hs.put("val_CallMost", "xpath#//select[@id='call_most' and contains(@aria-required, 'true')]");
			hs.put("val_HearAb", "xpath#//select[@id='hear_about' and contains(@aria-required, 'true')]");
			hs.put("val_SecretQues", "xpath#//select[@id='Secret_Question' and contains(@aria-required, 'true')]");
			hs.put("val_SecretAns", "xpath#//input[@id='Secret_Answer' and contains(@aria-required, 'true')]");
			hs.put("val_Language", "xpath#//select[@id='Language' and contains(@aria-required, 'true')]");
			hs.put("val_Terms", "xpath#//input[@id='Terms_Conditions' and contains(@style, 'red')]");
			//Edit Registration
			hs.put("edit_RegBtn", "id#editPreRegxn");
			hs.put("edit_CancelBtn", "id#Cancel");
			//Close Button
			hs.put("reg_CloseBtn", "xpath#//span[@class='close-icon']");
			hs.put("viewSubReg_CloseBtn", "xpath#//div[@class='close-icon close-view-icon']");
			//View_Subscriber
			hs.put("view_SubMSISDN", "xpath#//li[@id='liMSISDN']");
			hs.put("view_SubICICD", "xpath#//li[@id='liICICD']");
			//CRM Page loader
			hs.put("crm_PageLoader", "xpath#//div[@id='crmPreLoader']");
			
			String xpathValue = hs.get(locator);
			
			if(xpathValue.contains("text()=")){
			
				System.out.println("text()");	
					
				Pattern pattern = Pattern.compile("xpath(.*)text(.*)='([\\sa-zA-Z0-9_>]+)']");
				
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
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

	public synchronized String registration_USA_DatePicker(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("DOB", "txtdateofbirth");
			String locate = hs.get(locator);
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}
}
