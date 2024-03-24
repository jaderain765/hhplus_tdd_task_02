package hhplus.tdd.repository;

import hhplus.tdd.entity.LectureApplyHistoryEntity;
import hhplus.tdd.entity.LectureApplyHistoryEntityPK;

import java.util.List;

public interface LectureApplyHistoryRepository {
    List<LectureApplyHistoryEntity> findAllByLectureId(Long lectureId);
    LectureApplyHistoryEntity save(LectureApplyHistoryEntity lectureApplyHistoryEntity);
    boolean existsById(LectureApplyHistoryEntityPK id);
}
