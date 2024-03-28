package hhplus.tdd.domain;

import hhplus.tdd.entity.LectureApplyHistoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LectureApplyHistoryDomainTest {
    @Test
    @DisplayName("엔티티 객체 변환 테스트")
    void toEntity() {
        // given
        Long userId = 1L;
        Long lectureId = 1L;
        LocalDateTime createTime = LocalDateTime.now();

        LectureApplyHistoryDomain domain = new LectureApplyHistoryDomain(
                userId,
                lectureId,
                createTime
        );

        LectureApplyHistoryEntity entity = new LectureApplyHistoryEntity(
                userId,
                lectureId,
                createTime
        );

        // then
        assertThat(domain.toEntity()).isEqualTo(entity);
    }

    @Test
    @DisplayName("도메인의 검증로직 테스트")
    void validate() {
        // given
        Long userId_1 = 1L;
        Long userId_2 = 0L;
        Long userId_3 = null;
        Long userId_4 = -1L;

        Long lectureId_1 = 1L;
        Long lectureId_2 = null;
        Long lectureId_3 = -1L;
        Long lectureId_4 = 0L;

        LectureApplyHistoryDomain domain_1 = new LectureApplyHistoryDomain(userId_1, lectureId_1, LocalDateTime.now());
        LectureApplyHistoryDomain domain_2 = new LectureApplyHistoryDomain(userId_2, lectureId_2, LocalDateTime.now());
        LectureApplyHistoryDomain domain_3 = new LectureApplyHistoryDomain(userId_3, lectureId_3, LocalDateTime.now());
        LectureApplyHistoryDomain domain_4 = new LectureApplyHistoryDomain(userId_4, lectureId_4, LocalDateTime.now());

        // then
        assertDoesNotThrow(() -> domain_1.validate());
        assertThrows(RuntimeException.class, () -> domain_2.validate());
        assertThrows(RuntimeException.class, () -> domain_3.validate());
        assertThrows(RuntimeException.class, () -> domain_4.validate());
    }
}