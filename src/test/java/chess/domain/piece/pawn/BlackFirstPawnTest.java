package chess.domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.color.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.piece.pawn.BlackFirstPawn;
import chess.domain.piece.pawn.BlackPawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackFirstPawnTest {

    @Test
    @DisplayName("왼쪽 아래, 오른쪽 아래 방향으로 움직이는지 확인한다.")
    void checkIsCaptureMove() {
        BlackFirstPawn blackFirstPawn = new BlackFirstPawn(new Position(2, 7));
        assertAll(
                () -> assertThat(blackFirstPawn.isCaptureMove(new Position(1, 6))).isTrue(),
                () -> assertThat(blackFirstPawn.isCaptureMove(new Position(2, 6))).isFalse(),
                () -> assertThat(blackFirstPawn.isCaptureMove(new Position(3, 6))).isTrue()
        );
    }

    @Test
    @DisplayName("(2, 7) -> (2, 5), (2, 6)")
    void findMovablePositionsByUpUp() {
        BlackFirstPawn blackFirstPawn = new BlackFirstPawn(new Position(2, 7));
        Position destination = new Position(2, 5);

        assertThat(blackFirstPawn.findMovablePositions(destination)).containsExactly(new Position(2, 6));
    }


    @Test
    @DisplayName("왼쪽, 오른쪽으로는 이동할 수 없다.")
    void findMovablePositionsByInvalidDestinationLeftRight() {
        BlackFirstPawn blackFirstPawn = new BlackFirstPawn(new Position(2, 7));

        assertAll(
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> blackFirstPawn.findMovablePositions(new Position(1, 7)))
                        .withMessage("이동할 수 없습니다."),
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> blackFirstPawn.findMovablePositions(new Position(3, 7)))
                        .withMessage("이동할 수 없습니다.")
        );
    }

    @DisplayName("(2,7)일 때 (2, 1)로는 이동할 수 없다.")
    void findMovablePositionsByInvalidFarDestination() {
        BlackFirstPawn blackFirstPawn = new BlackFirstPawn(new Position(2, 7));
        Position destination = new Position(2, 1);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> blackFirstPawn.findMovablePositions(destination))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 말의 색상과 동일한 색을 가졌는지 확인한다.")
    void isSameColor() {
        BlackFirstPawn blackFirstPawn = new BlackFirstPawn(new Position(2, 7));

        org.junit.jupiter.api.Assertions.assertAll(
                () -> assertThat(blackFirstPawn.isSameColor(Color.BLACK)).isTrue(),
                () -> assertThat(blackFirstPawn.isSameColor(Color.WHITE)).isFalse()
        );
    }

    @Test
    @DisplayName("한 번 움직이면 일반 폰이 된다")
    void updateToGeneralPawn() {
        BlackFirstPawn blackFirstPawn = new BlackFirstPawn(new Position(2, 7));

        assertThat(blackFirstPawn.update(new Position(2, 4))).isInstanceOf(BlackPawn.class);
    }

    @Test
    @DisplayName("말의 색상과 모양에 맞는 PieceType을 반환한다.")
    void getPieceType() {
        BlackFirstPawn blackFirstPawn = new BlackFirstPawn(new Position(2, 7));

        assertThat(blackFirstPawn.pieceType()).isEqualTo(PieceType.BLACK_PAWN);
    }
}