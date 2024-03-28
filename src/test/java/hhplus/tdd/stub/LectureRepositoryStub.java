package hhplus.tdd.stub;

import hhplus.tdd.entity.LectureEntity;
import hhplus.tdd.repository.LectureRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class LectureRepositoryStub implements LectureRepository {

    Map<Long, LectureEntity> store = new ConcurrentHashMap<>();

    AtomicLong idSeq = new AtomicLong(1L);

    public void clear(){
        store.clear();
        idSeq.set(1L);

        String input = "2024-03-23 15:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 특강 정보 임의로 생성
        store.put(
                idSeq.getAndAdd(1L),
                new LectureEntity(
                        1L,
                        "TDD특강",
                        LocalDateTime.parse(input, formatter),
                        LocalDateTime.parse(input, formatter)
                )
        );
    }

    @Override
    public List<LectureEntity> findAll() {
        return (List<LectureEntity>) store.values();
    }

    @Override
    public Optional<LectureEntity> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public LectureEntity save(LectureEntity lectureEntity) {

        Long lectureId = (lectureEntity.getId() == null) ?
                idSeq.getAndAdd(1) :
                lectureEntity.getId();

        LectureEntity lecture = new LectureEntity(
                lectureId,
                lectureEntity.getName(),
                lectureEntity.getLectureDate(),
                lectureEntity.getUpdateTime()
        );

        return store.put(lectureId, lecture);
    }
}
