package montp.web.converters;


import montp.tools.Logger;
import montp.tools.Tools;
import tp.javaee.stockmarket.Company;
import tp.javaee.stockmarket.StockMarketClient;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("company")
public class CompanyConverter implements GenericCompanyConverter {

    private StockMarketClient client;

    public CompanyConverter(){
        client = new StockMarketClient();
    }

    @Override
    public Company getAsObject(FacesContext context, UIComponent component, String value) {
        Logger.info("value : "+value);
        return client.getCompany(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Company value) {
        if (value == null) return "";
        if (value.getSymbol() == null) return "";
        return value.getSymbol();
    }
}
