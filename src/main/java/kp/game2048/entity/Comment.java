package kp.game2048.entity;

import kp.game2048.dto.CommentDto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NamedQuery( name = "Comment.getComments",
        query = "SELECT s FROM Comment s WHERE s.game=:game ORDER BY s.commented_on DESC")

public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ident;
    @Column(nullable = false)
    private String player;
    @Column(nullable = false)
    private String game;
    @Column(nullable = false)
    public String comment;
    @Column(nullable = false)
    private LocalDateTime commented_on;


    public Comment() {
    }

    public Comment(CommentDto comment){
        this.player = comment.getPlayer();
        this.game = comment.getGame();
        this.comment = comment.getComment();
        this.commented_on = LocalDateTime.now();
    }

    public Comment(String player, String game, String comment, LocalDateTime commented_on) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commented_on = commented_on;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment.comment;
    }

    public LocalDateTime getCommented_on() {
        return commented_on;
    }

    public void setCommented_on(LocalDateTime commented_on) {
        this.commented_on = commented_on;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "ident=" + ident +
                ", player='" + player + '\'' +
                ", comment='" + comment + '\'' +
                ", game='" + game + '\'' +
                ", commented_on=" + commented_on +
                '}';
    }
}
