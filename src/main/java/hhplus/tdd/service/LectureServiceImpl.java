package hhplus.tdd.service;

import hhplus.tdd.domain.LectureApplyHistoryDomain;
import hhplus.tdd.entity.LectureApplyHistoryEntity;
import hhplus.tdd.entity.LectureApplyHistoryEntityPK;
import hhplus.tdd.repository.LectureApplyHistoryRepository;
import hhplus.tdd.repository.LectureRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {

    LectureRepository lectureRepository;
    LectureApplyHistoryRepository historyRepository;

    @Autowired
    public LectureServiceImpl(
            LectureRepository lectureRepository,
            LectureApplyHistoryRepository lectureApplyHistoryRepository
    ){
        this.lectureRepository = lectureRepository;
        this.historyRepository = lectureApplyHistoryRepository;
    }

    @Override
    @Transactional
    public LectureApplyHistoryDomain applyLecture(Long userId) {
        // 강의는 하나만 있는 것을 전제로 생각한다.
        Long lectureId = 1L;

        // 강의의 신청 기록을 조회
        List<LectureApplyHistoryEntity> historyList =
                historyRepository.findAllByLectureId(lectureId);

        if(historyList.size() >= 30)
            throw new RuntimeException("해당 강의는 더 이상 수강할 수 없습니다.");

        // 또 새로운 쿼리를 통해 해당 유저의 신청 기록을 조회 하는 것보다, 이미 찾은 목록에서 찾는 것이 효율적 이라고 판단.
        if (historyList.stream().anyMatch(h -> userId.equals(h.getUserId())))
            throw new RuntimeException("이미 신청한 강의 입니다.");

        LectureApplyHistoryDomain lectureApplyHistoryDomain = new LectureApplyHistoryDomain();

        lectureApplyHistoryDomain.setUserId(userId);
        lectureApplyHistoryDomain.setLectureId(lectureId);

        LectureApplyHistoryEntity result = historyRepository.save(lectureApplyHistoryDomain.toEntity());

        return result.toDomain();
    }

    @Override
    public Boolean checkApply(Long userId) {
        // 강의는 하나만 있는 것을 전제로 생각한다.
        Long lectureId = 1L;

        return historyRepository.existsById(
                new LectureApplyHistoryEntityPK(userId, lectureId)
        );
    }
}
