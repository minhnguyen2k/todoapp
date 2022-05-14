package com.laptrinhjava.project.entities;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "username cannot be empty")
    @Column(name = "username",unique = true)
    private String userName;

    @NotEmpty(message = "password cannot be empty")
//    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters")
    private String password;

    @Transient
    private String passwordMatch;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ToDo> toDoList;

    public String getPasswordMatch() {
        return passwordMatch;
    }

    public void setPasswordMatch(String passwordMatch) {
        this.passwordMatch = passwordMatch;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
