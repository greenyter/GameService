package com.service.gameservice.game.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.service.gameservice.util.JSONDataDeserializer;
import com.service.gameservice.util.JSONDataSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entity describing game. Games cannot be added by user or from servlet.
 * Game can be added, modified and deleted only using SQL and with access to db.
 * Every game contains id, game's name, category, release date,
 * publisher's name, developer's name, game's cover, game's trailer,
 * game's description.
 */

@Entity
@Table(name = "game")
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
