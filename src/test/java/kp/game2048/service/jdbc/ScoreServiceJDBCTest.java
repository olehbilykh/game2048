package kp.game2048.service.jdbc;

import kp.game2048.dto.ScoreDto;
import kp.game2048.service.ScoreService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScoreServiceJDBCTest {
    ScoreServiceJDBC service = new ScoreServiceJDBC();

    @Test
    @Order(1)
    public void addScore() {

        ScoreDto score = new ScoreDto("max1","2048",1, LocalDateTime.now());
        ScoreDto score1 = new ScoreDto("max2","2048",2, LocalDateTime.now());
        ScoreDto score2 = new ScoreDto("max3","2048",3, LocalDateTime.now());
        //ScoreService scoreService = new ScoreServiceJDBC();
        service.addScore(score);
        service.addScore(score1);
        ScoreDto returned = service.addScore(score2);


        assertNotEquals(0,returned.getIdent());
    }

    @Test
    @Order(2)
    void getBestScores() {

        List<ScoreDto> scores = service.getBestScores("2048");

        assertEquals(3,scores.size());

    }

    @Test
    @Order(3)
    void clearScores(){
        service.clearScores("2048");
        assertEquals(0,service.getBestScores("2048").size());
    }

}