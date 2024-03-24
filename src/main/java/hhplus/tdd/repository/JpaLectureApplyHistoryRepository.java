package hhplus.tdd.repository;

import hhplus.tdd.entity.LectureApplyHistoryEntity;
import hhplus.tdd.entity.LectureApplyHistoryEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLectureApplyHistoryRepository extends
        JpaRepository<LectureApplyHistoryEntity, LectureApplyHistoryEntityPK>,
        LectureApplyHistoryRepository {

    @Override
    boolean existsById(LectureApplyHistoryEntityPK lectureApplyHistoryEntityPK);
}
