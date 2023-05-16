package edu.eci.cvds.controladores;
import java.time.LocalDateTime;
import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Correo {
    private static String emailFrom = "iaapcorreo@gmail.com";
    private static String passwordFrom = "joaqkbizaseyfavu";
    private static String emailTo = "iaapcorreo@gmail.com";
    private String subject;
    private String content;
    
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    public Correo(){
        mProperties = new Properties();
    }
    public void addSubject(boolean modified, String nombre, String apellido, LocalDateTime startDate){
        if(modified){
            subject = "Se ha modificado la cita de "+nombre+" "+apellido;
        }else{
            subject = "Una nueva cita ha sido registrada con "+nombre+" "+apellido;
        }
   
    }
    public void addContent(boolean modified, String nombre, String apellido, LocalDateTime startDate, String descripcion,String casoAsilo, String negocioEEUU){
        if(modified){
            content = "Buen día, señorita Liliana. \n"+
                    "Se ha modificado la cita del usuario "+nombre+" "+apellido+"\n"+
                    "Se agendó para el día "+startDate.toString()+"\n"
                    +"¿Es caso de asilo? "+ casoAsilo+"\n"
                    +"¿Es negocio en EEUU? "+ negocioEEUU+"\n"+
                    "Realizó las siguientes observaciones: "+descripcion;
        }else{
            content = "Buen día, señorita Liliana. \n"+
                    "Se ha añadido la cita del usuario "+nombre+" "+apellido+"\n"+
                    "Se agendó para el día "+startDate.toString()+"\n"
                    +"¿Es caso de asilo? "+ casoAsilo+"\n"
                    +"¿Es negocio en EEUU? "+ negocioEEUU
                    +"Descripción del usuario: \n"+ descripcion;
        }
   
    }

    public void createEmail(){
        //Simple mail transfer protocol
        mProperties.put("mail.smtp.host","smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust","smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        mSession = Session.getDefaultInstance(mProperties);
        try{
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "ISO-8859-1", "html");
        }catch(AddressException e){
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } 
        
    }
    public void sendEmail(){
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
