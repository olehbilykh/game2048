package kp.game2048.entity;

import kp.game2048.dto.RatingDto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NamedQueries(value = {
        @NamedQuery(
                name = "Rating.getRating",
                query = "SELECT r FROM Rating r WHERE r.game =:game AND r.player =: player"),
        @NamedQuery(
                name = "Rating.getAverageRating",
                query = "SELECT AVG(r.rating) FROM Rating r WHERE r.game =: game"
        ),
}
)


public class Rating implements Comparable<Rating>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, name = "player")
    private String player;
    @Column(nullable = false, name = "game")
    private String game;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false)
    private LocalDateTime ratedon;

    public Rating() {
    }

    public Rating(RatingDto dto) {
        this.player = dto.getPlayer();
        this.game = dto.getGame();
        this.rating = dto.getRating();
        this.ratedon = LocalDateTime.now();
    }
    public Rating(String player, String game, int rating) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedon = LocalDateTime.now();
    }

    public Rating(String player, String game, int rating, LocalDateTime ratedon) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedon = ratedon;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", rating=" + rating +
                ", ratedon=" + ratedon +
                '}';
    }

    @Override
    public int compareTo(Rating o) {
        if (o == null) return -1;
        return this.getRating() - o.getRating();
    }
}
