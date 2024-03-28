package hhplus.tdd.service;

import hhplus.tdd.domain.LectureApplyHistoryDomain;
import hhplus.tdd.stub.LectureApplyHistoryRepositoryStub;
import hhplus.tdd.stub.LectureRepositoryStub;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
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
    void beforeEach() {
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
                lectureService.applyLecture(userId, lectureId);
                lectureService.applyLecture(userId, lectureId);
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
                    lectureService.applyLecture(userId1, lectureId));
            RuntimeException e2 = assertThrows(RuntimeException.class, () ->
                    lectureService.applyLecture(userId2, lectureId));
            RuntimeException e3 = assertThrows(RuntimeException.class, () ->
                    lectureService.applyLecture(userId3, lectureId));

            // then
            assertThat(e1.getMessage()).isEqualTo("사용자를 찾을 수 없습니다.");
            assertThat(e2.getMessage()).isEqualTo("사용자를 찾을 수 없습니다.");
            assertThat(e3.getMessage()).isEqualTo("사용자를 찾을 수 없습니다.");
        }

        @Test
        @DisplayName("존재하지 않는 강의를 신청하는 경우")
        void applyLecture5() {
            // given
            Long userId = 1L;
            Long lectureId = Long.MAX_VALUE;

            // when
            RuntimeException e = assertThrows(RuntimeException.class, () -> {
                lectureService.applyLecture(userId, lectureId);
            });

            // then
            assertThat(e.getMessage()).isEqualTo("강의 정보를 찾을 수 없습니다.");
        }

        /**
         * <h1>동시성 테스트</h1>
         *
         * <ul>
         *     <li>한 강의를 동시에 50명이 신청한다.</li>
         *     <li>한 강의는 30명의 정원을 갖고 있다.</li>
         *     <li>50개의 스레드를 이용하여, `applyLecture()`를 테스트한다.</li>
         *     <li>모든 스레드의 작업이 종료 되었을 때, 30개의 기록과, 20개의 예외가 발생하면 잘 됐다고 할 수 있다.</li>
         * </ul>
         *
         * @throws InterruptedException
         */
        @Test
        @DisplayName("강의 신청의 동시성 테스트")
        void applyLecture_concurrency_test_1() throws InterruptedException {
            // given
            Long userId = 1L;
            Long lectureId = 1L;
            int THREAD_COUNT = 50; // 등록을 동시에 시도할 횟수

            AtomicInteger exceptionCount = new AtomicInteger(); // 예외가 발생한 횟수

            // when
            CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

            for (int i = 0; i < THREAD_COUNT; i++) {
                Long applyUserId = userId + i;

                Thread thread = new Thread(() -> {
                    try {
                        lectureService.applyLecture(applyUserId, lectureId);
                    } catch (RuntimeException e) {
                        exceptionCount.incrementAndGet();
                    }
                    countDownLatch.countDown();
                });

                thread.start();
            }

            countDownLatch.await();

            // then
            assertThat(historyRepository.findAllByLectureId(lectureId).size())
                    .isEqualTo(30L);
            assertThat(exceptionCount.get()).isEqualTo(20);
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
            lectureService.applyLecture(userId, lectureId);

            //when
            Boolean result1 = lectureService.checkApply(userId, lectureId);
            Boolean result2 = lectureService.checkApply(2L, lectureId);

            // then
            assertThat(result1).isEqualTo(true);
            assertThat(result2).isEqualTo(false);
        }
    }
}