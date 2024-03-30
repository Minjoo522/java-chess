package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.blank.Blank;
import chess.domain.piece.nonsliding.King;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.sliding.Queen;
import chess.domain.piece.sliding.Rook;
import chess.domain.position.Position;
import chess.domain.state.BlankChessState;
import chess.domain.state.GeneralChessState;
import chess.domain.state.PawnChessState;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

    @Test
    @DisplayName("특정 위치에 있는 말을 찾는다.")
    void getPiece() {
        Map<Position, Piece> rawBoard = Map.of(
                new Position(1, 1), new Rook(Color.WHITE)
        );
        Board board = new Board(rawBoard);

        assertAll(
                () -> assertThat(board.getPiece(new Position(1, 1))).isInstanceOf(Rook.class),
                () -> assertThat(board.getPiece(new Position(1, 1)).color()).isEqualTo(Color.WHITE)
        );
    }

    @Nested
    @DisplayName("보드가 비어있는지 확인한다.")
    class BlankCheck {
        private Board board;

        @BeforeEach
        void setUp() {
            Map<Position, Piece> rawBoard = Map.of(
                    new Position(1, 1), new Rook(Color.WHITE),
                    new Position(1, 3), new Blank(),
                    new Position(1, 4), new Blank(),
                    new Position(1, 5), new Blank()
            );
            board = new Board(rawBoard);
        }

        @Test
        @DisplayName("특정 위치들이 비어있으면 true를 반환한다.")
        void isAllBlank() {
            Set<Position> positions = Set.of(
                    new Position(1, 3), new Position(1, 4), new Position(1, 5)
            );

            assertThat(board.isAllBlank(positions)).isTrue();
        }

        @Test
        @DisplayName("특정 위치 중 하나라도 비어있지 않으면 false를 반환한다.")
        void isNotAllBlank() {
            Set<Position> positions = Set.of(
                    new Position(1, 1), new Position(1, 3), new Position(1, 5)
            );

            assertThat(board.isAllBlank(positions)).isFalse();
        }
    }

    @Nested
    @DisplayName("보드에 킹이 두 개 다 있는지 확인한다.")
    class KingCountCheck {

        @Test
        @DisplayName("보드에 킹이 두 개 다 있으면 true를 반환한다.")
        void hasTwoKing() {
            Map<Position, Piece> rawBoard = Map.of(
                    new Position(4, 1), new King(Color.WHITE),
                    new Position(4, 8), new King(Color.BLACK)
            );
            Board board = new Board(rawBoard);

            assertThat(board.hasTwoKing()).isTrue();
        }

        @Test
        @DisplayName("보드에 킹이 한 개라도 없으면 false를 반환한다.")
        void hasNotTwoKing() {
            Map<Position, Piece> rawBoard = Map.of(
                    new Position(4, 1), new King(Color.WHITE),
                    new Position(4, 8), new Queen(Color.BLACK)
            );
            Board board = new Board(rawBoard);

            assertThat(board.hasTwoKing()).isFalse();
        }
    }

    @Nested
    @DisplayName("남아 있는 킹의 색상을 확인한다.")
    class RemainKing {

        @Test
        @DisplayName("킹이 둘 다 살아있으면 남아 있는 킹의 색상을 확인할 수 없으므로 예외가 발생한다.")
        void getRemainKingColorWhenHasTwoKing() {
            Map<Position, Piece> rawBoard = Map.of(
                    new Position(4, 1), new King(Color.WHITE),
                    new Position(4, 8), new King(Color.BLACK)
            );
            Board board = new Board(rawBoard);

            assertThatIllegalStateException()
                    .isThrownBy(board::getRemainKingColor)
                    .withMessage("아직 게임이 끝나지 않아 남은 킹의 색상을 확인할 수 없습니다.");
        }

        @Test
        @DisplayName("킹이 하나만 남아있으면 남은 킹의 색상을 반환한다.")
        void getRemainKingColor() {
            Map<Position, Piece> rawBoard = Map.of(
                    new Position(4, 1), new King(Color.WHITE),
                    new Position(4, 8), new Queen(Color.BLACK)
            );
            Board board = new Board(rawBoard);

            assertThat(board.getRemainKingColor()).isEqualTo(Color.WHITE);
        }

        @Test
        @DisplayName("킹이 하나도 없는 경우 게임이 이미 종료되었으므로 예외가 발생한다.")
        void getRemainKingPositionWhenHasNoKing() {
            Map<Position, Piece> rawBoard = Map.of(
                    new Position(1, 1), new Rook(Color.WHITE),
                    new Position(1, 2), new WhiteFirstPawn()
            );
            Board board = new Board(rawBoard);

            assertThatIllegalStateException()
                    .isThrownBy(board::getRemainKingColor)
                    .withMessage("왕이 모두 잡혔습니다.");
        }
    }
}
