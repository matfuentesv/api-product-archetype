package cl.company.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "El nombre no puede estar vacío")
    @NotNull(message = "El nombre no puede ser nulo")
    private String name;

    @Column(name = "price")
    @NotNull(message = "El precio no puede ser nulo")
    private double price;

    @Column(name = "discount")
    @NotNull(message = "El discount no puede ser nulo")
    private double discount;

    @Column(name = "description")
    @NotBlank(message = "No puede ingresar un description vacio")
    @NotNull(message = "No puede ingresar un description nulo")
    private String description;

    @Column(name = "image")
    @NotBlank(message = "No puede ingresar un image vacio")
    @NotNull(message = "No puede ingresar un image nulo")
    private String image;

    @Column(name = "category")
    @NotBlank(message = "No puede category un image vacio")
    @NotNull(message = "No puede category un image nulo")
    private String category;

    @Column(name = "originalPrice")
    private Double originalPrice;

    @Column(name = "rating")
    private int rating;

    @Column(name = "reviews")
    private int reviews;

    @Column(name = "quantity")
    private int quantity;


    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.discount = builder.discount;
        this.image = builder.image;
        this.category = builder.category;
        this.originalPrice = builder.originalPrice;
        this.rating = builder.rating;
        this.reviews = builder.reviews;
        this.quantity = builder.quantity;
    }

    public static class Builder {
        private Long id;
        private String name;
        private double price;
        private String description;
        private double discount;
        private String image;
        private String category;
        private double originalPrice;
        private int rating;
        private int reviews;
        private int quantity;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }


        public Builder discount(double discount) {
            this.discount = discount;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder originalPrice(double originalPrice) {
            this.originalPrice = originalPrice;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder reviews(int reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }


        public Product build() {
            return new Product(this);
        }
    }
}