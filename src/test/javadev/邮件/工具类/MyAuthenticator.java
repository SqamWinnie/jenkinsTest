package javadev.邮件.工具类;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.stereotype.Service;

/**
 * @author Tang
 */
@Service
public class MyAuthenticator extends Authenticator {
    String userName=null;
    String password=null;

    public MyAuthenticator(){
    }
    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }
    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);
    }
}