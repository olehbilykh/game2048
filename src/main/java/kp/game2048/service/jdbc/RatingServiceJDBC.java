package kp.game2048.service.jdbc;

import kp.game2048.dto.RatingDto;
import kp.game2048.entity.Rating;
import kp.game2048.service.exceptions.RatingException;
import kp.game2048.service.RatingService;

import java.sql.*;

public class RatingServiceJDBC implements RatingService {

    private static final String URL = "jdbc:postgresql://localhost:5432/gamestudio";

    private static final String LOGIN = "postgres";

    private static final String PASSWORD = "postgres";


    private static final String INSERT_COMMAND = "INSERT INTO rating (player, game, rating, ratedon) VALUES (?, ?, ?, ?) RETURNING id";

    private static final String SELECT_COMMAND = "SELECT * FROM rating WHERE  player = ? AND game = ?";

    private static final String SELECT_AVERAGE = "SELECT AVG(rating) FROM rating";

    private static final String DELETE_COMMAND = "DELETE FROM rating WHERE game = ?";


    @Override
    public RatingDto addRating(RatingDto rating) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(INSERT_COMMAND))
        {
            stmt.setString(1, rating.getPlayer());
            stmt.setString(2, rating.getGame());
            stmt.setInt(3, rating.getRating());
            stmt.setTimestamp(4, Timestamp.valueOf(rating.getRatedon()));

            ResultSet rs =  stmt.executeQuery();
            rs.next();
            Integer id = rs.getInt(1);

            if(id!=null){
                rating.setIdent(id);
            }

        } catch (SQLException e) {
            throw new RatingException(e.getMessage());
        }
        return rating;
    }

    @Override
    public double getAverageRating(String game) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(SELECT_AVERAGE)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return rs.getDouble(1);
                else
                    System.out.println("Not working:(");
            }
        }catch (SQLException e){
            throw new RatingException(e.getMessage());
        }
        return 0;
    }

    @Override
    public int getRating(String player, String game) throws RatingException, SQLException {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(SELECT_COMMAND))
        {
            stmt.setString(1, player);
            stmt.setString(2, game);

            try(ResultSet rs = stmt.executeQuery()) {

                if(rs.next()){
                    return rs.getInt("rating");
                }else{
                    System.out.println("Database is empty");
                }

            }
        } catch (SQLException e) {
            throw new RatingException(e.getMessage());
        }
        return 0;
    }

//    @Override
//    public RatingDto update(RatingDto rating) throws RatingException, SQLException {
//        return null;
//    }

    @Override
    public void clearRating(String game){
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(DELETE_COMMAND)){
            stmt.setString(1, game);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
