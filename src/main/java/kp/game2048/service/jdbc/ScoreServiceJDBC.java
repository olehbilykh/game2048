package kp.game2048.service.jdbc;

import kp.game2048.dto.ScoreDto;
import kp.game2048.entity.Score;
import kp.game2048.service.ScoreService;
import kp.game2048.service.exceptions.ScoreException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ScoreServiceJDBC implements ScoreService {
    private static final String URL = "jdbc:postgresql://localhost:5432/gamestudio";

    private static final String LOGIN = "postgres";

    private static final String PASSWORD = "postgres";

    private static final String INSERT_COMMAND = "INSERT INTO score ( player, game, points, played_on) VALUES (?, ?, ?, ?) RETURNING ident";

    private static final String SELECT_COMMAND = "SELECT * FROM score WHERE game = ? ORDER BY points DESC";

    private static final String DELETE_COMMAND = "DELETE FROM score WHERE game=?";

    @Override
    public ScoreDto addScore(ScoreDto score) throws ScoreException {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(INSERT_COMMAND)) {

            stmt.setString(1, score.getPlayer());
            stmt.setString(2, score.getGame());
            stmt.setInt(3, score.getPoints());
            stmt.setTimestamp(4, Timestamp.valueOf(score.getPlayedOn()));

            ResultSet rs = stmt.executeQuery();
            rs.next();
            Integer id = rs.getInt(1);

            if (id != null) {
                score.setIdent(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return score;
    }

    @Override
    public List<ScoreDto> getBestScores(String game) {
        List<ScoreDto> results = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(SELECT_COMMAND)) {
            stmt.setString(1, game);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ScoreDto score = new ScoreDto(
                            rs.getInt("ident"),
                            rs.getString("player"),
                            rs.getString("game"),
                            rs.getInt("points"),
                            rs.getTimestamp("played_on").toLocalDateTime());
                    results.add(score);
                }
            }
        } catch (SQLException e) {
            throw new ScoreException(e.getMessage());
        }

        return results;
    }

    @Override
    public void clearScores(String game) throws ScoreException {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(DELETE_COMMAND)) {
            stmt.setString(1, game);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

