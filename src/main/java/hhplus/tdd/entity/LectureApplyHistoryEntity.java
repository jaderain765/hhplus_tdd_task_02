package hhplus.tdd.entity;

import hhplus.tdd.domain.LectureApplyHistoryDomain;
import hhplus.tdd.dto.LectureApplyHistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplyHistoryEntity {
    private Long userId;
    private Long updateTime;

    public LectureApplyHistoryDomain toDomain(){
        LectureApplyHistoryDomain lectureApplyHistoryDomain = new LectureApplyHistoryDomain();

        lectureApplyHistoryDomain.setUserId(this.userId);
        lectureApplyHistoryDomain.setUpdateTime(this.updateTime);

        return lectureApplyHistoryDomain;
    }

}
