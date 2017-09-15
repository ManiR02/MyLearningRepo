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

public class RM_Ireland extends FunctionLibrary {

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
			//After logging
			hs.put("txtNewRegistration", "id#subsParameter");
			hs.put("btnNewRegister", "id#btnNewRegister");
			hs.put("rdbMSISDN", "id#rdoIrlmobile");
			hs.put("rdbPUK", "xpath#.//*[@id='rdoirlpukcode']");
			hs.put("txtMSISDN", "id#Msisdn");
			hs.put("txtPukcode", "id#Pukcode");
			hs.put("lblMSISDNToolTip", "xpath#.//*[@class='col-md-5 text-center msisdn-color']");
			//
			
			hs.put("txtPukcode", "id#Pukcode");

			hs.put("txtSimnumber", "id#Simnumber");
			hs.put("dpdnSecretQuestion", "id#SecretQuestion");
			hs.put("txtSecretAnswer", "id#SecretAnswer");
			hs.put("btnNext", "xpath#.//*[@class='button-next btn btn-primary']");
			//After filling basic info ,press next button
			hs.put("rdbPersonal", "id#rdoPersonal");
			hs.put("dpdnTitle", "id#Title");
			hs.put("txtFirstName", "id#FirstName");
			hs.put("txtLastName", "id#LastName");
			hs.put("txtEmail", "id#Email");
			hs.put("RestrictEmailAlert", "id#msgEmail");
			hs.put("txtConfirmEmail", "id#ConfirmEmail");
			hs.put("confirmEmailAlert", "xpath#.//div//input[@id='ConfirmEmail']//parent::div/div[@class='tooltip fade bottom in']");
			
			hs.put("dtDateOfBirth", "id#DateOfBirth");
			hs.put("AlternateContact", "id#AlternateContact");
			hs.put("dpdnNDDPreference", "id#NDDPreference");
			hs.put("txtCountry", "id#Country");
			hs.put("txtCounty", "id#County");
			hs.put("txtLocality", "id#Locality");
			hs.put("txtStreet", "id#Street");
			hs.put("btnGAFSearch", "xpath#.//*[@class='btn-gbr']");
			hs.put("btnGAFReset", "xpath#.//*[@class='btn-gbr-reset']");
			
			hs.put("listAddress", "id#lstAddress");
			hs.put("btnDone", "id#btnDone");
			hs.put("txtHouseNo", "id#HouseNo");
			hs.put("txtAddress1", "id#Address1");
			hs.put("txtAddress2", "id#Address2");
			hs.put("txtAddress3", "id#Address3");
			hs.put("dpdnPrefLanguage", "id#PrefLanguage");
			hs.put("dpdnAboutus", "id#Aboutus");
			hs.put("dpdnCallmostcountry", "id#Callmostcountry");
			hs.put("chkSMSMarketing", "id#chksmsmark");
			hs.put("chkTerms", "id#chkfterms");
			hs.put("rdbchklistname", "id#chklistname");
			hs.put("rdbchklistnumber", "id#chklistnumber");
			hs.put("rdbchkdonotlist", "id#chkdonotlist");
			hs.put("rdbchklistDG", "id#chklistDG");
			hs.put("chkbox_noreceiveirectmarketingFromOther", "id#chksmsothercompanies");
			hs.put("chkbox_noreceiveirectmarketingFromLyca", "id#chksmsaffliatedcompanies");
			hs.put("btnNext1", "xpath#.//*[@id='IRLForm-step-1']//a[@class='button-next btn btn-primary']");
			//After click on Next button ,confirmation page -shows personal information
			hs.put("PI_MSISDN", "id#sp_Msisdn");
			hs.put("PI_Simnumber", "id#sp_Simnumber");
			hs.put("PI_Secretrquestion", "id#sp_Secretrquestion");
			hs.put("PI_Secretranswer", "id#sp_Secretranswer");
			hs.put("PI_rdoPersonalcnf", "id#rdoPersonalcnf");
			hs.put("PI_Title", "id#sp_Title");
			hs.put("PI_FirstName", "id#sp_FirstName");
			hs.put("PI_LastName", "id#sp_LastName");
			hs.put("PI_Email", "id#sp_Email");
			hs.put("PI_ConfirmEmail", "id#sp_ConfirmEmail");
			hs.put("PI_DOB", "id#sp_DOB");
			hs.put("PI_Alternatecontact", "id#sp_Alternatecontact");
			hs.put("PI_NDDRef", "id#sp_NDDPref");
			hs.put("PI_Country", "id#sp_Country");
			hs.put("PI_County", "id#sp_County");
			hs.put("PI_HouseNo", "id#sp_HouseNo");
			hs.put("PI_Address1", "id#sp_Address1");
			hs.put("PI_Address2", "id#sp_Address2");
			hs.put("PI_Address3", "id#sp_Address3");
			hs.put("PI_Language", "id#sp_Language");
			hs.put("ICCID", "id#ICCID");
			hs.put("IMSI1", "id#IMSI1");
			hs.put("IMSI2", "id#IMSI2");
			hs.put("PI_Aboutus", "id#sp_Aboutus");
			hs.put("PI_Callmost", "id#sp_Callmost");
			hs.put("PI_cnfsmsmark", "id#cnfsmsmark");
			hs.put("PI_cnfterms", "id#cnfterms");
			hs.put("PI_cnfsmsaffliatedcompanies", "id#cnfsmsaffliatedcompanies");
			hs.put("PI_cnfsmsothercompanies", "id#cnfsmsothercompanies");
			hs.put("PI_btnSubmit", "xpath#.//*[@class='btn btn-primary stepy-finish confirm-success']");
			hs.put("LoadingImg", "id#LoadingImg");
			//Confirmation Message - Success
			hs.put("Registered_Successful_Message", "id#RegistrationFinalMessage");		
			hs.put("Email_Successful_Message", "id#RegistrationEmailMsg");		
			hs.put("SMS_Successful_Message", "id#RegistrationSmsMsg");		
			hs.put("Home_ICON", "xpath#.//*[@class='icon-home2 delete-img']");		
			//Error Messages
			hs.put("Already_Registered_Message","xpath#.//*[@id='ResponseMessage']");
			//tooltip
			hs.put("MSISDNCountryCodeToolTipAlert","xpath#.//*[@class='tooltip-inner']");
			hs.put("MSISDNCountryCodeAlert_HomePage","id#subsParameter-error");
			hs.put("HomePageForm","xpath#.//*[@class='register-wrapper form-group']");
			hs.put("Cancel_GAF_Button","id#btnCancel-error");
			hs.put("NoRecord","id#NoRecord");
			hs.put("NoRecord_OK","id#btnFailedCancel");
			hs.put("rdoOfficial","id#rdoOfficial");
			hs.put("txtCompanyName","id#CompanyName");
			hs.put("dpdnIndentInfo","id#IndentInfo");
			//Edit Registration
			hs.put("AddressInfo","xpath#.//*[@class='personalInfoClass ng-binding']");
			//ExpandICON
			hs.put("Expand_ICON","xpath#.//*[@class='su-expand-icon sub-view']");
			//edit confirmation mail
			hs.put("EditConfirmationMessage","id#EditRegistrationFinalMessage");
			
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
