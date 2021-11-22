package montp.web.controllers;

import montp.data.model.security.User;
import montp.locale.Messages;
import montp.services.UserService;
import montp.tools.EMailer;
import montp.tools.Logger;
import montp.web.FacesTools;
import montp.web.UserSession;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@ViewScoped
@Named("register")
public class RegisterView implements Serializable {

    @Inject private UserSession session;

    @Inject private UserService userService;
    @Inject private EMailer eMailer;
    @Inject private Messages messages;

    private String emailTo;

    @PostConstruct
    public void init() {
        Logger.log(Logger.LogLevel.INFO, IndexView.class.getSimpleName(), "initializing view controller");
    }

    public void register(){
        String usermail = FacesTools.getRequest().getParameter("usermail");
        if(usermail != null){
            String pwd = FacesTools.getRequest().getParameter("password");
            String confirmPwd = FacesTools.getRequest().getParameter("checkpassword");
            if(Objects.equals(pwd, confirmPwd)){
                User user = new User(usermail,pwd);
                userService.insert(user);
                FacesTools.redirect("login");
                return;
            }
        }
    }

    public void sendMail() {
        if (!emailTo.strip().isEmpty()) {
            try {
                eMailer.send(emailTo, messages.get("example.mailsubject"), messages.get("example.mailcontent"));
                FacesTools.addMessage(FacesMessage.SEVERITY_INFO, messages.get("example.mailsent"));
            } catch (MessagingException e) {
                FacesTools.addMessage(FacesMessage.SEVERITY_ERROR, messages.get("example.mailerror"), e.getMessage());
            }
        }
    }

    public String getEmailTo() { return emailTo;   }
    public void setEmailTo(String emailTo) {  this.emailTo = emailTo;  }
}
