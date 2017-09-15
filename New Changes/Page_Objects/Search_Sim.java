package Page_Objects;

import java.util.Hashtable;

import wrappers.FunctionLibrary;

public class Search_Sim extends FunctionLibrary{
	
	public synchronized String searchSimPage(String locator){
	
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();
		hs.put("simOptionsDD", "id#simoptions");
		hs.put("submitBtn", "id#btnSendSim");
		hs.put("fromDate", "id#txtfromdate");
		hs.put("nameTxtBox", "id#txtName");
		hs.put("transactionID", "id#txtTransactionId");
		hs.put("emailID", "id#txtEmailId");
		hs.put("pinTxt", "id#txtPin");
		hs.put("searchBtn", "id#btnsearchsimSearch");
		
		hs.put("cancelBtn", "id#btnsearchsimCancel");
		hs.put("pinTxt", "id#txtPin");
		hs.put("searchBtn", "id#btnsearchsimSearch");

		return hs.get(locator);
		
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
			return null;
		}
	}

}
