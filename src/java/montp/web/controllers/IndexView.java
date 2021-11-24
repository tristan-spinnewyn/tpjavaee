package montp.web.controllers;

import montp.data.model.UserCompany;
import montp.data.model.security.User;
import montp.locale.Messages;
import montp.services.UserCompanyService;
import montp.services.UserService;
import montp.tools.EMailer;
import montp.tools.Logger;
import montp.web.FacesTools;
import montp.web.UserSession;
import tp.javaee.stockmarket.StockMarketClient;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("index")
public class IndexView implements Serializable {

    @Inject private UserSession session;

    @Inject private UserService userService;
    @Inject private EMailer eMailer;
    @Inject private Messages messages;

    @Inject private EditUserDialog editUserDialog;
    @Inject private StockMarketClient client;
    @Inject private UserCompanyService service;


    private String emailTo;
    private List<UserCompany> userCompanies;

    @PostConstruct
    public void init() {
        Logger.log(Logger.LogLevel.INFO, IndexView.class.getSimpleName(), "initializing view controller");
        editUserDialog.setInstance(session.getUser());
    }

    public String getHello() {
        return String.format(messages.get("example.hello"), session.getUser());
    }

    public List<User> getUsers() { return userService.getUsers(); }

    public boolean isUserActive(User user) { return userService.isActive(user); }

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

    public String getCompanyName(String symbol){
        return client.getCompany(symbol).getName();
    }

    public String getCurrentQuote(String symbol){
        return String.format("%.2f",client.getQuote(symbol));
    }

    public void remove(long id){
        UserCompany userCompany = service.get(id);
        service.delete(userCompany);
    }

    public List<UserCompany> getUserCompanies() {
        return service.getAll();
    }

    public String getVariation(long id){
        UserCompany userCompany = service.get(id);
        Double quoteOrigine = userCompany.getPriceQuote();

        Double variation = ((client.getQuote(userCompany.getSymbol())-quoteOrigine)/quoteOrigine * 100);
        return String.format("%.2f",variation);
    }
}
