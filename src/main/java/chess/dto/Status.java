package chess.dto;

import chess.score.Scores;

public record Status(double whiteScore, double blackScore) {

    public static Status of(Scores scores) {
        double white = scores.getWhiteScore().getScore();
        double black = scores.getBlackScore().getScore();
        return new Status(white, black);
    }
}