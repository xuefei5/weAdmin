package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class RooFtpUtils {
	
	/*try {
		//FileOutputStream out = new FileOutputStream("test.jpg");
    	
        InputStream inputStream = files.get(0).getInputStream();
        
        
        InputStream in = inputStream; // 这里是你已经存在的输入流
        FileInputStream fin = null; // 转换后的文件输入流
         
        // 如果是FileInputStream类型，进行转换
        if (in instanceof FileInputStream) {
            fin = (FileInputStream) in;
            RooFtpUtils.listFileNames("192.168.19.129", 22, "root", "ys123456", "/usr/local/nginx/html",fin);
        } else { // 否则，转型失败
            throw new Exception();
        }
        
        //InputStreamReader is = new InputStreamReader(inputStream);
        //BufferedReader br = new BufferedReader(is);
        //byte[] b = new byte[10*1024];  
        while(fis.read(b,0,10240) != -1){  
          fos.write(b,0,10240);  
        }  

        while (inputStream.read(b,0,10240) != -1) {
        	//out.write();
        	out.write(b,0,1024);
        }
        

    } catch (Exception e) {
        // TODO Auto-generated catch block


    }
	*/
	
	

	/**
	 * @author yangsheng
	 * @param in 
	 */
	public static void pushToFtp(InputStream inputStreamOfFile,String fileName) {
		ChannelSftp sftp = null;
        Channel channel = null;
        Session sshSession = null;
        try {
        	
        	//进行FileInputStream类型转换
        	FileInputStream fin = null; // 转换后的文件输入流
            if (inputStreamOfFile instanceof FileInputStream) {
                fin = (FileInputStream) inputStreamOfFile;
            } else {
                throw new Exception();
            }
        	
            JSch jsch = new JSch();
            jsch.getSession("root", "192.168.19.129", 22);
            sshSession = jsch.getSession("root", "192.168.19.129", 22);
            sshSession.setPassword("ys123456");
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            
            sftp.put(fin, "/usr/local/nginx/html/"+fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeChannel(sftp);
            closeChannel(channel);
            closeSession(sshSession);
        }
	}

	private static void closeChannel(Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    private static void closeSession(Session session) {
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

	
	public static void main(String[] args) {
	}
}
