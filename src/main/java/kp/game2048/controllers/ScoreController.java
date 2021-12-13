package kp.game2048.controllers;

import kp.game2048.dto.RatingDto;
import kp.game2048.dto.ScoreDto;
import kp.game2048.service.ScoreService;
import kp.game2048.service.jdbc.ScoreServiceJDBC;
import kp.game2048.service.jpa.ScoreServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/add")
    public synchronized ScoreDto addScore(@RequestBody ScoreDto scoreDto){
        return scoreService.addScore(scoreDto);
    }

    @GetMapping("/gameBestScores")
    public synchronized List<ScoreDto> getBestScores(@RequestParam String game){

        return scoreService.getBestScores(game);
    }

    @DeleteMapping("/delete")
    public synchronized void deleteScores(@RequestParam String game){
        scoreService.clearScores(game);
    }

}
