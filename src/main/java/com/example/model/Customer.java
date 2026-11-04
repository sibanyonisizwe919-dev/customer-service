package com.example.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long uniqueCustomerId;
        @Column(name = "name", nullable = false, length = 50)
        private String name;
        @Column(name = "surname", nullable = false, length = 50)
        private String surname;
        @Column(name = "email", nullable = false, length = 50)
        private String email;
        @Column(name = "contact", nullable = false, length = 50)
        private String contact;

        public Customer() {
        }

        public Long getUniqueCustomerId() {
                return uniqueCustomerId;
        }

        public void setUniqueCustomerId(Long uniqueCustomerId) {
                this.uniqueCustomerId = uniqueCustomerId;
        }
        public Long getId() {
                return uniqueCustomerId;
        }

        public void setId(Long uniqueCustomerId) {
                this.uniqueCustomerId = uniqueCustomerId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                this.surname = surname;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getContact() {
                return contact;
        }

        public void setContact(String contact) {
                this.contact = contact;
        }
}
