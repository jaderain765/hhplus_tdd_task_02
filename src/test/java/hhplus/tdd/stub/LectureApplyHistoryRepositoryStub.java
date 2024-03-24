package hhplus.tdd.stub;

import hhplus.tdd.entity.LectureApplyHistoryEntity;
import hhplus.tdd.entity.LectureApplyHistoryEntityPK;
import hhplus.tdd.repository.LectureApplyHistoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LectureApplyHistoryRepositoryStub implements LectureApplyHistoryRepository {

//    List<LectureApplyHistoryEntity> store = new ArrayList<>();

    Map<LectureApplyHistoryEntityPK, LectureApplyHistoryEntity> store = new ConcurrentHashMap<>();

    public void clear(){
        store.clear();
    }

    @Override
    public List<LectureApplyHistoryEntity> findAllByLectureId(Long lectureId) {
        return store.values()
                .stream()
                .filter(history -> history.getLectureId().equals(lectureId))
                .toList();
    }

    @Override
    public LectureApplyHistoryEntity save(LectureApplyHistoryEntity lectureApplyHistoryEntity) {
        lectureApplyHistoryEntity.setCreateTime(LocalDateTime.now());

        store.put(
                new LectureApplyHistoryEntityPK(
                        lectureApplyHistoryEntity.getUserId(),
                        lectureApplyHistoryEntity.getLectureId()
                ),
                lectureApplyHistoryEntity
        );

        return lectureApplyHistoryEntity;
    }

    @Override
    public boolean existsById(LectureApplyHistoryEntityPK pk) {
        return store.containsKey(pk);
    }
}
