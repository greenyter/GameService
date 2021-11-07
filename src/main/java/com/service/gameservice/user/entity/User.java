package com.service.gameservice.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user",schema = "game_service")
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
    private byte[] userPassword;

    @Basic
    @Column(name = "isAdmin")
    private boolean isAdmin;

    @Basic
    @Column(name = "userEmail")
    private String userEmail;

    @Basic
    @Column(name = "userConfirmEmail")
    private boolean userConfirmEmail;





}
