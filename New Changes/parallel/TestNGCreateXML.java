package parallel;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;

import org.w3c.dom.Element;

import utils.Constants;
import utils.CreateBatFile;
import utils.ReadExcel;

/**
 * 
 * <h1>TestNGCreateXML</h1>
 * 
 * <p>
 * <b>Note: Used to Create Every TestNG XML files and Bat files to Start the Batch Execution</b> 
 * 
 * @author Lakshman A
 * @since OCT 1, 2016
 *
 */

public class TestNGCreateXML {

	private static Logger log = Logger.getLogger(TestNGCreateXML.class.getName());
	private static Properties sysProperty;
	//Create the String array to store all the file Names to write in the final TestNG file
	static ArrayList<String> alltestNGFiles= new ArrayList<>();
	static ArrayList<String> ieTestNGFiles= new ArrayList<>();
	static int configuredSplitCount=0;

	public static void main(String[] args) {
		//String testBatchFileName=null;

		String browserExecuteStatus = null;
		String browserExecuteName = null;
		ReadExcel readBrowserExecExcel = null;
		int noOfExecBrowsers = 0;
		String browserExcelFilePath = null;
		String browserExcelFileName = null;
		String browserExcelFileXtn = null;
		String testBatchParallelFile = null;
		String testBatchSerialFile = null;
		String testNGXMLFileWithAllSuites = null;
		String testNGXMLFileWithAllSuitesIE = null;

		try {

			//Instantiate the Property file
			sysProperty =new Properties();
			sysProperty.load(new FileInputStream(new File("./config/SystemConfig.properties")));

			//Instantiate the Log file
			String log4jConfPath = sysProperty.getProperty("log4jConfPath");
			PropertyConfigurator.configure(log4jConfPath);

			log.info("********* TestNG XML FILES AND .bat FILE CREATION STARTS *********\n");
			log.info("All files initialized in the TestNGCreateXML class\n");

			//Get the TestCase split count per testng file from the Property file
			configuredSplitCount=Integer.parseInt(sysProperty.getProperty("testCaseSplitCount"));
			log.info("Maximum No. of Test Nodes allowed per TestNG Xmls is : "+configuredSplitCount+"\n");

			//Clean the TestNGXML folder(Only the files in the folder)
			File testNGDirectory=new File(sysProperty.getProperty("TestNG_XML_FilePath"));
			for (File file: testNGDirectory.listFiles()) {
				if (!file.isDirectory()) file.delete();
			}

			//Create an object for this class for calling all the methods
			TestNGCreateXML run = new TestNGCreateXML();
			browserExcelFilePath = sysProperty.getProperty("testBrowserExcelFilePath");
			browserExcelFileName = sysProperty.getProperty("testBrowserExcelFileName");
			browserExcelFileXtn = ".xls";
			testBatchParallelFile = sysProperty.getProperty("testBatchParallelFileName");
			testBatchSerialFile = sysProperty.getProperty("testBatchSerialFileName");
			
			readBrowserExecExcel = new ReadExcel(browserExcelFilePath+browserExcelFileName+browserExcelFileXtn);
			noOfExecBrowsers=readBrowserExecExcel.getRowCount(Constants.BrowserList_Sheet);

			for (int i = 1; i <= noOfExecBrowsers; i++) {
				browserExecuteStatus = readBrowserExecExcel.getCellData(Constants.BrowserList_Sheet, Constants.BrowserList_TestExecute, i);
				browserExecuteName = readBrowserExecExcel.getCellData(Constants.BrowserList_Sheet, Constants.BrowserList_BrowserName, i);

			if( browserExecuteStatus.equalsIgnoreCase(Constants.RUNMODE_YES) && (browserExecuteName.equalsIgnoreCase(Constants.Chrome_Browser))){
			//Create Backup Result File Test NG XML 
			run.createBackupResultFileTestNGXML();
			//At first, create the testNG config file to set Pre conditions using TestBatch_Config xls
			//Generate Config for Chrome browser with Parallel Type
			run.createConfigTestNGXML("others");
			
			//Create TestNG Files from all the Parallel Batch sheets for Chrome Seperately
			//(Input as Parallel Test Batch Excel Name)
			run.createTestNGXMLsFromBatch("Parallel", testBatchParallelFile);
			
			//Create TestNG Files from all the Serial Batch sheets(Input as Serial Test Batch Excel Name)
			run.createTestNGXMLsFromBatch("Serial", testBatchSerialFile);
			
			//Create Zip Result File Test NG XML
			run.createZipResultFileTestNGXML();
			}
			else if( (browserExecuteStatus.equalsIgnoreCase(Constants.RUNMODE_YES)) && ((browserExecuteName.equalsIgnoreCase(Constants.IE_Browser)))){
			
			//Create Backup Result File Test NG XML 
			run.createBackupResultFileTestNGXML();		
			//Generate test Config for IE with Serial Type
			run.createConfigTestNGXML("ie");
			
			//Create TestNG Files from all the Parallel Batch sheets for IE Seperately
			//(Input as Parallel Test Batch Excel Name)
			run.createTestNGXMLsFromBatchForIE("Parallel", testBatchParallelFile);
			run.createTestNGXMLsFromBatchForIE("Serial", testBatchSerialFile);
			
			//Create Zip Result File Test NG XML
			run.createZipResultFileTestNGXML();
				}
			}
			
			for (int i = 1; i <= noOfExecBrowsers; i++) {
				browserExecuteStatus = readBrowserExecExcel.getCellData(Constants.BrowserList_Sheet, Constants.BrowserList_TestExecute, i);
				browserExecuteName = readBrowserExecExcel.getCellData(Constants.BrowserList_Sheet, Constants.BrowserList_BrowserName, i);

			if( (browserExecuteStatus.equalsIgnoreCase(Constants.RUNMODE_YES)) && ((browserExecuteName.equalsIgnoreCase(Constants.Chrome_Browser)))){
			
			//Create the TestNG file with All the TestBatches (With Config, Parallel and serial TestNG XMLs)
			testNGXMLFileWithAllSuites = run.createFullSuiteTestNGXML();
			
				}
			
			else if( (browserExecuteStatus.equalsIgnoreCase(Constants.RUNMODE_YES)) && ((browserExecuteName.equalsIgnoreCase(Constants.IE_Browser)))){
			testNGXMLFileWithAllSuitesIE = run.createFullSuiteTestNGXMLForIE();
			
			}
			
			//Create a Bat File with Browsers and  Batch file in the order as mentioned in Browser_exec_Order Excel file
			run.createBatFileWithSuiteAndBrowsers(testNGXMLFileWithAllSuites,testNGXMLFileWithAllSuitesIE);
			
			//Create a Bat file with all the Serial xml, Parallel xml, Config xml and Browserwise xml(Incase of reducing load to TestNG at stretch)
//			run.createBatFileWithXMLsAndBrowsers();

			}
			
			log.info("********* COMPLETED GENERATING ALL THE TestNG and .bat FILES *********\n\n");
			log.info("####### REFRESH THE PROJECT AND EXECUTE THE FILES! ########\n\n");
			
			
		} catch (Exception e) {
			log.info("Exception occured in TestNGCreateXML. Exception is "+e);
			e.printStackTrace();
		}

	}

	/**
	 * <b>createTestNGXMLsFromBatch</b><br>
	 * 
	 * <p><b>Objective : To Create TestNG XMLs from the Parallel and Serial Test Batch sheets placed in the TestBatch
	 * folder for both Parallel and Serial Spreadsheets<p></b>
	 * <i>Note: Multiple TestBatch sheets are supported. Multi TestNG files will be created based on the configured split count.</i>>
	 * @author LAKSHMAN
	 * @since Nov 29, 2016
	 */

	public void createTestNGXMLsFromBatch(String suiteType, String testBatchFileNameStartsWith) throws Exception {

		try {
			String testBatchFilePath=sysProperty.getProperty("testBatchFilePath");
			String testBatchExtension=".xls";

			//Retrieve the folder path of the Test Batch Spreadsheets
			File testBatchFolder = new File(testBatchFilePath);

			//Retrieve all the Parallel/Serial TestBatch Spreadsheets from the TestBatch folder
			String regexpattern = "^"+testBatchFileNameStartsWith+"_(\\d+)\\.xls";
			FileFilter filter = new RegexFileFilter(regexpattern);
			File[] testBatchFiles = testBatchFolder.listFiles(filter);

			//Function ArraySort to Sort the TestBatch file names in Numerical Order.
			Arrays.sort(testBatchFiles, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					int n1 = extractNumber(o1.getName());
					int n2 = extractNumber(o2.getName());
					return n1 - n2;
				}
				private int extractNumber(String name) {
					int i = 0;
					try {
						int s = name.indexOf('_')+7;
						int e = name.lastIndexOf('.');
						String number = name.substring(s, e);
						i = Integer.parseInt(number);
					} catch(Exception e) {
						i = 0; 
					}
					return i;
				}
			}	);

			String testBatchFileName=null;

			//Looping through all the Batch sheets in the TestBatch folder
			for (int testBatchPointer = 0; testBatchPointer < testBatchFiles.length; testBatchPointer++) {   

				String fileName = testBatchFiles[testBatchPointer].getName();

				int index = fileName.lastIndexOf('.');
				if (index == -1) {
					//log.info("Do nothing");
				} else {
					testBatchFileName= fileName.substring(0, index);
				}

				log.info("Creating the TestNG file from the TestBatch : "+testBatchFileName+"\n");

				//Read the TestBatch Excel with Split Function to Split the Case generated in TestNG File
				ReadExcel suiteXL = new ReadExcel(testBatchFilePath + testBatchFileName + testBatchExtension);
				int totalTestCaseCount = suiteXL.getRowCount(Constants.TestBatch_Sheet);

				//Get the Split count for the TestNG file(How many test should be present in a XML file)
				int totalBatchAfterSplit = totalTestCaseCount / configuredSplitCount;
				int testCaseCounter = 1;

				//Iterator for the Splitting the TestNG file with configured no of TestCase count in a xml file
				for (int batchIterator = 1; batchIterator <= totalBatchAfterSplit + 1; batchIterator++) {

					log.info("Creating the Splitted file for the Batch : "+testBatchFileName+". Split file No : "+batchIterator);

					//Instantiate the DOM to create XML
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.newDocument();
					Element rootElement = doc.createElement("suite");

					//Create Headers in Parallel TestNG file for Parallel Suite 
					if(suiteType.equalsIgnoreCase("Parallel")){
						//rootElement.setAttribute("name", sysProperty.getProperty("XML_Suite_Name_For_Parallel"));
						rootElement.setAttribute("name", testBatchFileName+"_"+batchIterator);
						rootElement.setAttribute("parallel", "tests");
						rootElement.setAttribute("thread-count", sysProperty.getProperty("No_of_Instances"));
						Element parameterIsName = doc.createElement("parameter");
						parameterIsName.setAttribute("name", "suiteType");
						parameterIsName.setAttribute("value", sysProperty.getProperty("ParallelSuiteType"));
						rootElement.appendChild(parameterIsName);
						doc.appendChild(rootElement);

						//Create Headers in Serial TestNG file for Parallel Suite 
					}else if(suiteType.equalsIgnoreCase("Serial")){
						rootElement.setAttribute("name", testBatchFileName+"_"+batchIterator);
						//rootElement.setAttribute("parallel", "tests");
						//rootElement.setAttribute("thread-count", sysProperty.getProperty("No_of_Instances_Serial"));
						rootElement.setAttribute("thread-count", "1");
						Element parameterIsName = doc.createElement("parameter");
						parameterIsName.setAttribute("name", "suiteType");
						parameterIsName.setAttribute("value", sysProperty.getProperty("SerialSuiteType"));
						rootElement.appendChild(parameterIsName);
						doc.appendChild(rootElement);
					}

					//To Keep track of No of cases in a TestNG File
					int caseCountInXML = 1;

					//Check to generate a XML file only when atleast single test is set to YES in Batch
					boolean isAtleastOneCaseIsYes = false;

					//To Name a TestNG file get the TestCase from and To Numbers 
					int testCaseFROM;
					testCaseFROM=testCaseCounter;
					int testCaseTO=1;

					//Create the Test Elements from the TestBatch sheets
					for (; (testCaseCounter < totalTestCaseCount + 1)
							&& (caseCountInXML <= configuredSplitCount); testCaseCounter++) {

						String testCaseName = suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestCase_ColName,testCaseCounter);
						String testCaseDesc = suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestDescription_ColName, testCaseCounter);
						String testCategory = suiteXL.getCellData(Constants.TestBatch_Sheet,Constants.TestCategory_ColName, testCaseCounter);
						String testAuthor = suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestAuthor_ColName, testCaseCounter);

						if (suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestRun_ColName, testCaseCounter)
								.equalsIgnoreCase(Constants.RUNMODE_YES)) {
							String testDataExcelPath = sysProperty.getProperty("testDataFilePath") + testCaseName
									+ ".xls";
							isAtleastOneCaseIsYes = true;

							boolean testCaseSheetExist = suiteXL.CheckTestCaseNamedSheetExist(testDataExcelPath,
									testCaseName);
							if (testCaseSheetExist) {
								try {
									Element test = doc.createElement("test");
									test.setAttribute("name", testCaseName);
									rootElement.appendChild(test);

									Element parameter = doc.createElement("parameter");
									parameter.setAttribute("name", "testcaseName");
									parameter.setAttribute("value", testCaseName);
									test.appendChild(parameter);

									Element parameter2 = doc.createElement("parameter");
									parameter2.setAttribute("name", "testcaseDesc");
									parameter2.setAttribute("value", testCaseDesc);
									test.appendChild(parameter2);

									Element parameter3 = doc.createElement("parameter");
									parameter3.setAttribute("name", "testCategory");
									parameter3.setAttribute("value", testCategory);
									test.appendChild(parameter3);

									Element parameter4 = doc.createElement("parameter");
									parameter4.setAttribute("name", "testAuthor");
									parameter4.setAttribute("value", testAuthor);
									test.appendChild(parameter4);

									Element parameter5 = doc.createElement("parameter");
									parameter5.setAttribute("name", "testdatasheet");
									parameter5.setAttribute("value",
											sysProperty.getProperty("testDataFilePath") + testCaseName + ".xls");
									test.appendChild(parameter5);

									Element classes = doc.createElement("classes");
									test.appendChild(classes);
									Element singleClass = doc.createElement("class");
									singleClass.setAttribute("name", "wrappers.Components");
									classes.appendChild(singleClass);
									Element method = doc.createElement("methods");
									singleClass.appendChild(method);

									Element include = doc.createElement("include");
									include.setAttribute("name", "runComponents");
									method.appendChild(include);

								} catch (Exception e) {
									log.error("TestCase Failed. Skip the TestCase.");
									e.printStackTrace();
								}

							}
						}
						caseCountInXML++;
						testCaseTO=testCaseCounter;
					}


					//Create the TestNG XML file with DOC object
					if (isAtleastOneCaseIsYes) {
						TransformerFactory transformFactory = TransformerFactory.newInstance();
						Transformer transformer = transformFactory.newTransformer();
						transformer.setOutputProperty(OutputKeys.INDENT, "yes");
						transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
						DOMSource source = new DOMSource(doc);

						String fileFullPath=null;
						if(suiteType.equalsIgnoreCase("Parallel")){
							fileFullPath = sysProperty.getProperty("TestNG_XML_FilePath")+testBatchFileName+"_No"+testCaseFROM+"to"+testCaseTO+ ".xml";
						}else if(suiteType.equalsIgnoreCase("Serial")){
							fileFullPath=sysProperty.getProperty("TestNG_XML_FilePath")+testBatchFileName+"_No"+testCaseFROM+"to"+testCaseTO+".xml";
						}

						StreamResult result = new StreamResult(new File(fileFullPath));
						alltestNGFiles.add(fileFullPath);

						transformer.transform(source, result);
						//StreamResult consleresult = new StreamResult(System.out);
						//transformer.transform(source, consleresult);
						log.info("Generated the TestNg File successfully. TestNG File : "+fileFullPath);

					}
				} 
			}
			log.info("Creation of the TestNG file from the TestBatch : "+testBatchFileName+" is Completed...!\n");

		} catch (Exception e) {
			log.info("Problem with the TestBatch Sheet. Exception is : "+e);
			e.printStackTrace();
		}
	}
	
	/**
	 * <b>createTestNGXMLsFromBatchForIE</b><br>
	 * 
	 * <p><b>Objective : To Create TestNG XMLs from the Parallel Suite to Serial TestNG XML files only for IE<p></b>
	 * <i>Note: Multiple TestBatch sheets are supported. Multi TestNG files will be created based on the configured split count.</i>>
	 * @author LAKSHMAN
	 * @since Nov 29, 2016
	 */

	public void createTestNGXMLsFromBatchForIE(String suiteType, String testBatchFileNameStartsWith) throws Exception {

		try {
			String testBatchFilePath=sysProperty.getProperty("testBatchFilePath");
			String testBatchExtension=".xls";

			//Retrieve the folder path of the Test Batch Spreadsheets
			File testBatchFolder = new File(testBatchFilePath);

			//Retrieve all the Parallel/Serial TestBatch Spreadsheets from the TestBatch folder
			String regexpattern = "^"+testBatchFileNameStartsWith+"_(\\d+)\\.xls";
			FileFilter filter = new RegexFileFilter(regexpattern);
			File[] testBatchFiles = testBatchFolder.listFiles(filter);

			//Function ArraySort to Sort the TestBatch file names in Numerical Order.
			Arrays.sort(testBatchFiles, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					int n1 = extractNumber(o1.getName());
					int n2 = extractNumber(o2.getName());
					return n1 - n2;
				}
				private int extractNumber(String name) {
					int i = 0;
					try {
						int s = name.indexOf('_')+7;
						int e = name.lastIndexOf('.');
						String number = name.substring(s, e);
						i = Integer.parseInt(number);
					} catch(Exception e) {
						i = 0; 
					}
					return i;
				}
			}	);

			String testBatchFileName=null;

			//Looping through all the Batch sheets in the TestBatch folder
			for (int testBatchPointer = 0; testBatchPointer < testBatchFiles.length; testBatchPointer++) {   

				String fileName = testBatchFiles[testBatchPointer].getName();

				int index = fileName.lastIndexOf('.');
				if (index == -1) {
					//log.info("Do nothing");
				} else {
					testBatchFileName= fileName.substring(0, index);
				}

				log.info("Creating the TestNG file from the TestBatch : "+testBatchFileName+"\n");

				//Read the TestBatch Excel with Split Function to Split the Case generated in TestNG File
				ReadExcel suiteXL = new ReadExcel(testBatchFilePath + testBatchFileName + testBatchExtension);
				int totalTestCaseCount = suiteXL.getRowCount(Constants.TestBatch_Sheet);

				//Get the Split count for the TestNG file(How many test should be present in a XML file)
				int totalBatchAfterSplit = totalTestCaseCount / configuredSplitCount;
				int testCaseCounter = 1;

				//Iterator for the Splitting the TestNG file with configured no of TestCase count in a xml file
				for (int batchIterator = 1; batchIterator <= totalBatchAfterSplit + 1; batchIterator++) {

					log.info("Creating the Splitted file for the Batch : "+testBatchFileName+". Spilt file No : "+batchIterator);

					//Instantiate the DOM to create XML
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.newDocument();
					Element rootElement = doc.createElement("suite");

					//Create Headers in Parallel TestNG file for Parallel Suite 
					if(suiteType.equalsIgnoreCase("Parallel")){
						rootElement.setAttribute("name", testBatchFileName+"_IE_"+batchIterator);
						//rootElement.setAttribute("parallel", "tests");
						//rootElement.setAttribute("thread-count", sysProperty.getProperty("No_of_Instances_Serial"));
						rootElement.setAttribute("thread-count", "1");
						Element parameterIsName = doc.createElement("parameter");
						parameterIsName.setAttribute("name", "suiteType");
						parameterIsName.setAttribute("value", sysProperty.getProperty("SerialSuiteType"));
						rootElement.appendChild(parameterIsName);
						doc.appendChild(rootElement);

						//Create Headers in Serial TestNG file for Parallel Suite 
					}else if(suiteType.equalsIgnoreCase("Serial")){
						rootElement.setAttribute("name", testBatchFileName+"_IE_"+batchIterator);
						//rootElement.setAttribute("parallel", "tests");
						//rootElement.setAttribute("thread-count", sysProperty.getProperty("No_of_Instances_Serial"));
						rootElement.setAttribute("thread-count", "1");
						Element parameterIsName = doc.createElement("parameter");
						parameterIsName.setAttribute("name", "suiteType");
						parameterIsName.setAttribute("value", sysProperty.getProperty("SerialSuiteType"));
						rootElement.appendChild(parameterIsName);
						doc.appendChild(rootElement);
					}

					//To Keep track of No of cases in a TestNG File
					int caseCountInXML = 1;

					//Check to generate a XML file only when atleast single test is set to YES in Batch
					boolean isAtleastOneCaseIsYes = false;

					//To Name a TestNG file get the TestCase from and To Numbers 
					int testCaseFROM;
					testCaseFROM=testCaseCounter;
					int testCaseTO=1;

					//Create the Test Elements from the TestBatch sheets
					for (; (testCaseCounter < totalTestCaseCount + 1)
							&& (caseCountInXML <= configuredSplitCount); testCaseCounter++) {

						String testCaseName = suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestCase_ColName,testCaseCounter);
						String testCaseDesc = suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestDescription_ColName, testCaseCounter);
						String testCategory = suiteXL.getCellData(Constants.TestBatch_Sheet,Constants.TestCategory_ColName, testCaseCounter);
						String testAuthor = suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestAuthor_ColName, testCaseCounter);

						if (suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestRun_ColName, testCaseCounter)
								.equalsIgnoreCase(Constants.RUNMODE_YES)) {
							String testDataExcelPath = sysProperty.getProperty("testDataFilePath") + testCaseName
									+ ".xls";
							isAtleastOneCaseIsYes = true;

							boolean testCaseSheetExist = suiteXL.CheckTestCaseNamedSheetExist(testDataExcelPath,
									testCaseName);
							if (testCaseSheetExist) {
								try {
									Element test = doc.createElement("test");
									test.setAttribute("name", testCaseName);
									rootElement.appendChild(test);

									Element parameter = doc.createElement("parameter");
									parameter.setAttribute("name", "testcaseName");
									parameter.setAttribute("value", testCaseName);
									test.appendChild(parameter);

									Element parameter2 = doc.createElement("parameter");
									parameter2.setAttribute("name", "testcaseDesc");
									parameter2.setAttribute("value", testCaseDesc);
									test.appendChild(parameter2);

									Element parameter3 = doc.createElement("parameter");
									parameter3.setAttribute("name", "testCategory");
									parameter3.setAttribute("value", testCategory);
									test.appendChild(parameter3);

									Element parameter4 = doc.createElement("parameter");
									parameter4.setAttribute("name", "testAuthor");
									parameter4.setAttribute("value", testAuthor);
									test.appendChild(parameter4);

									Element parameter5 = doc.createElement("parameter");
									parameter5.setAttribute("name", "testdatasheet");
									parameter5.setAttribute("value",
											sysProperty.getProperty("testDataFilePath") + testCaseName + ".xls");
									test.appendChild(parameter5);

									Element classes = doc.createElement("classes");
									test.appendChild(classes);
									Element singleClass = doc.createElement("class");
									singleClass.setAttribute("name", "wrappers.Components");
									classes.appendChild(singleClass);
									Element method = doc.createElement("methods");
									singleClass.appendChild(method);

									Element include = doc.createElement("include");
									include.setAttribute("name", "runComponents");
									method.appendChild(include);

								} catch (Exception e) {
									log.error("TestCase Failed. Skip the TestCase.");
									e.printStackTrace();
								}

							}
						}
						caseCountInXML++;
						testCaseTO=testCaseCounter;
					}


					//Create the TestNG XML file with DOC object
					if (isAtleastOneCaseIsYes) {
						TransformerFactory transformFactory = TransformerFactory.newInstance();
						Transformer transformer = transformFactory.newTransformer();
						transformer.setOutputProperty(OutputKeys.INDENT, "yes");
						transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
						DOMSource source = new DOMSource(doc);

						String fileFullPath=null;
						if(suiteType.equalsIgnoreCase("Parallel")){
							fileFullPath = sysProperty.getProperty("TestNG_XML_FilePath")+testBatchFileName+"_IE_No"+testCaseFROM+"to"+testCaseTO+ ".xml";
						}else if(suiteType.equalsIgnoreCase("Serial")){
							fileFullPath=sysProperty.getProperty("TestNG_XML_FilePath")+testBatchFileName+"_IE_"+testCaseFROM+"to"+testCaseTO+".xml";
						}

						StreamResult result = new StreamResult(new File(fileFullPath));
						ieTestNGFiles.add(fileFullPath);

						transformer.transform(source, result);
						//StreamResult consleresult = new StreamResult(System.out);
						//transformer.transform(source, consleresult);
						log.info("Generated the TestNg File successfully. TestNG File : "+fileFullPath);

					}
				} 
			}
			log.info("Creation of the TestNG file from the TestBatch : "+testBatchFileName+" is Completed...!\n");

		} catch (Exception e) {
			log.info("Problem with the TestBatch Sheet. Exception is : "+e);
			e.printStackTrace();
		}
	}
	
	/**
	 * <b>createConfigTestNGXML</b><br>
	 * 
	 * <p><b>Objective : To Create Config TestNG XML file from the Test_Batch_Config.xls placed in the TestBatch folder<b></p>
	 * <i>Note: No Multiple TestBatch Spreadsheets will supported and No Multi TestNG files will be created. Only single file.</i>>
	 * @author LAKSHMAN
	 * @since Nov 29, 2016
	 */

	public void createConfigTestNGXML(String browserName) throws Exception {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement=null;
			if (browserName.equalsIgnoreCase("ie")) {
				rootElement = doc.createElement("suite");
				//rootElement.setAttribute("name", sysProperty.getProperty("XML_Suite_Name_For_Config"));
				rootElement.setAttribute("name", sysProperty.getProperty("testBatchConfigFileName")+"_IE");
				rootElement.setAttribute("thread-count", "1");
				Element parameterIsName = doc.createElement("parameter");
				parameterIsName.setAttribute("name", "suiteType");
				parameterIsName.setAttribute("value", sysProperty.getProperty("SerialSuiteType"));
				rootElement.appendChild(parameterIsName);
			} else if (browserName.equalsIgnoreCase("Others")) {
				rootElement = doc.createElement("suite");
				//rootElement.setAttribute("name", sysProperty.getProperty("XML_Suite_Name_For_Config"));
				rootElement.setAttribute("name", sysProperty.getProperty("testBatchConfigFileName"));
				rootElement.setAttribute("parallel", "tests");
				rootElement.setAttribute("thread-count", sysProperty.getProperty("No_of_Instances"));
				Element parameterIsName = doc.createElement("parameter");
				parameterIsName.setAttribute("name", "suiteType");
				parameterIsName.setAttribute("value", sysProperty.getProperty("ParallelSuiteType"));
				rootElement.appendChild(parameterIsName);
			
			}
			doc.appendChild(rootElement);
			
			ReadExcel suiteXL = new ReadExcel(sysProperty.getProperty("testBatchFilePath")+sysProperty.getProperty("testBatchConfigFileName")+".xls");
			boolean isAtleastOneCaseIsYes=false;

			int totalTestCaseCount = suiteXL.getRowCount(Constants.TestBatch_Sheet);
			for(int testCaseCounter=1; testCaseCounter <totalTestCaseCount+1; testCaseCounter++){

				String testCaseName = suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestCase_ColName, testCaseCounter);
				String testCaseDesc = suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestDescription_ColName, testCaseCounter);
				String testCategory=suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestCategory_ColName, testCaseCounter);
				String testAuthor=suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestAuthor_ColName, testCaseCounter);

				if(suiteXL.getCellData(Constants.TestBatch_Sheet, Constants.TestRun_ColName, testCaseCounter).equalsIgnoreCase(Constants.RUNMODE_YES)){
					String testDataExcelPath = sysProperty.getProperty("testDataFilePath")+testCaseName+".xls";

					boolean testCaseSheetExist=suiteXL.CheckTestCaseNamedSheetExist(testDataExcelPath,testCaseName);
					if(testCaseSheetExist){
						isAtleastOneCaseIsYes=true;
						try{
							Element test = doc.createElement("test");
							test.setAttribute("name", testCaseName);
							rootElement.appendChild(test);

							Element parameter = doc.createElement("parameter");
							parameter.setAttribute("name", "testcaseName");
							parameter.setAttribute("value", testCaseName);
							test.appendChild(parameter);

							Element parameter2 = doc.createElement("parameter");
							parameter2.setAttribute("name", "testcaseDesc");
							parameter2.setAttribute("value", testCaseDesc);
							test.appendChild(parameter2);

							Element parameter3 = doc.createElement("parameter");
							parameter3.setAttribute("name", "testCategory");
							parameter3.setAttribute("value", testCategory);
							test.appendChild(parameter3);

							Element parameter4 = doc.createElement("parameter");
							parameter4.setAttribute("name", "testAuthor");
							parameter4.setAttribute("value", testAuthor);
							test.appendChild(parameter4);

							Element parameter5 = doc.createElement("parameter");
							parameter5.setAttribute("name", "testdatasheet");
							parameter5.setAttribute("value", sysProperty.getProperty("testDataFilePath")+testCaseName+".xls");
							test.appendChild(parameter5);

							Element classes = doc.createElement("classes");
							test.appendChild(classes);
							Element singleClass = doc.createElement("class");
							singleClass.setAttribute("name", "wrappers.Components");
							classes.appendChild(singleClass);
							Element method = doc.createElement("methods");
							singleClass.appendChild(method);

							Element include = doc.createElement("include");
							include.setAttribute("name", "runComponents");
							method.appendChild(include);

						}catch(Exception e){
							log.error("TestCase Failed. Skip the TestCase.");
							e.printStackTrace();
						}
					}
				}
			}

			if (isAtleastOneCaseIsYes) {
				TransformerFactory transformFactory = TransformerFactory.newInstance();
				Transformer transformer = transformFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
				DOMSource source = new DOMSource(doc);
				
				
				String fileFullPath=null;
				StreamResult result=null;
				if (browserName.equalsIgnoreCase("others")) {
					fileFullPath = sysProperty.getProperty("TestNG_XML_FilePath")
							+ sysProperty.getProperty("Config_TestNG_XML_FileName") + ".xml";
					result = new StreamResult(new File(fileFullPath));
					alltestNGFiles.add(fileFullPath);
				}else if (browserName.equalsIgnoreCase("ie")) {
					fileFullPath = sysProperty.getProperty("TestNG_XML_FilePath")
							+ sysProperty.getProperty("Config_TestNG_XML_FileName_IE") + ".xml";
					result = new StreamResult(new File(fileFullPath));
					ieTestNGFiles.add(fileFullPath);
				}
				
				transformer.transform(source, result);
				//StreamResult consleresult = new StreamResult(System.out);
				//transformer.transform(source, consleresult);
				log.info("Generated the Config TestNg File successfully. TestNG File : "+fileFullPath);
			}

		} catch (Exception e) {
			log.info("Problem with the TestBatch Sheet. Exception is : "+e);
			e.printStackTrace();
		}
	}

	/**
	 * <b>createFullSuiteTestNGXML</b><br>
	 * 
	 * <p><b>Objective : To Create Full Suite TestNG XML with the generated Config, Parallel and Serial testNG xml file names<b></p>
	 * <p>A TestNG File Generated by this method is the full suite to get executed.</p>
	 * <i>Note: All Multiple TestBatch XMLs and Multi TestNG XML file names will be consolidated and created here in this XML file</i>
	 *
	 * @author LAKSHMAN
	 * @since Nov 29, 2016
	 */

	public String createFullSuiteTestNGXML() throws Exception {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("suite");
			rootElement.setAttribute("name", sysProperty.getProperty("XML_Suite_Name_For_Project"));
			doc.appendChild(rootElement);

			try{
				Element suite = doc.createElement("suite-files");
				rootElement.appendChild(suite);

				for (String testNGFile : alltestNGFiles) {
					Element parallelSuite = doc.createElement("suite-file");
					parallelSuite.setAttribute("path", testNGFile);
					suite.appendChild(parallelSuite);
				}

			}catch(Exception e){
				e.printStackTrace();
			}

			TransformerFactory transformFactory =  TransformerFactory.newInstance();
			Transformer transformer = transformFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
			DOMSource source = new DOMSource(doc);
			String fileFullPath=sysProperty.getProperty("Main_TestNG_XML_FilePath")+ sysProperty.getProperty("Common_TestNG_XML_FileName")+".xml";
			StreamResult result = new StreamResult(new File(fileFullPath));
			transformer.transform(source, result);
			//StreamResult consleresult = new StreamResult(System.out);
			//transformer.transform(source, consleresult);

			log.info("Generated the TestNg File successfully. TestNG File : "+fileFullPath);
			log.info("Generated the .bat file for the TestNG file...!");

			//Create Bat file for the Full TestNG Batch
			/*String fileFullPathWithoutExtention=sysProperty.getProperty("Main_TestNG_XML_FilePath")+ sysProperty.getProperty("bat_FileName_For_Full_Suite");
			String contentToWrite=sysProperty.getProperty("Common_TestNG_XML_FileName")+".xml";
			CreateBatFile.createTestNGBatFile(fileFullPathWithoutExtention);
			CreateBatFile.writeTestNGBatFile(fileFullPathWithoutExtention,contentToWrite);*/

			return fileFullPath;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * <b>createFullSuiteTestNGXMLForIE</b><br>
	 * 
	 * <p><b>Objective : To Create Full Suite TestNG XML with the generated Config and Serial testNG xml file names for IE Browser<b></p>
	 * <p>A TestNG File Generated by this method is the full suite to get executed.</p>
	 * <i>Note: All Multiple TestBatch XMLs and Multi TestNG XML file names will be consolidated and created here in this XML file</i>
	 *
	 * @author LAKSHMAN
	 * @since JAN 03, 2017
	 */

	public String createFullSuiteTestNGXMLForIE() throws Exception {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("suite");
			rootElement.setAttribute("name", sysProperty.getProperty("XML_Suite_Name_For_Project")+"_IE");
			doc.appendChild(rootElement);

			try{
				Element suite = doc.createElement("suite-files");
				rootElement.appendChild(suite);

				for (String testNGFile : ieTestNGFiles) {
					Element parallelSuite = doc.createElement("suite-file");
					parallelSuite.setAttribute("path", testNGFile);
					suite.appendChild(parallelSuite);
				}

			}catch(Exception e){
				e.printStackTrace();
			}

			TransformerFactory transformFactory =  TransformerFactory.newInstance();
			Transformer transformer = transformFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
			DOMSource source = new DOMSource(doc);
			String fileFullPath=sysProperty.getProperty("Main_TestNG_XML_FilePath")+ sysProperty.getProperty("Common_TestNG_XML_FileName_IE")+".xml";
			StreamResult result = new StreamResult(new File(fileFullPath));
			transformer.transform(source, result);
			//StreamResult consleresult = new StreamResult(System.out);
			//transformer.transform(source, consleresult);

			log.info("Generated the TestNg File successfull for IE. TestNG File : "+fileFullPath);
			log.info("Generated the .bat file for the TestNG file...!");

			//Create Bat file for the Full TestNG Batch
			String fileFullPathWithoutExtention=sysProperty.getProperty("Main_TestNG_XML_FilePath")+ sysProperty.getProperty("bat_FileName_For_Full_Suite_IE");
			String contentToWrite=sysProperty.getProperty("Common_TestNG_XML_FileName_IE")+".xml";
			CreateBatFile.createTestNGBatFile(fileFullPathWithoutExtention);
			CreateBatFile.writeTestNGBatFile(fileFullPathWithoutExtention,contentToWrite);

			return fileFullPath;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * <b>createBrowserUpdateTestNGXML</b><br>
	 * 
	 * <p><b>Objective : To Create Browser Update TestNG XML based on the BrowserName and Description<b></p>
	 * <p>A TestNG File Generated by this method will Execute the Browser Update Script in utils package based on the given Input</p>
	 *
	 * @author LAKSHMAN
	 * @since Nov 29, 2016
	 */

	public String createBrowserUpdateTestNGXML(String browserName, String browserDesc) throws Exception {
		String fileFullPath=null;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("suite");
			rootElement.setAttribute("name", browserName+"BrowserUpdate");
			doc.appendChild(rootElement);

			try{
				Element test = doc.createElement("test");
				test.setAttribute("name", "Set_Browser_"+browserName);
				rootElement.appendChild(test);

				Element parameter = doc.createElement("parameter");
				parameter.setAttribute("name", "browserName");
				parameter.setAttribute("value", browserName);
				test.appendChild(parameter);

				Element parameter2 = doc.createElement("parameter");
				parameter2.setAttribute("name", "browserDesc");
				parameter2.setAttribute("value", browserDesc);
				test.appendChild(parameter2);

				Element classes = doc.createElement("classes");
				test.appendChild(classes);

				Element singleClass = doc.createElement("class");
				singleClass.setAttribute("name", "utils.BrowserUpdate");
				classes.appendChild(singleClass);

			}catch(Exception e){
				e.printStackTrace();
			}

			TransformerFactory transformFactory = TransformerFactory.newInstance();
			Transformer transformer = transformFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
			DOMSource source = new DOMSource(doc);
			fileFullPath = sysProperty.getProperty("TestNG_XML_FilePath")
					+sysProperty.getProperty("Browser_TestNG_XML_FileName")+"_"+browserName+ ".xml";
			StreamResult result = new StreamResult(new File(fileFullPath));

			transformer.transform(source, result);
			//StreamResult consleresult = new StreamResult(System.out);
			//transformer.transform(source, consleresult);

			log.info("Generated the Browser Update TestNg File successfully. TestNG File : "+fileFullPath);

			return fileFullPath;

		} catch (Exception e) {
			log.info("Problem with the TestBatch Sheet. Exception is : "+e);
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * <b>createBatFileWithSuiteAndBrowsers</b><br>
	 * 
	 * <p><b>Objective : To Create Final TestNG XML with the BrowserUpdate Script and Full Suite one after the another<b></p>
	 * <p>A .bat File Generated by this method is the full suite to get executed with multiple browser.</p>
	 *
	 * @author LAKSHMAN
	 * @since Nov 29, 2016
	 */

	public void createBatFileWithSuiteAndBrowsers(String testNGXMLFileWithAllSuites, String testNGXMLFileWithAllSuitesIE) throws Exception {

		try {

			String fileFullPath=sysProperty.getProperty("Main_TestNG_XML_FilePath")+ sysProperty.getProperty("bat_FileName_Suite_with_Browser");
			CreateBatFile.createTestNGBatFile(fileFullPath);

			log.info("Creating the .bat file for Browser Update and full suite TestNg File. .bat file Name is : "+fileFullPath);

			String browserExcelFilePath=sysProperty.getProperty("testBrowserExcelFilePath");
			String browserExcelFileName=sysProperty.getProperty("testBrowserExcelFileName");
			String browserExcelFileXtn=".xls";
			ReadExcel readBrowserExcel = new ReadExcel(browserExcelFilePath+browserExcelFileName+browserExcelFileXtn);
			int noOfBrowsers=readBrowserExcel.getRowCount(Constants.BrowserList_Sheet);

			for (int i = 1; i <= noOfBrowsers; i++) {
				String browserExecuteStatus=readBrowserExcel.getCellData(Constants.BrowserList_Sheet, Constants.BrowserList_TestExecute,i);	

				if(browserExecuteStatus.equalsIgnoreCase(Constants.RUNMODE_YES)){
					String browserName=readBrowserExcel.getCellData(Constants.BrowserList_Sheet, Constants.BrowserList_BrowserName,i);
					String browserDesc=readBrowserExcel.getCellData(Constants.BrowserList_Sheet, Constants.BrowserList_BrowserDesc,i);

					//Create the TestNG XMLs based on the Browsers set to YES
					String browserTestNGFile=createBrowserUpdateTestNGXML(browserName, browserDesc);

					//Create the Bat file with BrowserUpdate and Full Suite XML files
					CreateBatFile.writeTestNGBatFile(fileFullPath,browserTestNGFile);
					if(browserName.equalsIgnoreCase("ie")){
						CreateBatFile.writeTestNGBatFile(fileFullPath,testNGXMLFileWithAllSuitesIE);
					}else{
						CreateBatFile.writeTestNGBatFile(fileFullPath,testNGXMLFileWithAllSuites);
					}
					
				}
			}

			log.info(".bat file for Browser Update and full suite created successfully.");

		} catch (Exception e) {
			log.info("Exception occured while creating the XML file : "+e);
			e.printStackTrace();
		}
	}

	/**
	 * <b>createBatFileWithXMLsAndBrowsers</b><br>
	 * 
	 * <p><b>Objective : To Create Final TestNG XML with the BrowserUpdate Script and instead Full Suite xml 
	 * we specify all the TestNG xmls in TestNG file seperately one after the another<b></p>
	 * <p>A .bat File Generated by this method is also the full suite to get executed with multiple browser.</p>
	 * <b>Executing this .bat file will reduce the long continous execution of same single TestNG thread for all Batch</b>
	 *
	 * @author LAKSHMAN
	 * @since Nov 29, 2016
	 */

	public void createBatFileWithXMLsAndBrowsers() throws Exception {

		try {
			String fileFullPath=sysProperty.getProperty("Main_TestNG_XML_FilePath")+ sysProperty.getProperty("bat_FileName_All_XMLs_with_Browser");
			CreateBatFile.createTestNGBatFile(fileFullPath);

			log.info("Creating the .bat file for Browser Update and Seperate All Test TestNg File. .bat file Name is : "+fileFullPath);

			String browserExcelFilePath=sysProperty.getProperty("testBrowserExcelFilePath");
			String browserExcelFileName=sysProperty.getProperty("testBrowserExcelFileName");
			String browserExcelFileXtn=".xls";
			ReadExcel readBrowserExcel = new ReadExcel(browserExcelFilePath+browserExcelFileName+browserExcelFileXtn);
			int noOfBrowsers=readBrowserExcel.getRowCount(Constants.BrowserList_Sheet);

			for (int i = 1; i <= noOfBrowsers; i++) {

				String browserExecuteStatus=readBrowserExcel.getCellData(Constants.BrowserList_Sheet, Constants.BrowserList_TestExecute,i);	

				if(browserExecuteStatus.equalsIgnoreCase(Constants.RUNMODE_YES)){
					String browserName=readBrowserExcel.getCellData(Constants.BrowserList_Sheet, Constants.BrowserList_BrowserName,i);
					String browserDesc=readBrowserExcel.getCellData(Constants.BrowserList_Sheet, Constants.BrowserList_BrowserDesc,i);

					String browserTestNGFile=createBrowserUpdateTestNGXML(browserName, browserDesc);

					CreateBatFile.writeTestNGBatFile(fileFullPath,browserTestNGFile);
					if(browserName.equalsIgnoreCase("ie")){
						for (String testNgFile : ieTestNGFiles) {
							CreateBatFile.writeTestNGBatFile(fileFullPath,testNgFile);
						}
					}else{
						for (String testNgFile : alltestNGFiles) {
							CreateBatFile.writeTestNGBatFile(fileFullPath,testNgFile);
						}
					}
				
				}
			}


			log.info(".bat file for Browser Update and full suite created successfully.");

		} catch (Exception e) {
			log.info("Exception occured while creating the XML file : "+e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * <b>createBackupResultFilesTestNGXML</b><br>
	 * 
	 * <p><b>Objective : To Create Backup Result Files TestNG XML <b></p>
	 * <p>A TestNG File Generated by this method will Execute the Backup Result File class in utils package</p>
	 *
	 * @author Hari Prakash
	 * @since Aug 01, 2017
	 */

	public void createBackupResultFileTestNGXML() throws Exception {		
		String BackupfileFullPath;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("suite");
			rootElement.setAttribute("name", "BackupTestResults");
			doc.appendChild(rootElement);

			try{
				Element test = doc.createElement("test");
				test.setAttribute("name", "Backup_Test_Results");
				rootElement.appendChild(test);				

				Element classes = doc.createElement("classes");
				test.appendChild(classes);

				Element singleClass = doc.createElement("class");
				singleClass.setAttribute("name", "utils.BackupResultFiles");
				classes.appendChild(singleClass);

			}catch(Exception e){
				e.printStackTrace();
			}

			TransformerFactory transformFactory = TransformerFactory.newInstance();
			Transformer transformer = transformFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
			DOMSource source = new DOMSource(doc);
			BackupfileFullPath = sysProperty.getProperty("TestNG_XML_FilePath")
					+sysProperty.getProperty("BackupResults_TestNG_XML_FileName")+".xml";
			StreamResult result = new StreamResult(new File(BackupfileFullPath));
			alltestNGFiles.add(BackupfileFullPath);
			transformer.transform(source, result);

			log.info("Generated the Results Backup TestNG File successfully. TestNG File : "+BackupfileFullPath);			

		} catch (Exception e) {
			log.info("Problem in generating Backup Result File Test NG File. Exception is : "+e);
			e.printStackTrace();
			throw e;
		}

	}

	
	/**
	 * <b>createZipResultFileTestNGXML</b><br>
	 * 
	 * <p><b>Objective : To Create Zip Result Files TestNG XML <b></p>
	 * <p>A TestNG File Generated by this method will Execute the Zip Result File class in utils package</p>
	 *
	 * @author Hari Prakash
	 * @since Aug 01, 2017
	 */

	public void createZipResultFileTestNGXML() throws Exception {		
		String ZipfileFullPath;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("suite");
			rootElement.setAttribute("name", "zipTestResults");
			doc.appendChild(rootElement);

			try{
				Element test = doc.createElement("test");
				test.setAttribute("name", "ZIP_Test_Results");
				rootElement.appendChild(test);				

				Element classes = doc.createElement("classes");
				test.appendChild(classes);

				Element singleClass = doc.createElement("class");
				singleClass.setAttribute("name", "utils.ZipResultFiles");
				classes.appendChild(singleClass);

			}catch(Exception e){
				e.printStackTrace();
			}

			TransformerFactory transformFactory = TransformerFactory.newInstance();
			Transformer transformer = transformFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
			DOMSource source = new DOMSource(doc);
			ZipfileFullPath = sysProperty.getProperty("TestNG_XML_FilePath")
					+sysProperty.getProperty("ZipResults_TestNG_XML_FileName")+".xml";
			StreamResult result = new StreamResult(new File(ZipfileFullPath));
			alltestNGFiles.add(ZipfileFullPath);
			transformer.transform(source, result);		

			log.info("Generated the Zip Results TestNg File successfully. TestNG File : "+ZipfileFullPath);			

		} catch (Exception e) {
			log.info("Problem in generating Backup Result File Test NG File. Exception is : "+e);
			e.printStackTrace();
			throw e;
		}

	}

}
