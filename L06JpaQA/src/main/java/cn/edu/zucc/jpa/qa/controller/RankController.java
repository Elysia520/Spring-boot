package cn.edu.zucc.jpa.qa.controller;

import cn.edu.zucc.jpa.qa.Bean.RankBean;
import cn.edu.zucc.jpa.qa.entity.RankEntity;
import cn.edu.zucc.jpa.qa.repository.RankRepository;
import cn.edu.zucc.jpa.qa.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class RankController{
    private final RankRepository rankRepository;
    private final UserRepository userRepository;

    public RankController(RankRepository rankRepository, UserRepository userRepository) {
        this.rankRepository = rankRepository;
        this.userRepository = userRepository;
    }
    @ResponseBody
    @PostMapping ("/user/rank")
    public String saveScore(@RequestBody Map<String, String> body, @RequestParam("id") int id){
        System.out.println(body);
        RankEntity rank = rankRepository.findById(id);
        RankEntity rankNew = new RankEntity();
        System.out.println(rank);
        if(rank != null) {
            if(Double.compare(rank.getScore(), Double.parseDouble(body.get("time"))) > 0){
                rankRepository.updateScoreById(id, Double.parseDouble(body.get("time")));
            }
        }
        else {
            rankNew.setId(id);
            rankNew.setScore(Double.parseDouble(body.get("time")));
            rankRepository.save(rankNew);
        }

        return "success";
    }
    @GetMapping ("/user/rank")
    public RankBean getRank(@RequestParam("id") int id){
        RankEntity rankE = rankRepository.findById(id);
        RankBean rankB = new RankBean();
        if(rankE != null){
            rankB = new RankBean(rankE.getId(), userRepository.findById(id).getName(), rankE.getScore());
        }
        return rankB;
    }

    @GetMapping ("/user/rankList")
    public List<RankBean> getRankList(){
        List<RankBean> rankBeansList = new ArrayList<>();
        List<RankEntity> rankList = rankRepository.findAll();
        List<RankEntity> sortedList = rankList.stream()
                .sorted(Comparator.comparing(RankEntity::getScore))
                .collect(Collectors.toList());
        for(RankEntity rankE : sortedList){
            RankBean rankB = new RankBean(rankE.getId(), userRepository.findById(rankE.getId()).getName(), rankE.getScore());
            rankBeansList.add(rankB);
        }
        return rankBeansList;
    }

}