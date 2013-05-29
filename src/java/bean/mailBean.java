package bean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.UnsupportedEncodingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mail.SendEmail;

/**
 *
 * @author satyam
 */
@ManagedBean(name = "mailBean")
@RequestScoped
public class mailBean {

    /**
     * Creates a new instance of mailBean
     */
    public String content;
    public String to;
    public String subject;

    public mailBean() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void sendContent() throws MessagingException, UnsupportedEncodingException {
        String htmltext = content.replaceAll("\n", "<br/>");
        SendEmail se = new SendEmail(subject);
        se.composeSend(to, htmltext);
    }
}
