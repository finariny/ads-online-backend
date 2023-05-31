package ru.skypro.ads.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Set;

/**
 * Сущность пользователя
 */
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "pass")
    private String password;
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "?", fetch = FetchType.LAZY)
    private Set<Ads> ads;
}
