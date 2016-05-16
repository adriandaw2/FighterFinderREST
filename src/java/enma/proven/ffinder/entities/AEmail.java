/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.entities;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Alumne
 */
public class AEmail {
    
    private Properties props;
    private Session session;
    private String fromWho;
    private String toWho;
    private String subject;
    private String msgText;
    private String mailUser;
    private String mailPass;
    private int idUser;

    public AEmail() {
    }

    public AEmail(String toWho) {
        this.mailUser = "fighterfinderinfo@gmail.com";
        this.mailPass = "fighter@623DPblock";
        setProperties();
        this.fromWho = this.mailUser;
        this.toWho = toWho;
        this.subject = "Confirm email for FighterFinder";
        //this.idUser = idUser;
    }

    private void setProperties() {
        /*props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");*/
        props = System.getProperties();
        props.setProperty("mail.smtp.host", "localhost");
        createSession();
    }

    private void createSession() {
        /*session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
                                @Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailUser,mailPass);
				}
			});*/
        session = Session.getDefaultInstance(props);
    }
    
    
    /**
     * sendEmailConfirmation
     * Method to send a Email to activate the account
     * Instance all the neccessary stuff to do it
     */
    public void sendEmailConfirmation()
    {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.fromWho));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.toWho));
            
            message.setSubject(this.subject);
            StringBuilder sb = new StringBuilder();
            sb.append("Please click the next link to activate your account int FighterFinder\n");
            sb.append("<a href='#'>Activate account</a>");
            this.msgText = sb.toString();
            message.setText(this.msgText);
        }catch(MessagingException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * sendEmailDeactivation
     * Method to send a Email to deactivate the account
     * Instance all the neccessary stuff to do it
     */
    public void sendEmailDeactivation()
    {
        try{
            //link to activate the account
            String linkToActivate = "";
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.fromWho));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.toWho));
            
            message.setSubject(this.subject);
            StringBuilder sb = new StringBuilder();
            sb.append("Please click the next link to deactivate your account in FighterFinder\n");
            sb.append("<a href='#'>Deactivate account</a>");
            this.msgText = sb.toString();
            message.setText(this.msgText);
        }catch(MessagingException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
    
}
