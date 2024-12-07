package cl.company.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


public class ProductResponse {

    private List<Product> notebooks;
    private List<Product> cellPhones;
    private List<Product> coffeeMakers;
    private List<Product> airConditioning;
    private List<Product>outstanding;


    public List<Product> getNotebooks() {
        return notebooks;
    }

    public ProductResponse setNotebooks(List<Product> notebooks) {
        this.notebooks = notebooks;
        return this;
    }

    public List<Product> getCellPhones() {
        return cellPhones;
    }

    public ProductResponse setCellPhones(List<Product> cellPhones) {
        this.cellPhones = cellPhones;
        return this;
    }

    public List<Product> getCoffeeMakers() {
        return coffeeMakers;
    }

    public ProductResponse setCoffeeMakers(List<Product> coffeeMakers) {
        this.coffeeMakers = coffeeMakers;
        return this;
    }

    public List<Product> getAirConditioning() {
        return airConditioning;
    }

    public ProductResponse setAirConditioning(List<Product> airConditioning) {
        this.airConditioning = airConditioning;
        return this;
    }

    public List<Product> getOutstanding() {
        return outstanding;
    }

    public ProductResponse setOutstanding(List<Product> outstanding) {
        this.outstanding = outstanding;
        return this;
    }
}
