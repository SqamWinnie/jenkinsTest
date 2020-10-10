package javadev.邮件;

import javadev.邮件.工具类.MailSenderInfo;
import javadev.邮件.工具类.SimpleMailSender;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @description 邮件测试
 * @date 2019/2/19
 */
public class 邮件测试 {
    public static void main(String[] args) {
        //发送邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        // 139 邮箱：pop.139.com    qq 邮箱：smtp.qq.com(需要设置授权码)
        // 汉得邮箱：mail.hand-china.com | smtphm.qiye.163.com
        // 网易邮箱：SMTP:smtp.qiye.163.com  POP3:pop.qiye.163.com  IMAP:imap.qiye.163.com
        mailInfo.setMailServerHost("smtphm.qiye.163.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("yachen.li@hand-china.com");
        mailInfo.setPassword("3s8dmTV9");
        mailInfo.setFromAddress("yachen.li@hand-china.com");
        mailInfo.setToAddress("18270882559@139.com");
        mailInfo.setSubject("邮件标题测试");
        mailInfo.setContent("<html><body>邮件<h1>内容</h1>测试<br/><hr/></body></html>");

        //这个类主要来发送邮件
        //发送文体格式
//        SimpleMailSender sms = new SimpleMailSender();
//        sms.sendTextMail(mailInfo)

        // 发送 html 格式
        SimpleMailSender.sendHtmlMail(mailInfo);
    }
}
