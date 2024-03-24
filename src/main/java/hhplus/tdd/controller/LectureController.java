package hhplus.tdd.controller;

import hhplus.tdd.dto.LectureApplyHistoryDTO;
import hhplus.tdd.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecture")
public class LectureController {

    LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService){
        this.lectureService = lectureService;
    }

    /**
     * 사용자 ID를 통해 특강을 신청함
     *
     * @param userId
     * @return
     */
    @PutMapping("{userId}")
    public ResponseEntity<LectureApplyHistoryDTO> apply(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(lectureService.applyLecture(userId).toDTO(), HttpStatus.OK);
    }

    /**
     * 특강 신청 여부 확인
     *
     * @param userId
     * @return
     */
    @GetMapping("{userId}")
    public ResponseEntity<Boolean> check(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(lectureService.checkApply(userId), HttpStatus.OK);
    }
}
