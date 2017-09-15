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

public class ValueAddedServices extends FunctionLibrary{

	public synchronized String RetrieveTestDataValue(String filePath,String compName,String strColumnName,String gblrecordsCounter,int strExecEventFlag) throws Exception{
		String strData=null;
		ReadExcel readExcel=new ReadExcel(filePath);
		if(strExecEventFlag!=0){
			strData=readExcel.getCellData(compName, strColumnName, Integer.parseInt(gblrecordsCounter));
		}
		return strData;
	}

	public synchronized String SafeCustody(String locator){

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
			hs.put("VAS_Icon", "xpath#//a[@class='catgLinkID' and @title='VAS']//span");
			hs.put("safeCustody_Icon", "xpath#//div[@title='MSISDN Safe Custody']/following::*");
			hs.put("lblStatus", "id#lblStatus");
			hs.put("txtEmail", "id#txtEmail");
			hs.put("input_StartDate", "id#StartDate");
			hs.put("input_StartDate_JS", "StartDate");
			hs.put("lst_SafeDateYear", "id#SafeDateYear");
			hs.put("lst_SafeDateMonth", "id#SafeDateMonth");
			hs.put("lbl_EndDate", "id#EndDate");
			hs.put("lbl_SafeHoldingFee", "id#SafeHoldingFee");
			hs.put("btnConfirm", "id#btnConfirm");
			hs.put("lbl_moneyType", "xpath#//input[@id='SafeHoldingFee']/following::*");
			hs.put("lbl_Notes", "xpath#//strong[contains(.,'TopupResources>>Notes')]");
			hs.put("lbl_NotesContent", "xpath#//strong[contains(.,'TopupResources>>Notes')]/following::*");
			hs.put("lblConfirmStatus", "id#spStatus");
			hs.put("lblConfirmEmail", "id#spEmail");
			hs.put("lblConfirmStartDate", "id#spStartDate");
			hs.put("lblConfirmSafeDate", "id#spSafeDate");
			hs.put("lblConfirmEndDate", "id#spEndDate");
			hs.put("lblConfirmSafeHoldingFee", "id#spSafeHoldingFee");
			hs.put("lblConfirmMoneySymbol", "xpath#//label[@id='spSafeHoldingFee']/following::*");
			hs.put("btnSubmit", "id#btnSubmit");
			hs.put("btnBack", "id#btnBack");
			//Date:
			hs.put("selectYear", "xpath#//select[@data-handler='selectYear']");
			hs.put("selectMonth", "xpath#//select[@data-handler='selectMonth']");
			hs.put("selectDate31", "linktext#31");
			hs.put("NumberLockerMsg", "id#NumberLockerMsg");
			hs.put("close_button", "xpath#//div[@class='close-icon close-view-icon']");
			
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
