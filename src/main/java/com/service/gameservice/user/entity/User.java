package com.service.gameservice.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "userName")
    private String userName;

    @Basic
    @Column(name = "userPassword")
    private String userPassword;

    @Basic
    @Column(name = "isAdmin")
    private boolean isAdmin;

    @Basic
    @Column(name = "userEmail")
    private String userEmail;

    @Basic
    @Column(name = "userConfirmEmail")
    private boolean userConfirmEmail;

    @Basic
    @Column(name = "tokenUser")
    private String tokenUser;


    public void setUserPassword(String userPassword){
        if(userPassword.length() < 8 || userPassword.length() > 12 ){
            return;
        }else{
            this.userPassword = userPassword;
        }
    }



}
