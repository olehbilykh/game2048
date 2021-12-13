package kp.game2048.service.jpa;

import kp.game2048.dto.CommentDto;
import kp.game2048.entity.Comment;
import kp.game2048.service.exceptions.CommentException;
import kp.game2048.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class CommentServiceJPA implements CommentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CommentDto addComment(CommentDto comment) throws CommentException {
        Comment entity = new Comment(comment);
        entityManager.persist(entity);

        return new CommentDto(entity);
    }


    @Override
    public List<CommentDto> getComments(String game) throws CommentException {
        return entityManager.createNamedQuery("Comment.getComments")
                .setParameter("game", game).setMaxResults(5).getResultList();
    }

    @Override
    public void clearComments(String game) {
        entityManager.createQuery("DELETE FROM Comment e WHERE  e.game = :game")
                .setParameter("game",game).executeUpdate();

    }
}
