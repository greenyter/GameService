package com.service.gameservice.comment_user.entity;

import com.service.gameservice.game.entity.Game;
import com.service.gameservice.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="commentuser")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "comment")
    private String commentText;

    @Basic
    @Column(name = "game_id")
    private Long idGame;

    @Basic
    @Column(name = "user_id")
    private Long idUser;

}
