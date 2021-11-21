package montp.web;

import montp.data.model.security.User;
import montp.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class UserSession implements Serializable {

    @Inject
    private UserService userService;

    private User user;
    

    @PostConstruct
    public void init() {
        user = userService.getFromUsername(FacesTools.getRequest().getUserPrincipal().getName()); // si authentification activée
        //user = userService.getFromUsername("tristan.spinnewyn@gmail.com"); // désactiver la sécurité dans web.xml pour l'autologin
    }

    public boolean isConnected(){
        return FacesTools.getRequest().getUserPrincipal().getName() != null;
    }
    
    public void logout() {
        FacesTools.getRequest().getSession().invalidate();
        FacesTools.redirect("index");
    }

    public User getUser() { return user; }

    public boolean isAdmin() {
        return FacesTools.getRequest().isUserInRole("ADMIN");
    }

}
