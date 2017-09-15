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

public class Voucher_Status extends FunctionLibrary {

	public synchronized String Voucher_Status_Page(String locator){

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
			hs.put("Topup_Menu", "xpath#//a[@class='catgLinkID']//span[@class='topup-menu-image']");
			hs.put("Voucher_Status", "xpath#//div[@class='voucher-status-thumbnail']//div[@class='thumbnail-icon']");
			hs.put("Voucher_Type", "id#voucherType");
			hs.put("Apply", "id#btnVoucherStatus");
			hs.put("Reset", "id#btnResetVoucherStatus");
			hs.put("Mandatory_Alert_Message", "id#resultdiv");
			hs.put("Voucher_Code", "id#voucherCode");
			hs.put("Alert_Message", "xpath#.//*[@id='dvVoucherDetails']//*[contains(@class, 'errorMessage')]");
			hs.put("Voucher_Number", "xpath#.//*[@id='dvVoucherDetails']//h4/b");
			hs.put("Status", "xpath#//strong[contains(text(),'VoucherResources>>Status')]/../../div[2]");
			hs.put("Activation_Date", "xpath#//strong[contains(text(),'VoucherResources>>ActivationDate')]/../../div[2]");
			hs.put("Used_MSISDN", "xpath#//strong[contains(text(),'VoucherResources>>UsedMSISDN')]/../../div[2]");
			hs.put("Blocked_Date", "xpath#//strong[contains(text(),'VoucherResources>>BlockedDate')]/../../div[2]");
			hs.put("Blocked_Reason", "xpath#//strong[contains(text(),'VoucherResources>>BlockedReason')]/../../div[2]");
			hs.put("Recharge_Date", "xpath#//strong[contains(text(),'VoucherResources>>RechargeDate')]/../../div[2]");
			hs.put("Reseller_ID", "xpath#//strong[contains(text(),'VoucherResources>>ResellerID')]/../../div[2]");
			hs.put("Activation_Level", "xpath#//strong[contains(text(),'VoucherResources>>ResellerLevel')]/../../div[2]");
			hs.put("Reseller_Message", "xpath#//strong[contains(text(),'VoucherResources>>ResellerMsg')]/../../div[2]");
			hs.put("Plan_Name", "xpath#//strong[contains(text(),'VoucherResources>>PlanName')]/../../div[2]");
			hs.put("Bundle", "xpath#//strong[contains(text(),'VoucherResources>>Bundlecode')]/../../div[2]");
			hs.put("Face_Value", "xpath#//strong[contains(text(),'VoucherResources>>FaceValue')]/../../div[2]");
			hs.put("Onnet_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[2]/td[2]");
			hs.put("Onnet_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[2]/td[3]");
			hs.put("MTOnnet_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[3]/td[2]");
			hs.put("MTOnnet_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[3]/td[3]");
			hs.put("MTOffnet_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[4]/td[2]");
			hs.put("MTOffnet_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[4]/td[3]");
			hs.put("Zone1_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[6]/td[2]");
			hs.put("Zone1_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[6]/td[3]");
			hs.put("Zone2_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[7]/td[2]");
			hs.put("Zone2_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[7]/td[3]");
			hs.put("Zone3_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[8]/td[2]");
			hs.put("Zone3_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[8]/td[3]");
			hs.put("close_button", "xpath#//div[@class='close-icon close-view-icon']");
			
			hs.put("Total_Bundle_Price", "xpath#//strong[contains(text(),'VoucherResources>>TotalBundlePrice')]/../../div[2]");
			hs.put("Bundle_Name_Popup", "xpath#//strong[contains(text(),'VoucherResources>>Bundlename')]/../../div[3]");
			hs.put("Bundle_Code_Popup", "xpath#//strong[contains(text(),'VoucherResources>>Bundlecode')]/../../div[3]");
			hs.put("Validity_Popup", "xpath#//strong[contains(text(),'VoucherResources>>Validity')]/../../div[3]");
			hs.put("Bundle_Mode_Popup", "xpath#//strong[contains(text(),'VoucherResources>>Bundlemode')]/../../div[3]");
			hs.put("Bundle_Type_Popup", "xpath#//strong[contains(text(),'VoucherResources>>Bundletype')]/../../div[3]");
			hs.put("Face_Value_Popup", "xpath#//strong[contains(text(),'VoucherResources>>FaceValue')]/../../div[3]");
			hs.put("Onnet_Mins_Popup", "xpath#//strong[contains(text(),'VoucherResources>>Freemins')]/../../following-sibling::div[1]/div[3]");
			hs.put("Offnet_Mins_Popup", "xpath#//strong[contains(text(),'VoucherResources>>Freemins')]/../../following-sibling::div[2]/div[3]");
			hs.put("Onnet_SMS_Popup", "xpath#//strong[contains(text(),'VoucherResources>>FreeSMS')]/../../following-sibling::div[1]/div[3]");
			hs.put("Offnet_SMS_Popup", "xpath#//strong[contains(text(),'VoucherResources>>FreeSMS')]/../../following-sibling::div[2]/div[3]");
			hs.put("Free_Data_Popup", "xpath#//strong[contains(text(),'VoucherResources>>Freedata')]/../../div[3]");
			hs.put("Close_Popup", "xpath#.//*[@id='myModal2']//button[@class='crm-btn red']");

			String xpathValue = hs.get(locator);
			
			if(xpathValue.contains(">>")) {
			
				if(xpathValue.contains("text()=")){
			
				System.out.println("text()");	
					
				Pattern pattern = Pattern.compile("xpath(.*)text(.*)='([\\sa-zA-Z_->]+)']");
				
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
		return xpathValue;
		

		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}
}
