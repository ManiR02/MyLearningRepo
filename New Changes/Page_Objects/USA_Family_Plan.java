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

public class USA_Family_Plan extends FunctionLibrary {

	public synchronized String usaFamilyPlanPage(String locator){

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
			hs.put("mng_FamilyPlan", "xpath#//div[@title='Manage Family Plan']");
			hs.put("parentMSISDN", "xpath#//strong[text()='TopupResources>>ParentMSISDN']");
			hs.put("addButton", "id#bundleToggler");
			hs.put("childMSISDN", "id#txtMSISDN");
			hs.put("mainBal_RadioBtn", "id#rdbtnMainBalance");
			hs.put("CC_RadioBtn", "id#rdbtnCreditCard");
			hs.put("cardType", "id#cardType");
			hs.put("newCard", "id#inlineRadio1");
			hs.put("txtNameOncard", "id#txtNameOncard");
			hs.put("card1", "id#paymentCRM_cardNumber1");
			hs.put("card2", "id#paymentCRM_cardNumber2");
			hs.put("card3", "id#paymentCRM_cardNumber3");
			hs.put("card4", "id#paymentCRM_cardNumber4");
			hs.put("expiryDate", "id#paymentCRM_expiryDate");
			hs.put("cvv", "id#paymentCRM_cvvNumber");
			hs.put("USACountry", "xpath#//div[@id='countryName']/strong");
			hs.put("houseNo", "id#houseNumber");
			hs.put("stName", "id#streetName");
			hs.put("cityName", "id#cityName");
			hs.put("apartmentNo", "id#apartmentNo");
			hs.put("postCode", "id#postCode");
			hs.put("successLoadIcon", "id#btnrotate");
			hs.put("addressList", "id#lstAddressResult");
			hs.put("addressAccept", "xpath#//a[@id='btnAcceptAddress']/span");
			hs.put("stateName", "id#stateName");
			hs.put("emailTxt", "id#txtEmail");
			hs.put("search", "xpath#//a[@id='btnFindAddress']/span");
			hs.put("submit", "id#btnSubmit");
			hs.put("reset", "id#btnReset");
			hs.put("childCon_Msg", "id#AmountMsg");
			hs.put("confirmMsg", "id#ErrorMsgPurchase");
			hs.put("swapButton", "xpath#//a[@class='btnAction']//following::img[@title='Swap']");
			hs.put("swapYesBtn", "id#SwapYes");
			hs.put("swapSucessMsg", "id#swapsucess");
			hs.put("swapSucessOk", "id#btnHome");
			hs.put("deleteChild", "xpath#//img[@title='Delete']");
			hs.put("deleteConMsg", "xpath#//button[@id='No']//ancestor::div[@class='modal-content']//div[@class='modal-body']//p");
			hs.put("deleteYesBtn", "id#Yes");
			hs.put("childConfirmMsg", "id#ErrorMsg");
			hs.put("10DigitMSISDN", "id#txtMSISDN-error");
			hs.put("valChildMSISDN", "xpath#//input[@id='txtMSISDN' and contains(@aria-required, 'true')]");
			
			hs.put("wifiAddress1", "id#wifiAddress1");
			hs.put("wifiAddress2", "id#wifiAddress2");
			hs.put("wifiCity", "id#wifiCity");
			hs.put("wifiState", "id#wifiState");
			hs.put("wifiPostCode", "id#wifiPostCode");
			hs.put("wifiSubmit", "id#btnWifiSubmit");
			hs.put("wifiMsg", "id#wifiMsg");
			hs.put("wifiCancelbtn", "id#btnWifiCancel");
			
			
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
}
