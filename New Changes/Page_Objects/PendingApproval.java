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

public class PendingApproval extends FunctionLibrary{
	

	public synchronized String pendingApproval_Page(String locator){
		
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

			//hs.put("pendingApproval_Body", "xpath#//*[@id='pendingSlimScroll']/table/tbody");
			hs.put("pendingApproval_Body", "xpath#//div[@class='pending-wrapper']//*//*");
			hs.put("staff_TopUp", "xpath#//a[text()='HomeResources>>PAStaffTopup']");
			hs.put("approve_Button", "id#btnPendingApp");
			hs.put("reject_Button", "id#btnReject");
			hs.put("pendingApproval_Text", "id#crmPageHeader");
			hs.put("nextBtn", "xpath#//span[@class='ui-icon ui-icon-seek-next']");
			hs.put("pageCount", "xpath#//span[@id='sp_1_pendingItemPager']");
			hs.put("requestID_Text", "xpath#//input[@id='gs_Id']");
			hs.put("repsonse_Message", "id#responseMessage");
			hs.put("sim_block", "xpath#//a[text()='HomeResources>>PASIMBlock']");
			hs.put("sim_unblock", "xpath#//a[text()='HomeResources>>PASIMUnBlock']");
			hs.put("Credit", "xpath#//a[text()='HomeResources>>PACredit']");
			hs.put("Credit_Approve_Button", "id#btnApprove");
			hs.put("Credit_Reject_Button", "id#btnReject");
			hs.put("Credit_TicketID", "id#ReCreditDetails_TicketId");
			hs.put("Credit_Amount", "id#ReCreditDetails_RecreditAmt");
			hs.put("Credit_Reason", "id#ddlReason");
			hs.put("Credit_Comments", "id#txtComment");
			hs.put("Credit_Admin_Comments", "id#txtAdminComment");
			hs.put("Credit_Confirm_Message", "id#NumberLockerMsg");
			hs.put("Credit_Old_Balance_Message", "id#OldBalanceMsg");
			hs.put("Credit_New_Balance_Message", "id#NewBalanceMsg");
			hs.put("Debit", "xpath#//a[text()='HomeResources>>PADebit']");
			hs.put("balanceTransfer", "xpath#//a[text()='HomeResources>>PABalanceTransfer']");
			hs.put("partialbalanceTransfer", "xpath#//a[text()='HomeResources>>PAPartialBalanceTransfer']");
			hs.put("PBT_Transfer_Amount", "id#txtamount");
			hs.put("PBT_toMSISDN", "id#toMSISDN");
			hs.put("Partial_Balance_Reason", "id#PartialTransferReason");
			hs.put("PBT_ticketID", "id#ticketID");
			hs.put("PBT_Comments", "id#ticketComment");
			hs.put("PBT_Admin_Comments", "id#PaComments");
			hs.put("PBT_Confirm_Message", "id#lblbBalTransMessage");
			hs.put("pendingApproval_Body_Type", "xpath#//b[contains(text(),'HomeResources>>PAType')]");
			hs.put("OBAPlanUpgrade", "xpath#//a[text()='HomeResources>>PAOBAPlanUpgrade']");
			hs.put("obaAddCredit", "xpath#//a[text()='HomeResources>>PAOBAAdditionalCredit']");
			hs.put("Stafftopup_Approve_Button", "id#btnPendingApp");
			hs.put("Stafftopup_Reject_Button", "id#btnReject");
			hs.put("Stafftopup_TicketID", "id#txtTicketId");
			hs.put("Stafftopup_Amount", "id#txttopupamt");
			hs.put("Stafftopup_Reason", "id#ddlReason");
			hs.put("Stafftopup_Comments", "id#txtComments");
			hs.put("Stafftopup_Confirm_Message", "id#bStaffTopupsMessage");
			hs.put("Stafftopup_Topup_Type", "id#ddlTopupType");
			hs.put("Stafftopup_Bundle", "id#ddlBundle");
			hs.put("Stafftopup_Bundle_Autorenewal", "id#chkBundleARenewal");
			hs.put("Stafftopup_radio_StaffTopup", "id#rdbStaffTopup");
			hs.put("VIP_staff_TopUp", "xpath#//a[text()='HomeResources>>PAVIPStaffTopup']");
			hs.put("Auto_Topup_Checkbox", "id#chkAutoTopup");
			hs.put("Threshold_Limit", "id#txtThresholdLimit");
			hs.put("Frequency", "id#ddlFrequency");
			hs.put("radio_Account_Balance", "id#rdbAccBalance");
			hs.put("Refund_Payment", "xpath#//a[text()='HomeResources>>PARefundPayment']");
			hs.put("Refund_Payment_Accept_Button", "id#btnSubmitRefundAuthorise");
			hs.put("Refund_Payment_Reject_Button", "id#btnRejectRefundAuthorise");
			hs.put("Refund_Payment_RequestID", "xpath#.//*[@id='refundAuthoriseForm']//strong[contains(text(),'TopupResources>>RequestID')]/../../div[2]");
			hs.put("Refund_Payment_Transaction_ID", "xpath#.//*[@id='refundAuthoriseForm']//strong[contains(text(),'TopupResources>>TransactionID')]/../../div[2]");
			hs.put("Refund_Payment_Topup_Type", "xpath#.//*[@id='refundAuthoriseForm']//strong[contains(text(),'TopupResources>>TopupType')]/../../div[2]");
			hs.put("Refund_Payment_OBA_Credit_Allocated", "xpath#.//*[@id='refundAuthoriseForm']//strong[contains(text(),'BundleResources>>OBACreditAllocated')]/../../div[2]");
			hs.put("Refund_Payment_OBA_Credit_Used", "xpath#.//*[@id='refundAuthoriseForm']//strong[contains(text(),'BundleResources>>OBACreditUsed')]/../../div[2]");
			hs.put("Refund_Payment_Refund_To", "xpath#.//*[@id='refundAuthoriseForm']//strong[contains(text(),'BundleResources>>RefundTo')]/../../div[2]");
			hs.put("Refund_Payment_TAX_Amount", "id#refundTaxAmount");
			hs.put("Refund_Payment_VAT_Amount", "id#refundVatAmount");
			hs.put("Refund_Payment_Refund_Amount", "id#refundTotalAmount");
			hs.put("Refund_Payment_Action", "id#ddlRefundAction");
			hs.put("Refund_Payment_Refund_Reason", "id#authRefundReason");
			hs.put("Refund_Payment_Comments", "id#authRefundComment");
			hs.put("Refund_Payment_Submitted_By", "xpath#.//*[@id='refundAuthoriseForm']//strong[contains(text(),'AccountResources>>SubmitBy')]/../../div[2]");
			hs.put("Refund_Payment_Submitted_Date", "xpath#.//*[@id='refundAuthoriseForm']//strong[contains(text(),'AccountResources>>SubmitDate')]/../../div[2]");
			hs.put("Refund_Payment_Reinitiation_Date", "xpath#.//*[@id='refundAuthoriseForm']//strong[contains(text(),'AccountResources>>ReInititiationDate')]/../../div[2]");
			hs.put("Refund_Payment_Request_Count", "xpath#.//*[@id='refundAuthoriseForm']//strong[contains(text(),'AccountResources>>RequestCount')]/../../div[2]");
			hs.put("Refund_Payment_Message", "xpath#.//*[@id='lblErrorAR'][@class='true']");
			
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

	public synchronized String RetrieveTestDataValue(String filePath,String compName,String strColumnName,String gblrecordsCounter,int strExecEventFlag) throws Exception{
		String strData=null;
		ReadExcel readExcel=new ReadExcel(filePath);

		if(strExecEventFlag!=0){
			strData=readExcel.getCellData(compName, strColumnName, Integer.parseInt(gblrecordsCounter));
		}
		return strData;
	}

	public synchronized String staff_TopUp_Pending_Approvals(String filePath,String compName,String gblrecordsCounter,String locator, String dynamicValue){

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
			String envName =RetrieveTestDataValue(filePath, compName, dynamicValue,gblrecordsCounter, 1);
			String dynamicValueToEnter= Runtimevalue.getProperty(envName);
			
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("pendingApproval_MSISDN", "xpath#//text()[normalize-space()='TopupResources>>RequestID']/ancestor::div/following-sibling::div//td[@title='"+dynamicValueToEnter+"']/following-sibling::td[@aria-describedby='pendingItemTable_Msisdn']//a");
		
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
				
				} else if(xpathValue.contains("normalize-space()=")){
					
					System.out.println("normalize-space()");	
						
					Pattern pattern = Pattern.compile("(.*)normalize-space(.*)='(.*)']/ancestor");
					
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
		}
		return null;
	}

	public synchronized String Sim_Block_Pending_Approvals(String filePath,String compName,String gblrecordsCounter,String locator, String dynamicValue){

		try{
			String envName =RetrieveTestDataValue(filePath, compName, dynamicValue,gblrecordsCounter, 1);
			String dynamicValueToEnter= Runtimevalue.getProperty(envName);
			
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("pendingApproval_SimBlockRID", "xpath#//td[@title='"+dynamicValueToEnter+"']/following-sibling::td[@aria-describedby='pendingItemTable_Msisdn']");
			return hs.get(locator);
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

	public synchronized String pendingApproval_Page_ScrollBar(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("scrollBar_JS", "//div[@class='slimScrollBar']");
			String locate = hs.get(locator);
			return locate;

		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

}
