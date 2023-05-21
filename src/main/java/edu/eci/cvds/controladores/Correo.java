package edu.eci.cvds.controladores;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Correo {
    private static String emailFrom = "iaapcorreo@gmail.com";
    private static String passwordFrom = "joaqkbizaseyfavu";
    private String emailTo;
    private String subject;
    private String content;
    @Autowired
    private Properties mProperties;
    @Autowired
    private Imagen imagen;
    private byte[] imagenBytes;
    private Session mSession;
    private MimeMessage mCorreo;
    public Correo(){
    }
    public void addSubject(boolean modified, boolean toHals, String nombre, String apellido, LocalDateTime startDate){
        if(toHals){
            emailTo = "iaapcorreo@gmail.com";
            if(modified){
                subject = "Se ha modificado la cita de "+nombre+" "+apellido;
            }else{
                subject = "Una nueva cita ha sido registrada con "+nombre+" "+apellido;
            }
        }else{
            if(modified){
                subject = "Se ha modificado su cita con la abogada Liliana.";
            }else{
                subject = "Ha registrado una nueva cita con la abogada Liliana.";
            }
        }
   
    }
    public void addContent(boolean modified, boolean toHals, String nombre, String apellido, LocalDateTime startDate, String descripcion,String casoAsilo, String negocioEEUU, String firma){
        try {
            imagenBytes = imagen.generateSignature(firma);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(toHals){
            if(modified){
                content = "Buen día, señorita Liliana. <br>"
                        + "Se ha modificado la cita del usuario: <strong>"+nombre+" "+apellido+"</strong><br>"
                        + "Se agendó para el día: "+startDate.toString()+"<br>"
                        + "¿Es caso de asilo? "+ casoAsilo+"<br>"
                        + "¿Es negocio en EEUU? "+ negocioEEUU+"<br>"
                        + "Realizó las siguientes observaciones: "+descripcion;
            }else{
                content = "Buen día, señorita Liliana. <br>"
                        + "Se ha añadido la cita del usuario: <strong>"+nombre+" "+apellido+"</strong><br>"
                        + "Se agendó para el día: "+startDate.toString()+"<br>"
                        + "¿Es caso de asilo? "+ casoAsilo+"<br>"
                        + "¿Es negocio en EEUU? "+ negocioEEUU+"<br>"
                        + "Descripción del usuario: "+ descripcion;
                        
            }
        }else{
            if(modified){
                content = "Buen día, <strong>"+nombre+" "+apellido+"</strong>"+"."+"<br>"
                        + "Se ha modificado su cita."
                        + "Se agendó para el día: "+startDate.toString()+"<br>"
                        + "¿Es caso de asilo? "+ casoAsilo+"<br>"
                        + "¿Es negocio en EEUU? "+ negocioEEUU+"<br>"
                        + "Realizó las siguientes observaciones: "+descripcion;
            }else{
                content = "Buen día, <strong>"+nombre+" "+apellido+"</strong>"+"."+"<br>"
                        + "Se ha añadido su cita."
                        + "Se agendó para el día: "+startDate.toString()+"<br>"
                        + "¿Es caso de asilo? "+ casoAsilo+"<br>"
                        + "¿Es negocio en EEUU? "+ negocioEEUU+"<br>"
                        + "Realizó las siguientes observaciones: "+descripcion;
            }
        }
   
    }
    public void createEmail(String emailTo){
        this.emailTo = emailTo;
        createEmail();
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
            //crear el cuerpo del correo
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content, "ISO-8859-1", "html");
            //adjuntar firma
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            String attachmentPath = "firma.png";
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(attachmentPath);
                fileOutputStream.write(imagenBytes);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataSource source = new FileDataSource(attachmentPath);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(attachmentPath);
            //juntar partes
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);
            //crear el correo electrónico
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setContent(multipart);

            
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
