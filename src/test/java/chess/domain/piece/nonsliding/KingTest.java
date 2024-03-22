package chess.domain.piece.nonsliding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.color.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("킹은 이동 가능한 위치인 경우 중간 경로가 없으므로 빈 값을 반환한다.")
    void findPath() {
        King king = new King(Color.WHITE);
        Position departure = new Position(1, 1);
        Position destination = new Position(1, 2);

        assertThat(king.findPath(departure, destination)).hasSize(0);
    }

    @Test
    @DisplayName("(1, 1)일 때 (1, 3)으로는 이동할 수 없다.")
    void findPathToInvalidDestination() {
        King king = new King(Color.WHITE);
        Position destination = new Position(1, 3);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> king.findPath(new Position(1, 1), destination))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 말의 색상과 동일한 색을 가졌는지 확인한다.")
    void isSameColor() {
        King king = new King(Color.WHITE);

        Assertions.assertAll(
                () -> assertThat(king.isSameColor(Color.WHITE)).isTrue(),
                () -> assertThat(king.isSameColor(Color.BLACK)).isFalse()
        );
    }

    @Test
    @DisplayName("말의 색상과 모양에 맞는 PieceType을 반환한다.")
    void getPieceType() {
        King king = new King(Color.WHITE);

        assertThat(king.pieceType()).isEqualTo(PieceType.WHITE_KING);
    }
}
