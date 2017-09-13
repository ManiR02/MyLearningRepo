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

public class Registration_POL extends FunctionLibrary {

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
			hs.put("txtNewRegistration", "id#subsParameter");
			hs.put("btnNewRegister", "id#btnNewRegister");
			hs.put("rdbPersonal", "id#rdopersonal");
			hs.put("lstPOLtitle", "id#POLtitle");
			hs.put("txtfirstName", "id#txtfirstName");
			hs.put("txtlastName", "id#txtlastName");
			hs.put("txtPolDOB", "id#txtPolDOB");
			hs.put("select_Personallang", "id#ddlPersonallang");
			hs.put("txtStreet", "id#txtStreet_1");
			hs.put("txtCity", "id#txtCity_1");
			hs.put("txtPostCode1", "id#txtCode_1");
			hs.put("txtPostCode2", "id#txtCodesuff_1");
			hs.put("txtHouseNo", "id#txtHouseNo_1");
			hs.put("listAddressResult", "id#lstAddressResult");
			hs.put("btnAcceptAddress", "id#btnAcceptAddress");
			hs.put("ModalSpace", "id#myModel");
			hs.put("Icon_SearchAddress", "xpath#//a[@id='btnFindAddressPers']//span[@class='glyphicon glyphicon-search']");	
			hs.put("rdomobile", "id#rdomobile");
			hs.put("LabelMSISDN", "id#MSISDN");
			hs.put("txtsimnumber", "id#txtsimnumber");
			hs.put("rdopesel", "id#rdopesel");
			hs.put("txtpesel", "id#txtpesel");
			hs.put("cbxMarketingSMS1", "id#cbxMarketingSMS1");		
			hs.put("chktermsconditions_1", "id#chktermsconditions_1");		
			hs.put("btnPOLcancel", "id#btnPOLcancel");		
			hs.put("btnPOLnext", "id#btnPOLnext");		
			hs.put("lblTitle", "id#lblTitle");	
			hs.put("lblfirstname", "id#lblfirstname");	
			hs.put("lbllastname", "id#lbllastname");	
			hs.put("lblDOB", "id#lblDOB");	
			hs.put("lblLanguage", "id#lblLanguage");	
			hs.put("lblStreet", "id#lblStreet");	
			hs.put("lblCity", "id#lblCity");	
			hs.put("lblcode", "id#lblcode");	
			hs.put("lblHouseNo", "id#lblHouseNo");	
			hs.put("lblMSISDN_1", "id#lblMSISDN_1");	
			hs.put("lblsimnumber_1", "id#lblsimnumber_1");	
			hs.put("lblidproof", "id#lblidproof");	
			hs.put("btnPOLback", "id#btnPOLback");	
			hs.put("btnPOLsubmit", "id#btnPOLsubmit");	
			hs.put("label_Regmessage", "xpath#//label[@id='btnCirclePOLReg' and @class='true']");	
			hs.put("label_RegErrmessage", "xpath#//label[@id='btnCirclePOLReg' and @class='false']");	
			hs.put("btnClose", "xpath#//div[@class='close-icon close-view-icon']");
			
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
