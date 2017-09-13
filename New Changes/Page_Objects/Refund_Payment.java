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

public class Refund_Payment extends FunctionLibrary{

	public synchronized String Refund_Payment_Page(String locator){
		
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
			hs.put("Load_Type", "id#loadType");
			hs.put("Load_Parameter", "id#loadParameter");
			hs.put("Load_Subscriber", "id#btnLoadSubscriber");
			hs.put("Menu_Items", "xpath#//a[@class='menu-icon dropdown-toggle']");
			hs.put("Bundle_Menu", "xpath#//a[@class='catgLinkID']//span[@class='bundles-menu-image']");
			hs.put("Refund_Payment", "xpath#//div[@class='refund-topup-thumbnail']//div[@class='thumbnail-icon']");
			hs.put("Refund_Bundle_Grid", "id#dvRefundBundle");
			hs.put("Refund_Bundle_Button", "xpath#.//*[@id='dvRefundBundle']//span[@class='glyphicon glyphicon-retweet']");
			hs.put("TransactionID", "xpath#.//*[@id='bundleRefundForm']//strong[contains(text(),'TopupResources>>TransactionID')]/../../div[2]");
			hs.put("Product", "xpath#.//*[@id='bundleRefundForm']//strong[contains(text(),'AccountResources>>Product')]/../../div[2]");
			hs.put("OBA_Credit_Allocated", "xpath#.//*[@id='bundleRefundForm']//strong[contains(text(),'BundleResources>>OBACreditAllocated')]/../../div[2]");
			hs.put("OBA_Credit_Used", "xpath#.//*[@id='bundleRefundForm']//strong[contains(text(),'BundleResources>>OBACreditUsed')]/../../div[2]");
			hs.put("Tax_Amount", "id#bundleTaxAmount");
			hs.put("VAT_Amount", "id#bundleVatAmount");
			hs.put("Refund_Amount", "id#bundleRefundAmount");
			hs.put("Total_Amount", "xpath#.//*[@id='bundleRefundForm']//strong[contains(text(),'SubscriberResources>>TotalAmount')]/../../div[2]");
			hs.put("Refund_To", "id#bundleRefundTo");
			hs.put("Action", "id#bundleRefundAction");
			hs.put("Refund_Reason", "id#bundleRefundReason");
			hs.put("Confirm", "id#btnSubmitRefundBundle");
			hs.put("Confirm_Message", "id#lblErrorBR");
			hs.put("Close", "xpath#.//*[@id='dvBundleProp']//button[@class='crm-btn red']");
			hs.put("Loading_Image", "xpath#.//*[@id='dvBundleProp']//b[@class='false glyphicon-rotate']");
			hs.put("Allowance_Button", "xpath#.//*[@id='dvRefundBundle']//span[@class='glyphicon glyphicon-new-window']");
			hs.put("Bundle_Type_Overview", "xpath#.//*[@id='bundleOverview']//th[contains(text(),'BundleResources>>BundleType')]/../th[2]");
			hs.put("Auto_Renewal_Status", "xpath#.//*[@id='bundleOverview']//th[contains(text(),'SubscriberResources>>AutoRenewalStatus')]/../td");
			hs.put("Mode_Of_Renewal", "xpath#.//*[@id='bundleOverview']//th[contains(text(),'SubscriberResources>>ModeofRenewal')]/../td");
			hs.put("Expiry_Date_Overview", "xpath#.//*[@id='bundleOverview']//th[contains(text(),'SubscriberResources>>ExpDate')]/../td");
			hs.put("Status_Tab", "xpath#.//*[@id='dvTopupBundle']/ul/li/a[contains(@href, 'dvRefundStatus')]");
			hs.put("Refund_Status_Grid", "id#dvRefundStatus");
			hs.put("Request_Id_Link", "xpath#.//*[@id='dvRefundStatus']/table/tbody/tr/td[@ng-click = 'RefundStatusDetails(refundStatusInfo)']/b");
			hs.put("RefundStatus_RequestID", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>RequestID')]/../../div[2]");
			hs.put("RefundStatus_Status", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>Status')]/../../div[2]");
			hs.put("RefundStatus_TransactionID", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'TopupResources>>TransactionID')]/../../div[2]");
			hs.put("RefundStatus_Topuptype", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>TopupType')]/../../div[2]");
			hs.put("RefundStatus_OBACreditAllocated", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'BundleResources>>OBACreditAllocated')]/../../div[2]");
			hs.put("RefundStatus_OBACreditUsed", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'BundleResources>>OBACreditUsed')]/../../div[2]");
			hs.put("RefundStatus_Taxamount", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'TopupResources>>TaxAmount')]/../../div[2]");
			hs.put("RefundStatus_Vatamount", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'TopupResources>>VatAmount')]/../../div[2]");
			hs.put("RefundStatus_Refundamount", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'TopupResources>>RefundAmount')]/../../div[2]");
			hs.put("RefundStatus_Refundto", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'BundleResources>>RefundTo')]/../../div[2]");
			hs.put("RefundStatus_Refundreason", "id#reInRefundReason");
			hs.put("RefundStatus_Comments", "id#reInRefundComment");
			hs.put("RefundStatus_Submittedby", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>SubmitBy')]/../../div[2]");
			hs.put("RefundStatus_Submitteddate", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>SubmitDate')]/../../div[2]");
			hs.put("RefundStatus_Reinitiationdate", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>ReInititiationDate')]/../../div[2]");
			hs.put("RefundStatus_Authorisedby", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>AuthorizedBy')]/../../div[2]");
			hs.put("RefundStatus_Authoriseddate", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>AuthorizedDate')]/../../div[2]");
			hs.put("RefundStatus_Rejectedby", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>RejectBy')]/../../div[2]");
			hs.put("RefundStatus_Rejecteddate", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>RejectDate')]/../../div[2]");
			hs.put("RefundStatus_Requestcount", "xpath#.//*[@id='refundStatusForm']//strong[contains(text(),'AccountResources>>RequestCount')]/../../div[2]");
			hs.put("RefundStatus_Reinitiate", "id#btnSubmitRefundReInitiate");
			hs.put("RefundStatus_Close", "xpath#.//*[@id='dvReInitiateProp']//button[@class='crm-btn red']");
			hs.put("RefundStatus_Reinitiate_Message", "id#lblErrorRI");
			hs.put("Reinitiate_Loading_Image", "xpath#.//*[@id='dvReInitiateProp']//b[@class='false glyphicon-rotate']");
			hs.put("Voice_Tab", "xpath#.//*[@id='bundlePropCRM']/ul/li/a[contains(@href, 'bundleVoice')]");
			hs.put("SMS_Tab", "xpath#.//*[@id='bundlePropCRM']/ul/li/a[contains(@href, 'bundleSMS')]");
			hs.put("VAS_Data_Tab", "xpath#.//*[@id='bundlePropCRM']/ul/li/a[contains(@href, 'bundleVasData')]");
			hs.put("Voice_MO_Onnet", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>ONNET')]/../th[2]");
			hs.put("Voice_MT_Onnet", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>ONNET')]/../th[3]");
			hs.put("Voice_MT_Offnet", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>MTOFFNET')]/../th[3]");
			hs.put("Voice_MO_Zone_1", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>Zone1')]/../th[2]");
			hs.put("Voice_MT_Zone_1", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>Zone1')]/../th[3]");
			hs.put("Voice_MO_Zone_2", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>Zone2')]/../th[2]");
			hs.put("Voice_MT_Zone_2", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>Zone2')]/../th[3]");
			hs.put("Voice_MO_Zone_3", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>Zone3')]/../th[2]");
			hs.put("Voice_MT_Zone_3", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>Zone3')]/../th[3]");
			hs.put("Voice_Roam", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>Roam')]/../th[2]");
			hs.put("Voice_Local_Roam", "xpath#.//*[@id='bundleVoice']//th[contains(text(),'SubscriberResources>>LocalRoam')]/../th[2]");
			hs.put("SMS_MO_Onnet", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>ONNET')]/../th[2]");
			hs.put("SMS_MT_Onnet", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>ONNET')]/../th[3]");
			hs.put("SMS_MT_Offnet", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>MTOFFNET')]/../th[3]");
			hs.put("SMS_MO_Zone_1", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>Zone1')]/../th[2]");
			hs.put("SMS_MT_Zone_1", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>Zone1')]/../th[3]");
			hs.put("SMS_MO_Zone_2", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>Zone2')]/../th[2]");
			hs.put("SMS_MT_Zone_2", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>Zone2')]/../th[3]");
			hs.put("SMS_MO_Zone_3", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>Zone3')]/../th[2]");
			hs.put("SMS_MT_Zone_3", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>Zone3')]/../th[3]");
			hs.put("SMS_Roam", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>Roam')]/../th[2]");
			hs.put("SMS_Local_Roam", "xpath#.//*[@id='bundleSMS']//th[contains(text(),'SubscriberResources>>LocalRoam')]/../th[2]");
			hs.put("VAS_Bundle_Balance", "xpath#.//*[@id='bundleVasData']//th[contains(text(),'SubscriberResources>>BundleBalance')]/../td");
			hs.put("VAS_BParty_Number", "xpath#.//*[@id='bundleVasData']//th[contains(text(),'SubscriberResources>>BPartyNumber')]/../th[2]/p");
			hs.put("VAS_Add_On_Bundle", "xpath#.//*[@id='bundleVasData']//th[contains(text(),'SubscriberResources>>AddonBundle')]/../th[2]");
			hs.put("VAS_Data", "xpath#.//*[@id='bundleVasData']//th[contains(text(),'SubscriberResources>>Data')]/../td");
			hs.put("Topup_Tab", "xpath#.//*[@id='dvTopupBundle']/ul/li/a[contains(@href, 'dvRefundTopup')]");
			hs.put("Refund_Topup_Grid", "id#dvRefundTopup");
			hs.put("Topup_TransactionID", "xpath#.//*[@id='topupRefundForm']//strong[contains(text(),'TopupResources>>TransactionID')]/../../div[2]");
			hs.put("Topup_Product", "xpath#.//*[@id='topupRefundForm']//strong[contains(text(),'AccountResources>>Product')]/../../div[2]");
			hs.put("Topup_Tax_Amount", "id#topupTaxAmount");
			hs.put("Topup_VAT_Amount", "id#topupVatAmount");
			hs.put("Topup_Refund_Amount", "id#topupRefundAmount");
			hs.put("Topup_Total_Amount", "xpath#.//*[@id='topupRefundForm']//strong[contains(text(),'SubscriberResources>>TotalAmount')]/../../div[2]");
			hs.put("Topup_Refund_Reason", "id#topupRefundReason");
			hs.put("Topup_Confirm", "id#btnSubmitRefundTopup");
			hs.put("Topup_Confirm_Message", "id#lblErrorTR");
			hs.put("Topup_Close", "xpath#.//*[@id='dvTopupProp']//button[@class='crm-btn red']");
			hs.put("Topup_Loading_Image", "xpath#.//*[@id='dvTopupProp']//b[@class='false glyphicon-rotate']");
			hs.put("Refund_Topup_Button", "xpath#.//*[@id='dvRefundTopup']//span[@class='glyphicon glyphicon-retweet']");
			hs.put("close_button", "xpath#//li[@title='SubscriberResources>>IconClose']");
			hs.put("Pending_Approval_Grid", "id#gbox_pendingItemTable");
			hs.put("Close_Pending_Approval", "xpath#//div[@class='close-icon close-view-icon']");
			
			String xpathValue = hs.get(locator);
			
			if(xpathValue.contains(">>")) {
			
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
						xpathValue = xpathValue.replace(splittedXpath, Resxfilevalue);
					}
					
		
					}
				
				} else if(xpathValue.contains("title=")){
					
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
			}
			
			System.out.println(xpathValue);
		return xpathValue;
		

		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}
	
	

}
