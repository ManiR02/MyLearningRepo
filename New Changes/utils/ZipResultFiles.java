
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;


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

public class ZipResultFiles{	
	List<String> fileList = new ArrayList<String>();		
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
    public void createZipFile(){    	
    	try{
    		
    		sysProperty =new Properties();
			sysProperty.load(new FileInputStream(new File("./config/SystemConfig.properties")));			
			// Result file type to be zipped
			strFileType="*."+sysProperty.getProperty("fileTypetoZIP");
   	
    	//Delete HTML_Files folder
    	FileUtils.deleteDirectory(new File(htmlFilesPath));
    	
      	//Delete Results_Zipped folder
    	FileUtils.deleteDirectory(new File(resultZIPFilePath));
    	
    	// Creating HTML_Files folder
    	if(!(htmlDIR.toFile().exists()))
    		htmlDIR.toFile().mkdir();    	
    	
    	// Creating Results_Zipped folder
    	if(!(resultZIPFileDIR.toFile().exists()))
    		resultZIPFileDIR.toFile().mkdir();
    	    	
    	
    	// Copying Results to temp folder HTML_Files
    	for (final Path pathCpyResults: Files.newDirectoryStream(resultsDIR,strFileType))    	
    		Files.copy(pathCpyResults, htmlDIR.resolve(pathCpyResults.getFileName()));    	
    	
    	System.out.println("Zipping the html result file(s)");
    	ZipResultFiles objZIP = new ZipResultFiles();
    	
    	objZIP.generateFileList(new File(htmlDIR.toString()),htmlDIR);
    	objZIP.zipFiles(resultZIPFile,htmlDIR,htmlFilesPath);
    	
    	System.out.println("Results.zip file is created successfully inside Results_Zipped folder");
    	
    	}catch(Exception e){    		
    		e.printStackTrace();
    	}
    	
    }
    
    public void zipFiles(String zipFile,Path htmlDIR,String htmlFilesPath){
     try{
         byte[] buffer = new byte[1024];
    	FileOutputStream fos = new FileOutputStream(zipFile);
    	ZipOutputStream zos = new ZipOutputStream(fos);    	

    	for(String file : this.fileList){    		
    		ZipEntry ze= new ZipEntry(file);
        	zos.putNextEntry(ze);        	
        	FileInputStream in = new FileInputStream(htmlDIR.toString() + File.separator + file);
        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}
        	in.close();
        }
    	    	
    	//Deleting the temporary HTML_Files folder
    	FileUtils.deleteDirectory(new File(htmlFilesPath));
    	zos.closeEntry();    	
    	zos.close();
    }catch(IOException ex){
       ex.printStackTrace();
    }
   }
    

    //Traverse a directory and get all files, and add the file into fileList 
    
    public void generateFileList(File htmlFolderContents,Path htmlDIR){
    	try{		    	
		    	//add file only
			if(htmlFolderContents.isFile()){	
				fileList.add(generateZipEntry(htmlFolderContents.getAbsoluteFile().toString(),htmlDIR));
			}	
		
			 if((htmlFolderContents.isDirectory())){		
				 String[] subNote = htmlFolderContents.list();
				for(String filename : subNote){
					generateFileList(new File(htmlFolderContents, filename),htmlDIR);			
				}
		}
    	}catch(Exception e){    		
    		e.printStackTrace();
    	}

    }

    // Format the file path for zip
    // @return Formatted file path
    
    private String generateZipEntry(String file,Path htmlDIR){    	
    	return file.substring(htmlDIR.toString().length()+1, file.length());
    }

}

