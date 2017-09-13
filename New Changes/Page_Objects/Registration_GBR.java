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

public class Registration_GBR extends FunctionLibrary {

	public synchronized String Registration_Page(String locator){

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
			//Murali -start
			hs.put("txtNewRegistration", "id#subsParameter");
			hs.put("Error_Message", "id#subsParameter-error");
			hs.put("btnNewRegister", "id#btnNewRegister");
			hs.put("portoutalert", "id#spnPortinSubscriberMsg");
			hs.put("btnFindAddress", "xpath#.//*[@id='btnFindAddress']/span");
			hs.put("lblPersonalInfo", "xpath#//div[text()='RegistrationResources>>PersonalInformation']");
			hs.put("restrictErrorMsg", "id#restrictErrorMsg");
			hs.put("txtEmail1", "id#txtEmail1");
			hs.put("EmailErrorMsg", "id#txtEmail1-error");
			hs.put("MandateError", "id#lblError");
			//Murali -End
			hs.put("title_Dropdown","id#ddlTitle");
			hs.put("confirm_Title", "id#step2Title");
			hs.put("firstName_TxtBox", "id#txtfirstName");
			hs.put("lastName_TxtBox", "id#txtlastName");
			hs.put("email_TxtBox", "id#txtEmail1");
			hs.put("country_TxtBox", "id#txtCountry_1");
			hs.put("dob_TxtBox", "id#txtdateofbirth");
			//span[@class='SuccessClass']
			hs.put("county_TxtBox", "id#txtCounty");
			hs.put("PostcodeGBR_TxtBox", "id#txtCode_1");
			hs.put("StateGBR_TxtBox", "id#txtState");
			hs.put("HouseNoGBR_TxtBox", "id#txtLine1_1");
			hs.put("StreetGBR_TxtBox", "id#txtLine2_1");
			hs.put("HouseNameGBR_TxtBox", "id#txtHouseName");
			hs.put("ContactGBR_TxtBox", "id#txtContact1");
			hs.put("MobileNo_Label", "id#txtMobileNo");
			hs.put("PUKCode_TxtBox", "id#txtPukCode");
			hs.put("Language_DropDown", "id#GBRLang");
			hs.put("HearAbout_DropDown", "id#ddlHearAbout");
			hs.put("CheckSMSMarket_Select", "id#chkSMS");
			hs.put("city_TxtBox", "id#txtCity_1");
			hs.put("SIMisNotAssociateToResellerError", "id#step1error");
			hs.put("MSISDN_TxtBox", "id#txtMobileNo");
			hs.put("terms_Checkbox", "id#Terms_Conditions");
			hs.put("personal_Info", "xpath#//div[text()='RegistrationResources>>PersonalInformation']");
			hs.put("cancel_Btn", "id#Cancel");
			hs.put("next_Btn", "id#Next");
			hs.put("ErrorMsg_Label", "id#spnPortinSubscriberMsg");
			hs.put("RegLoad_Icon", "xpath#//span[@class='glyphicon-rotate col-md-12 text-center']");
			//Buttons in Registration
			hs.put("back_Button", "id#Back");
			hs.put("submit_Button", "id#Submit");
			hs.put("successMessage_Label", "id#step1error");
			//Edit Registration
			hs.put("edit_RegBtn", "id#editPreRegxn");
			hs.put("edit_CancelBtn", "id#Cancel");
			//Close Button
			hs.put("reg_CloseBtn", "xpath#.//span[@title='Close']");
			hs.put("viewSubReg_CloseBtn", "xpath#//div[@class='close-icon close-view-icon']");
			hs.put("view_Title", "xpath#//label[text()='RegistrationResources>>Title']/ancestor::div/following-sibling::div[@class='form-group col-md-1']");
			hs.put("view_FirstName", "xpath#//label[text()='RegistrationResources>>Title']/ancestor::div/following-sibling::div[@class='form-group col-md-2'][1]");
			hs.put("view_LastName", "xpath#//label[text()='RegistrationResources>>Title']/ancestor::div/following-sibling::div[@class='form-group col-md-2'][2]");
			hs.put("view_DOB", "id#step2dob");
			hs.put("view_Email", "id#step2txtEmail1");
			hs.put("view_Contact", "id#step2txtContactNumber_1");
			hs.put("view_Country", "id#step2txtCountry_1");
			hs.put("view_County", "id#step2txtCounty");
			hs.put("view_State", "id#step2txtState");
			hs.put("view_PostCode", "id#step2txtPostCode_1");
			hs.put("view_HouseNo", "id#step2House_Number");
			hs.put("view_HouseName", "id#step2HouseName");
			hs.put("view_StreetAdd", "id#step2txtStreet_1");
			hs.put("view_City", "id#step2txtCity_1");
			hs.put("view_PUKCode", "id#step2PUK_Number");
			hs.put("view_MSISDN", "id#step2MSISDN_Number");
			hs.put("view_HearAbUs", "id#step2hear_about");
			hs.put("view_Language", "id#step2Language");
			hs.put("view_CloseButton", "xpath#//h4[@class='modal-title' and contains(text(), 'United Kingdom')]/preceding-sibling::button[@class='close']");
			//View Registration
			hs.put("expand_RegIcon", "xpath#//div[@class='su-expand-icon sub-view']");
			hs.put("view_RegBtn", "id#viewPreRegxn");
			hs.put("viewReg_LoadIcon", "xpath#//span[@class='glyphicon-rotate']");
			hs.put("title_Text", "xpath#//label[@class='bold' and contains(text(), 'RegistrationResources>>Title')]");
			hs.put("view_reg_Title", "xpath#//label[text()='RegistrationResources>>Title']/ancestor::div/following-sibling::div[@class='form-group col-md-1']");
			hs.put("view_reg_FirstName", "xpath#//label[text()='RegistrationResources>>Title']/ancestor::div/following-sibling::div[@class='form-group col-md-3'][1]");
			hs.put("view_reg_LastName", "xpath#//label[text()='RegistrationResources>>Title']/ancestor::div/following-sibling::div[@class='form-group col-md-3'][2]");
			hs.put("view_reg_DOB", "xpath#//text()[normalize-space()='RegistrationResources>>DateOfBirth']/ancestor::div[2]/div[2]");
			hs.put("view_reg_Email", "xpath#//label[text()='RegistrationResources>>EMail']/ancestor::div/following-sibling::div[@class='col-md-3'][1]");
			hs.put("view_reg_Contact", "xpath#//label[text()='RegistrationResources>>EMail']/ancestor::div/following-sibling::div[@class='col-md-3'][2]");
			hs.put("view_reg_AccNo", "xpath#//label[text()='RegistrationResources>>AccountNo']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_reg_Country", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[1]");
			hs.put("view_reg_PostCode", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[2]/div[2]");
			hs.put("view_reg_County", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[2]/div[1]");
			hs.put("view_reg_HouseNo", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[5]/div");
			hs.put("view_reg_HouseName", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[6]/div");
			hs.put("view_reg_StreetAdd", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[7]/div");
			hs.put("view_reg_City", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[4]/div");
			hs.put("view_reg_State", "xpath#//label[text()='RegistrationResources>>Address']/parent::div/parent::div/following-sibling::div[3]/div");
			hs.put("view_reg_PUKCode", "xpath#//label[text()='RegistrationResources>>lblPUK']/ancestor::div/following-sibling::div[@class='col-md-3'][1]");
			hs.put("view_reg_MSISDN", "xpath#//label[text()='RegistrationResources>>MSISDN']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_reg_IMSI1", "xpath#//label[text()='RegistrationResources>>IMSI1']/ancestor::div/following-sibling::div[@class='col-md-3'][1]");
			hs.put("view_reg_IMSI2", "xpath#//label[text()='RegistrationResources>>IMSI2']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_reg_ICCID", "xpath#//label[text()='RegistrationResources>>ICCID']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_reg_MostCallCtry", "xpath#//label[text()='RegistrationResources>>Call_most']/ancestor::div/following-sibling::div[@class='form-group col-md-3'][1]");
			hs.put("view_reg_HearAbUs", "xpath#//label[text()='RegistrationResources>>Hearaboutus']/ancestor::div/following-sibling::div[@class='form-group col-md-3']");
			hs.put("view_reg_SecQues", "xpath#//label[text()='RegistrationResources>>Secret_Question']/ancestor::div/following-sibling::div[@class='form-group col-md-3']");
			hs.put("view_reg_SecAns", "xpath#//label[text()='RegistrationResources>>Secret_Answer']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_reg_Language", "xpath#//label[text()='RegistrationResources>>PreferredLanguage']/ancestor::div/following-sibling::div[@class='form-group col-md-3']");
			hs.put("view_reg_CloseButton", "xpath#.//*[@id='dvViewRegistration']/div/div/div[3]/button");

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

	public synchronized String registration_GBR_Page(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("newSubscriber_txtBox", "id#subsParameter");
			return hs.get(locator);
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

	public synchronized String registration_GBR_DatePicker(String locator){

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

	public synchronized String Registration_Page_ResetObj(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("btnFindAddress", ".//*[@id='btnFindAddress']/span");
			String locate = hs.get(locator);
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}

	}


}
