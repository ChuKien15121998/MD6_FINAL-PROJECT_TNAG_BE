package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "address_categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "addressCategory"
        })
})
public class AddressCategory {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String addressCategory;

        public AddressCategory() {
        }

        public AddressCategory(Long id, String addressCategory) {
                this.id = id;
                this.addressCategory = addressCategory;
        }

        public AddressCategory(String addressCategory) {
                this.addressCategory = addressCategory;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getAddressCategory() {
                return addressCategory;
        }

        public void setAddressCategory(String addressCategory) {
                this.addressCategory = addressCategory;
        }
}
