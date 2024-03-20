package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public class BlackFirstPawn extends Pawn {
    private static Set<Direction> DIRECTIONS = Direction.getBlackFirstPawnDirection();

    public BlackFirstPawn(Position position) {
        super(position, Color.BLACK, DIRECTIONS);
    }

    public boolean isCaptureMove(Position destination) {
        Direction direction = position.findDirectionTo(destination);
        return direction == Direction.LEFT_DOWN || direction == Direction.RIGHT_DOWN;
    }

    @Override
    public BlackPawn update(Position destination) {
        return new BlackPawn(destination);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BLACK_PAWN;
    }
}