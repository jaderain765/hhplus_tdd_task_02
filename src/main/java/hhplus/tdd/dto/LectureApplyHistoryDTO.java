package hhplus.tdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplyHistoryDTO {
    private Long userId;
    private Long lectureId;
    private LocalDateTime createTime;
}
