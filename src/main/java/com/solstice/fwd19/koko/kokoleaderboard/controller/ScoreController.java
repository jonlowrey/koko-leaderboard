package com.solstice.fwd19.koko.kokoleaderboard.controller;

import com.solstice.fwd19.koko.kokoleaderboard.entity.NewScoreRequest;
import com.solstice.fwd19.koko.kokoleaderboard.entity.Score;
import com.solstice.fwd19.koko.kokoleaderboard.entity.Rank;
import com.solstice.fwd19.koko.kokoleaderboard.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RestController("/")
public class ScoreController {
    private ScoreRepository scoreRepository;

    @Autowired
    public ScoreController(ScoreRepository scoreRepository){
        this.scoreRepository = scoreRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Score>> getScore(){
        return new ResponseEntity(top5(), HttpStatus.OK);
    }

    @GetMapping("delete")
    public void delete(){
        scoreRepository.deleteAll();
        return;
    }

    private int getRank(Long r){
        List<Score> currentScores =(List<Score>) scoreRepository.findAll();
        Score toFind = new Score();
        toFind.setScore(r);
        currentScores.add(toFind);
        Collections.sort(currentScores);
        return currentScores.indexOf(toFind)+1;

    }

    @GetMapping("rank")
    public String rank(@RequestParam(name = "score") Long score){
        return "{\"rank\": "+getRank(score)+"}";
    }

    private List<Score> rankList(List<Score> theList){
        for(int i =0;i<theList.size();i++){
            theList.get(i).setRank(i+1);
        }
        return theList;
    }

    private List<Score> top5(){
        List<Score> topFive = new ArrayList<>();

        topFive =(ArrayList<Score>) scoreRepository.findAll();
        Collections.sort(topFive);
        if(topFive.size()<5){
            return rankList(topFive.subList(0,topFive.size()));
        }
        return rankList(topFive.subList(0,5));
    }

    @PostMapping("newScore")
    public List<Score> newScore(@RequestBody List<NewScoreRequest> scores){
        List<Score> scoresCreated = new ArrayList<>();
        for(NewScoreRequest n: scores){
            Score s = n.toScore();
            s = scoreRepository.save(s);
            scoresCreated.add(s);
        }

        return scoresCreated;
    }
}
