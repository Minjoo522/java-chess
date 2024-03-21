package chess.view;

import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.view.display.PieceDisplay;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Map<Position, PieceType> board) {
        List<String> lines = PieceDisplay.makeBoardDisplay(board);
        lines.forEach(System.out::println);
        System.out.println();
    }
}