package com.example.demo.model;
import javax.persistence.*;

@Entity
@Table(name="CryptoCurrency" )

public class CryptoCurrency {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(unique = true)
        private String name;

        private Float price;

        public CryptoCurrency(String name, Float price) {
                this.name = name;
                this.price = price;
        }

        public CryptoCurrency(Integer id, String name, Float price) {
                this.id = id;
                this.name = name;
                this.price = price;
        }

        public CryptoCurrency() {
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Float getPrice() {
                return price;
        }

        public void setPrice(Float price) {
                this.price = price;
        }
}
