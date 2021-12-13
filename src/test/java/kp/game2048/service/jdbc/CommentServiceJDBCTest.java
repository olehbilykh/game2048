package kp.game2048.service.jdbc;

import kp.game2048.dto.CommentDto;
import kp.game2048.entity.Comment;
import kp.game2048.service.exceptions.CommentException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentServiceJDBCTest {
    CommentServiceJDBC service = new CommentServiceJDBC();


    @Test
    @Order(1)
    void addComment() throws CommentException {

        CommentDto comment = new CommentDto("max2", "2048", "nice2", LocalDateTime.now());

        CommentDto returned = service.addComment(comment);

        assertNotEquals(0, returned.getIdent());
    }

    @Test
    @Order(2)
    void getComments() throws CommentException {
        List<CommentDto> comments = service.getComments("2048");

        assertEquals(1, comments.size());
        assertEquals("nice2", comments.get(0).getComment());
    }


    @Test
    @Order(10)
    void clearComments() throws CommentException {

        service.clearComments("2048");
        assertEquals(0, service.getComments("2048").size());

    }
}