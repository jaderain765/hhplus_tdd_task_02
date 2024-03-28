package hhplus.tdd.service;

import hhplus.tdd.domain.LectureApplyHistoryDomain;
import hhplus.tdd.domain.LectureDomain;
import hhplus.tdd.entity.LectureApplyHistoryEntity;
import hhplus.tdd.entity.LectureApplyHistoryEntityPK;
import hhplus.tdd.repository.LectureApplyHistoryRepository;
import hhplus.tdd.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {

    LectureRepository lectureRepository;
    LectureApplyHistoryRepository historyRepository;

    @Autowired
    public LectureServiceImpl(
            LectureRepository lectureRepository,
            LectureApplyHistoryRepository lectureApplyHistoryRepository
    ) {
        this.lectureRepository = lectureRepository;
        this.historyRepository = lectureApplyHistoryRepository;
    }

//    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    @Transactional
    public LectureApplyHistoryDomain applyLecture(Long userId, Long lectureId) {
        // 강의 정보를 찾을 수 없는 경우
        lectureRepository.findById(lectureId).orElseThrow(() -> new RuntimeException("강의 정보를 찾을 수 없습니다."));

        // 강의의 신청 기록을 조회 (@Transactional 안에서 조회 되기에 데이터 일관성이 보장된다.)
        List<LectureApplyHistoryEntity> historyList =
                historyRepository.findAllByLectureId(lectureId);

        if (historyList.size() >= 30) // 해당 부분에선 수강 신청수 칼럼을 추가해서 관리하는 것도 방법이라고 하심
            throw new RuntimeException("해당 강의는 더 이상 수강할 수 없습니다.");

        // 또 새로운 쿼리를 통해 해당 유저의 신청 기록을 조회 하는 것보다, 이미 찾은 목록에서 찾는 것이 효율적 이라고 판단.
        if (historyList.stream().anyMatch(h -> userId.equals(h.getUserId())))
            throw new RuntimeException("이미 신청한 강의 입니다.");

        LectureApplyHistoryDomain lectureApplyHistoryDomain = new LectureApplyHistoryDomain(
                userId,
                lectureId,
                null // 생성일자는 자동으로 생성
        );

        LectureApplyHistoryEntity result = historyRepository.save(lectureApplyHistoryDomain.toEntity());

        return result.toDomain();
    }

    @Override
    public Boolean checkApply(Long userId, Long lectureId) {
        return historyRepository.existsById(
                new LectureApplyHistoryEntityPK(userId, lectureId)
        );
    }

    @Override
    public List<LectureDomain> searchLectureList() {
        return lectureRepository.findAll()
                .stream()
                .map(lecture -> lecture.toDomain())
                .toList();
    }
}
