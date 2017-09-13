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

public class Bundle_Purchase extends FunctionLibrary{

	public synchronized String Bundle_Purchase_Page(String locator){

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
			hs.put("Bundle_Purchase", "xpath#//div[@class='purchase-bundle-thumbnail']//div[@class='thumbnail-icon']");
			hs.put("Bundle_Name", "id#bundleName");
			hs.put("Bundle_Months", "id#bundleMonths");
			hs.put("Bundle_Amount", "id#bundleAmount");
			hs.put("Bundle_Category", "id#bundleType");
			hs.put("Bundle_Description", "id#bundleDesc");
			hs.put("Auto_Renewal", "id#chkAutoRenewal");
			hs.put("Query_Bundle", "id#btnCalcBundleCost");
			hs.put("Reset_Bundle", "id#btnResetBundle");
			hs.put("Query_Bundle_Response", "id#discountMsg");
			hs.put("Submit", "id#btnSubmitBundle");
			hs.put("Alert_Message", "xpath#.//*[@id='bundlePurchaseForm']//div[contains(@class,'errorMessage')]");
			hs.put("Bundle_Alert_Message", "xpath#.//*[@id='dvBundleBuyMsg']//div[contains(@class,'errorMessage')]");
			hs.put("Bundle_Purchase_Message", "id#dvBundleBuyMsg");
			hs.put("Total_Minutes", "id#totalMinsRecvd");
			hs.put("Total_SMS", "id#bundleSMS");
			hs.put("Onnet_SMS", "id#onNetSMS");
			hs.put("Offnet_SMS", "id#offNetSMS");
			hs.put("Onnet_Mins", "id#onNetCalls");
			hs.put("Offnet_Mins", "id#offNetCalls");
			hs.put("Bundle_Expiry_Date", "id#bundleExpiry");
			hs.put("Pre_Receiver_MSISDN", "id#preReceiverMSISDN");
			hs.put("Avail_Promo_Button", "id#btnAvailPromo");
			hs.put("Edit_Promo_Code", "id#promoCode");
			hs.put("Apply_Promo_Button", "id#btnBundlePromo");

			hs.put("close_button", "xpath#//div[@class='close-icon close-view-icon']");
			
		
			String xpathValue = hs.get(locator);
			
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
			
			return xpathValue;

		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

}
