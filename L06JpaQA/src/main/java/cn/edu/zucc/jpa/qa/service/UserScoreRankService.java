package cn.edu.zucc.jpa.qa.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengbin
 * @version 1.0
 * @date 2023/04/22 12:03
 */
@Service
public interface UserScoreRankService {

    String caseLike(Integer caseId, Integer userId);

    List<String> getTop20Cases();

    Long getLikeCount(Integer caseId);

    void saveCaseRank(List<Integer> caseRank);

    void saveKnowRank(List<Integer> knowRank);
}
