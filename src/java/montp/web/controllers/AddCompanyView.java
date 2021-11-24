package montp.web.controllers;

import montp.data.model.UserCompany;
import montp.services.UserCompanyService;
import montp.tools.Logger;
import montp.web.FacesTools;
import montp.web.UserSession;
import tp.javaee.stockmarket.Company;
import tp.javaee.stockmarket.StockMarketClient;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ViewScoped
@Named("addCompany")
public class AddCompanyView implements Serializable {
    @Inject private UserSession session;

    @Inject private StockMarketClient client;

    @Inject private UserCompanyService service;

    private Company company;
    private final List<Company> companies = new LinkedList<Company>();

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Company> getCompanies(){
        return companies;
    }

    @PostConstruct
    public void init() {
        Logger.log(Logger.LogLevel.INFO, IndexView.class.getSimpleName(), "initializing view controller");
    }

    public List<Company> findCompaniesByName(String name){
        if (name=="" || name==null) return new LinkedList<Company>();
        List<Company> companies = (List<Company>)client.getCompanies(name);
        return companies;
    }

    public void save(){
        for(Company company : companies){
            UserCompany userCompany = new UserCompany(company.getSymbol(),session.getUser(),getQuote(company));
            service.insert(userCompany);
        }
        FacesTools.redirect("index");
    }

    public void add(){
        if(this.company != null)
            this.companies.add(this.company);
    }

    private Double getQuote(Company company){
        return client.getQuote(company);
    }


}
