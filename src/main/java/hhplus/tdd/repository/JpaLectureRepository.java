package hhplus.tdd.repository;

import hhplus.tdd.entity.LectureEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaLectureRepository extends
        JpaRepository<LectureEntity, Long>,
        LectureRepository {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<LectureEntity> findAllById(Iterable<Long> longs);
}
