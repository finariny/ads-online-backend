package ru.skypro.ads.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Objects;

/**
 * Сущность пользователя
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "USER_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * для мягкого удаления
     */
//    private boolean deleted;


//    @OneToOne(cascade = CascadeType.ALL)
//    private ImageForExample avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getFirstName(), user.getFirstName())
                && Objects.equals(getLastName(), user.getLastName())
                && Objects.equals(getPhone(), user.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFirstName(), getLastName(), getPhone());
    }
}
