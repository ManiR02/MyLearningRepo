package Page_Objects;

import java.util.Hashtable;

import utils.ReadExcel;
import wrappers.FunctionLibrary;


public class Network_Feature extends FunctionLibrary{

	public synchronized String RetrieveTestDataValue(String filePath,String compName,String strColumnName,String gblrecordsCounter,int strExecEventFlag) throws Exception{
		String strData=null;
		ReadExcel readExcel=new ReadExcel(filePath);

		if(strExecEventFlag!=0){
			strData=readExcel.getCellData(compName, strColumnName, Integer.parseInt(gblrecordsCounter));
		}
		return strData;
	}

	public synchronized String Network_Feature_Page(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			hs.put("Load_Type", "id#loadType");
			hs.put("Load_Parameter", "id#loadParameter");
			hs.put("Load_Subscriber", "id#btnLoadSubscriber");
			hs.put("Loading_Image", "xpath#.//*[@id='NetworkListForm']//span[@class='glyphicon-rotate']");
			hs.put("Menu_Items", "xpath#//a[@class='menu-icon dropdown-toggle']");
			hs.put("Billing_Menu", "xpath#//a[@class='catgLinkID']//span[@class='billing-menu-image']");
			hs.put("Network_Feature", "xpath#//div[@class='services-thumbnail']//div[@class='thumbnail-icon']");
			hs.put("TicketID", "id#txtTicketId");
			hs.put("Reason", "id#txtReason");
			hs.put("MO_DATA", "xpath#.//*[@id='chkMoData']/..");
			hs.put("MO_DATA_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMoData']");
			hs.put("MO_ROAMING_DATA", "xpath#.//*[@id='chkMoRoamingData']/..");
			hs.put("MO_ROAMING_DATA_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMoRoamingData']");
			hs.put("MO_CALLS", "xpath#.//*[@id='chkMoCall']/..");
			hs.put("MO_CALLS_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMoCall']");
			hs.put("MO_ROAMING_CALLS", "xpath#.//*[@id='chkMORoamingCall']/..");
			hs.put("MO_ROAMING_CALLS_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMORoamingCall']");
			hs.put("MT_CALLS", "xpath#.//*[@id='chkMTCall']/..");
			hs.put("MT_CALLS_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMTCall']");
			hs.put("MT_ROAMING_CALLS", "xpath#.//*[@id='chkMTRoamingCall']/..");
			hs.put("MT_ROAMING_CALLS_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMTRoamingCall']");
			hs.put("MO_SMS", "xpath#.//*[@id='chkMOSMS']/..");
			hs.put("MO_SMS_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMOSMS']");
			hs.put("MT_SMS", "xpath#.//*[@id='chkMTSMS']/..");
			hs.put("MT_SMS_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMTSMS']");
			hs.put("MO_ROAMING_SMS", "xpath#.//*[@id='chkMORoamingSMS']/..");
			hs.put("MO_ROAMING_SMS_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMORoamingSMS']");
			hs.put("MT_ROAMING_SMS", "xpath#.//*[@id='chkMTRoamingSMS']/..");
			hs.put("MT_ROAMING_SMS_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMTRoamingSMS']");
			hs.put("IVR", "xpath#.//*[@id='chkIVR']/..");
			hs.put("IVR_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkIVR']");
			hs.put("USSD", "xpath#.//*[@id='chkUSSD']/..");
			hs.put("USSD_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkUSSD']");
			hs.put("VMS", "xpath#.//*[@id='chkVMS']/..");
			hs.put("VMS_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkVMS']");
			hs.put("TOPUP", "xpath#.//*[@id='chkSMSTopup']/..");
			hs.put("TOPUP_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkSMSTopup']");
			hs.put("MHA", "xpath#.//*[@id='chkMobileHomeAccount']/..");
			hs.put("MHA_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMobileHomeAccount']");
			hs.put("MCA", "xpath#.//*[@id='chkMCA']/..");
			hs.put("MCA_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMCA']");
			hs.put("CRBT", "xpath#.//*[@id='chkCRBT']/..");
			hs.put("CRBT_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkCRBT']");
			hs.put("CRBT", "xpath#.//*[@id='chkCRBT']/..");
			hs.put("CRBT_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkCRBT']");
			hs.put("BUNDLE_TOPUP", "xpath#.//*[@id='chkBundleTopup']/..");
			hs.put("BUNDLE_TOPUP_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkBundleTopup']");
			hs.put("SIM_SWAP_SUSPEND", "xpath#.//*[@id='chkSimSwapSuspend']/..");
			hs.put("SIM_SWAP_SUSPEND_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkSimSwapSuspend']");
			hs.put("MSISDN_SWAP_SUSPEND", "xpath#.//*[@id='chkMsisdnSwapSuspend']/..");
			hs.put("MSISDN_SWAP_SUSPEND_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMsisdnSwapSuspend']");
			hs.put("Portout_SWAP_SUSPEND", "xpath#.//*[@id='chkPortOutSwapSuspend']/..");
			hs.put("Portout_SWAP_SUSPEND_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkPortOutSwapSuspend']");
			hs.put("ADULT_CONTENT", "xpath#.//*[@name='adultContent']/..");
			hs.put("ADULT_CONTENT_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@name='adultContent']");
			hs.put("4G_HOME", "xpath#.//*[@id='chkMO4G']/..");
			hs.put("4G_HOME_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMO4G']");
			hs.put("4G_ROAM", "xpath#.//*[@id='chkMORoam4G']/..");
			hs.put("4G_ROAM_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chkMORoam4G']");
			hs.put("4G_LOCAL_ROAM", "xpath#.//*[@id='chk4GLocalRoam']/..");
			hs.put("4G_LOCAL_ROAM_Toggle", "xpath#//label[@class='i-switch bg-success']/input[@id='chk4GLocalRoam']");
			
			hs.put("MT_CALLS_STATUS", "id#MT_Calls");
			hs.put("MO_ROAM_CALLS_STATUS", "id#MO_Roam_Calls");
			hs.put("MT_ROAM_CALLS_STATUS", "id#MT_Roam_Calls");
			hs.put("MO_SMS_STATUS", "id#MO_SMS");
			hs.put("MT_SMS_STATUS", "id#MT_SMS");
			hs.put("MO_ROAMING_SMS_STATUS", "id#MO_ROAM_SMS");
			hs.put("MT_ROAMING_SMS_STATUS", "id#MT_ROAM_SMS");
			hs.put("IVR_STATUS", "id#IVR");
			hs.put("USSD_STATUS", "id#USSD");
			hs.put("VMS_STATUS", "id#VMS");
			hs.put("TOPUP_STATUS", "id#SMS_TOPUP");
			hs.put("MHA_STATUS", "id#MHA");
			hs.put("MCA_STATUS", "id#MCA");
			hs.put("CRBT_STATUS", "id#CRBT");
			hs.put("MO_DATA_STATUS", "id#MO_Data");
			hs.put("MO_ROAMING_DATA_STATUS", "id#MO_Roaming_Data");
			hs.put("BUNDLE_TOPUP_STATUS", "id#Bundle_Topup");
			hs.put("SIM_SWAP_SUSPEND_STATUS", "id#SIMSwap_SuspRequest");
			hs.put("MSISDN_SWAP_SUSPEND_STATUS", "id#MsisdnSwap_SuspRequest");
			hs.put("Portout_SWAP_SUSPEND_STATUS", "id#PortSwap_SuspendRequest");
			hs.put("4G_HOME_STATUS", "id#4G_Home");
			hs.put("4G_ROAM_STATUS", "id#4G_Roam");
			hs.put("4G_LOCAL_ROAM_STATUS", "id#4G_Local_Roam");
			hs.put("Feature_Type", "id#lblFeatureType");
			hs.put("Status", "id#lblStatus");
			hs.put("Ticket_ID", "id#lblTicketID");
			hs.put("Submitted_By", "id#lblSubmittedBy");
			hs.put("Submitted_Date", "id#lblSubmittedDate");
			hs.put("Status_Reason", "id#lblReason");
			hs.put("Close", "xpath#.//*[@id='nwDialogBox']//button[@class='btn btn-default'][@data-dismiss='modal']");
			return hs.get(locator);
		}catch(Exception e){
			return null;
		}
	}

	public synchronized String Get_Network_Feature(String filePath,String compName,String gblrecordsCounter,String strFeatureName,int strEventFlag) throws Exception{

		String featureName=null;

		try{
			featureName=RetrieveTestDataValue(filePath, compName, strFeatureName,gblrecordsCounter, 1);
			return featureName;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}


}
