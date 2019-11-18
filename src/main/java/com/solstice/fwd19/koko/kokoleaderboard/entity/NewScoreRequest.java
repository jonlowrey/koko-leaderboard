package com.solstice.fwd19.koko.kokoleaderboard.entity;

import java.time.LocalDateTime;

public class NewScoreRequest {

    private String name;
    private String email;
    private long score;

    public NewScoreRequest() {
    }

    public Score toScore(){
        return new Score(this.email, this.name, this.score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
