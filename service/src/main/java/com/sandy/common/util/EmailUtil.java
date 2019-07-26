package com.sandy.common.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件发送工具类
 * 
 * @author sandy
 * @version $Id: EmailUtil.java, v 0.1 2017年3月31日 上午9:48:36 sandy Exp $
 */
public final class EmailUtil {
    private static Logger logger   = LoggerFactory.getLogger(EmailUtil.class);
    /**
     *  "smtp.ym.163.com"
     */
    private static String HOST     = "smtp.adpanshi.com";

    private static String PROTOCOL = "smtp";

    /**
     * service@xxxx.com
     */
    private static String FROM     = "psadmin@adpanshi.com ";

    /**
     *  发送邮箱账号
     * service@xxx.com
     */
    private static String USER     = "psadmin@adpanshi.com ";

    /**
     *  发送邮箱密码
     */
    private static String PASS     = "xxxxx";


    /**
     *  统一初始化一次   
     * 
     * @param host       smtp.ym.163.com
     * @param protocol   协议 默认smtp
     * @param from     发送出的邮件地址
     * @param user     用户
     * @param pass
     */
    public static void init(String host, String protocol, String from, String user, String pass) {
        HOST = host;
        PROTOCOL = protocol;
        FROM = from;
        USER = user;
        PASS = pass;
    }

    /**
     *  统一初始化一次   
     * 
     * @param host       smtp.ym.163.com
     * @param protocol   协议 默认smtp
     * @param from     发送出的邮件地址
     * @param user     用户
     * @param pass
     */
    public static void init(String host, String from, String user, String pass) {
        init(host, PROTOCOL, from, user, pass);
    }


    /**
     *  发送邮件   邮件内容支持HTML
     * 
     * @param host   
     * @param protocol
     * @param from
     * @param user
     * @param pass
     * @param toMail
     * @param mailTitle
     * @param mailContent
     * @throws Exception
     */
    public static void sendMail(String host, String protocol, String from, String user, String pass,
                                String toMail, String mailTitle,
                                String mailContent) throws Exception {

        Properties props = new Properties(); //可以加载一个配置文件  
        // 使用smtp：简单邮件传输协议  
        props.put("mail.smtp.host", host);//存储发送邮件服务器的信息  
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", "true");//同时通过验证  
        props.put("mail.smtp.localhost", "localhost");
        Session session = Session.getInstance(props);//根据属性新建一个邮件会话  
        //        session.setDebug(true); //有他会打印一些调试信息。  

        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象  
        message.setFrom(new InternetAddress(from));//设置发件人的地址  
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));//设置收件人,并设置其接收类型为TO  
        message.setSubject(mailTitle);//设置标题  
        //设置信件内容  
        //        message.setText(mailContent); //发送 纯文本 邮件 todo  
        message.setContent(mailContent, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富  
        message.setSentDate(new Date());//设置发信时间  
        message.saveChanges();//存储邮件信息  


        //发送邮件  
        //        Transport transport = session.getTransport("smtp");  
        Transport transport = session.getTransport();
        transport.connect(user, pass);
        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址  
        transport.close();

    }

    /**
     *   发送邮件， 可以初始化init方法，    邮件内容支持HTML
     * 
     * @param toMail
     * @param mailTitle
     * @param mailContent
     * @throws Exception
     */
    public static void sendMail(String toMail, String mailTitle,
                                String mailContent) throws Exception {
        Properties props = new Properties(); //可以加载一个配置文件  
        // 使用smtp：简单邮件传输协议  
        props.put("mail.smtp.host", HOST);//存储发送邮件服务器的信息  
        props.put("mail.transport.protocol", PROTOCOL);
        props.put("mail.smtp.auth", "true");//同时通过验证  
        props.put("mail.smtp.localhost", "localhost");
        Session session = Session.getInstance(props);//根据属性新建一个邮件会话  
        //        session.setDebug(true); //有他会打印一些调试信息。  

        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象  
        message.setFrom(new InternetAddress(FROM));//设置发件人的地址  
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));//设置收件人,并设置其接收类型为TO  
        message.setSubject(mailTitle);//设置标题  
        //设置信件内容  
        //        message.setText(mailContent); //发送 纯文本 邮件 todo  
        message.setContent(mailContent, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富  
        message.setSentDate(new Date());//设置发信时间  
        message.saveChanges();//存储邮件信息  

        logger.error("EMAIL-SEND: {}-> {}, {}", FROM, toMail, mailTitle);

        //发送邮件  
        //        Transport transport = session.getTransport("smtp");  
        Transport transport = session.getTransport();
        transport.connect(USER, PASS);
        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址  
        transport.close();
    }

}
