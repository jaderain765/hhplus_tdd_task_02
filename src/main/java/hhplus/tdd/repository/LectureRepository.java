package hhplus.tdd.repository;

import hhplus.tdd.entity.LectureEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    List<LectureEntity> findAll();

    Optional<LectureEntity> findById(Long Id);

    LectureEntity save(LectureEntity lectureEntity);
}
