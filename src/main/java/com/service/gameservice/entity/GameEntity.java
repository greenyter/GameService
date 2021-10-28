package com.service.gameservice.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "game", schema = "game_service")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Basic
    @Column(name = "gameName")
    private String gameName;

    @Basic
    @Column(name = "releaseDate")
    private Date releaseDate;

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
