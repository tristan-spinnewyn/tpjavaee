package montp.web.converters;

import tp.javaee.stockmarket.Company;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public interface GenericCompanyConverter extends Converter<Company> {
    String getAsString(FacesContext context, UIComponent component, Company value);
}
