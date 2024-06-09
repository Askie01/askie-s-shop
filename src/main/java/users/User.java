package users;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(name = "unique_username_email_phone", columnNames = {"username", "email", "phone"})})
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String email;
    private String phone;

    public User(String username, String password, String firstName, String lastName, LocalDate birthdate, String email, String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
    }
}
