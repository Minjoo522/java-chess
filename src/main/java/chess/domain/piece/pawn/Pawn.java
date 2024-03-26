package chess.domain.piece.pawn;

import chess.domain.Score;
import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.PawnMoveStrategy;
import java.util.Map;
import java.util.Set;

public abstract class Pawn extends Piece {
    private final Set<Direction> directions;

    protected Pawn(Color color, Set<Direction> directions) {
        super(color);
        this.directions = directions;
    }

    public abstract boolean isCaptureMove(Positions positions);

    public abstract Piece update();

    @Override
    public final Set<Position> findPath(Positions positions) {
        Position from = positions.from();
        Position to = positions.to();
        Set<Position> movable = from.findMovablePositions(directions);

        if (!movable.contains(to)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return from.findCourses(from.findDirectionTo(to), to);
    }

    @Override
    public final boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public final MoveStrategy strategy(Map<Position, Piece> board) {
        return new PawnMoveStrategy(board);
    }

    @Override
    public Score score() {
        return new Score(1);
    }
}
