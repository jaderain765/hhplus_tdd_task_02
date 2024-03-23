package hhplus.tdd.stub;

import hhplus.tdd.entity.LectureApplyHistoryEntity;
import hhplus.tdd.repository.LectureApplyHistoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectureApplyHistoryRepositoryStub implements LectureApplyHistoryRepository {

    List<LectureApplyHistoryEntity> store = new ArrayList<>();

    @Override
    public List<LectureApplyHistoryEntity> findAll() {
        return this.store;
    }

    @Override
    public List<LectureApplyHistoryEntity> findAllByUserId(long userId) {
        return store
                .stream()
                .filter(e -> e.getUserId().equals(userId))
                .toList();
    }

    @Override
    public Optional<LectureApplyHistoryEntity> save(LectureApplyHistoryEntity lectureApplyHistoryEntity) {
        lectureApplyHistoryEntity.setUpdateTime(System.currentTimeMillis());
        store.add(lectureApplyHistoryEntity);
        return Optional.of(lectureApplyHistoryEntity);
    }

    @Override
    public boolean existsByUserId(long userId) {
        return store.stream().anyMatch(e->e.getUserId().equals(userId));
    }
}
