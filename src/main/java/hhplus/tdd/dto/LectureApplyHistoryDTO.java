package hhplus.tdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplyHistoryDTO {
    private Long userId;
    private Long updateTime;
}
