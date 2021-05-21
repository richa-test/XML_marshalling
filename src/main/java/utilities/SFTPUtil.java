package utilities;
import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;

import java.io.File;
import java.io.FileInputStream;


public class SFTPUtil {
    Session session = null;
    Channel channel = null;
    ChannelSftp channelSftp = null;

    public void connectToSftpHost(String sftpHostName, int sftpPort, String sftpUserName, String sftpPsssword) throws Exception {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(sftpUserName, sftpHostName, sftpPort);
            session.setPassword(sftpPsssword);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            System.out.println("Host Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fileUploadToSftpLocation(String serverDirectory, String fileName) throws Exception {


        try {
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("SFTP channel opened and connected");
             channelSftp = (ChannelSftp) channel;
            channelSftp.cd(serverDirectory);
            File file = new File(fileName);
            channelSftp.put(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            channelSftp.exit();
            channel.disconnect();
            session.disconnect();
        }

    }




}
