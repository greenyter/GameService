package com.service.gameservice.game.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.service.gameservice.util.JSONDataDeserializer;
import com.service.gameservice.util.JSONDataSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "game", schema = "game_service")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Game implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Basic
    @Column(name = "gameName")
    private String gameName;

    @Basic
    @Column(name = "gameCategory")
    private String gameCategory;

    @Basic
    @JsonSerialize(using = JSONDataSerializer.class)
    @JsonDeserialize(using = JSONDataDeserializer.class)
    @Column(name = "releaseDate")
    private LocalDate releaseDate;

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

    @Basic
    @Column(name = "gameDescription")
    private String gameDescription;




}
