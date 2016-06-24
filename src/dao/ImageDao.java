package dao;

import java.io.File;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;



public class ImageDao {
	
	public static String imageUpload(HttpServletRequest request, String uploadKey, Part part) {
		
		try {				
			String contentType = part.getContentType();
			boolean validType = contentType.equals("image/png")
					|| contentType.equals("image/jpeg")
					|| contentType.equals("image/gif");
			boolean validSize = part.getSize()<=1024*1024;
			if(validType && validSize) {
				String appPath = request.getServletContext().getRealPath("");
	            String s = File.separator;         
	            String savePath = appPath + s + "View" + s + "Image" + s + uploadKey;
	            File fileSaveDir = new File(savePath);
	            if (!fileSaveDir.exists()) {
	                fileSaveDir.mkdir();
	            }
	            String fileName = getFileName(savePath, contentType);
	            part.write(savePath + s + fileName);
	           // System.out.println("duong dan luu "+savePath + s + fileName);
	            return fileName;
			}
			else {
				String validKey = "file không đúng định dạng ảnh hoặc kích thước vượt quá 1M..!";
				request.getSession().setAttribute("flash_error",validKey);
				return null;			
			}
		} 
		catch(Exception ex) {
			request.getSession().setAttribute("flash_error","lỗi trong quá trình upload ảnh..!");
			ex.printStackTrace();
			return null;
		}		
	}

	public static String imageLink(HttpServletRequest request, String dir, String name) {
		String path = request.getContextPath() + "/View/Image/";
		return path + dir + "/" + name;
	}
	
	private static String getFileName(String savePath, String contentType) {
		String s = File.separator;
		String fileP = "img";
        String fileS = "." + contentType.substring(6);
        Random ran = new Random();
        String fileN = ""+ ran.nextInt(1000);
        String fileName = fileP + fileN + fileS;
        File file = new File(savePath + s + fileName);	            
        while (file.exists()) {
        	fileN = ran.nextInt(100) + fileN;
        	fileName = fileP + fileN + fileS;
        	file = new File(savePath + s + fileName);
        }
		return fileName;
	}
}
