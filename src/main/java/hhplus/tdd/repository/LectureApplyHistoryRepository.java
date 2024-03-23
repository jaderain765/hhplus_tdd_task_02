package hhplus.tdd.repository;

import hhplus.tdd.entity.LectureApplyHistoryEntity;

import java.util.List;
import java.util.Optional;

public interface LectureApplyHistoryRepository {

    List<LectureApplyHistoryEntity> findAll();
    List<LectureApplyHistoryEntity> findAllByUserId(long userId);
    Optional<LectureApplyHistoryEntity> save(LectureApplyHistoryEntity lectureApplyHistoryEntity);
    boolean existsByUserId(long userId);
}
