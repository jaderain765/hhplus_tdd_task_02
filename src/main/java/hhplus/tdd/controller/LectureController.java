package hhplus.tdd.controller;

import hhplus.tdd.domain.LectureApplyHistoryDomain;
import hhplus.tdd.domain.LectureDomain;
import hhplus.tdd.service.LectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecture")
public class LectureController {

    private final Logger log = LoggerFactory.getLogger(getClass());

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
    @PutMapping("{userId}/{lectureId}")
    public ResponseEntity<LectureApplyHistoryDomain> apply(
            @PathVariable("userId") Long userId,
            @PathVariable("lectureId") Long lectureId
    ) {
        log.debug("사용자({})가 강의({})를 등록 신청함", userId, lectureId);
        return new ResponseEntity<>(lectureService.applyLecture(userId,lectureId), HttpStatus.OK);
    }

    /**
     * 특강 신청 여부 확인
     *
     * @param userId
     * @param lectureId
     * @return
     */
    @GetMapping("{userId}/{lectureId}")
    public ResponseEntity<Boolean> check(
            @PathVariable("userId") Long userId,
            @PathVariable("lectureId") Long lectureId
    ) {
        log.debug("사용자({})가 강의({})를 등록 확인 조회함", userId, lectureId);
        return new ResponseEntity<>(lectureService.checkApply(userId,lectureId), HttpStatus.OK);
    }


    /**
     * 특강 목록 조회
     *
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<LectureDomain>> check() {
        log.debug("모든 강의 리스트 요청됨");
        return new ResponseEntity<>(lectureService.searchLectureList(), HttpStatus.OK);
    }
}
