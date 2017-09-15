package Page_Objects;

import java.util.Hashtable;

import wrappers.FunctionLibrary;

public class Dynamic_Number_Allocation extends FunctionLibrary {

	public synchronized String DNA_Page(String locator){

	
		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();

			hs.put("New_Subscriber", "id#subsParameter");
			hs.put("Btn_Register", "id#btnNewRegister");
			hs.put("Radio_Simple_Registration", "id#radio-button2-simple");
			hs.put("Title", "id#step1_simple_title");
			hs.put("First_Name", "id#step1_simple_firstName");
			hs.put("Last_Name", "id#step1_simple_lastName");
			hs.put("Email", "id#step1_simple_email");
			hs.put("Sim_Number_1", "id#step1_simple_simTxtbox1");
			hs.put("Sim_Number_2", "id#step1_simple_simTxtbox2");
			hs.put("Sim_Number_3", "id#step1_simple_simTxtbox3");
			hs.put("Sim_Number_4", "id#step1_simple_simTxtbox4");
			hs.put("Zip_Code", "id#step1_simple_zipCode");
			hs.put("Find_Address", "xpath#.//*[@id='btnReserveBundle']/span");
			hs.put("Language", "id#step1_simple_language");
			hs.put("Bundle_Type", "id#ddlBundle");
			hs.put("Reserve_Plan", "id#step1_simple_reservePlan");
			hs.put("Auto_Renewal", "id#step1_simple_autoRenewal");
			hs.put("Wholesale_Plan", "id#step1_simple_ddlWholesalePlan");
			hs.put("Expand_Voucher_Pin", "id#bundleToggler");
			hs.put("Voucher_Pin", "id#step1_simple_voucherPin");
			hs.put("Expand_MNP", "id#bundleToggler1");
			hs.put("Portin_Number", "id#step1_simple_portIn");
			hs.put("Account_Number", "id#step1_simple_account");
			hs.put("Password", "id#step1_simple_password");
			hs.put("Contact_Number", "id#step1_simple_ContactNo");
			hs.put("Btn_Cancel", "id#Cancel");
			hs.put("Btn_Next", "id#Next");
			hs.put("Btn_Submit", "id#Submit");
			hs.put("Btn_Back", "id#Back");
			hs.put("Confirm_Dummy_MSISDN", "id#step2_simple_AllocatedMSISDN");
			hs.put("Confirm_Title", "id#step2_simple_title");
			hs.put("Confirm_First_Name", "id#step2_simple_firstName");
			hs.put("Confirm_Last_Name", "id#step2_simple_lastName");
			hs.put("Confirm_Email", "id#step2_simple_email");
			hs.put("Confirm_Sim_1", "id#step2_simple_simTxtbox1");
			hs.put("Confirm_Sim_2", "id#step2_simple_simTxtbox2");
			hs.put("Confirm_Sim_3", "id#step2_simple_simTxtbox3");
			hs.put("Confirm_Sim_4", "id#step2_simple_simTxtbox4");
			hs.put("Confirm_Zip_Code", "id#step2_simple_zipCode");
			hs.put("Confirm_Language", "id#step2_simple_language");
			hs.put("Confirm_Reserve_Plan", "id#step2_simple_reservePlan");
			hs.put("Confirm_Wholesale_Plan", "id#step2_simple_wholesalePlan");
			hs.put("Confirm_Auto_Renewal_Status", "id#step2_simple_autoRenewal");
			hs.put("Confirm_Voucher_Pin", "id#step2_simple_voucherPin");
			hs.put("Confirm_Message", "id#step1error");
			hs.put("Show_Voucher_Pin", "xpath#.//*[@id='voucher-pin']/strong");
			hs.put("Hide_Voucher_Pin", "xpath#.//*[@id='voucher-pin']");
			hs.put("Show_MNP_Details", "xpath#.//*[@id='mnp-details']/strong");
			hs.put("Hide_MNP_Details", "xpath#.//*[@id='mnp-details']");
			hs.put("Alert_Message", "id#lblError");
			hs.put("Highlighted_Reserve_Plan", "xpath#//select[@id='step1_simple_reservePlan' and contains(@class,'select w-xsx txtRefresh error')]");
			
			return hs.get(locator);

		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

}
