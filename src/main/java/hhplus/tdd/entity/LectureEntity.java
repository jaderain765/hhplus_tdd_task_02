package hhplus.tdd.entity;

import hhplus.tdd.domain.LectureDomain;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="LECTURE")
@SequenceGenerator(
        name = "LECTURE_SEQ_GENERATOR",
        sequenceName = "LECTURE_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@EqualsAndHashCode
@ToString
public class LectureEntity {
    @Id
    @Column(name="ID")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LECTURE_SEQ_GENERATOR"
    )
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="LECTURE_DATE")
    @CreatedDate
    private LocalDateTime lectureDate;

    @Column(name="UPDATE_TIME")
    @LastModifiedDate
    private LocalDateTime updateTime;

    public LectureDomain toDomain(){
        return new LectureDomain(
                this.id,
                this.name,
                this.lectureDate,
                this.updateTime
        );
    }
}
