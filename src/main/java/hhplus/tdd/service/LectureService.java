package hhplus.tdd.service;

import hhplus.tdd.domain.LectureApplyHistoryDomain;
import hhplus.tdd.dto.LectureApplyHistoryDTO;
import org.springframework.stereotype.Service;

@Service
public interface LectureService {
    /**
     * <h1>특강 신청 메소드</h1>
     *
     * @param userId
     * @return
     */
    public LectureApplyHistoryDomain applyLecture(Long userId);

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
     * @return 등록 여부 불린 반환
     */
    public Boolean checkApply(Long userId);
}
