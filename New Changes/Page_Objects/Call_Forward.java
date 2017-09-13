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

public class Call_Forward extends FunctionLibrary {

	public synchronized String callForwardPage(String locator){

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

			hs.put("billingMenuDropdown", "xpath#//a[@class='menu-icon dropdown-toggle']");
			hs.put("billingButton", "xpath#//a[@title='SettingResource>>Billing']");
			hs.put("callFwdButton", "xpath#//div[@title='Call Forward']");
			hs.put("ticketID", "id#txtTicketId");
			hs.put("reason", "id#txtReason");
			hs.put("callForwarding", "xpath#//label[@class='i-switch bg-success']//input[@id='ChkCallForwarding']");
			hs.put("unConditional_Chkbox", "id#ChkUnConditional");
			hs.put("unConditional_Txtbox", "id#txtUnconditional");

			hs.put("noAnswer_Chkbox", "id#ChkNoAnswer");
			hs.put("noAnswer_Txtbox", "id#txtNoAnswer");
			hs.put("switchedOff_Chkbox", "id#ChkSwitchedOff");
			hs.put("switchedOff_Txtbox", "id#txtSwitchedOff");
			hs.put("busy_Chkbox", "id#ChkBusy");
			hs.put("busy_Txtbox", "id#txtBusy");
			hs.put("checkAns_ChkBox", "xpath#//input[@id='ChkNoAnswer' and contains(@checked, 'checked')]");
			hs.put("callFwd_Enabled", "xpath#//input[@id='ChkCallForwarding' and contains(@checked, 'checked')]");

			hs.put("unCond_TxtboxDisabled", "xpath#//input[@id='txtUnconditional' and contains(@disabled, 'disabled')]");

			hs.put("submitButton", "id#btnSubmitCF");
			hs.put("confirm_Message", "xpath#//label[@id='lblError' and contains(@style, 'rgb')]");
			hs.put("validationMsg", "xpath#//b[@class='false']");

			//Validations
			hs.put("valTitle", "xpath#//input[@id='txtTicketId' and contains(@aria-required, 'true')]");
			hs.put("valReason", "xpath#//input[@id='txtReason' and contains(@aria-required, 'true')]");
			hs.put("valNoAnswer", "xpath#//input[@id='txtNoAnswer' and contains(@aria-required, 'true')]");

			String xpathValue = hs.get(locator);
			
			if(xpathValue.contains(">>")){
			
				if(xpathValue.contains("title=")){
				
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
		}
		return null;
	}

}
