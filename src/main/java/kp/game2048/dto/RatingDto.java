package kp.game2048.dto;

import kp.game2048.entity.Rating;

import java.time.LocalDateTime;

public class RatingDto {

    private Integer ident;

    private String player;

    private String game;

    private int rating;

    private LocalDateTime ratedon;

    public RatingDto() {
    }


    public RatingDto(int id, String player, String game, int rating, LocalDateTime ratedon) {
        this.ident = id;
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedon = ratedon;
    }
    public RatingDto(String player, String game, int rating, LocalDateTime ratedon) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedon = ratedon;
    }

    public RatingDto(Rating rating) {
        this.ident = rating.getId();
        this.player = rating.getPlayer();
        this.game = rating.getGame();
        this.rating = rating.getRating();
        this.ratedon = rating.getRatedon();
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getRatedon() {
        return ratedon;
    }

    public void setRatedon(LocalDateTime ratedon) {
        this.ratedon = ratedon;
    }

    public Integer getIdent() {
        return ident;
    }

    public void setIdent(Integer ident) {
        this.ident = ident;
    }


}
