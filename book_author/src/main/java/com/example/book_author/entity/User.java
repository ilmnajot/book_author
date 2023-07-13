package com.example.book_author.entity;
import com.example.book_author.entity.absClass.BaseEntity;
import com.example.book_author.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="users")
@Data
public class User extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;



    public User(String firstName, String lastName, String username, String password, RoleName roleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roleName = roleName;
    }
}
