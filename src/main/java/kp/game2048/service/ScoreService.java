package kp.game2048.service;

import kp.game2048.dto.ScoreDto;
import kp.game2048.service.exceptions.ScoreException;

import java.util.List;

public interface ScoreService {
    ScoreDto addScore(ScoreDto score) throws ScoreException;

    List<ScoreDto> getBestScores(String game) throws ScoreException;

    void clearScores(String name);
}
