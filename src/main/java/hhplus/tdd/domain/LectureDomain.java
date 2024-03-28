package hhplus.tdd.domain;

import hhplus.tdd.entity.LectureEntity;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class LectureDomain {
    private Long id;
    private String name;
    private LocalDateTime lectureDate;
    private LocalDateTime updateTime;

    public LectureEntity toEntity(){
        return new LectureEntity(
                this.id,
                this.name,
                this.lectureDate,
                this.updateTime
        );
    }
}
