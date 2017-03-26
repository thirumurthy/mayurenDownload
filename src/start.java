import java.nio.file.Path;
import java.util.List;

import Common.DocumentReader;
import Common.XmlParser;


public class start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DocumentReader.readSettings();
		
		switch (DocumentReader.AppData.get("type")) {
		case "add":
			List<String> data = XmlParser. xmltoList(DocumentReader.AppData.get("filename"),"location");
			Download.DownloadFile(data,DocumentReader.AppData.get("destfolder"));
			break;

		default:
			break;
		}
	}

}
