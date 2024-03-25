package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("문자에 해당하는 열 번호를 반환한다.")
    void findFile() {
        String input = "a";
        assertThat(File.findFile(input)).isEqualTo(1);
    }

    @Test
    @DisplayName("존재하지 않는 열문자열을 입력하면 예외가 발생한다..")
    void findFileByInvalidInput() {
        String input = "i";
        assertThatIllegalArgumentException()
                .isThrownBy(() -> File.findFile(input))
                .withMessage("올바르지 않은 열입니다.");
    }
}
