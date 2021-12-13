package kp.game2048.service.jpa;

import kp.game2048.dto.ScoreDto;
import kp.game2048.entity.Score;
import kp.game2048.service.ScoreService;
import kp.game2048.service.exceptions.ScoreException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class ScoreServiceJPA implements ScoreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ScoreDto addScore(ScoreDto score) throws ScoreException {

        Score score2 = new Score(score);

        Score score1 = entityManager.merge(score2);


        return new ScoreDto(score1);

//        return new ScoreDto(entityManager.merge(new Score(score)));
    }

    @Override
    public List<ScoreDto> getBestScores(String game) throws ScoreException {
        return entityManager.createNamedQuery("Score.getBestScores")
                .setParameter("game", game).setMaxResults(10).getResultList();
    }

    @Override
    public void clearScores(String game) {
        //entityManager.createNamedQuery("Score.clearScores").setParameter("game");
        entityManager.createQuery("DELETE FROM Score WHERE game = game").
                executeUpdate();
    }
}
