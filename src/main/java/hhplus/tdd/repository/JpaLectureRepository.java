package hhplus.tdd.repository;

import hhplus.tdd.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLectureRepository extends
        JpaRepository<LectureEntity, Long>,
        LectureRepository {

}
