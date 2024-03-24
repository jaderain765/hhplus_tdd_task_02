package hhplus.tdd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplyHistoryEntityPK implements Serializable {
    private Long userId;
    private Long lectureId;
}
