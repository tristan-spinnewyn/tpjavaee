package montp.data.dao;

import montp.data.model.UserCompany;
import montp.data.model.security.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserCompanyDAO extends GenericDAO<UserCompany>{
    public UserCompanyDAO() {
        super(UserCompany.class);
    }
    @SuppressWarnings("unchecked")
    public List<UserCompany> getAll(User user){
        return em.createQuery("SELECT uc FROM UserCompany uc where uc.user = :user")
                .setParameter("user",user)
                .getResultList();
    }
}
