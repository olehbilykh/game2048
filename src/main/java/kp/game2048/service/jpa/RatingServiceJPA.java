package kp.game2048.service.jpa;

import kp.game2048.dto.RatingDto;
import kp.game2048.entity.Rating;
import kp.game2048.service.RatingService;
import kp.game2048.service.exceptions.RatingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Service
@Transactional
public class RatingServiceJPA implements RatingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RatingDto addRating(RatingDto rating) throws RatingException {
         return new RatingDto(entityManager.merge(new Rating(rating)));
    }

    @Override
    public double getAverageRating(String game) throws RatingException, SQLException {
        double res = Math.round((double)entityManager.createQuery("SELECT AVG(e.rating) FROM Rating e WHERE e.game=:game")
                .setParameter("game",game).getSingleResult());
        return res;
    }

    @Override
    public int getRating(String player, String game) throws RatingException, SQLException {
        Rating current = (Rating) entityManager.createNamedQuery("Rating.getRating").setParameter("game", game)
                .setParameter("player", player).getSingleResult();

        return current.getRating();

    }

    @Override
    public void clearRating(String game) {
        //entityManager.createQuery("DELETE FROM Rating WHERE game = game").executeUpdate();
        entityManager.createQuery("DELETE FROM Rating e WHERE  e.game = :game")
                .setParameter("game",game).executeUpdate();
    }

//    @Override
//    public RatingDto update(RatingDto rating) throws RatingException, SQLException {
//        return new RatingDto(entityManager.merge(new Rating(rating)));
//    }
}
