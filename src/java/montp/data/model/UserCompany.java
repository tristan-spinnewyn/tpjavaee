package montp.data.model;

import montp.data.model.security.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UserCompany extends GenericEntity{
    @Column(nullable = false, columnDefinition = "TEXT NOT NULL")
    private String Symbol;
    @ManyToOne
    private User user;

    public UserCompany(String symbol, User user, Double priceQuote) {
        Symbol = symbol;
        this.user = user;
        this.priceQuote = priceQuote;
    }

    @Column(nullable = false)
    private Double priceQuote;

    public UserCompany() {

    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPriceQuote() {
        return priceQuote;
    }

    public void setPriceQuote(Double priceQuote) {
        this.priceQuote = priceQuote;
    }
}
