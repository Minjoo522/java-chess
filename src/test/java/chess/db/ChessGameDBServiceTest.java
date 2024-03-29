package chess.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.ChessGame;
import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.sliding.Rook;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDBServiceTest {
    private ChessGameDBService chessGameDBService;

    @BeforeEach
    void setUp() {
        StubPiecesDao stubPiecesDao = new StubPiecesDao();
        StubTurnsDao stubTurnsDao = new StubTurnsDao();
        chessGameDBService = new ChessGameDBService(stubPiecesDao, stubTurnsDao);
    }

    @Test
    @DisplayName("기존에 저장된 데이터가 있는지 확인한다.")
    void hasPreviousData() {
        Map<Position, Piece> board = Map.of(
                new Position(1, 1), new Rook(Color.WHITE),
                new Position(1, 2), new WhiteFirstPawn()
        );
        ChessGame chessGame = new ChessGame(board, Color.BLACK);

        chessGameDBService.saveGame(chessGame);

        assertThat(chessGameDBService.hasPreviousData()).isTrue();
    }


    @Test
    @DisplayName("체스 게임을 저장한다.")
    void saveChessGame() {
        Map<Position, Piece> board = Map.of(
                new Position(1, 1), new Rook(Color.WHITE),
                new Position(1, 2), new WhiteFirstPawn()
        );
        ChessGame chessGame = new ChessGame(board, Color.BLACK);

        chessGameDBService.saveGame(chessGame);

        assertAll(
                () -> assertThat(
                        chessGameDBService.getCurrentChessGame().collectBoard()).containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                new Position(1, 1), PieceType.WHITE_ROOK,
                                new Position(1, 2), PieceType.WHITE_FIRST_PAWN
                        )),
                () -> assertThat(chessGameDBService.getCurrentChessGame().turn()).isEqualTo(Color.BLACK)
        );
    }
}
