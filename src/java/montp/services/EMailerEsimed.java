package montp.services;

import montp.tools.EMailer;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EMailerEsimed extends EMailer {

    public EMailerEsimed() {
        super("Mon TP <mon_login@esimed.fr>",
               "mail.esimed.fr", 
               "mon_login@esimed.fr",
               "mon_motdepasse",
               587);
    }
    
}
