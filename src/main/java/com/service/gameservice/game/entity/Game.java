package com.service.gameservice.game.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "game", schema = "game_service")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Basic
    @Column(name = "gameName")
    private String gameName;

    @Basic
    @Column(name = "releaseDate")
    private LocalDateTime releaseDate;

    @Basic
    @Column(name = "publisherName")
    private String publisherName;

    @Basic
    @Column(name = "developerName")
    private String developerName;

    @Basic
    @Column(name = "gameCover")
    private String gameCover;

    @Basic
    @Column(name = "gameTrailer")
    private String gameTrailer;





}
