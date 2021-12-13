package kp.game2048.game;

import kp.game2048.service.exceptions.CommentException;
import kp.game2048.service.exceptions.RatingException;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws CommentException, RatingException, SQLException {

        ConsoleUI ui = new ConsoleUI(new Field());
        ui.play();
    }
}
