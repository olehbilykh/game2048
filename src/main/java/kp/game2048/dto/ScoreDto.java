package kp.game2048.dto;

import kp.game2048.entity.Score;

import java.time.LocalDateTime;
import java.util.Date;

public class ScoreDto {
    private String game;
    private String player;
    private int points;
    private LocalDateTime playedOn;
    private Integer ident;

    public ScoreDto(){}

    public ScoreDto(int i, String max, String s, int i1, Date date) { }

    public ScoreDto(int id,String player, String game, int points, LocalDateTime playedOn) {
        this.ident = id;
        this.player = player;
        this.game = game;
        this.points = points;
        this.playedOn = playedOn;
    }


    public ScoreDto(String player, String game, int points, LocalDateTime playedOn) {
        this.player = player;
        this.game = game;
        this.points = points;
        this.playedOn = playedOn;
    }
    public ScoreDto(Score score){
        this.player = score.getPlayer();
        this.game = score.getGame();
        this.points = score.getPoints();
        this.ident = score.getIdent();
        this.playedOn = score.getPlayedOn();
    }

    public Integer getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public String getGame() {
        return game;
    }
    public void setGame(String game) {
        this.game = game;
    }
    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDateTime getPlayedOn() {
        return playedOn;
    }

    public void setPlayedOn(LocalDateTime playedOn) {
        this.playedOn = playedOn;
    }

    public String toString() {
        return "\nScore{" +
                "ident=" + ident +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", points=" + points +
                ", playedOn=" + playedOn +
                '}';
    }

    public int compareTo(Score o) {
        if (o == null) return -1;
        return this.getPoints() - o.getPoints();
    }
}
