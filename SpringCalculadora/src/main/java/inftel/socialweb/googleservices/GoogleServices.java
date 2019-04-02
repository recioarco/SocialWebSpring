package inftel.socialweb.googleservices;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

@Service
public class GoogleServices {
	
	// -----     CREAR CARPETA     ----- 
	
	 public static final File createGoogleFolder(String folderIdParent, String folderName) throws Exception {
		 
	        File fileMetadata = new File();
	 
	        fileMetadata.setName(folderName);
	        fileMetadata.setMimeType("application/vnd.google-apps.folder");
	 
	        if (folderIdParent != null) {
	            List<String> parents = Arrays.asList(folderIdParent);
	 
	            fileMetadata.setParents(parents);
	        }
	        Drive driveService = GoogleDriveUtils.getDriveService();
	 
	        // Crea una carpeta
	        // Devuelve un archivo con el ID y el Nombre
	        File file = driveService.files().create(fileMetadata).setFields("id, name").execute();
	 
	        return file;
	    }
	 
	// -----     CREAR ARCHIVO    ----- 
	 
	 private static File _createGoogleFile(String googleFolderIdParent, String contentType, //
	            String customFileName, AbstractInputStreamContent uploadStreamContent) throws Exception {
	 
	        File fileMetadata = new File();
	        fileMetadata.setName(customFileName);
	 
	        List<String> parents = Arrays.asList(googleFolderIdParent);
	        fileMetadata.setParents(parents);
	        //
	        Drive driveService = GoogleDriveUtils.getDriveService();
	 
	        File file = driveService.files().create(fileMetadata, uploadStreamContent)
	                .setFields("id, webContentLink, webViewLink, parents").execute();
	 
	        return file;
	    }
	 
	// -----     Crea un archivo desde un byte[]    ----- 
	   
	    public static File createGoogleFile(String googleFolderIdParent, String contentType, //
	            String customFileName, byte[] uploadData) throws Exception {
	        //
	        AbstractInputStreamContent uploadStreamContent = new ByteArrayContent(contentType, uploadData);
	        //
	        return _createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
	    }
	    
	 // -----     Crea un archivo desde un MultipartFile    -----   
	 
	    public static File createGoogleFile(String googleFolderIdParent, //
	            String customFileName, MultipartFile multipartFile) throws Exception {
	    	
	    		//logger.debug("Inside Upload Service...");
	    	  	String property = "java.io.tmpdir";

	    	  	// Get the temporary directory and print it.
	    	  	String tempDir = System.getProperty(property);
	    	  	System.out.println("OS temporary directory is " + tempDir);
	   

	    		//String path = getResource("classpath:data/employees.dat");
	    		String fileName = multipartFile.getOriginalFilename();
	    		String contentType = multipartFile.getContentType();
	    		
	    		 
	    		//java.io.File transferedFile = new ClassPathResource(fileName).getFile();

	    		java.io.File transferedFile = new java.io.File(tempDir, fileName);
	    		multipartFile.transferTo(transferedFile);

	    		File fileMetadata = new File();
	    		fileMetadata.setName(fileName);
	    		FileContent uploadStreamContent = new FileContent(contentType, transferedFile);
	 
	        return _createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
	    }
	 
	 // -----     Crea un archivo desde un java.io.File    ----- 
	    
	    public static File createGoogleFile(String googleFolderIdParent, String contentType, //
	            String customFileName, java.io.File uploadFile) throws Exception {
	 
	        //
	        AbstractInputStreamContent uploadStreamContent = new FileContent(contentType, uploadFile);
	        //
	        return _createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
	    }
	 
	 // -----     Crea un archivo desde un InputStream    -----
	    
	    public static File createGoogleFile(String googleFolderIdParent, String contentType, //
	            String customFileName, InputStream inputStream) throws Exception {
	 
	        //
	        AbstractInputStreamContent uploadStreamContent = new InputStreamContent(contentType, inputStream);
	        //
	        return _createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
	    }
	 
	 // -----     BORRAR ARCHIVO POR ID   -----
	    
	    public static void deleteFile(String fileId) throws Exception {
	    	
	    	Drive driveService = GoogleDriveUtils.getDriveService();
	    	
	        try {
	        	driveService.files().delete(fileId).execute();
	        } catch (IOException e) {
	          System.out.println("An error occurred: " + e);
	        }
	      }
	    
	 // -----     BUSCAR ARCHIVO POR NOMBRE    ----- 
	    
	    public static final List<File> getGoogleFilesByName(String fileNameLike) throws Exception {
	    	 
	        Drive driveService = GoogleDriveUtils.getDriveService();
	 
	        String pageToken = null;
	        List<File> list = new ArrayList<File>();
	 
	        String query = " name contains '" + fileNameLike + "' " //
	                + " and mimeType != 'application/vnd.google-apps.folder' ";
	 
	        do {
	            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
	                    // Fields will be assigned values: id, name, createdTime, mimeType
	                    .setFields("nextPageToken, files(id, name, createdTime, mimeType)")//
	                    .setPageToken(pageToken).execute();
	            for (File file : result.getFiles()) {
	                list.add(file);
	            }
	            pageToken = result.getNextPageToken();
	        } while (pageToken != null);
	        //
	        return list;
	    }
	    
	  // -----     BUSCAR SUBCARPETAS    ----- 
	 
	 
	    public static final List<File> getGoogleSubFolders(String googleFolderIdParent) throws Exception {
	 
	        Drive driveService = GoogleDriveUtils.getDriveService();
	 
	        String pageToken = null;
	        List<File> list = new ArrayList<File>();
	 
	        String query = null;
	        if (googleFolderIdParent == null) {
	            query = " mimeType = 'application/vnd.google-apps.folder' " //
	                    + " and 'root' in parents";
	        } else {
	            query = " mimeType = 'application/vnd.google-apps.folder' " //
	                    + " and '" + googleFolderIdParent + "' in parents";
	        }
	 
	        do {
	            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
	                    // Fields will be assigned values: id, name, createdTime
	                    .setFields("nextPageToken, files(id, name, createdTime)")//
	                    .setPageToken(pageToken).execute();
	            for (File file : result.getFiles()) {
	                list.add(file);
	            }
	            pageToken = result.getNextPageToken();
	        } while (pageToken != null);
	        //
	        return list;
	    }
	 
	    // -----     LISTAR CARPETAS DE LA RAIZ    ----- 
	    
	    public static final List<File> getGoogleRootFolders() throws Exception {
	        return getGoogleSubFolders(null);
	    }
	    
	    // -----     BUSCAR EN SUBCARPTAS POR NOMBRE    ----- 
	    
	    public static final List<File> getGoogleSubFolderByName(String googleFolderIdParent, String subFolderName)
	            throws Exception {
	 
	        Drive driveService = GoogleDriveUtils.getDriveService();
	 
	        String pageToken = null;
	        List<File> list = new ArrayList<File>();
	 
	        String query = null;
	        if (googleFolderIdParent == null) {
	            query = " name = '" + subFolderName + "' " //
	                    + " and mimeType = 'application/vnd.google-apps.folder' " //
	                    + " and 'root' in parents";
	        } else {
	            query = " name = '" + subFolderName + "' " //
	                    + " and mimeType = 'application/vnd.google-apps.folder' " //
	                    + " and '" + googleFolderIdParent + "' in parents";
	        }
	 
	        do {
	            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
	                    .setFields("nextPageToken, files(id, name, createdTime)")//
	                    .setPageToken(pageToken).execute();
	            for (File file : result.getFiles()) {
	                list.add(file);
	            }
	            pageToken = result.getNextPageToken();
	        } while (pageToken != null);
	        //
	        return list;
	    }
	 
	    
	    public static final List<File> getGoogleRootFoldersByName(String subFolderName) throws Exception {
	        return getGoogleSubFolderByName(null,subFolderName);
	    }
	

}
