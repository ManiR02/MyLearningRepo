
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.testng.annotations.Test;

import wrappers.FunctionLibrary;

/**
 * 
 * <h1>ZipResultsFiles</h1> * 
 * <p>
 * <b>Class will copy only the HTML files from Results folder and store them in a temporary folder HTML_Files</b> 
 * <b>All the HTML files which were copied in HTML_Files will be zipped and stored in Results_Zipped folder as Results.zip</b> * 
 * @author Hari Prakash
 * @since Jul 27, 2017
 *
 */

public class BackupResultFiles extends FunctionLibrary{	
	List<String> fileList = new ArrayList<String>();	
	//private static Logger log = Logger.getLogger(BackupResultFiles.class.getName());
	static Properties sysProperty;
		
	String resultsPath="Reports\\Test_Results";
	String resultsBackupPath="Reports\\Test_Results\\Backup_Results";
	String htmlFilesPath=System.getProperty("user.dir")+"\\HTML_Files";
	String resultZIPFilePath=System.getProperty("user.dir")+"\\Results_Zipped";
	String resultZIPFile = resultZIPFilePath+"\\Results.zip";
	String strFileType;
	
	Path resultsDIR = Paths.get(resultsPath);
	Path resultsBackupDIR = Paths.get(resultsBackupPath);	   		
	Path htmlDIR = Paths.get(htmlFilesPath);
	Path resultZIPFileDIR = Paths.get(resultZIPFilePath);
	
	@Test
	public void backupOldTestResults(){		
		try{			
			sysProperty =new Properties();
			sysProperty.load(new FileInputStream(new File("./config/SystemConfig.properties")));			
			// Result file type to be zipped
			strFileType="*."+sysProperty.getProperty("fileTypetoZIP");
			System.out.println("strFileType is " + strFileType);
			//Checking whether Results folder contains HTML files, if exist backup will be taken
			int noofFilesPresent;
			boolean htmlResultFileExist=false;
			
			File[] filesPresent = resultsDIR.toFile().listFiles();
			noofFilesPresent=filesPresent.length;			
			
			if(noofFilesPresent>0){
			for(int j=0;j<noofFilesPresent;j++){								
					if(filesPresent[j].toString().contains(".html")){	
						htmlResultFileExist=true;						
						break;
				}				
				}		
			}
		if(htmlResultFileExist){			
			// Creating Backup folder to store old results
			if(!(resultsBackupDIR.toFile().exists()))
				resultsBackupDIR.toFile().mkdir();
			
			//Taking backup of old results files
			System.out.println("Taking backup of the old html result file(s)");
			for (final Path pathResults: Files.newDirectoryStream(resultsDIR,strFileType))
				Files.copy(pathResults, resultsBackupDIR.resolve(pathResults.getFileName()),StandardCopyOption.REPLACE_EXISTING);
			
			// Deleting the old result files
	    	for (final Path pathDelResults: Files.newDirectoryStream(resultsDIR,strFileType))    	
	    	Files.delete(pathDelResults);
	    	System.out.println("Deleted the old html result file(s)");
		}
		
	}catch(Exception e){    		
		e.printStackTrace();
	}
	}
    }

