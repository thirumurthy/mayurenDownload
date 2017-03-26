import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import Common.FileOperations;


public class Download {
	
	public static void DownloadFile(List<String> data,String destfolder)
	{
		try
		{
			String runningid=FileOperations.readFile("running.txt");
			boolean isnew=false;
			int i=1;
			for(String slgitem:data)
			{
				
				System.out.println("Downloading :"+data.size()+" items . Job picks now "+i+" th item ");
				if(runningid.equals("")||isnew)
				{
					runningid=slgitem;
					
				}
				if(runningid.equals( slgitem))
				{
					WriteData(slgitem,destfolder);
					FileOperations.WriteData("running.txt", runningid);
					isnew=true;
				}
				else
				System.out.println("Skipping:"+slgitem);
				i++;
			}
			
		}
		catch(Exception exp)
		{
			 
			System.out.print("OOPS..");
		}
	}

	private static void WriteData(String slgitem,String destfolder) {
		
		try
		{
			URL url = new URL(slgitem);
			String filename=slgitem.substring(slgitem.lastIndexOf("/")+1,slgitem.length());
			filename=URLDecoder.decode( filename.substring(0, filename.indexOf("."))+".mp3");
			Path targetPath = new File(destfolder+filename).toPath();
			System.out.println("Downloading... !! ..Song name:" +filename);
			Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File Saved in "+ targetPath.toString());
			
		}
		catch(Exception ex)
		{
			FileOperations.appendFile("failed.txt", slgitem);
		}
		
	}

}
