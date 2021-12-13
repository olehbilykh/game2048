package kp.game2048.controllers;

import kp.game2048.dto.RatingDto;
import kp.game2048.service.RatingService;
import kp.game2048.service.exceptions.RatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping("/add")
    public RatingDto addRating(@RequestBody RatingDto rating) {

        try {
            return ratingService.addRating(rating);
        } catch (RatingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/game-avg")
    public double getGameAvg(@RequestParam String game) {
        try {
            return ratingService.getAverageRating(game);
        } catch (RatingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

//    @PutMapping("/update")
//    public RatingDto updateRating(@RequestBody RatingDto rating) {
//
//        try {
//            return ratingService.addRating(rating);
//        } catch (RatingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    @DeleteMapping("/delete/{game}")
    public void deleteRatings(@PathVariable(value = "game") String game){
        ratingService.clearRating(game);
    }
}
