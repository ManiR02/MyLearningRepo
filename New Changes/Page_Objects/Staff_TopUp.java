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

public class Staff_TopUp extends FunctionLibrary{

	public synchronized String staff_TopUp_Page(String locator){

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
			hs.put("topUp_Button", "xpath#//a[@class='catgLinkID']//span[@class='topup-menu-image']");
			hs.put("staff_TopUp_Button", "xpath#//div[@class='staff-topup-thumbnail']//div[@class='thumbnail-icon']");
			hs.put("amount_Dropdown", "id#txttopupamt");
			hs.put("plan_Dropdown", "id#ddlPlan");
			hs.put("bundle_Dropdown", "id#ddlBundle");
			hs.put("ticketID_TextBox", "id#txtTicketId");
			hs.put("reason_Dropdown", "id#ddlReason");
			hs.put("comments_TextBox", "id#txtComments");
			hs.put("comments_TextBox_Staff", "id#txtComments");
			hs.put("approve_Button", "id#btnApprove");
			hs.put("reject_Button", "id#btnReject");
			hs.put("confirm_Message", "id#bStaffTopupsMessage");
			hs.put("ticket_Scroll", "xpath#//div[@class='slimScrollRail' and contains(@style, 'block')]");
			hs.put("highlighted_ValMessage", "id#lblError");
			hs.put("amountDropdown_Validation", "xpath#//select[@id='txttopupamt' and contains(@aria-required, 'true')]");
			hs.put("planDropdown_Validation", "xpath#//select[@id='ddlPlan' and contains(@aria-required, 'true')]");
			hs.put("ticketID_Validation", "xpath#//input[@id='txtTicketId' and contains(@aria-required, 'true')]");
			hs.put("bundleDropdown_Validation", "xpath#//select[@id='ddlBundle' and contains(@aria-required, 'true')]");
			hs.put("reasonDropdown_Validation", "xpath#//select[@id='ddlReason' and contains(@aria-required, 'true')]");
			hs.put("comments_TextBox_Validation", "xpath#//textarea[@id='txtComments' and contains(@aria-required, 'true')]");
			hs.put("simBlock_ValText", "xpath#//b[@class='false']");
			hs.put("submit_Button", "id#btnSubmit");
			hs.put("csAgent_TopUp_SuccessMsg", "id#bStaffTopupsMessage");
			hs.put("simBlock_Button", "xpath#//a[@class='catgLinkID']//span[@class='sim-menu-image']");
			hs.put("simBlockUnblock_Button", "xpath#//div[@class='sim-block-thumbnail']//div[@class='thumbnail-icon']");
			hs.put("action_Dropdown", "id#loadAction");
			hs.put("ticketIDSIMBlock_TextBox", "id#txtTicketID");
			hs.put("comments_TextBox", "id#Comments");
			hs.put("reset_Button", "id#btnPendingReset");
			hs.put("response_Message", "id#responseMessage");
			hs.put("close_button", "xpath#//div[@class='close-icon close-view-icon']");
			hs.put("header_CloseBtn", "xpath#//div[@class='close-icon close-view-icon']");
			hs.put("Topup_Type", "id#ddlTopupType");
			hs.put("Bundle_Auto_Renewal", "id#chkBundleARenewal");
			hs.put("radio_Account_Balance", "id#rdbAccBalance");
			hs.put("radio_Staff_Topup", "id#rdbStaffTopup");
			hs.put("Sim_Alert_Message", "xpath#.//*[@id='dvWorkArea']//b");
			hs.put("History", "id#btnHistory");
			hs.put("Topup_History_Table", "id#dvHistory");
			hs.put("Auto_Topup_Checkbox", "id#chkAutoTopup");
			hs.put("Threshold_Limit", "id#txtThresholdLimit");
			hs.put("Frequency", "id#ddlFrequency");
			hs.put("Popup_Alert_1", "xpath#.//*[@id='ModalChkAutoTopup']/div/div/div[2]");
			hs.put("Yes_button_1", "id#btnTopupYesE");
			hs.put("Popup_Alert_2", "xpath#.//*[@id='ModalChkSuffBal']/div/div/div[2]/p[2]");
			hs.put("Yes_button_2", "id#btnTopupYesSB");
			
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
		}
		return null;
	}

	public synchronized String TicketID_Page(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			//Ticket:
			hs.put("createNewTicket_Button", "classname#create-ticket");
			hs.put("product_DropDown", "id#Productspan");
			hs.put("category_DropDown", "id#Categoryspan");
			hs.put("subCategory_DropDown", "id#SubCategoryspan");
			hs.put("designation_DropDown", "id#Designationspan");
			hs.put("fileUpload_Box", "classname#upload-button");
			hs.put("description_TextBox", "id#txtDescription");
			hs.put("assignto_DropDown", "id#Assigntospan");
			hs.put("priority_DropDown", "id#Priorityspan");
			hs.put("FCR_DropDown", "id#FCR");
			hs.put("custFeedBack_DropDown", "id#CustFeedBackspan");
			hs.put("dateTicket_field", "id#txtEstCloDate");
			hs.put("createTicket_Button", "id#create");
			hs.put("resetTicket_Button", "id#Reset");
			hs.put("cancelTicket_Button", "id#CreateCancel");
			hs.put("ticketMessage_label", "xpath#//div[@id='myModel']//*//*//div[@class='modal-header']");
			hs.put("ticketMessage_label1", "xpath#(//div[@class='modal-header'])[5]");
			hs.put("closeMessage_Button", "id#ErrmsgClose");
			hs.put("closeMessage_Button1", "css#div#ErrmsgClose ");
			hs.put("CreatedTicket1st_label","xpath#(//div[@id='ticketSlimmScroll']//div[@class='ticket-detail']//*//div[@class='header-left']//span[2])[1]");
			hs.put("previousTicketArea_Button1", "classname#search-header");
			hs.put("TicketinPopup_Label", "xpath#//div[contains(text(), 'Ticket ID:')]");
			hs.put("TicketinPopupError_Label", "xpath#//div[@class='modal fade in']//*//*//div[@class='c']");
			hs.put("closeBox_Button", "id#ErrmsgClose");
			
			return hs.get(locator);
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

	public synchronized String TicketID_Page_Date(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("dateTicket_field", "txtEstCloDate");
			String locate = hs.get(locator);
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

	public synchronized String TicketID_Page_MessageBox(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("ticket_msgBox", "//div[@id='myModel']//*//*//div[@class='modal-header']");
			String locate = hs.get(locator);
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

	public synchronized String TicketID_Page_MessageBox1(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("Close_Button", "//button[@id='ErrmsgClose']");
			String locate = hs.get(locator);
			return locate;
		}catch(Exception e){
			System.out.println("Error occurred in POM classes :"+e);
		}
		return null;
	}

}
