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

public class OBA_Credit extends FunctionLibrary {

	public synchronized String obaCreditPage(String locator){

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

			hs.put("bundleButton", "xpath#//a[@title='SettingResource>>Bundle']");
			hs.put("obaCreditBtn", "xpath#//div[@title='OBA Credit Payment']");
			hs.put("bundleDataTbl", "id#tblBundleData");
			hs.put("noRecords", "xpath#//div[@id='dvBundleData']/b");
			hs.put("bundleName", "id#hdnBundleName");
			hs.put("expiryDate", "xpath#//input[@id='hdnBundleName']/parent::th/following-sibling::th[1]");
			hs.put("autoRenew", "xpath#//input[@id='hdnBundleName']/parent::th/following-sibling::th[2]");
			hs.put("mode", "xpath#//input[@id='hdnBundleName']/parent::th/following-sibling::th[3]");
			hs.put("allocated", "xpath#//input[@id='hdnBundleName']/parent::th/following-sibling::th[4]");
			hs.put("remaining", "xpath#//input[@id='hdnBundleName']/parent::th/following-sibling::th[5]");
			hs.put("consumed", "xpath#//input[@id='hdnBundleName']/parent::th/following-sibling::th[6]");
			hs.put("activateBtn", "id#aOBARenewal");
			hs.put("renewalChange", "xpath#//button[@id='closeRenewalChangeModal']/following-sibling::h4[@class='modal-title']");
			hs.put("radDeactiveBtn", "id#rdbDeActive");
			hs.put("radioActiveBtn", "id#rdbActive");
			hs.put("renSubmit", "id#btnRenewalSubmit");
			hs.put("confirmMsg", "xpath#//div[@id='obaMsg' and contains(@style, 'rgb')]");
			hs.put("errorMsg", "id#errMsg");
			//Cancel Bundle
			hs.put("payNow", "id#btnPayNow");
			hs.put("cancelBunButton", "id#btnCnlBundle");
			hs.put("selectCncBun", "id#ddlCancelBundle");
			hs.put("submitCncBun", "id#btnSubmitCnlBundle");
			hs.put("cancelBunConfirmMsg", "xpath#//div[@id='obaMsg' and contains(text(), 'ErrorResources>>SemiPostPaid_220')]");
			//Bundle Upgrade
			hs.put("bundleUpgrade", "id#btnUpgradeNow");
			hs.put("bunUpgRadioBtn", "id#rdbUpgradePlan");
			hs.put("bunUpgradeDD", "id#ddlBundles");
			//hs.put("bunUpgradeSubmit", "xpath#//button[@id='btnUpgradeSubmit']");
			hs.put("bunUpgradeSubmit", "id#btnUpgradeSubmit");
			//Bundle Credit
			hs.put("bunCreditRadioBtn", "id#rdbAddCreditOption");
			hs.put("bunCreditAmt", "id#txtAOR");
			//POM for Credit card details
			hs.put("payNewCard", "id#inlineRadio1");
			hs.put("cardType", "id#cardType");
			hs.put("cardName", "id#txtNameOncard");
			hs.put("card1", "id#paymentCRM_cardNumber1");
			hs.put("card2", "id#paymentCRM_cardNumber2");
			hs.put("card3", "id#paymentCRM_cardNumber3");
			hs.put("card4", "id#paymentCRM_cardNumber4");
			hs.put("expiryDateCard", "id#paymentCRM_expiryDate");
			hs.put("cvv", "id#paymentCRM_cvvNumber");
			hs.put("emailTxt", "id#txtEmail");
			hs.put("ccPayButton", "id#btnPayment");
			hs.put("cardRenewal", "id#chkDefaultCard");
			hs.put("ccConfirmMsg", "xpath#//div[@id='obaMsg' and contains(text(), 'ErrorResources>>ManaageOBABundle_0')]");
			hs.put("UKCountry", "xpath#//div[@id='countryName']/strong");
			hs.put("postCode", "id#postCode");
			hs.put("search", "xpath#//a[@id='btnFindAddress']/span");
			hs.put("addressList", "id#lstAddressResult");
			hs.put("addressAccept", "xpath#//a[@id='btnAcceptAddress']/span");
			hs.put("houseNo", "id#houseNumber");
			hs.put("stName", "id#streetName");
			hs.put("cityName", "id#cityName");
			hs.put("apartmentNo", "id#apartmentNo");
			hs.put("successLoadIcon", "id#btnrotate");
			hs.put("stateName", "id#stateName");
			hs.put("submit", "id#btnSubmit");
			
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
				
				Pattern pattern = Pattern.compile("(.*)contains(.*)text(.*),*'(.*)'");
				
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
			return null;
		}
	}

}
