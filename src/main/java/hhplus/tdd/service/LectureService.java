package hhplus.tdd.service;

import hhplus.tdd.domain.LectureApplyHistoryDomain;
import hhplus.tdd.domain.LectureDomain;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LectureService {
    /**
     * <h1>특강 신청 메소드</h1>
     *
     * <h2>요구 사항</h2>
     * <ul>
     *     <li>사용자 아이디와 특강 아이디를 받아서 해당 특강을 신청한다.</li>
     *     <li>특강은 30명만이 들을 수 있다.</li>
     *     <li>사용자는 신청한 강의를 또 신청할 수 없다.</li>
     * </ul>
     * 
     * @param userId : 신청한 사용자 아이디
     * @param lectureId : 신청한 특강 아이디
     * @return
     */
    public LectureApplyHistoryDomain applyLecture(Long userId, Long lectureId);

    /**
     * <h1>특강 등록 확인 메소드</h1>
     * 
     * <h2>요구 사항</h2>
     * <ul>
     *     <li>매개변수로 주어진 값을 통해 신청기록이 있는지 확인한다.</li>
     *     <li>있을 경우 true, 없을 경우 false를 반환한다.</li>
     * </ul>
     * 
     * @param userId 등록여부를 확인할 사용자 아이디
     * @param lectureId 등록여부를 확인한 특강 아이디
     * @return
     */
    public Boolean checkApply(Long userId, Long lectureId);

    /**
     * <h1>특강 등록 확인 메소드</h1>
     *
     * <h2>요구 사항</h2>
     * <ul>
     *     <li>현재 있는 특강 정보들을 불러온다.</li>
     * </ul>
     * @return
     */
    public List<LectureDomain> searchLectureList();
}
