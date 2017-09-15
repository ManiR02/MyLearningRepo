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

public class USA_Pages extends FunctionLibrary{

	public synchronized String RetrieveTestDataValue(String filePath,String compName,String strColumnName,String gblrecordsCounter,int strExecEventFlag) throws Exception{
		String strData=null;
		ReadExcel readExcel=new ReadExcel(filePath);
		if(strExecEventFlag!=0){
			strData=readExcel.getCellData(compName, strColumnName, Integer.parseInt(gblrecordsCounter));
		}
		return strData;
	}

	public synchronized String ChangePlan(String locator){

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
			hs.put("services_Select", "id#loadType");
			hs.put("mobileNo_Textbox", "id#loadParameter");
			hs.put("loadSubscriber_Button", "id#btnLoadSubscriber");
			hs.put("staff_MenuDropdown", "xpath#//a[@class='menu-icon dropdown-toggle']");
			hs.put("Bundle_Icon", "xpath#//a[@class='catgLinkID' and @title='SettingResource>>Bundle']//span");
			hs.put("ChangePlan_Icon", "xpath#//div[@title='Change Plan']/following::*");
			hs.put("currentPlan", "id#currentPlan");
			hs.put("disabledcurrentPlan", "xpath#//input[@id='currentPlan' and @disabled='disabled']");
			hs.put("ddlnewPlan", "id#ddlnewPlan");
			hs.put("reason", "id#reason");
			hs.put("btnSubmit", "id#btnSubmit");
			//admin to submit request
			hs.put("btnChangeplanAccept", "id#btnChangeplanAccept");
			hs.put("btnChangeplanReset", "id#btnChangeplanReset");
			//Admin to approve/Reject:
			hs.put("btnChangeplanApprove", "id#btnChangeplanApprove");
			hs.put("btnChangeplanReject", "id#btnChangeplanReject");
			//Reject Reason Box:
			hs.put("rejectReason_Id", "id#rejectReason_Id");
			hs.put("planChngeHistory", "id#planChngeHistory");
			hs.put("responseMessage", "xpath#//label[@id='responseMessage' and (@class='true' or @class='false')]");
			hs.put("disabledMessage", "xpath#//label[contains(@style,'color')]");
			hs.put("close_button", "xpath#//div[@class='close-icon close-view-icon']");
			//Approval
			hs.put("pendingApproval_Body_Type", "xpath#//b[contains(text(),'SubscriberResources>>Type')]");
			hs.put("linkChangePlan", "xpath#//a[text()='HomeResources>>PAChangePlan']");
			//Full view
			hs.put("DOB_Icon", "xpath#//div[@class='su-expand-icon sub-view']");
			hs.put("lbl_Plan", "xpath#//*[@class='PrePostLocalPlan']/strong[contains(text(), 'Plan')]/../span[@class='ng-binding']");
			//History table:
			hs.put("tbl_ChangePlanHistory", "xpath#//div[@class='bundle-open1 changeplanHistory']//table");
			hs.put("btn_OkPlanHistory", "id#OkPlanHistory");
			hs.put("approvalCloseBtn", "xpath#//span[@class='close-icon']");
			
			String xpathValue = hs.get(locator);
			
			if(xpathValue.contains(">>")){
				
			
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
			}
		return xpathValue;
		
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

	public synchronized String Pending_MSISDN(String filePath,String compName,String gblrecordsCounter, String locator,String strMSISDN,int strEventFlag) throws Exception{
		String MSISDN=null;
		try{
			MSISDN =RetrieveTestDataValue(filePath, compName, strMSISDN,gblrecordsCounter, 1);
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("strPendingMSISDN", "xpath#//td[@aria-describedby='pendingItemTable_Msisdn']//a[@title='"+MSISDN+"']");
			return hs.get(locator);
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

	public synchronized String ChangePlanHistoryTable(String filePath,String compName,String gblrecordsCounter, String locator,String rowNumber,int strEventFlag) throws Exception{

		String rowNumberInTable=null;
		try{
			rowNumberInTable =RetrieveTestDataValue(filePath, compName, rowNumber,gblrecordsCounter, 1);
			Hashtable<String, String> hs = new Hashtable<String, String>();

			String rowType=null;
			if(rowNumberInTable.equalsIgnoreCase("header")||rowNumberInTable.equalsIgnoreCase("head")||rowNumberInTable.equalsIgnoreCase("primary")){
				rowType="th";
				rowNumberInTable="1";
			}else{
				rowType="td";
			}
			hs.put("tblChangePlan_Date", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[1]");
			hs.put("tblChangePlan_Time", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[2]");
			hs.put("tblChangePlan_CurrentPlan", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[3]");
			hs.put("tblChangePlan_PreviousPlan", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[4]");
			hs.put("tblChangePlan_Channel", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[5]");
			hs.put("tblChangePlan_Reason", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[6]");
			return hs.get(locator);
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}
}
