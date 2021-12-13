package kp.game2048.service.jdbc;

import kp.game2048.dto.RatingDto;
import kp.game2048.service.RatingService;
import kp.game2048.service.exceptions.RatingException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RatingServiceJDBCTest {
    RatingServiceJDBC service = new RatingServiceJDBC();

    @Test
    @Order(1)
    void addRating() throws RatingException, SQLException {
        RatingDto rating = new RatingDto("max", "2048", 5, LocalDateTime.now());

        RatingDto ratingDto = service.addRating(rating);

        assertEquals(rating, ratingDto);

    }


    @Test
    @Order(2)
    void getAverageRating() throws RatingException, SQLException {
        RatingDto rating = new RatingDto("ivan", "2048", 5, LocalDateTime.now());

        service.addRating(rating);

        double avg = service.getAverageRating("2048");

        assertEquals(5, avg);


    }

    @Test
    @Order(3)
    void getRating() throws SQLException, RatingException {
        int ratingDto = service.getRating("ivan", "2048");
        assertEquals(5, ratingDto);
    }

    @Test
    @Order(4)
    void clearRating() throws RatingException {
        service.clearRating("2048");
        assertEquals(0, service.getAverageRating("2048"));
    }
}