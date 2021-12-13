package kp.game2048.service;

import kp.game2048.dto.RatingDto;
import kp.game2048.entity.Rating;
import kp.game2048.service.exceptions.RatingException;

import java.sql.SQLException;

public interface RatingService {
    RatingDto addRating(RatingDto rating) throws RatingException;

    double getAverageRating(String game) throws RatingException, SQLException;

    int getRating(String player, String game) throws RatingException, SQLException;

//    RatingDto update(RatingDto rating) throws RatingException, SQLException;

    void clearRating(String name);
}
