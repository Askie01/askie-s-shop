package com.askie01.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true, nullable = false)
    private String login;
    private String password;

    @Column(unique = true, nullable = false)
    private String username;
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;
    private LocalDate birthdate;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

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

    public User(User user) {
        this.userId = user.userId;
        this.login = user.login;
        this.password = user.password;
        this.username = user.username;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.phone = user.phone;
        this.birthdate = user.birthdate;
        this.profilePicture = user.profilePicture;
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
