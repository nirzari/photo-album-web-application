package myPackage;
import com.dropbox.core.*;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Dropbox1 {
	
	String filename;
	Map<String, Object> filelist = new HashMap<String, Object>();
	String dfile;
	DbxClient client;
	String dispImage = "";
	

	final String APP_KEY = "jqptq5c67ofpaps";
    final String APP_SECRET = "du73a1uc689iels";
    
	public String getDispImage() {
		return dispImage;
	}

	public void setDispImage(String dispImage) {
		this.dispImage = dispImage;
	}

	public String getFilename() {
		return filename;
	}
	
	public void setFilename(File filename) {
		this.filename = filename.getPath();
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setFilelist(Map<String, Object> filelist) {
		this.filelist = filelist;
	}

	public Map<String, Object> getFilelist() {
		return filelist;
	}
	
	public String getDfile() {
		return dfile;
	}

	public void setDfile(String dfile) {
		this.dfile = dfile;
	}

	
	public Dropbox1() throws DbxException, IOException{
	    DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
	    DbxRequestConfig config = new DbxRequestConfig(
	        "JavaTutorial/1.0", Locale.getDefault().toString());
	    DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
	    String authorizeUrl = webAuth.start();
	    System.out.println("1. Go to: " + authorizeUrl);
	    System.out.println("2. Click \"Allow\" (you might have to log in first)");
	    System.out.println("3. Copy the authorization code.");
	    String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
	    DbxAuthFinish authFinish = webAuth.finish(code);
	    String accessToken = authFinish.accessToken;
	    client = new DbxClient(config, accessToken);
	    //System.out.println("Linked account: " + client.getAccountInfo().displayName);
	    System.out.println("Authentication Successful");
	 
	}
	 
	public void dbUpload() throws IOException {
		File inputFile = new File(filename);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");	
			e.printStackTrace();
		}
		try {
			if(inputStream!=null){
			    DbxEntry.File uploadedFile = client.uploadFile("/"+inputFile.getName(),
			        DbxWriteMode.add(), inputFile.length(), inputStream);
			    //System.out.println("Uploaded: " + uploadedFile.toString());
			    System.out.println("File " + uploadedFile.toString() + "Successfully uploaded");
			}
		} catch (DbxException e) {
			System.out.println("Error while uploading file");
			e.printStackTrace();
		}
		finally {
			if(inputStream!=null)
				inputStream.close();
		}
	}
	
	public void dbListing() throws IOException{
		filelist = new HashMap<String, Object>();
		DbxEntry.WithChildren listing = null;
		try {
			listing = client.getMetadataWithChildren("/");
		} catch (DbxException e) {
			System.out.println("Error while listing files");
			e.printStackTrace();
		}
		System.out.println("Files in the root path:");
			for (DbxEntry child : listing.children) {
			    	filelist.put(""+child.name, ""+child.name);
					System.out.println("	" + child.name + ": " + child.toString());
				}
		}
	
	public void dbDownload() throws IOException {
		FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Nirzari18\\workspace\\project5\\WebContent\\resources\\"+dfile);
		System.out.println(dfile);
			try {
			    DbxEntry.File downloadedFile = client.getFile("/"+dfile, null,
			        outputStream);
			    System.out.println("File " + downloadedFile.toString() + "Successfully downloaded");
			    dispImage = client.createShareableUrl("/"+dfile).replace("://www.", "://dl.");
			} catch (DbxException e) {
				System.out.println("Error while downloading file");
				e.printStackTrace();
			} 
			finally {
			    outputStream.close();
			}
		}
	
	public void dbDelete() throws IOException {
		File delfile = new File(dfile);
			try {
				client.delete("/"+ delfile.getName());
			    System.out.println("File " + delfile.getName() + " Successfully deleted");
			    dbListing();
			    dispImage = "";
			} catch (DbxException e) {
				System.out.println("Error while deleting a file");
				e.printStackTrace();
			} 
			finally {
			    
			}
		}
//	Made for Testing	
//	public static void main(String[] args) throws IOException, DbxException {
//		String filename = "E:\\noddy1.jpg";
//		String dfile = "noddy1.jpg";
//		Dropbox1 db = new Dropbox1();
//		db.setFilename(filename);
//		db.setDfile(dfile);
//		db.dbUpload();
//		db.dbListing();
//		db.dbDownload();
//		db.dbDelete();
//	}
}	