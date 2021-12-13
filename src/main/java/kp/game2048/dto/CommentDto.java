package kp.game2048.dto;

import kp.game2048.entity.Comment;

import java.time.LocalDateTime;

public class CommentDto {

    private int ident;
    private String player;
    private String game;
    private String comment;
    private LocalDateTime commentedOn;


    public CommentDto() {
    }

    public CommentDto(int id,String player, String game, String comment, LocalDateTime commentedOn) {
        this.ident = id;
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commentedOn = commentedOn;
    }

    public CommentDto(String player, String game, String comment, LocalDateTime commentedOn) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commentedOn = commentedOn;
    }

    public CommentDto(String player, String game, String comment) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commentedOn = LocalDateTime.now();
    }

    public CommentDto(Comment comment){
        this.player = comment.getPlayer();
        this.game = comment.getGame();
        this.comment = comment.getComment();
        this.commentedOn = comment.getCommented_on();
        this.ident = comment.getIdent();
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

    public LocalDateTime getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(LocalDateTime commentedOn) {
        this.commentedOn = commentedOn;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "ident=" + ident +
                ", player='" + player + '\'' +
                ", comment='" + comment + '\'' +
                ", game='" + game + '\'' +
                ", commentedOn=" + commentedOn +
                '}';
    }
}
