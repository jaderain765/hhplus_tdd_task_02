package hhplus.tdd.domain;

import hhplus.tdd.dto.LectureApplyHistoryDTO;
import hhplus.tdd.entity.LectureApplyHistoryEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LectureApplyHistoryDomainTest {
    @Test
    void toEntity() {
        // given
        Long userId = 1L;
        Long updateTime = System.currentTimeMillis();

        LectureApplyHistoryDomain domain = new LectureApplyHistoryDomain(
                userId,
                updateTime
        );

        LectureApplyHistoryEntity entity = new LectureApplyHistoryEntity(
                userId,
                updateTime
        );

        // then
        assertThat(domain.toEntity()).isEqualTo(entity);
    }

    @Test
    void toDTO() {
        // given
        Long userId = 1L;
        Long updateTime = System.currentTimeMillis();

        LectureApplyHistoryDomain domain = new LectureApplyHistoryDomain(
                userId,
                updateTime
        );

        LectureApplyHistoryDTO DTO = new LectureApplyHistoryDTO(
                userId,
                updateTime
        );

        // then
        assertThat(domain.toDTO()).isEqualTo(DTO);
    }

    @Test
    void validate() {
        // given
        Long userId_1 = 1L;
        Long userId_2 = 0L;
        Long userId_3 = null;
        Long userId_4 = -1L;

        LectureApplyHistoryDomain domain_1 = new LectureApplyHistoryDomain(userId_1, System.currentTimeMillis());
        LectureApplyHistoryDomain domain_2 = new LectureApplyHistoryDomain(userId_2, System.currentTimeMillis());
        LectureApplyHistoryDomain domain_3 = new LectureApplyHistoryDomain(userId_3, System.currentTimeMillis());
        LectureApplyHistoryDomain domain_4 = new LectureApplyHistoryDomain(userId_4, System.currentTimeMillis());

        // then
        assertDoesNotThrow(() -> domain_1.validate());
        assertThrows(RuntimeException.class, () -> domain_2.validate());
        assertThrows(RuntimeException.class, () -> domain_3.validate());
        assertThrows(RuntimeException.class, () -> domain_4.validate());
    }
}