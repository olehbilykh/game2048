package kp.game2048.controllers;


import kp.game2048.dto.CommentDto;
import kp.game2048.service.CommentService;
import kp.game2048.service.exceptions.CommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/add")
    public CommentDto addComment(@RequestBody CommentDto commentDto) throws CommentException {
        return commentService.addComment(commentDto);
    }
    @GetMapping("/get")
    public List<CommentDto> getComments(@RequestParam String game) throws CommentException {
        return commentService.getComments(game);
    }

    @DeleteMapping("/delete/{game}")
    public void deleteComments(@PathVariable(value = "game") String game){
        commentService.clearComments(game);
    }
}
