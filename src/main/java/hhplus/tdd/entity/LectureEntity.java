package hhplus.tdd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
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
}
