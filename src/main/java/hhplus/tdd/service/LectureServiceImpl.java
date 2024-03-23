package hhplus.tdd.service;

import hhplus.tdd.domain.LectureApplyHistoryDomain;
import hhplus.tdd.entity.LectureApplyHistoryEntity;
import hhplus.tdd.repository.LectureApplyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LectureServiceImpl implements LectureService {

    LectureApplyHistoryRepository historyRepository;

    @Autowired
    public LectureServiceImpl(LectureApplyHistoryRepository lectureApplyHistoryRepository){
        this.historyRepository = lectureApplyHistoryRepository;
    }

    @Override
    public LectureApplyHistoryDomain applyLecture(Long userId) {
        List<LectureApplyHistoryEntity> historyList = historyRepository.findAll();

        if(historyList.size() >= 30)
            throw new RuntimeException("해당 강의는 더 이상 수강할 수 없습니다.");
        if (historyList.stream().filter(h -> userId.equals(h.getUserId())).count() > 0)
            throw new RuntimeException("이미 신청한 강의 입니다.");

        LectureApplyHistoryDomain lectureApplyHistoryDomain = new LectureApplyHistoryDomain();

        lectureApplyHistoryDomain.setUserId(userId);

        LectureApplyHistoryEntity result = historyRepository.save(lectureApplyHistoryDomain.toEntity())
                .orElseThrow(() -> new RuntimeException("등록에 실패했습니다."));

        return result.toDomain();
    }

    @Override
    public Boolean checkApply(Long userId) {
        return historyRepository.existsByUserId(userId);
    }
}
