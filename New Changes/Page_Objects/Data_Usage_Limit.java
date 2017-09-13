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

public class Data_Usage_Limit extends FunctionLibrary{

	public synchronized String Data_Usage_Limit_Page(String locator){
	
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
			hs.put("Data_Usage_Limit", "xpath#//div[@class='accounts-thumbnail']//div[@class='thumbnail-icon']");
			hs.put("Home_Financial_Limit", "id#HomeFinLimit");
			hs.put("Home_Data_Limit", "id#HomeDataLimit");
			hs.put("Home_Data_Limit_Dropdown", "id#ddHome_DataLimit");
			hs.put("Roam_Financial_Limit", "id#RoamFinLimit");
			hs.put("Roam_Data_Limit", "id#RoamDataLimit");
			hs.put("Roam_Data_Limit_Dropdown", "id#ddRoam_DataLimit");
			hs.put("Home_Network", "id#ddHome");
			hs.put("Roam_Network", "id#ddRoam");
			hs.put("Submit", "id#btnSubmit");
			hs.put("Clear", "id#btnCancel");
			hs.put("Confirm_Message", "id#DataUsageErrorMsg");
			hs.put("Home_Financial_Error", "id#HomeFinLimit-error");
			hs.put("Roam_Data_Limit_Error", "id#RoamDataLimit-error");
			hs.put("updateMsg", "xpath#//span[@id='DataUsageErrorMsg' and contains(@style, 'rgb')]");
			hs.put("close_button", "xpath#//li[@title='SubscriberResources>>IconClose']");
			
			String xpathValue = hs.get(locator);
			if(xpathValue.contains(">>")) {
				

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
			return null;
		}

	}

}
