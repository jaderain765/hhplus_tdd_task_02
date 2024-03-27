package hhplus.tdd.service;

import hhplus.tdd.domain.LectureApplyHistoryDomain;
import hhplus.tdd.stub.LectureApplyHistoryRepositoryStub;
import hhplus.tdd.stub.LectureRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LectureServiceTest {

    LectureService lectureService;
    LectureRepositoryStub lectureRepository;
    LectureApplyHistoryRepositoryStub historyRepository;

    public LectureServiceTest() {
        lectureRepository = new LectureRepositoryStub();
        historyRepository = new LectureApplyHistoryRepositoryStub();

        lectureService = new LectureServiceImpl(lectureRepository, historyRepository);
    }

    @BeforeEach
    void beforeEach(){
        lectureRepository.clear();
        historyRepository.clear();
    }

    @Nested
    @DisplayName("applyLecture 테스트")
    class ApplyLecture {
        @Test
        @DisplayName("정상 동작 확인")
        void applyLecture() {
            // given
            Long userId = 1L;
            Long lectureId = 1L;

            // when
            LectureApplyHistoryDomain applyHistoryDomain = lectureService.applyLecture(userId, lectureId);

            // then
            assertThat(applyHistoryDomain.getUserId()).isEqualTo(userId);
            assertThat(applyHistoryDomain.getLectureId()).isEqualTo(lectureId);
        }

        @Test
        @DisplayName("정원(30명)을 채운 강의를 신청할 경우")
        void applyLecture2() {
            // given
            Long lectureId = 1L;

            for (Long userId = 1L; userId < 31; userId++)
                lectureService.applyLecture(userId, lectureId);

            // when
            // 31번째 수강등록이 일어날 경우
            RuntimeException e = assertThrows(RuntimeException.class, () ->
                    lectureService.applyLecture(31L, lectureId));

            // then
            assertThat(e.getMessage()).isEqualTo("해당 강의는 더 이상 수강할 수 없습니다.");
        }

        @Test
        @DisplayName("이미 신청한 사용자가 신청할 경우")
        void applyLecture3() {
            // given
            Long userId = 1L;
            Long lectureId = 1L;

            // when
            RuntimeException e = assertThrows(RuntimeException.class, () -> {
                lectureService.applyLecture(userId,lectureId);
                lectureService.applyLecture(userId,lectureId);
            });

            // then
            assertThat(e.getMessage()).isEqualTo("이미 신청한 강의 입니다.");
        }

        @Test
        @DisplayName("매개변수(사용자 ID)가 자연수가 아닌 경우")
        void applyLecture4() {
            // given
            Long userId1 = 0L;
            Long userId2 = -2L;
            Long userId3 = null;

            Long lectureId = 1L;

            // when
            RuntimeException e1 = assertThrows(RuntimeException.class, () ->
                    lectureService.applyLecture(userId1,lectureId));
            RuntimeException e2 = assertThrows(RuntimeException.class, () ->
                    lectureService.applyLecture(userId2,lectureId));
            RuntimeException e3 = assertThrows(RuntimeException.class, () ->
                    lectureService.applyLecture(userId3,lectureId));

            // then
            assertThat("사용자를 찾을 수 없습니다.")
                    .isEqualTo(e1.getMessage())
                    .isEqualTo(e2.getMessage())
                    .isEqualTo(e3.getMessage());
        }

        @Test
        @DisplayName("존재하지 않는 강의를 신청하는 경우")
        void applyLecture5() {
            // given
            Long userId = 1L;
            Long lectureId = Long.MAX_VALUE;

            // when
            RuntimeException e = assertThrows(RuntimeException.class, () -> {
                lectureService.applyLecture(userId,lectureId);
            });

            // then
            assertThat(e.getMessage()).isEqualTo("강의 정보를 찾을 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("checkApply 테스트")
    class CheckApply {
        @Test
        @DisplayName("정상 동작 확인")
        void checkApply() {
            // given
            Long userId = 1L;
            Long lectureId = 1L;
            lectureService.applyLecture(userId,lectureId);

            //when
            Boolean result1 = lectureService.checkApply(userId,lectureId);
            Boolean result2 = lectureService.checkApply(2L,lectureId);

            // then
            assertThat(result1).isEqualTo(true);
            assertThat(result2).isEqualTo(false);
        }
    }
}