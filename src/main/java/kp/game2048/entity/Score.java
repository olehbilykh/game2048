package kp.game2048.entity;

import kp.game2048.dto.ScoreDto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NamedQuery( name = "Score.getBestScores",
              query = "SELECT s FROM Score s WHERE s.game =: game ORDER BY s.points DESC"
)
public class Score implements Comparable<Score>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ident;
    @Column(nullable = false)
    private String game;
    @Column(nullable = false)
    private String player;
    @Column(nullable = false)
    private int points;
    @Column(nullable = false)
    private LocalDateTime playedOn;

    public Score() { }

    public Score(ScoreDto scoreDto){
        this.player = scoreDto.getPlayer();
        this.game = scoreDto.getGame();
        this.points = scoreDto.getPoints();
        this.ident = scoreDto.getIdent();
        //this.playedOn = scoreDto.getPlayedOn();
        this.playedOn = LocalDateTime.now();

    }

    public Score(String player, String game, int points) {
        this.player = player;
        this.game = game;
        this.points = points;
        this.playedOn = LocalDateTime.now();
    }

    public Score(String player, String game, int points, LocalDateTime played_on) {
        this.player = player;
        this.game = game;
        this.points = points;
        this.playedOn = played_on;
    }

    public Integer getIdent() {
        return ident;
    }

    public void setIdent(Integer ident) {
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

    @Override
    public String toString() {
        return "\nScore{" +
                "ident=" + ident +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", points=" + points +
                ", playedOn=" + playedOn +
                '}';
    }

    @Override
    public int compareTo(Score o) {
        if (o == null) return -1;
        return this.getPoints() - o.getPoints();
    }
}
