package montp.services;

import montp.data.dao.UserCompanyDAO;
import montp.data.model.UserCompany;
import montp.data.model.security.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserCompanyService extends GenericService<UserCompany, UserCompanyDAO>{
    public List<UserCompany> getAll(User user) {
        return dao.getAll(user);
    }
}
