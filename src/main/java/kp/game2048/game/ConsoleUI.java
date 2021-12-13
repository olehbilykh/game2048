package kp.game2048.game;

import kp.game2048.dto.CommentDto;
import kp.game2048.dto.RatingDto;
import kp.game2048.dto.ScoreDto;
import kp.game2048.entity.*;
import kp.game2048.service.*;
import kp.game2048.service.exceptions.CommentException;
import kp.game2048.service.exceptions.RatingException;
import kp.game2048.service.jdbc.CommentServiceJDBC;
import kp.game2048.service.jdbc.RatingServiceJDBC;
import kp.game2048.service.jdbc.ScoreServiceJDBC;
import kp.game2048.service.jpa.CommentServiceJPA;
import kp.game2048.service.jpa.RatingServiceJPA;
import kp.game2048.service.jpa.ScoreServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class ConsoleUI {


    private Field field;
    private Scanner scanner;

//    private static final Pattern INPUT_PATTERN = Pattern.compile("([OM])([A-I])([1-9])");

    private Scanner scanner1 = new Scanner(System.in);


    @Autowired
    private ScoreService scoreService = new ScoreServiceJDBC();


    @Autowired
    private RatingService ratingService = new RatingServiceJDBC();

    @Autowired
    private CommentService commentService = new CommentServiceJDBC();



    public ConsoleUI(Field field) {
        scanner = new Scanner(System.in);
        this.field = field;
        field.setScore(0);
    }

    public void play() throws CommentException, RatingException, SQLException {

        do {
            field.printField();
            processInput();

        } while (!field.isWon() && !field.isLost());

        if(field.getState() == GameState.SOLVED){
            System.out.println("Congratulations, you won! \n" +
                    "Your score was : \n" + field.getScore());

            UsingServices();

        }else if(field.getState() == GameState.FAILED){
            System.out.println("You lost :(" +
                    "Your score was : " + field.getScore());

            UsingServices();
        }

    }


    private void UsingServices() throws CommentException, RatingException, SQLException {
        System.out.println("Please type your name : ");
        String name = scanner.next();

        ScoreDto score = new ScoreDto(name,"2048" ,field.getScore(), LocalDateTime.now());
        scoreService.addScore(score);

        System.out.println("Comment game: ");
        String comment = scanner.next();

        CommentDto commentDto = new CommentDto(name,"2048",comment,LocalDateTime.now());
        commentService.addComment(commentDto);

        System.out.println("Please rate the game from 1 to 5");
        int rating = scanner.nextInt();

        RatingDto ratingDto = new RatingDto(name,"2048",rating,LocalDateTime.now());
        ratingService.addRating(ratingDto);

        List<ScoreDto> scores= scoreService.getBestScores("2048");
        System.out.println(scores);

        commentService.getComments("2048");

        System.out.println("Average rating is: " + ratingService.getAverageRating("2048"));

        System.out.println("Would you like to play again? yes/no");
        String ans = scanner1.next().toLowerCase();

        if(ans.equals("yes")){
            field.reload();
            play();
        }

        System.out.println("See you next time!");
        System.exit(0);
    }

    public void processInput() {

        System.out.println(
                "Choose direction: up - w," +
                        " down - s," +
                        " left - a," +
                        " right - d," +
                        " exit - x," +
                        " restart - r");

        String line = scanner.next().toUpperCase();

        switch (line) {
            case "X":
                System.out.println("See you next time!");
                System.exit(0);
            case "W":
                field.moveTilesUp();
                break;
            case "A":
                field.moveTilesLeft();
                break;
            case "S":
                field.moveTilesDown();
                break;
            case "D":
                field.moveTilesRight();
                break;
            case "R":
                field.reload();
                break;
            default:
                System.out.println("Incorrect input");
                processInput();
                break;
        }
    }

}
