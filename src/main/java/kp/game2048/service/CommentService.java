package kp.game2048.service;

import kp.game2048.dto.CommentDto;
import kp.game2048.entity.Comment;
import kp.game2048.service.exceptions.CommentException;

import java.util.List;

public interface CommentService {
    CommentDto addComment(CommentDto comment) throws CommentException;

    List<CommentDto> getComments(String game) throws CommentException;

    void clearComments(String name);
}