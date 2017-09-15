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

public class SwapImsi_Page extends FunctionLibrary {

	public synchronized String SwapImsi_PageObj(String locator){

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
			hs.put("simButton", "xpath#//a[@title='RegistrationResources>>Sim']");
			hs.put("SWAPImsiLink", "xpath#.//*[@id='SIM_ImsiSwap']//div[@class='thumbnail-icon']");
			hs.put("Submit_button", "id#btnSubmit");
			hs.put("NewMSISDN", "id#newMSISDN");
			hs.put("newICCID", "id#newICCID");
			hs.put("PAN_Number", "id#panNumber");
			hs.put("CIP", "id#cip");
			hs.put("AlertMessage", "id#responseMessage");
			hs.put("FrequentDialedNumber", "id#frequentCalNumber");
			hs.put("CIPErrorMessage", "id#cip-error");
			hs.put("PANErrorMessage", "id#panNumber-error");
			hs.put("Reject_Button", "id#btnReject");
			hs.put("SwapIMSIApprovalLink", "xpath#.//a[text()='HomeResources>>PASwapIMSI']");
			hs.put("RequestID", "xpath#.//*[@id='gs_Id']");	
			hs.put("PreAuthorize_button", "id#btnPendingApp");	
			hs.put("PreAuthorize_button_admin", "id#btnApprove");	
			
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
		return xpathValue;
		
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

	public synchronized String SwapIMSI_Pending_Approvals(String locator, String dynamicValue){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("pendingApproval_SwapIMSI", "xpath#//td[@title='"+dynamicValue+"']/following-sibling::td[@aria-describedby='pendingItemTable_Msisdn']");
			return hs.get(locator);
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

}
