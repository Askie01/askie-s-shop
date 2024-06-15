package com.askie01.users;

import jakarta.persistence.*;
import lombok.*;
import com.askie01.products.Product;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_login", columnNames = "login"),
                @UniqueConstraint(name = "unique_username", columnNames = "username"),
                @UniqueConstraint(name = "unique_email", columnNames = "email"),
                @UniqueConstraint(name = "unique_phone", columnNames = "phone")
        }
)
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String login;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthdate;
    private File image; //  This should be replaced with a "default" photo from database.

    @ManyToMany(mappedBy = "consumers", cascade = CascadeType.ALL)
    private final List<Product> shoppingCard = new ArrayList<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private final List<Product> listedItems = new ArrayList<>();

    public User(String login, String password, String username, String firstName, String lastName, String email, String phone, LocalDate birthdate) {
        this.login = login;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
