package hhplus.tdd.domain;

import hhplus.tdd.dto.LectureApplyHistoryDTO;
import hhplus.tdd.entity.LectureApplyHistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureApplyHistoryDomain {
    private Long userId;
    private Long updateTime;

    public LectureApplyHistoryEntity toEntity(){
        this.validate();
        LectureApplyHistoryEntity LectureApplyHistoryEntity = new LectureApplyHistoryEntity();

        LectureApplyHistoryEntity.setUserId(this.userId);
        LectureApplyHistoryEntity.setUpdateTime(this.updateTime);

        return LectureApplyHistoryEntity;
    }

    public LectureApplyHistoryDTO toDTO(){
        this.validate();
        LectureApplyHistoryDTO LectureApplyHistoryDTO = new LectureApplyHistoryDTO();

        LectureApplyHistoryDTO.setUserId(this.userId);
        LectureApplyHistoryDTO.setUpdateTime(this.updateTime);

        return LectureApplyHistoryDTO;
    }

    public void validate() {
        if (this.userId == null || this.userId < 1)
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
    }
}
