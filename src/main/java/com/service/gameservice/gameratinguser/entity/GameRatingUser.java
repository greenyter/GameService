package com.service.gameservice.gameratinguser.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="gameratinguser")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GameRatingUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "gameRatingUser")
    private int gameRatingUser;

    @Basic
    @Column(name = "game_id")
    private Long idGame;

    @Basic
    @Column(name = "user_id")
    private Long idUser;

}
