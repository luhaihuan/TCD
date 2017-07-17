package com.sulliar.ypq.fileManagement;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class SpecialBatchDownloadAction extends ActionSupport {

	String path;
	String dsname;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDsname() {
		return dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}
	
	private  String msg; 
    private String contentType; 
    private File docmentFile; 
    private String fileName;

    public String getFileName() { 
        return fileName; 
    } 
   
    public void setFileName(String fileName) { 
        this.fileName = fileName; 
    } 
   
    // since we are using <s:file name="upload" .../> the file name will be 
    // obtained through getter/setter of <file-tag-name>FileName 
    public String getUploadFileName() { 
        return fileName; 
    } 
   
    public void setUploadFileName(String fileName) { 
        this.fileName = fileName; 
    } 
   
    // since we are using <s:file name="upload" ... /> the content type will be 
    // obtained through getter/setter of <file-tag-name>ContentType 
    public String getUploadContentType() { 
        return contentType; 
    } 
   
    public void setUploadContentType(String contentType) { 
        this.contentType = contentType; 
    } 
   
    // since we are using <s:file name="upload" ... /> the File itself will be 
    // obtained through getter/setter of <file-tag-name> 
    public File getUpload() { 
        return docmentFile; 
    } 
   
    public void setUpload(File docmentFile) { 
        this.docmentFile = docmentFile; 
    } 
   
    public String getMsg() { 
        return msg; 
    } 
   
    public void setMsg(String msg) { 
        this.msg = msg; 
    } 
   
    public String getContentType() { 
        return contentType; 
    } 
   
    public void setContentType(String contentType) { 
        this.contentType = contentType; 
    } 
   
    public File getDocmentFile() { 
        return docmentFile; 
    } 
   
    public void setDocmentFile(File docmentFile) { 
        this.docmentFile = docmentFile; 
    } 

    boolean success;
    
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}	
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//String realPath = "E:\\" + fileName; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String realPath = ServletActionContext.getServletContext().getRealPath("/")+"HFTempFiles/批量下载模板"+sdf.format(new Date())+fileName;
        if (docmentFile.isFile()) { 
            BufferedInputStream bis = new BufferedInputStream( 
                    new FileInputStream(docmentFile)); 
            BufferedOutputStream bos = null; 
            try { 
                bos = new BufferedOutputStream(new FileOutputStream(realPath));// 为以防万一，以后写文件的路径尽量写成正双斜杠的 
                // 从源文件中取数据，写到目标文件中 
                byte[] buff = new byte[8192]; 
                for (int len = -1; (len = bis.read(buff)) != -1;) { 
                    bos.write(buff, 0, len); 
                } 
                bos.flush(); 
            } catch (IOException ie) { 
                ie.printStackTrace(); 
                msg="文件上传失败";  
                success = false;
                path = "";
        		return "none";
            } finally { 
                if (bis != null) { 
                    try { 
                        bis.close(); 
                    } catch (IOException ie) { 
                        ie.printStackTrace(); 
                    } 
                } 
                if (bos != null) { 
                    try { 
                        bos.close(); 
                    } catch (IOException ie) { 
                        ie.printStackTrace(); 
                    } 
                } 
            } 
        }else{
        	 msg="请选择文件"; 
        }
        msg="文件上传成功"; 
        path = 	realPath;	
		success = true;
		return "success";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
