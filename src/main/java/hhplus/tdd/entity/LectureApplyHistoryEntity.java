package hhplus.tdd.entity;

import hhplus.tdd.domain.LectureApplyHistoryDomain;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "LECTURE_APPLY_HISTORY")
@IdClass(LectureApplyHistoryEntityPK.class)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class LectureApplyHistoryEntity {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Id
    @Column(name = "LECTURE_ID")
    private Long lectureId;

    @CreatedDate
    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    public LectureApplyHistoryDomain toDomain(){

        LectureApplyHistoryDomain lectureApplyHistoryDomain = new LectureApplyHistoryDomain(
                this.userId,
                this.lectureId,
                this.createTime
        );

        return lectureApplyHistoryDomain;
    }

}
