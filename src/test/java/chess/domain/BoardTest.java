package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.blank.Blank;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.sliding.Rook;
import chess.domain.position.Position;
import chess.domain.state.BlankChessState;
import chess.domain.state.GeneralChessState;
import chess.domain.state.PawnChessState;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("특정 위치에 있는 말의 종류에 따라 적합한 체스 상태를 찾는다.")
    void getChessState() {
        Map<Position, Piece> rawBoard = Map.of(
                new Position(1, 1), new Rook(Color.WHITE),
                new Position(1, 2), new WhiteFirstPawn(),
                new Position(1, 3), new Blank()
        );
        Board board = new Board(rawBoard);

        assertAll(
                () -> assertThat(board.getChessState(new Position(1, 1))).isInstanceOf(GeneralChessState.class),
                () -> assertThat(board.getChessState(new Position(1, 2))).isInstanceOf(PawnChessState.class),
                () -> assertThat(board.getChessState(new Position(1, 3))).isInstanceOf(BlankChessState.class)
        );
    }
}
