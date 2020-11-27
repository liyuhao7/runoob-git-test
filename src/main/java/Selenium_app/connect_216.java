package Selenium_app;

import com.jcraft.jsch.*;

import java.io.*;

public class connect_216 {
    public static void connectLinux() throws JSchException, SftpException, IOException {
        JSch jSch = new JSch();
        jSch.getSession("root", "192.168.202.216", 22);
        Session sshSession = jSch.getSession("root", "192.168.202.216", 22);
        sshSession.setPassword("EverCsrcSkyNet18!g*&o0oAd#cCQ-_");
        sshSession.setConfig("StrictHostKeyChecking", "no");
        sshSession.connect();
        ChannelSftp channel = (ChannelSftp) sshSession.openChannel("sftp");
        channel.connect();
        System.out.println(channel.isConnected());

        channel.cd("/home/lyh/appSpider/");
        File file = new File("src/newapp.txt");
        InputStream input = new BufferedInputStream(new FileInputStream(file));
        channel.put(input, file.getName());
        input.close();
        channel.disconnect();
        sshSession.disconnect();
        System.out.println("上传完毕");


    }

    public static void main(String[] args) throws JSchException, IOException, SftpException {
        connectLinux();

    }
}
