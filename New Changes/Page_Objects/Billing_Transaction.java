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

public class Billing_Transaction extends FunctionLibrary {

	public synchronized String billingTransactionPage(String locator){
		
		String multiLanguageExcelPath = System.getProperty("user.dir")+property.getProperty("resourceMgmtfilekeyPath");

		String languageName = property.getProperty("Language_Required");
		
		ReadExcel excel=new ReadExcel(multiLanguageExcelPath);
		
		String[] xpathsplittext = null;
		String resourceFileName = null;
		String filePathLocation = null;
		NodeList nodeList=null;
		
		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();

			hs.put("billingMenuDropdown", "xpath#//a[@class='menu-icon dropdown-toggle']");
			hs.put("billingButton", "xpath#//a[@title='SettingResource>>Billing']");
			hs.put("billingTnsBtn", "xpath#//div[@title='Billing Transaction']");
			hs.put("ticketID", "id#txtTicketID");
			hs.put("status", "id#transStatus");
			hs.put("type", "id#transType");
			hs.put("fromDate", "id#FromdatePick");
			hs.put("toDate", "id#TodatePick");
			hs.put("search", "id#btnLoad");
			hs.put("reset", "id#btnResetTrans");
			hs.put("tableView", "id#gview_mainTransTable");


			//Get Values from tables

			hs.put("verifyTnsID", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']");
			hs.put("typeVerify", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Type']");
			hs.put("MSISDN", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_MSISDN']");
			hs.put("debitAmt", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_DebitAmt']");
			hs.put("reCreditAmt", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_RecreditAmt']");

			hs.put("dialledNo", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_DialledNo']");
			hs.put("dialledDate", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_DialledDate']");
			hs.put("duration", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Duration']");

			hs.put("ticketIDVerify", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_TicketId']");
			hs.put("reason", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Reason']");
			hs.put("comments", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Comments']");
			hs.put("submitBy", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_SubmittedBy']");
			hs.put("submitDate", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_RequestDate']");
			hs.put("authoriseDate", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Authoriseddate']");
			hs.put("authoriseBy", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_AuthorisedBy']");
			hs.put("authoriseStatus", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_AuthorisedStatus']");
			hs.put("prevBal", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_OldBalance']");
			hs.put("curBal", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_NewBalance']");
			hs.put("channel", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Channel']");

			hs.put("MSISDNFrom", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_MSISDNFrom']");
			hs.put("MSISDNTo", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_MSISDNTo']");
			hs.put("transferAmt", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_TransferAmt']");
			hs.put("adminCmts", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_AdminComments']");

						
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

					xpathValue =  xpathValue.replaceAll(splittedXpath, nodeList.item(0).getTextContent());

					} 
				}
			}
			
			return xpathValue;			

		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

	public synchronized String RetrieveTestDataValue(String filePath,String compName,String strColumnName,String gblrecordsCounter,int strExecEventFlag) throws Exception{
		String strData=null;
		ReadExcel readExcel=new ReadExcel(filePath);


		if(strExecEventFlag!=0){
			strData=readExcel.getCellData(compName, strColumnName, Integer.parseInt(gblrecordsCounter));
		}
		return strData;
	}

	public synchronized String tableValues(String filePath,String compName,String gblrecordsCounter, String locator, String tableValues) throws Exception{

		String value =RetrieveTestDataValue(filePath, compName, tableValues,gblrecordsCounter, 1);
		String getRunTime = Runtimevalue.getProperty(value);

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("transactionID", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@style, 'height')]//td//a[text()='"+getRunTime+"']");
			return hs.get(locator);
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}	

}
