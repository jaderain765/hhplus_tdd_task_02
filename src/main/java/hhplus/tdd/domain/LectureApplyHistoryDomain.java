package hhplus.tdd.domain;

import hhplus.tdd.entity.LectureApplyHistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureApplyHistoryDomain {
    private Long userId;
    private Long lectureId;
    private LocalDateTime createTime;

    public LectureApplyHistoryEntity toEntity(){
        validate();

        LectureApplyHistoryEntity LectureApplyHistoryEntity = new LectureApplyHistoryEntity(
                this.userId,
                this.lectureId,
                this.createTime
        );

        return LectureApplyHistoryEntity;
    }

    public void validate() {
        if (this.userId == null || this.userId < 1)
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        if (this.lectureId == null || this.lectureId < 1)
            throw new RuntimeException("강의 정보를 찾을 수 없습니다.");
    }
}
