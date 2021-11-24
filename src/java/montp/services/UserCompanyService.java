package montp.services;

import montp.data.dao.UserCompanyDAO;
import montp.data.model.UserCompany;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserCompanyService extends GenericService<UserCompany, UserCompanyDAO>{
}
