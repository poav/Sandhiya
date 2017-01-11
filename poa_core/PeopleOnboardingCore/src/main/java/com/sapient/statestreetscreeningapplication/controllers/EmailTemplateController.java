package com.sapient.statestreetscreeningapplication.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.ui.bean.EmailConfigBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

@RestController
public class EmailTemplateController {
	
	@CrossOrigin
	@RequestMapping("/retrieveAllEmailTemplates")
	public List<EmailConfigBean> viewEmailConfigs() {
		CustomLoggerUtils.INSTANCE.log.info("inside viewEmailConfigs() method and in ViewEmailsAction");
		BufferedReader reader = null;
		String configAttachment=null;
		String directoryLocation = "D:/PeopleOnboarding3.0/MailTemplates";//This one is for deployed
		//String directoryLocation="D:/ScreeningApplication/MailTemplates";//This one is for developers
		File directory = new File(directoryLocation);
		File[] files = directory.listFiles();
		List<EmailConfigBean> emailConfigList = new ArrayList<>();
		for (File file : files)
		{
			EmailConfigBean emailConfigBean=new EmailConfigBean();
			String stp="";
			String subject;
			List<String> attachment = new ArrayList<String>();
			List<String> selectedAttachments=new ArrayList<String>();
			try {
				reader = new BufferedReader(new FileReader(file));
				String str = reader.readLine();
				subject=str;
				str = reader.readLine();
				while (str!=null)
				{
					 stp=stp.concat(str);
		          str = reader.readLine();
		         }
				String[] subj=subject.split("---");
				emailConfigBean.setContent(stp);
				emailConfigBean.setName(file.getName());
				emailConfigBean.setSubject(subj[0]);
				String fileName=file.getName();
				if(!subj[1].equals("No Attachments") ||fileName.equals("SendEmailToCandidateIndiaBGC")||fileName.equals("SendEmailToCandidateNYOnsiteBGC")
				||fileName.equals("SendEmailToCandidateUSOnsiteBGC")||fileName.equals("SendPSIDEmailToCandidateBoston")||fileName.equals("SendEmailToCandidateCostaRicaBGC")){
				String directoryLoc = "D:/PeopleOnboarding3.0/Attachments";//This one is for deployed
					//String directoryLoc ="D:/ScreeningApplication/Attachments";
				String fileLoc=directoryLoc+"/"+fileName;
				File direc = new File(fileLoc);
				File[] files2 = direc.listFiles();
					
				for (File file2 : files2)
				{
					attachment.add(file2.getName());
				}
				String[] attachmentNames=subj[1].split(",");
					for(String attachmentName:attachmentNames){
						if(!attachmentName.equals("No Attachments")){
							selectedAttachments.add(attachmentName);
						}
						else if(attachmentName.equals("No Attachments")){
							break;
						}	
					}
					for (String attachFileName : selectedAttachments) {
						if(configAttachment==null){
							configAttachment=fileName+"$$$"+attachFileName+"@@@";
						}else{
							configAttachment=configAttachment+fileName+"$$$"+attachFileName+"@@@";
						}
					}
				}
				else{
					attachment.add("No Attachments");
					selectedAttachments.add("No Attachments");
				}
				
				emailConfigBean.setAttachments(attachment);
				emailConfigBean.setSelectedAttachments(selectedAttachments);
				emailConfigList.add(emailConfigBean);
				
				}
			catch (Exception e){
		  			throw new RuntimeException(e);
		  	} 
			finally {
	  			if (reader != null) {
	  				try {
	  					reader.close();
	  				}
	  				catch (Exception e) {
	  					// ignore
	  				}
	  			}
	  		}
		}
		return emailConfigList;
	}
	
	@CrossOrigin
	@RequestMapping(value="/updateEmailConfig" , method= RequestMethod.POST)
	public void updateEmailConfig(@RequestBody EmailConfigBean emailConfigBean ) throws IOException {
		
		CustomLoggerUtils.INSTANCE.log.info("inside updateEmailConfig() method and in UpdateEmailsAction");
		File newAttachment = null;
		String extension = null;
		String filename = null;
		String configAttachment = null;
		
		
		List<String> selectedAttachments= emailConfigBean.getSelectedAttachments();

		if(newAttachment!=null){
			
		InputStream is = null;
	    OutputStream os = null;
	    //String path="D:/ScreeningApplication/Attachments";//This one is for developers
	    String path="D:/PeopleOnboarding3.0/Attachments";
	    
	    
	    
	    String filePath=emailConfigBean.getName();
	    		String direcPath=path+"/"+filePath;
		File folder = new File(direcPath);
		extension=filename.substring(filename.lastIndexOf(".")+1);
		filename=filename.substring(0,filename.lastIndexOf("."));
		
		File file;
		try { file = new File(folder+"/"+filename+"."+extension);
			 is = new FileInputStream(newAttachment);
		        os = new FileOutputStream(file);
		        byte[] buffer = new byte[1000000];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }        
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
	        is.close();
	        os.close();
	    }
		}
	
	String fileName=emailConfigBean.getName();
	//String directoryLocation ="D:/ScreeningApplication/MailTemplates"; //This one is for developers
	 String directoryLocation="D:/PeopleOnboarding3.0/MailTemplates";
	String fileLoc=directoryLocation+"/"+fileName;
	File fnew=new File(fileLoc);

//	String[] sArray=configAttachment.split("@@@");
//	 List<String> checked = Arrays.asList(sArray);
	 
	 
	 List<String> modifiedChecked=new ArrayList<String>();
	 for(String attachmentName:selectedAttachments)
	 {
		 modifiedChecked.add(attachmentName);
	 }
	 
	 if(newAttachment!=null)
	 {
		 modifiedChecked.add(filename+"."+extension);
	 }
	 
    String source = emailConfigBean.getContent();
    String subject=emailConfigBean.getSubject();
    subject=subject+"---";
    if(modifiedChecked.size()==1){
    	subject=subject+modifiedChecked.get(0);
    }
    else{
    	for(String check:modifiedChecked){
	    	subject=subject+check;
	    	subject=subject+",";
    }
    	
    StringBuilder b = new StringBuilder(subject);
    b.replace(subject.lastIndexOf(","), subject.lastIndexOf(",") + 1, "" );
    subject = b.toString();}
    FileWriter f2;
  try {
        f2 = new FileWriter(fnew,false);
        f2.write(subject);
        f2.write(System.getProperty( "line.separator" ));
        f2.write(source);
        f2.close();  
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }         
  
	}


}
