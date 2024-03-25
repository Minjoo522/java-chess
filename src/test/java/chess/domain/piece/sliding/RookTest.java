package chess.domain.piece.sliding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.color.Color;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("오른쪽으로 이동시 중간 경로를 찾는다.")
    void findMovablePositionsRight() {
        Rook rook = new Rook(Color.WHITE);
        Set<Position> movablePositions = rook.findPath(new Positions(new Position(1, 1), new Position(4, 1)));
        assertThat(movablePositions)
                .containsExactlyInAnyOrder(new Position(2, 1), new Position(3, 1));
    }

    @Test
    @DisplayName("위쪽으로 이동시 중간 경로를 찾는다.")
    void findMovablePositionsUp() {
        Rook rook = new Rook(Color.WHITE);
        Set<Position> movablePositions = rook.findPath(new Positions(new Position(1, 1), new Position(1, 4)));
        assertThat(movablePositions)
                .containsExactlyInAnyOrder(new Position(1, 2), new Position(1, 3));
    }

    @Test
    @DisplayName("대각선으로 이동하려고하면 예외가 발생한다.")
    void findPathToInvalidDestination() {
        Rook rook = new Rook(Color.WHITE);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> rook.findPath(new Positions(new Position(1, 1), new Position(2, 2))))
                .withMessage("이동할 수 없습니다.");
    }
}
