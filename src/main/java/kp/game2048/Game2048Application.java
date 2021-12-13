package kp.game2048;

import kp.game2048.game.ConsoleUI;
import kp.game2048.game.Field;
import kp.game2048.service.CommentService;
import kp.game2048.service.RatingService;
import kp.game2048.service.ScoreService;
import kp.game2048.service.jpa.CommentServiceJPA;
import kp.game2048.service.jpa.RatingServiceJPA;
import kp.game2048.service.jpa.ScoreServiceJPA;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
public class Game2048Application {

    public static void main(String[] args) {

        SpringApplication.run(Game2048Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(ConsoleUI ui) {
        return args -> ui.play();
    }

    @Bean
    public ConsoleUI consoleUI(Field field) {
        return new ConsoleUI(field);
    }

    @Bean
    public Field field() {
        return new Field();
    }

    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceJPA();
    }

    @Bean
    public CommentService commentService(){
        return new CommentServiceJPA();
    }
    @Bean
    public RatingService ratingService(){
        return new RatingServiceJPA();
    }
}
