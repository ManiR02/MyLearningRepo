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

public class Registration_FRA extends FunctionLibrary {

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
		String fullAttributewithName = null;
		String fullAttributewithName1 = null;
		String fullAttributewithName2 = null;
		String Resx_Value1=null;
		String Resx_Value2=null;
		String[] xpathaftersplittext = null;
		
		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("MSISDN_New_Registration", "id#subsParameter");
			hs.put("NewRegister", "id#btnNewRegister");
			hs.put("Sim_Type", "id#ddlPrePost");
			hs.put("Title", "id#ddtitle");
			hs.put("radio_Pukcode", "id#rdbtnPUKCode");
			hs.put("radio_MSISDN", "id#rdbtnMSISDN");
			hs.put("Number_Of_Sims_Pukcode", "xpath#//div[@class='personal-page']//input[@name='radio-button2']/..");
			hs.put("Number_Of_Sims_MSISDN", "xpath#//div[@class='official-page']//input[@name='radio-button2']/..");
			hs.put("Puk_Code_1", "id#txtPUKCode1");
			hs.put("Puk_Code_Sim_1", "id#txtPUKSIM1");
			hs.put("Puk_Code_2", "id#txtPUKCode2");
			hs.put("Puk_Code_Sim_2", "id#txtPUKSIM2");
			hs.put("Puk_Code_3", "id#txtPUKCode3");
			hs.put("Puk_Code_Sim_3", "id#txtPUKSIM3");
			hs.put("Puk_Code_4", "id#txtPUKCode4");
			hs.put("Puk_Code_Sim_4", "id#txtPUKSIM4");
			hs.put("Puk_Code_5", "id#txtPUKCode5");
			hs.put("Puk_Code_Sim_5", "id#txtPUKSIM5");
			hs.put("MSISDN_1", "id#txtMSISDNno1");
			hs.put("MSISDN_Sim_1", "id#txtmsisdnSIM1");
			hs.put("MSISDN_2", "id#txtMSISDNno2");
			hs.put("MSISDN_Sim_2", "id#txtmsisdnSIM2");
			hs.put("MSISDN_3", "id#txtMSISDNno3");
			hs.put("MSISDN_Sim_3", "id#txtmsisdnSIM3");
			hs.put("MSISDN_4", "id#txtMSISDNno4");
			hs.put("MSISDN_Sim_4", "id#txtmsisdnSIM4");
			hs.put("MSISDN_5", "id#txtMSISDNno5");
			hs.put("MSISDN_Sim_5", "id#txtmsisdnSIM5");
			hs.put("First_Name", "id#txtFirstName");
			hs.put("Last_Name", "id#txtLastName");
			hs.put("Date_Of_Birth", "id#txtDOB");
			hs.put("Contact_Number", "id#txtContact");
			hs.put("Email", "id#txtEmail");
			hs.put("Country", "id#txtCountry");
			hs.put("Post_Code", "id#txtCode");
			hs.put("Find_Address", "xpath#.//*[@id='btnFindAddress']/span");
			hs.put("City", "id#ddCity");
			hs.put("Street", "id#ddStreet");
			hs.put("House_Number", "id#ddHouseno");
			hs.put("Loading_Image", "id#btnrotate1");
			hs.put("ID_Form", "id#ddFormid");
			hs.put("ID_Number", "id#txtIDNumber");
			hs.put("ID_Proof", "xpath#//span[@class='upload-icon']");
			hs.put("Uploaded_File", "xpath#//a[@class='FloatLeft']");
			hs.put("Delete_File", "id#UploadDelete");
			hs.put("Language", "id#ddLanguage");
			hs.put("Marketing_SMS", "id#cbxSMSMarketing");
			hs.put("Terms_Conditions", "id#cbxTermsCondition");
			hs.put("Next", "id#Next");
			hs.put("Mode_Of_Registration", "name#radio-button1");
			hs.put("Number_Of_Sims_Confirm", "name#radio-button2");
			hs.put("Confirm_MSISDN_1", "id#step2txtMSISDNno1");
			hs.put("Confirm_MSISDN_SIM_1", "id#step2txtmsisdnSIM1");
			hs.put("Confirm_MSISDN_2", "id#step2txtMSISDNno2");
			hs.put("Confirm_MSISDN_SIM_2", "id#step2txtmsisdnSIM2");
			hs.put("Confirm_MSISDN_3", "id#step2txtMSISDNno3");
			hs.put("Confirm_MSISDN_SIM_3", "id#step2txtmsisdnSIM3");
			hs.put("Confirm_MSISDN_4", "id#step2txtMSISDNno4");
			hs.put("Confirm_MSISDN_SIM_4", "id#step2txtmsisdnSIM4");
			hs.put("Confirm_MSISDN_5", "id#step2txtMSISDNno5");
			hs.put("Confirm_MSISDN_SIM_5", "id#step2txtmsisdnSIM5");
			hs.put("Confirm_Puk_Code_1", "id#step2txtPUKCode1");
			hs.put("Confirm_Puk_Code_SIM_1", "id#step2txtPUKSIM1");
			hs.put("Confirm_Puk_Code_2", "id#step2txtPUKCode2");
			hs.put("Confirm_Puk_Code_SIM_2", "id#step2txtPUKSIM2");
			hs.put("Confirm_Puk_Code_3", "id#step2txtPUKCode3");
			hs.put("Confirm_Puk_Code_SIM_3", "id#step2txtPUKSIM3");
			hs.put("Confirm_Puk_Code_4", "id#step2txtPUKCode4");
			hs.put("Confirm_Puk_Code_SIM_4", "id#step2txtPUKSIM4");
			hs.put("Confirm_Puk_Code_5", "id#step2txtPUKCode5");
			hs.put("Confirm_Puk_Code_SIM_5", "id#step2txtPUKSIM5");
			hs.put("Confirm_Title", "id#step2ddtitle");
			hs.put("Confirm_First_Name", "id#step2txtFirstName");
			hs.put("Confirm_Last_Name", "id#step2txtLastName");
			hs.put("Confirm_Date_Of_Birth", "id#step2txtDOB");
			hs.put("Confirm_Contact_Number", "id#step2txtContact");
			hs.put("Confirm_Email", "id#step2txtEmail");
			hs.put("Confirm_Country", "id#step2txtCountry");
			hs.put("Confirm_Post_Code", "id#step2txtCode");
			hs.put("Confirm_City", "id#step2txtCity");
			hs.put("Confirm_Street", "id#step2txtStreet");
			hs.put("Confirm_House_Number", "id#step2txtHouseno");
			hs.put("Confirm_ID_Form", "id#step2ddFormid");
			hs.put("Confirm_ID_Number", "id#step2txtIDNumber");
			hs.put("Confirm_ID_Proof", "id#step2uploadIDProof");
			hs.put("Confirm_Uploaded_File", "id#step2uploadIDProof");
			hs.put("Confirm_Language", "id#step2ddLanguage");
			hs.put("Submit", "id#Submit");
			hs.put("Confirm_Message_Table", "id#tblErrorMsg");
			hs.put("Confirm_Message", "xpath#.//*[@id='tblErrorMsg']/tr[3]/th[2]");
			hs.put("Confirm_Message_2", "xpath#.//*[@id='tblErrorMsg']/tr[4]/th[2]");
			hs.put("Confirm_Message_3", "xpath#.//*[@id='tblErrorMsg']/tr[5]/th[2]");
			hs.put("Confirm_Message_4", "xpath#.//*[@id='tblErrorMsg']/tr[6]/th[2]");
			hs.put("Confirm_Message_5", "xpath#.//*[@id='tblErrorMsg']/tr[7]/th[2]");
			hs.put("Email_Alert_Message", "xpath#.//*[@class='email']");
			hs.put("Alert_Message", "id#FranceErrorMsg");
			hs.put("Load_Type", "id#loadType");
			hs.put("Load_Parameter", "id#loadParameter");
			hs.put("Load_Subscriber", "id#btnLoadSubscriber");
			hs.put("Subscriber_View", "xpath#//div[@class='su-expand-icon sub-view']");
			hs.put("Reset_Address", "xpath#.//*[@id='btnResetAddress']/span");
			// View Registration
			hs.put("View_Registration", "id#viewPreRegxn");
			hs.put("View_Puk_Code", "xpath#//label[contains(text(),'RegistrationResources>>lblPUK')]/parent::div/parent::div/div[3]");
			hs.put("View_Puk_Code_Sim_Number", "xpath#//label[contains(text(),'RegistrationResources>>SIMCard')]/parent::div/parent::div/div[5]");
			hs.put("View_IMSI_1", "xpath#//label[contains(text(),'RegistrationResources>>IMSI1')]/parent::div/parent::div/div[2]");
			hs.put("View_IMSI_2", "xpath#//label[contains(text(),'RegistrationResources>>IMSI2')]/parent::div/parent::div/div[4]");
			hs.put("View_MSISDN", "xpath#//label[text()[normalize-space() = 'RegistrationResources>>MSISDN']]/parent::div/parent::div/div[2]");
			hs.put("View_Title", "xpath#//label[contains(text(),'RegistrationResources>>Title')]/parent::div/parent::div/div[2]");
			hs.put("View_First_Name", "xpath#//label[contains(text(),'RegistrationResources>>Title')]/parent::div/parent::div/div[3]");
			hs.put("View_Last_Name", "xpath#//label[contains(text(),'RegistrationResources>>Title')]/parent::div/parent::div/div[4]");
			hs.put("View_Date_Of_Birth", "xpath#//label[contains(text(),'RegistrationResources>>DateOfBirth')]/parent::div/parent::div/div[2]");
			hs.put("View_Contact_Number", "xpath#//label[contains(text(),'RegistrationResources>>Contact')]/parent::div/parent::div/div[2]");
			hs.put("View_Email", "xpath#//label[contains(text(),'RegistrationResources>>EMail')]/parent::div/parent::div/div[2]");
			hs.put("View_Account_Number", "xpath#//label[contains(text(),'RegistrationResources>>AccountNo')]/parent::div/parent::div/div[2]");
			hs.put("View_Country", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[8]/div[1]");
			hs.put("View_Post_Code", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[8]/div[2]");
			hs.put("View_City", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[9]/div[1]");
			hs.put("View_Street", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[9]/div[2]");
			hs.put("View_House_Number", "xpath#//label[contains(text(),'RegistrationResources>>Address')]/parent::div/parent::div/parent::div/div[10]/div[1]");
			hs.put("View_ID_Form", "xpath#//label[contains(text(),'RegistrationResources>>Form|ID')]/parent::div/parent::div/div[2]");
			hs.put("View_ID_Number", "xpath#//label[contains(text(),'RegistrationResources>>ID|Number')]/parent::div/parent::div/div[4]");
			hs.put("View_Language", "xpath#//label[contains(text(),'RegistrationResources>>PreferredLanguage')]/parent::div/parent::div/div[4]");
			hs.put("View_SMS_Marketing", "id#cbxSMSMarketing");
			hs.put("View_Cancel", "xpath#.//*[@class='modal-footer']/button[@data-dismiss='modal' and contains(@class, 'crm-btn')]");
			hs.put("Loading_Image_Subscriber_View", "xpath#.//*[@class='spnRegDiv glyphicon-rotate']");
			// Edit Registration
			hs.put("Edit_Registration", "id#editPreRegxn");
			hs.put("Edit_Puk_Code", "id#txtPUKCode");
			hs.put("Edit_Puk_Sim_Number", "id#txtPUKSIM");
			hs.put("Edit_IMSI_1", "id#txtIMSI1");
			hs.put("Edit_IMSI_2", "id#txtIMSI2");
			hs.put("Edit_MSISDN", "id#txtMSISDNno");
			hs.put("Edit_Account_Number", "id#txtAccNo");
			hs.put("Edit_City", "id#txtCity");
			hs.put("Edit_Street", "id#txtStreet");
			hs.put("Edit_House_Number", "id#txtHouseno");
			hs.put("Edit_Uploaded_File", "xpath#.//*[@id='document1']//a[@class='FloatLeft']");
			hs.put("Edit_Delete_Uploaded_File", "xpath#.//*[@id='document1']//img");
			hs.put("Edit_Confirm_Puk_Code", "id#step2txtPUKCode");
			hs.put("Edit_Confirm_Puk_Sim_Number", "id#step2txtPUKSIM");
			hs.put("Edit_Confirm_IMSI_1", "id#step2txtIMSI1");
			hs.put("Edit_Confirm_IMSI_2", "id#step2txtIMSI2");
			hs.put("Edit_Confirm_MSISDN", "id#step2txtMSISDNno");
			hs.put("Edit_Confirm_Account_Number", "id#step2txtAccNo");
			hs.put("Edit_Cancel", "id#Cancel");
			hs.put("Edit_Confirm_Message", "id#remove-five");
			hs.put("Loading_Image_Submit", "id#btnrotate");
			hs.put("close_button", "xpath#//div[@class='close-icon close-view-icon']");
			hs.put("View_Edit_Reg_CloseBtn", "xpath#//div[@class='close-icon close-view-icon']");
			
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
					fullAttributewithName=commonAttribute+"[@name='"+ xpathsplittext[1] +"']/value";
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
					
					System.out.println("xpathValue : "+ xpathValue);
					
					xpathsplittext = splittedXpath.split(">>");
					
					resourceFileName = xpathsplittext[0] + excel.RetrieveAutomationKeyFromExcel("Extension_Language", "Resx_Extension", languageName);
					
					filePathLocation=property.getProperty("resourcesFilePath_GBR");
					String resourceFileToGetValue=filePathLocation+"\\"+resourceFileName;
					log.info("File path is "+resourceFileToGetValue);
					File file=new File("//\\"+resourceFileToGetValue);

					String commonAttribute=property.getProperty("attributeCommonValue");
					
					if(xpathsplittext[1].contains("|")){
						
					xpathaftersplittext = 	xpathsplittext[1].split("\\|");
					
					fullAttributewithName1=commonAttribute+"[@name='"+ xpathaftersplittext[0] +"']/value";
					fullAttributewithName2=commonAttribute+"[@name='"+ xpathaftersplittext[1] +"']/value";
					
					} else {

					fullAttributewithName=commonAttribute+"[@name='"+ xpathsplittext[1] +"']/value";
					
					}
					log.info("Attribute to Retrieve Node value is : "+fullAttributewithName);

					DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
					DocumentBuilder builder=factory.newDocumentBuilder();
					Document document=builder.parse(file);
					document.getDocumentElement().normalize();
					XPath xpath=XPathFactory.newInstance().newXPath();
					
					if(xpathaftersplittext == null){
						
						
						nodeList=(NodeList)xpath.compile(fullAttributewithName).evaluate(document,XPathConstants.NODESET);

						Resxfilevalue = nodeList.item(0).getTextContent();
						
					} else {
					
						nodeList=(NodeList)xpath.compile(fullAttributewithName1).evaluate(document,XPathConstants.NODESET);

						Resx_Value1 = nodeList.item(0).getTextContent();
						
						nodeList=(NodeList)xpath.compile(fullAttributewithName2).evaluate(document,XPathConstants.NODESET);

						Resx_Value2 = nodeList.item(0).getTextContent();

						Resxfilevalue = Resx_Value1 + " " + Resx_Value2;

					
					}
					
					System.out.println("Resxfilevalue : "+ Resxfilevalue);
					
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
						xpathValue = xpathValue.replace(splittedXpath, Resxfilevalue);
					}
					
					System.out.println("xpathValue : "+ xpathValue);
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
						fullAttributewithName=commonAttribute+"[@name='"+ xpathsplittext[1] +"']/value";
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
						fullAttributewithName=commonAttribute+"[@name='"+ xpathsplittext[1] +"']/value";
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
						
						System.out.println("xpathValue :" + xpathValue);
						} 
					
				}
		
		return xpathValue;

		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

}
