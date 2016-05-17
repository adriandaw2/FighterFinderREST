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
import javax.mail.Transport;
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
    private String toWhoCip;
    private String subject;
    private String msgText;
    final private String mailUser = "fighterfinderinfo@gmail.com";
    final private String mailPass = "fighter@623DPblock";
    private int idUser;

    public AEmail() {
    }

    public AEmail(String toWho) {
        /*this.mailUser = "fighterfinderinfo@gmail.com";
        this.mailPass = "fighter@623DPblock";*/
        setProperties();
        this.fromWho = this.mailUser;
        this.toWho = toWho;
        this.subject = "Confirm email for FighterFinder";
        //this.idUser = idUser;
    }

    private void setProperties() {
        props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
        createSession();
    }

    private void createSession() {
        session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
                                @Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("fighterfinderinfo@gmail.com","fighter@623DPblock");
				}
			});
    }
    
    
    /**
     * sendEmailConfirmation
     * Method to send a Email to activate the account
     * Instance all the neccessary stuff to do it
     */
    public void sendEmailConfirmation()
    {
        try{
            //link to activate the account
            String linkToActivate = "http://provenapps.cat:8080/FighterFinderREST/ff/user/activateAcc/"+this.toWho;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.fromWho));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.toWho));
            
            message.setSubject(this.subject);
            StringBuilder sb = new StringBuilder();
            sb.append("Please click the next link to activate your account in FighterFinder\n");
            sb.append("<a href=");
            sb.append(linkToActivate);
            sb.append(">Activate account</a>");
            this.msgText = sb.toString();
            message.setContent(this.msgText, "text/html; charset=utf-8");
            Transport.send(message);
        }catch(MessagingException ex)
        {
            ex.printStackTrace(System.out);
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
            String linkToActivate = "http://provenapps.cat:8080/FighterFinderREST/ff/user/deactivateAcc/"+this.toWho;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.fromWho));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.toWho));
            
            message.setSubject(this.subject);
            StringBuilder sb = new StringBuilder();
            sb.append("Please click the next link to deactivate your account in FighterFinder\n");
            sb.append("<a href=");
            sb.append(linkToActivate);
            sb.append(">Deactivate account</a>");
            this.msgText = sb.toString();
            //message.setText(this.msgText);
            message.setContent(this.msgText, "text/html; charset=utf-8");
            Transport.send(message);
        }catch(MessagingException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public void sendEmailRandomPassword(String nick, String password) {
        try{
            //link to activate the account
            String linkToActivate = "http://provenapps.cat:8080/FighterFinderREST/ff/user/deactivateAcc/"+this.toWho;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.fromWho));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.toWho));
            
            message.setSubject(this.subject);
            StringBuilder sb = new StringBuilder();
            sb.append("This is your information of your FighterFinder account\n");
            sb.append("Name: <b>");
            sb.append(nick);
            sb.append("</b><br/>Password: <b>");
            sb.append(password);
            sb.append("</b><br/>We recommend you to change the password in the edit profile tab to a one more easier for you.");
            this.msgText = sb.toString();
            //message.setText(this.msgText);
            message.setContent(this.msgText, "text/html; charset=utf-8");
            Transport.send(message);
        }catch(MessagingException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
    
}
