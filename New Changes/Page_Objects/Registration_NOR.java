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

public class Registration_NOR extends FunctionLibrary {

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
			hs.put("txtLoginError", "xpath#.//div[@class='errorTxt']");
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
			hs.put("withPersonalOptions", "xpath#.//*[@id='inlineRadio1'][@value='WP']");
			hs.put("withoutPersonalOptions", "xpath#.//*[@id='inlineRadio1'][@value='WOP']");
			hs.put("TypeofID_WOP", "id#ddlTypeOfIDWOP");
			hs.put("TypeofID_WP", "id#ddlTypeOfIDWP");
			hs.put("PersonalInformation_WOP", "id#IdNumber");
			hs.put("PersonalNumber_WP", "id#PersonalNumber");
			hs.put("ddlTitle", "id#ddlTitle");
			hs.put("FirstName", "id#txtFname");
			hs.put("LastName", "id#txtLastName");
			hs.put("DOB", "id#txtdateofbirth");
			hs.put("Nationality", "id#ddNationality");
			hs.put("CountryName", "id#ddCountry");
			hs.put("CountryName_WP", "id#txtCountry_1");
			hs.put("ID_Proof", "xpath#//span[@class='upload-icon']");
			hs.put("Uploaded_File", "xpath#//a[@class='FloatLeft']");
			hs.put("State", "id#txtState_1");
			hs.put("PostCode", "xpath#.//*[@id='txtPostCode_1']");
			hs.put("disabledPostcode", "xpath#.//*[@id='txtPostCode_1'][@readonly='readonly']");
			hs.put("btnFindAddress", "xpath#.//*[@id='btnFindAddress']/span");
			hs.put("ddlCity", "id#ddCity");
			hs.put("ApartmentNumber", "id#txtApartmentNo_1");
			hs.put("ddlStreet", "id#ddStreet");
			hs.put("HouseNumber", "id#ddHouseno");
			hs.put("LycaMobileMSISDN", "id#txtMSISDN");
			hs.put("txtSimCardNumber", "id#SimCardNumber");
			hs.put("RetailerID", "id#RetailorID");
			hs.put("EmailID", "id#EmailID");
			hs.put("ddLanguage", "id#ddLanguage");
			hs.put("Contact", "id#Contact");
			hs.put("Upload-ICON", "xpath#.//span[@class='upload-icon']");
			hs.put("chkboxMarketingSMS", "id#chkMarkSMS");
			hs.put("chkboxTermsAndCondition", "id#chkTermsCond");
			hs.put("chkboxCheckEmailAndSMS", "id#chkEM");
			hs.put("chkboxBusinessAndMarketingAcceptance", "id#chkAcceptEmail");
			hs.put("chkboxPersonalInfoAcceptance", "id#chkAcceptFax");
			hs.put("Next_button", "xpath#.//*[@id='Next']");
			hs.put("Cancel_button", "id#Cancel");
			hs.put("madatoryField_lblError", "id#lblError");
			hs.put("EmailIDError", "id#EmailID-error");
			hs.put("lblTitleText", "id#lblTitleText");
			hs.put("lblFirstNameText", "id#lblFirstNameText");
			hs.put("lblLastNameText", "id#lblLastNameText");
			hs.put("lblDOB", "id#lblDateOfBirthText");
			hs.put("lblNationality", "id#lblNationalityText");
			hs.put("lblHouseNo", "id#lblhouseNumber");
			hs.put("lblstreetName", "id#lblstreetName");
			hs.put("lblCountryName", "id#lblCountryName");
			hs.put("lblcityName", "id#lblcityName");
			hs.put("lblpostCode", "id#lblpostCode");
			hs.put("lblTypeOfID_WOP", "id#spnPSelected");
			hs.put("lblPersonalNumber_WOP", "id#lblIDNumberText");
			hs.put("lblPersonalNumber_WP", "id#lblPersonalNumberText");
			hs.put("lblMSISDNText", "id#lblMSISDNText");
			hs.put("lblSimCardNoText", "id#lblSimCardNoText");
			hs.put("lblEmailText", "id#lblEmailText");
			hs.put("lblLanguageCode", "id#lblLngCodeText");
			hs.put("lblSMSMarketing", "xpath#.//label[contains(text(),'SMS Marketing')]");
			hs.put("lblContact", "id#lblContactText");
			hs.put("Submit_button", "id#btnNORSubmit");
			hs.put("SuccessLabel", "id#step1error");
			//view registration
			hs.put("lblview_Title", "xpath#.//label[text()='RegistrationResources>>Title']/ancestor::div/div[@class='form-group col-md-1']");
			hs.put("lblview_FirstName", "xpath#.//label[text()='RegistrationResources>>Title']/ancestor::div/div[@class='form-group col-md-2'][1]");
			hs.put("lblview_Account", "xpath#.//label[contains(text(),'RegistrationResources>>AccountNo')]/parent::div/following-sibling::div[1]");
			hs.put("lblview_LastName", "xpath#.//label[text()='RegistrationResources>>Title']/ancestor::div/div[@class='form-group col-md-2'][2]");
			hs.put("lblview_DOB", "xpath#.//label[contains(text(),'RegistrationResources>>DateOfBirth')]/parent::div/following-sibling::div[1]");
			hs.put("lblview_Nationality", "xpath#.//label[contains(text(),'RegistrationResources>>Nationality')]/parent::div/following-sibling::div[1]");
			hs.put("lblview_MSISDN", "xpath#.//label[contains(text(),'RegistrationResources>>MSISDN')]/parent::div/following-sibling::div[1]");
			hs.put("lblview_IMSI1", "xpath#.//label[contains(text(),'RegistrationResources>>IMSI1')]/parent::div/following-sibling::div[1]");
			hs.put("lblview_IMSI2", "xpath#.//label[contains(text(),'RegistrationResources>>IMSI1')]/parent::div/following-sibling::div[3]");
			hs.put("lblview_SIMNumber", "xpath#.//label[contains(text(),'RegistrationResources>>MSISDN')]/parent::div/following-sibling::div[3]");
			hs.put("lblview_PUKCode", "xpath#.//label[contains(text(),'RegistrationResources>>PUKNumber')]/parent::div/following-sibling::div[1]");
			hs.put("lblview_Email", "xpath#.//label[contains(text(),'RegistrationResources>>EmailID')]/parent::div/following-sibling::div[1]");
			hs.put("lblview_Language", "xpath#.//label[contains(text(),'RegistrationResources>>PreferredLanguage')]/parent::div/following-sibling::div[1]");
			hs.put("lblview_Contact", "xpath#.//label[contains(text(),'RegistrationResources>>Contact')]/parent::div/following-sibling::div[1]");
			hs.put("lblview_SMSMarketing", "xpath#.//label[contains(text(),'RegistrationResources>>SMS')]");
			hs.put("btnview_Cancel", "xpath#//button[@id='refreshSubscriber']");
			hs.put("lblview_Country", "xpath#.//*[@id='psteps_circle_steps_simple']/div/div/form/div/div[7]/div");
			hs.put("lblview_state", "xpath#.//*[@id='psteps_circle_steps_simple']/div/div/form/div/div[8]/div[1]");
			hs.put("lblview_city", "xpath#.//*[@id='psteps_circle_steps_simple']/div/div/form/div/div[9]/div[1]");
			hs.put("lblview_postcode", "xpath#.//*[@id='psteps_circle_steps_simple']/div/div/form/div/div[8]/div[2]");
			hs.put("lblview_streetname", "xpath#.//*[@id='psteps_circle_steps_simple']/div/div/form/div/div[9]/div[2]");
			hs.put("lblview_HouseNo", "xpath#.//*[@id='psteps_circle_steps_simple']/div/div/form/div/div[10]/div");
			hs.put("btnReset", "id#submitResetPassword");
			hs.put("resetPassMsg","id#resetPassMsg");
			hs.put("rotateElement","xpath#.//*[@id='resetPassMsg'][@class='glyphicon-rotate']");
			hs.put("view_reg_CloseButton", "xpath#.//button[contains(text(),'×')]");
			hs.put("viewSubReg_CloseBtn", "xpath#//div[@class='close-icon close-view-icon']");
			hs.put("edit_RegBtn", "id#editPreRegxn");
			hs.put("edit_CancelBtn", "id#Cancel");
			//added by murali on 30-09-2016		
			hs.put("btnSendPassword","id#SendPassword");
			hs.put("SendPasswordsMsg","id#SendPasswordsMsg");
			//Edit Reg validations added
			hs.put("EditReg_City", "id#txtCity_1");
			hs.put("EditReg_Street", "id#txtStreet_1");
			hs.put("EditReg_House_Number", "id#House_Number");
			hs.put("pukcode", "id#txtPukCode");
			
			hs.put("ID_Number_Alert", "id#IdNumber-error");
			hs.put("ID_Proof", "xpath#//span[@class='upload-icon']");
			hs.put("Uploaded_File", "xpath#//a[@class='FloatLeft']");
			hs.put("Confirm_Uploaded_File", "id#lblAttPathText");
			hs.put("Load_Address_Image", "id#btnNorrotate1");
			
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
