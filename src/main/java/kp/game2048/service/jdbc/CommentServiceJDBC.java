package kp.game2048.service.jdbc;

import kp.game2048.dto.CommentDto;
import kp.game2048.entity.Comment;
import kp.game2048.service.exceptions.CommentException;
import kp.game2048.service.CommentService;
import kp.game2048.service.exceptions.ScoreException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceJDBC implements CommentService {

    private static final String URL = "jdbc:postgresql://localhost:5432/gamestudio";

    private static final String LOGIN = "postgres";

    private static final String PASSWORD = "postgres";

    private static final String INSERT_COMMAND = "INSERT INTO comment (player, game, comment, commented_on) VALUES (?, ?, ?, ?) RETURNING ident ";

    private static final String SELECT_COMMAND = "SELECT * FROM comment WHERE game = ? ";

    private static final String DELETE_COMMAND = "DELETE FROM comment WHERE game=?";


    @Override
    public synchronized CommentDto addComment(CommentDto comment) throws CommentException {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(INSERT_COMMAND)) {

            stmt.setString(1, comment.getPlayer());
            stmt.setString(2, comment.getGame());
            stmt.setString(3, comment.getComment());
            stmt.setTimestamp(4, Timestamp.valueOf(comment.getCommentedOn()));

            ResultSet rs = stmt.executeQuery();
            rs.next();

            Integer id = rs.getInt(1);

            if (id != null) {
                comment.setIdent(id);
            }
        } catch (SQLException e) {
            throw new CommentException(e.getMessage());
        }
        return comment;
    }

    @Override
    public  synchronized List<CommentDto> getComments(String game) throws CommentException {
        List<CommentDto> results = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(SELECT_COMMAND)) {
            stmt.setString(1, game);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CommentDto comment = new CommentDto(
                            rs.getInt("ident"),
                            rs.getString("player"),
                            rs.getString("game"),
                            rs.getString("comment"),
                            rs.getTimestamp("commented_on").toLocalDateTime());
                    results.add(comment);
                }
            }
        } catch (SQLException e) {
            throw new CommentException(e.getMessage());
        }

        return results;
    }

    @Override
    public synchronized void clearComments(String game) throws ScoreException {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(DELETE_COMMAND)) {
            stmt.setString(1, game);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
