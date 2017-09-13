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

public class Online_TopUp extends FunctionLibrary{


	public synchronized String RetrieveTestDataValue(String filePath,String compName,String strColumnName,String gblrecordsCounter,int strExecEventFlag) throws Exception{
		String strData=null;
		ReadExcel readExcel=new ReadExcel(filePath);
		if(strExecEventFlag!=0){
			strData=readExcel.getCellData(compName, strColumnName, Integer.parseInt(gblrecordsCounter));
		}
		return strData;
	}

	public synchronized String Online_TopUp_Page(String locator){

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
			hs.put("online_TopUp_Button", "xpath#//div[@class='card-topup-thumbnail']//div[@class='thumbnail-icon']");
			hs.put("topupAmount_Dropdown", "id#txttopupAmount");
			hs.put("email_TextBox", "id#eMailID");
			hs.put("promoCode_TextBox", "id#txtpromoCode");
			hs.put("promoCode_Button", "id#btnPromo");
			hs.put("discountMessage_label", "xpath#//label[@class='false successMessage']");
			hs.put("promoCodeEmpty_label", "id#lblPromoDiscount");
			hs.put("OthersSubmit_Button", "id#btnAmountSubmit");
			hs.put("OthersReset_Button", "id#btnAmountReset");
			hs.put("OthersCancel_Button", "id#btnAmountCancel");
			hs.put("OthersAmount_TextBox", "id#txtAmount");
			hs.put("OthersConfirmAmount_TextBox", "id#txtconfirmAmount");
			hs.put("newCard_rdButton", "id#inlineRadio1");
			hs.put("existingCard_rdButton", "id#inlineRadio2");
			//Repeat
			hs.put("ExCardNumberHead_Label", "id#jqgh_mappedCardTable_cardNumber");
			hs.put("ExCardNumber_Label", "xpath#//td[@aria-describedby='mappedCardTable_cardNumber']");
			hs.put("ExCardName_Label", "xpath#//td[@aria-describedby='mappedCardTable_nameOnCard']");
			hs.put("ExCardType_Label", "xpath#//td[@aria-describedby='mappedCardTable_cardtypeName']");
			hs.put("ExCardExpiryDate_Label", "xpath#//td[@aria-describedby='mappedCardTable_cardexpirydate']");
			hs.put("ExCCNo_Label", "id#existCard_cvvNumber");
			hs.put("MenuLoading_Icon", "xpath#//span[@class='col-md-offset-0 glyphicon-rotate']");
			hs.put("PaymentAmount_Label", "id#txtpaymentAmount");
			hs.put("taxAmountLabel", "id#txttaxamount");
			hs.put("TaxId_Label", "id#txtTaxId");
			hs.put("VATAmount_Label", "id#txtvatamount");
			hs.put("TotalPayAmount_Label", "id#txtTotalPayAmount");
			hs.put("confirmTaxAmt", "id#lblTaxAmt");
			hs.put("confirmVatAmt", "id#lblVatAmt");
			hs.put("cardType_Dropdown", "id#cardType");
			hs.put("cardName_TextBox", "id#txtNameOncard");
			hs.put("cardNumber1_TextBox", "id#paymentCRM_cardNumber1");
			hs.put("cardNumber2_TextBox", "id#paymentCRM_cardNumber2");
			hs.put("cardNumber3_TextBox", "id#paymentCRM_cardNumber3");
			hs.put("cardNumber4_TextBox", "id#paymentCRM_cardNumber4");
			hs.put("expiryDate_TextBox", "id#paymentCRM_expiryDate");
			hs.put("cvv_TextBox", "id#paymentCRM_cvvNumber");
			hs.put("postcode_TextBox", "id#postCode");
			hs.put("loadAddress_Icon", "xpath#//span[@class='glyphicon-rotate col-md-12 text-center']");
			hs.put("searchIcon_Button", "xpath#//span[@class='glyphicon glyphicon-search']");
			hs.put("addressList_TextBox", "xpath#//select[@id='lstAddressResult']");
			hs.put("OKIcon_Button", "xpath#//a[@id='btnAcceptAddress']/span");
			hs.put("purchase_Button", "id#purchase");
			hs.put("reset_Button", "id#btnCardReset");
			hs.put("okBtn_Button", "id#btnOK");
			hs.put("cancelBtn_Button", "id#btnCancel");
			hs.put("responseMsg_Button", "id#btnStaffTopup");
			hs.put("Amount_Label", "id#lblTopupAmt");
			hs.put("ReferenceNo_Label", "id#lblReferenceNo");
			hs.put("TransactionNo_Label", "id#lblTransactionNo");
			hs.put("load_Image", "xpath#//label[@class='glyphicon-rotate col-md-2 text-center']");
			hs.put("AddMessagebox_label", "xpath#//div[@class='modal-dialog' and  contains(@style,'margin-top')]//*//div[@class='modal-header']");
			hs.put("closeBox_Button", "id#ErrmsgClose");
			hs.put("countryName_label", "id#countryName");
			hs.put("streetName_Textbox", "id#streetName");
			hs.put("cityName_Textbox", "id#cityName");
			hs.put("apartmentNo_Textbox", "id#apartmentNo");
			hs.put("houseNo_Textbox", "id#houseNumber");
			hs.put("NOAddressPopup_Label", "xpath#//div[contains(text(), 'ErrorResources>>InvalidPostcodeAlertmsg')]");
			hs.put("close_button", "xpath#//div[@class='close-icon close-view-icon']");
			hs.put("UpdateSuccessMessage", "xpath#.//label[contains(text(),'SubscriberResources>>PrePostSucess')]");
			hs.put("ExistingCVVNumber", "id#existCard_cvvNumber");
			hs.put("BalanceLimitdpdwn", "id#txtcardBalanceLimit");
			hs.put("txtcardtopupAmount", "id#txtcardtopupAmount");
			hs.put("txtcardLimitTopupAmt", "id#txtcardLimitTopupAmt");
			hs.put("txtcardAutoDays", "id#txtcardAutoDays");
			hs.put("isAutotopupLink", "xpath#.//*[@id='divcheckboxautorecharge']//i");
			
			
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
				
				Pattern pattern = Pattern.compile("(.*)contains(.*)text(.*),.*'(.*)'");
				
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
	
	public synchronized String Online_TopUp_Page_ExpDate(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("expiryDate_TextBox", "paymentCRM_expiryDate");
			hs.put("cvv_TextBox", "paymentCRM_cvvNumber");
			hs.put("ExCCNo_Label", "existCard_cvvNumber");
			
			return hs.get(locator);
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

	public synchronized String Auto_TopUp_Page(String locator){

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
			hs.put("Auto_TopUp_Image", "xpath#//div[@class='card-topup-thumbnail']//div[@title='Auto Topup']/following-sibling::div[@class]");
			hs.put("MenuLoading_Icon", "xpath#//span[@class='col-md-offset-0 glyphicon-rotate']");
			hs.put("BalanceLimit_Dropdown", "id#txtBalanceLimit");
			hs.put("IsAutoTopup_Select", "xpath#//form[@id='AutoTopupValid']//label[@class='i-switch bg-success']/input[@id='isautotopup']");
			hs.put("IsAutoTopup_Select_New", "isautotopup");
			hs.put("ATopupAmount_Dropdown", "id#txtTopupamount");
			hs.put("MaxLimit_Dropdown", "id#txtMaxlimit");
			hs.put("APerWeek_Dropdown", "id#txtAutoDays");
			hs.put("UsingCard_Label", "xpath#//label[@class='w-full']");
			hs.put("Submit_Button", "id#btnBalance");
			hs.put("Refresh_Button", "id#btnRefresh");
			hs.put("ATopupMessage_Label", "id#bAutoTopupsMessage");
			hs.put("ResponseMessage_Label", "xpath#//span[@id='bAutoTopupsMessage']");
			hs.put("Response_message", "xpath#//span[contains(text(), 'SubscriberResources>>Success')]");
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
					
					Pattern pattern = Pattern.compile("(.*)contains(.*)text(.*),.*'(.*)'");
					
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

	public synchronized String AllInOne_TopUp_Page(String locator){

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
			hs.put("AllInOne_TopUp_Image", "xpath#//div[@class='card-topup-thumbnail']//div[@title='AllinOne']/following-sibling::div[@class]");
			hs.put("MenuLoading_Icon", "xpath#//span[@class='col-md-offset-0 glyphicon-rotate']");
			hs.put("lstAllinoneAmount", "id#txtAllinoneAmount");
			hs.put("btnAllinOneTopup", "id#btnAllinOneTopup");
			hs.put("lstBundleType", "id#ddlBundles");
			hs.put("lstBundleNo", "id#ddlBundleTypes");
			hs.put("lstNoOfMonths", "id#ddNoOfMonths");
			hs.put("btnplanSubmit", "id#btnAllinOneBundle");
			hs.put("lblHeaderbundlecategory", "xpath#//div[contains(text(),'TopupResources>>BundleCategory')]");
			hs.put("lblbundlecategory", "id#lblbundlecategory");
			hs.put("lblbundleDescription", "id#lblbundleDescription");
			hs.put("chkAllinoneAutorenewal", "id#chkAllinoneAutorenewal");
			hs.put("lstAllinonePaymentMode", "id#ddlAllinonePaymentMode");
			hs.put("txtAllinonepromoCode", "id#txtAllinonepromoCode");
			hs.put("btnAllinonePromo", "id#btnAllinonePromo");
			hs.put("btnAllinoneResetPromo", "id#btnAllinoneResetPromo");
			hs.put("tblAllInOneBbundles", "id#tbAllInOneBbundles");
			hs.put("labelAssociated", "id#divAssociated");
			hs.put("txtAssociatedMsisdn", "id#AssociatedMsisdn");
			hs.put("tdType_1", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[1]//td[1]");
			hs.put("tdSimPlan_1", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[1]//td[2]");
			hs.put("tdBundleType_1", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[1]//td[3]");
			hs.put("tdAutoRenewal_1", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[1]//td[4]");
			hs.put("tdNoofMonths_1", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[1]//td[5]");
			hs.put("tdOriginalPrice_1", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[1]//td[6]");
			hs.put("tdDiscountPrice_1", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[1]//td[7]");
			hs.put("tdtrashIcon_1", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[1]//td[27]//p[@class='glyphicon glyphicon-trash']");
			hs.put("tdType_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[1]");
			hs.put("tdSimPlan_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[2]");
			hs.put("tdBundleType_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[3]");
			hs.put("tdAutoRenewal_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[4]");
			hs.put("tdNoofMonths_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[5]");
			hs.put("tdOriginalPrice_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[6]");
			hs.put("tdDiscountPrice_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[7]");
			hs.put("tdtrashIcon_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[27]//p[@class='glyphicon glyphicon-trash']");
			hs.put("tdSubmitType_1", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[1]//td[1]");
			hs.put("tdSubmitSimPlan_1", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[1]//td[2]");
			hs.put("tdSubmitBundleType_1", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[1]//td[3]");
			hs.put("tdSubmitAutoRenewal_1", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[1]//td[4]");
			hs.put("tdSubmitNoofMonths_1", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[1]//td[5]");
			hs.put("tdSubmitOriginalPrice_1", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[1]//td[6]");
			hs.put("tdSubmitDiscountPrice_1", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[1]//td[7]");
			hs.put("tdSubmitPayableVAT_1", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[1]//td[10]");
			hs.put("tdSubmitType_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[1]");
			hs.put("tdSubmitSimPlan_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[2]");
			hs.put("tdSubmitBundleType_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[3]");
			hs.put("tdSubmitAutoRenewal_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[4]");
			hs.put("tdSubmitNoofMonths_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[5]");
			hs.put("tdSubmitOriginalPrice_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[6]");
			hs.put("tdSubmitDiscountPrice_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[7]");
			hs.put("tdSubmitPayableVAT_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[10]");
			hs.put("lstAddressResult", "id#lstAddressResult");
			hs.put("txtEmail", "id#Email");
			hs.put("txtpostCode", "id#postCode");
			hs.put("lblCountry", "xpath#//div[@id='countryName']//strong");
			hs.put("iconSelectAddress", "xpath#//a[@id='btnAcceptAddress']/span");
			hs.put("iconAddressSearch", "xpath#//span[@class='glyphicon glyphicon-search']");
			hs.put("labelstreetName", "id#streetName");
			hs.put("lblcityName", "id#cityName");
			hs.put("lblpostCode", "id#postCode");
			hs.put("txthouseNumber", "id#houseNumber");
			hs.put("btnallinonePaynow", "id#btnallinonePaynow");
			hs.put("lblAllinoneVatTotalAmt", "id#lblAllinoneVatTotalAmt");
			hs.put("btnCardPaymentSubmit", "id#btnCardPaymentSubmit");
			hs.put("btnCardPaymentCancel", "id#btnCardPaymentCancel");
			hs.put("btnallinoneAllCancel", "id#btnallinoneAllCancel");
			hs.put("labelResponseAllInOne", "xpath#//label[@id='btnAllInOne' and (@class='true' or @class='false')]");
			hs.put("labelErrResponseAllInOne", "xpath#//label[@id='btnAllInOne' and @class='false']");
			hs.put("labelDiscountResponseAllInOne", "xpath#//label[@id='btnAllInOne' and @class='false successMessage']");
			hs.put("lblAllinoneFamilyAccountId", "id#lblAllinoneFamilyAccountId");
			//YTopup:
			hs.put("txtAllinoneAmount", "id#txtAllinoneAmount");
			hs.put("btnAllinOneTopup", "id#btnAllinOneTopup");
			hs.put("tdType_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[1]");
			hs.put("tdSimPlan_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[2]");
			hs.put("tdBundleType_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[3]");
			hs.put("tdAutoRenewal_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[4]");
			hs.put("tdNoofMonths_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[5]");
			hs.put("tdOriginalPrice_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[6]");
			hs.put("tdDiscountPrice_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[7]");
			hs.put("tdtrashIcon_2", "xpath#//tbody[@id='tbAllInOneBbundles']//tr[2]//td[8]//p[@class='glyphicon glyphicon-trash']");
			hs.put("tdSubmitType_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[1]");
			hs.put("tdSubmitSimPlan_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[2]");
			hs.put("tdSubmitBundleType_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[3]");
			hs.put("tdSubmitAutoRenewal_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[4]");
			hs.put("tdSubmitNoofMonths_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[5]");
			hs.put("tdSubmitOriginalPrice_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[6]");
			hs.put("tdSubmitDiscountPrice_2", "xpath#//tbody[@id='tbcheckAllInOneBbundles']//tr[2]//td[7]");
			hs.put("btnallinoneCancel", "id#btnallinoneCancel");
			hs.put("rbdNewCard", "id#inlineRadio1");
			hs.put("rbdExistingCard", "id#inlineRadio2");
			hs.put("lstcardType", "id#cardType");
			hs.put("txtNameOncard", "id#txtNameOncard");
			hs.put("paymentCRM_cardNumber1", "id#paymentCRM_cardNumber1");
			hs.put("paymentCRM_cardNumber2", "id#paymentCRM_cardNumber2");
			hs.put("paymentCRM_cardNumber3", "id#paymentCRM_cardNumber3");
			hs.put("paymentCRM_cardNumber4", "id#paymentCRM_cardNumber4");
			hs.put("paymentCRM_expiryDate", "id#paymentCRM_expiryDate");
			hs.put("paymentCRM_cvvNumber", "id#paymentCRM_cvvNumber");
			hs.put("lblAllinoneMSISDN", "id#lblAllinoneMSISDN");
			hs.put("lblAllinoneAmount", "id#lblAllinoneAmount");
			hs.put("lblAllinoneReferenceNumber", "id#lblAllinoneReferenceNumber");
			hs.put("lblAllinoneTransactionNumber", "id#lblAllinoneTransactionNumber");
			hs.put("Alert_Message", "id#dvWorkArea");
			hs.put("Payment_Type", "id#divPaymentType");
			hs.put("AIO_Paymnet_Type", "xpath#//*[@id='divPaymentType']//label[@class='radio-inline']");
			hs.put("Bundle_Table", "id#tblAllInOneBundles");
			hs.put("Confirm_Bundle_Table", "id#tblCheckAllInOneBundles");
			hs.put("Confirm_MSISDN", "id#lblAllinoneMSISDN");
			hs.put("Confirm_Amount", "id#lblAllinoneAmount");
			hs.put("Confirm_Reference_Number", "id#lblAllinoneReferenceNumber");
			hs.put("Confirm_Transaction_Number", "id#lblAllinoneTransactionNumber");
			hs.put("VAT_Amount", "id#lblAllinoneVatAmount");
			hs.put("Confirm_VAT_Amount", "id#lblAllinonefinalVatAmount");
			hs.put("list_State", "id#stateName");
			hs.put("apartmentNo_Textbox", "id#apartmentNo");
			hs.put("TAX_Amount", "id#lblAllinoneTaxAmount");
			hs.put("Confirm_TAX_Amount", "id#lblAllinonefinalTaxAmount");	
			//Topup Lakshman:
			hs.put("modal_Popup", "xpath#//div[@id='myModel']//*//div[@class='modal-header']");
			hs.put("modal_btnClose", "xpath#//div[@class='modal-footer']//button[@id='ErrmsgClose']");
			hs.put("newCard_rdButton", "id#inlineRadio1");
			hs.put("existingCard_rdButton", "id#inlineRadio2");
			hs.put("ExCardNumberHead_Label", "id#jqgh_mappedCardTable_cardNumber");
			hs.put("ExCardNumber_Label", "xpath#//td[@aria-describedby='mappedCardTable_cardNumber']");
			hs.put("ExCardName_Label", "xpath#//td[@aria-describedby='mappedCardTable_nameOnCard']");
			hs.put("ExCardType_Label", "xpath#//td[@aria-describedby='mappedCardTable_cardtypeName']");
			hs.put("ExCardExpiryDate_Label", "xpath#//td[@aria-describedby='mappedCardTable_cardexpirydate']");
			hs.put("ExCCNo_Label", "id#existCard_cvvNumber");
			hs.put("MenuLoading_Icon", "xpath#//span[@class='col-md-offset-0 glyphicon-rotate']");
			hs.put("PaymentAmount_Label", "id#txtpaymentAmount");
			hs.put("TaxId_Label", "id#txtTaxId");
			hs.put("VATAmount_Label", "id#txtvatamount");
			hs.put("TotalPayAmount_Label", "id#txtTotalPayAmount");
			hs.put("cardType_Dropdown", "id#cardType");
			hs.put("cardName_TextBox", "id#txtNameOncard");
			hs.put("cardNumber1_TextBox", "id#paymentCRM_cardNumber1");
			hs.put("cardNumber2_TextBox", "id#paymentCRM_cardNumber2");
			hs.put("cardNumber3_TextBox", "id#paymentCRM_cardNumber3");
			hs.put("cardNumber4_TextBox", "id#paymentCRM_cardNumber4");
			hs.put("expiryDate_TextBox", "id#paymentCRM_expiryDate");
			//Added for Parallel
			hs.put("expiryDate_TextBoxJS", "paymentCRM_expiryDate");
			hs.put("cvv_TextBox", "id#paymentCRM_cvvNumber");
			hs.put("postcode_TextBox", "id#postCode");
			hs.put("loadAddress_Icon", "xpath#//span[@class='glyphicon-rotate col-md-12 text-center']");
			hs.put("searchIcon_Button", "xpath#//span[@class='glyphicon glyphicon-search']");
			hs.put("addressList_TextBox", "id#lstAddressResult");
			hs.put("streetName_Textbox", "id#streetName");
			hs.put("cityName_Textbox", "id#cityName");
			hs.put("apartmentNo_Textbox", "id#apartmentNo");
			hs.put("houseNo_Textbox", "id#houseNumber");
			//Repeat:
			hs.put("existingCard_rdButton", "id#inlineRadio2");
			hs.put("ExCardNumberHead_Label", "id#jqgh_mappedCardTable_cardNumber");
			hs.put("ExCardNumber_Label", "xpath#//td[@aria-describedby='mappedCardTable_cardNumber']");
			hs.put("ExCardName_Label", "xpath#//td[@aria-describedby='mappedCardTable_nameOnCard']");
			hs.put("ExCardType_Label", "xpath#//td[@aria-describedby='mappedCardTable_cardtypeName']");
			hs.put("ExCardExpiryDate_Label", "xpath#//td[@aria-describedby='mappedCardTable_cardexpirydate']");
			hs.put("ExCCNo_Label", "id#existCard_cvvNumber");
			hs.put("lbl_DiscountMessage", "xpath#//label[@id='btnAllInOne' and (@class='false successMessage' or @class='false')]");
			hs.put("OthersSubmit_Button", "id#btnAmountSubmit");
			hs.put("OthersReset_Button", "id#btnAmountReset");
			hs.put("OthersCancel_Button", "id#btnAmountCancel");
			hs.put("OthersAmount_TextBox", "id#txtAmount");
			hs.put("OthersConfirmAmount_TextBox","id#txtconfirmAmount");
			hs.put("lblAllinonefinalVatAmount","id#lblAllinonefinalVatAmount");
			hs.put("lblVAT_Amount", "id#lblAllinoneVatAmount");
			hs.put("lststateName", "id#stateName");
			hs.put("lblAllinoneTaxAmount", "id#lblAllinoneTaxAmount");
			hs.put("lblAllinonefinalTaxAmount", "id#lblAllinonefinalTaxAmount");
			hs.put("lblAllinoneVatTotalAmt2", "xpath#//label[@id='lblAllinoneVatTotalAmt' and contains(@style,'bold')]");
			hs.put("close_button", "xpath#//div[@class='close-icon close-view-icon']");
			
			//WiFi Address
			hs.put("wifiAddress1", "xpath#//input[@id='wifiAddress1']");
			hs.put("wifiAddress2", "xpath#//input[@id='wifiAddress2']");
			hs.put("wifiCity", "xpath#//input[@id='wifiCity']");
			hs.put("wifiState", "xpath#//input[@id='wifiState']");
			hs.put("wifiPostCode", "xpath#//input[@id='wifiPostCode']");
			hs.put("wifiSubmit", "id#btnWifiSubmit");
			hs.put("wifiMsg", "id#wifiMsg");
			hs.put("wifiCancelbtn", "id#btnWifiCancel");
			
			hs.put("wifiGridTable", "id#tblAllInOneBundles");
			hs.put("wifiType", "xpath#//tbody[@id='tbAllInOneBbundles']//tr//td[1]");
			hs.put("wifiSimPlan", "xpath#//tbody[@id='tbAllInOneBbundles']//tr//td[2]");
			hs.put("wifiBundleType", "xpath#//tbody[@id='tbAllInOneBbundles']//tr//td[3]");
			hs.put("wifiRenewal", "xpath#//tbody[@id='tbAllInOneBbundles']//tr//td[4]");
			hs.put("wifiNoOfMon", "xpath#//tbody[@id='tbAllInOneBbundles']//tr//td[5]");
			hs.put("wifiOrgPrice", "xpath#//tbody[@id='tbAllInOneBbundles']//tr//td[6]");
			hs.put("wifiDisPrice", "xpath#//tbody[@id='tbAllInOneBbundles']//tr//td[7]");
			hs.put("wifiHighManField", "xpath#//label[@id='btnAllInOne' and contains(@class, 'false')]");
			
			hs.put("addressAccept", "xpath#//a[@id='btnAcceptAddress']/span");
			
			hs.put("valWifiAddress1", "id#wifiAddress1");
			hs.put("valWifiAddress2", "id#wifiAddress2");
			hs.put("valWifiCity", "id#wifiCity");
			hs.put("valWifiState", "id#wifiState");
			hs.put("valWifiPostCode", "id#wifiPostCode");
			
			
			hs.put("wifiConfirmTable", "xpath#//tr[@id='trregulartax']/parent::thead/following-sibling::tbody//tr");
			hs.put("wifiConfirmType", "xpath#//tr[@id='trregulartax']/parent::thead/following-sibling::tbody//tr//td[1]");
			hs.put("wifiConfirmSimPlan", "xpath#//tr[@id='trregulartax']/parent::thead/following-sibling::tbody//tr//td[2]");
			hs.put("wifiConfirmBundleType", "xpath#//tr[@id='trregulartax']/parent::thead/following-sibling::tbody//tr//td[3]");
			hs.put("wifiConfirmRenewal", "xpath#//tr[@id='trregulartax']/parent::thead/following-sibling::tbody//tr//td[4]");
			hs.put("wifiConfirmNoOfMon", "xpath#//tr[@id='trregulartax']/parent::thead/following-sibling::tbody//tr//td[5]");
			hs.put("wifiConfirmOrgPrice", "xpath#//tr[@id='trregulartax']/parent::thead/following-sibling::tbody//tr//td[6]");
			hs.put("wifiConfirmDisPrice", "xpath#//tr[@id='trregulartax']/parent::thead/following-sibling::tbody//tr//td[7]");
			
			
			hs.put("existCCMsg", "xpath#//div[@class='col-md-12 text-center errorMessage']");
			
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
				
				Pattern pattern = Pattern.compile("(.*)contains(.*)text(.*),.*'(.*)'");
				
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
						
						if(splittedXpath.contains(">>")){
							

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
			}
		return xpathValue;
		
		
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

	public synchronized String Modify_Bundle(String locator){

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
			hs.put("Bundle_Button", "xpath#//a[@class='catgLinkID']//span[@class='bundles-menu-image']");
			hs.put("ModifyBundle_Image", "xpath#//div[@class='purchase-bundle-thumbnail']//div[@title='Modifybundle']/following-sibling::div[@class]");
			hs.put("lstBundleCode", "id#ddlBundleCode");
			hs.put("lstOptionalMode", "id#ddlOptionalMode");
			hs.put("chkCommomAllowance", "id#chkCommomAllowance");
			hs.put("txtCommomAllowance", "id#txtCommomAllowance");
			hs.put("txtExpiryDate", "id#txtExpireDate");
			hs.put("btnExpireSubmit","id#btnExpireSubmit");
			hs.put("ErrmsgClose","id#ErrmsgClose");
			//Table Data:
			hs.put("tdBundleName_1", "xpath#//div[@id='divBundleDetails']//table//td[1]");
			hs.put("tdExpiryDate_1", "xpath#//div[@id='divBundleDetails']//table//td[2]");
			hs.put("tdOnnetZoneName_1", "xpath#//div[@id='divBundleDetails']//table//td[3]");
			hs.put("tdOnnetMinutes_1", "xpath#//div[@id='divBundleDetails']//table//td[4]");
			hs.put("tdOnnetSMS_1", "xpath#//div[@id='divBundleDetails']//table//td[5]");
			hs.put("tdZone1Name_1", "xpath#//div[@id='divBundleDetails']//table//td[6]");
			hs.put("tdZone1FreeMinutes_1", "xpath#//div[@id='divBundleDetails']//table//td[7]");
			hs.put("tdZone1FreeSMS_1", "xpath#//div[@id='divBundleDetails']//table//td[8]");
			hs.put("tdZone2Name_1", "xpath#//div[@id='divBundleDetails']//table//td[9]");
			hs.put("tdZone2FreeMinutes_1", "xpath#//div[@id='divBundleDetails']//table//td[10]");
			hs.put("tdZone2FreeSMS_1", "xpath#//div[@id='divBundleDetails']//table//td[11]");
			hs.put("tdZone3Name_1", "xpath#//div[@id='divBundleDetails']//table//td[12]");
			hs.put("tdZone3FreeMinutes_1", "xpath#//div[@id='divBundleDetails']//table//td[13]");
			hs.put("tdZone3FreeSMS_1", "xpath#//div[@id='divBundleDetails']//table//td[14]");
			hs.put("tdFreeData_1", "xpath#//div[@id='divBundleDetails']//table//td[15]");
			hs.put("tdFreeMTonnet_1", "xpath#//div[@id='divBundleDetails']//table//td[16]");
			hs.put("tdFreeMTSMSonnet_1", "xpath#//div[@id='divBundleDetails']//table//td[17]");
			hs.put("tdFreeMTothercalls_1", "xpath#//div[@id='divBundleDetails']//table//td[18]");
			hs.put("tdFreeMTSMSother_1", "xpath#//div[@id='divBundleDetails']//table//td[19]");
			hs.put("tdOnnetMTBundleType_1", "xpath#//div[@id='divBundleDetails']//table//td[20]");
			hs.put("tdAutoRenewal_1", "xpath#//div[@id='divBundleDetails']//table//td[21]");
			hs.put("tdBundleBalance_1", "xpath#//div[@id='divBundleDetails']//table//td[22]");
			hs.put("tdRoamUsage_1", "xpath#//div[@id='divBundleDetails']//table//td[23]");
			hs.put("tdLocalRoamUsage_1", "xpath#//div[@id='divBundleDetails']//table//td[24]");
			hs.put("chkbox_Offnet1", "xpath#//input[@id='chkData-1']");
			hs.put("chkbox_Offnet2", "xpath#//input[@id='chkData-2']");
			hs.put("chkbox_Offnet3", "xpath#//input[@id='chkData-3']");
			hs.put("chkbox_Onnet", "xpath#//input[@id='chkData-4']");
			hs.put("chkbox_MTOnnet", "xpath#//input[@id='chkData-5']");
			hs.put("chkbox_MTOffnet", "xpath#//input[@id='chkData-6']");
			hs.put("txt_Offnet1_Voice", "xpath#//input[@id='txtVoice-1']");
			hs.put("txt_Offnet2_Voice", "xpath#//input[@id='txtVoice-2']");
			hs.put("txt_Offnet3_Voice", "xpath#//input[@id='txtVoice-3']");
			hs.put("txt_Onnet_Voice", "xpath#//input[@id='txtVoice-4']");
			hs.put("txt_MTOnnet_Voice", "xpath#//input[@id='txtVoice-5']");
			hs.put("txt_MTOffnet_Voice", "xpath#//input[@id='txtVoice-6']");
			hs.put("txt_Offnet1_SMS", "xpath#//input[@id='txtSMS-1']");
			hs.put("txt_Offnet2_SMS", "xpath#//input[@id='txtSMS-2']");
			hs.put("txt_Offnet3_SMS", "xpath#//input[@id='txtSMS-3']");
			hs.put("txt_Onnet_SMS", "xpath#//input[@id='txtSMS-4']");
			hs.put("txt_MTOnnet_SMS", "xpath#//input[@id='txtSMS-5']");
			hs.put("txt_MTOffnet_SMS", "xpath#//input[@id='txtSMS-6']");
			hs.put("chkbox_DataOnnet", "xpath#//input[@id='chkData-8']");
			hs.put("chkbox_DataDomesticRoaming", "xpath#//input[@id='chkData-9']");
			hs.put("chkbox_DataInternationalRoaming", "xpath#//input[@id='chkData-10']");
			hs.put("txt_DataOnnet", "xpath#//input[@id='txtData-8']");
			hs.put("txt_DataDomesticRoaming", "xpath#//input[@id='txtData-9']");
			hs.put("txt_DataInternationalRoaming", "xpath#//input[@id='txtData-10']");
			hs.put("btnRejectInPending", "id#btnReject");
			hs.put("btnApproveInPending", "id#btnApprove");
			hs.put("btnSubmit", "id#btnSubmit");
			hs.put("btnAdminApprove", "id#btnAdminApprove");
			hs.put("btnReset", "id#btnReset");
			hs.put("lblResponseMessage", "xpath#//span[@id='lblError' and contains(@style,'color: rgb(0, 128, 0)')]");
			hs.put("linkBundleBucket", "xpath#//a[text()='HomeResources>>PABundleBucket']");
			hs.put("UpdateSuccessMessage", "xpath#.//label[contains(text(),'SubscriberResources>>PrePostSucess')]");
			hs.put("AlertMessage", "xpath#//span[@id='lblError' and contains(@style,'Red')]");
			hs.put("close_button", "xpath#//div[@class='close-icon close-view-icon']");
			hs.put("labelVoiceSMS","id#tVoiceSMS");
			
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

	public synchronized String Sim_Block_Pending_Approvals(String locator, String dynamicValue){
		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("pendingApproval_SimBlockRID", "xpath#//td[@title='"+dynamicValue+"']/following-sibling::td[@aria-describedby='pendingItemTable_Msisdn']");
			return hs.get(locator);
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

	public synchronized String Pending_MSISDN(String filePath,String compName,String gblrecordsCounter,String locator,String strMSISDN,int strEventFlag) throws Exception{

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

}
