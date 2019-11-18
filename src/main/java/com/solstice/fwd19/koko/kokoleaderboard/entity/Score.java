package com.solstice.fwd19.koko.kokoleaderboard.entity;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Score implements Comparable<Score>{
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    private long score;
    private long rank;
    private LocalDateTime created;

    public Score() {
        this.created = LocalDateTime.now();
    }

    public Score(String email, String name, long score) {
        this.email = email;
        this.name = name;
        this.score = score;
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Score s) {
        if (s.score < this.score) {
            return -1;
        }
        else if (s.score > this.score) {
            return 1;
        }
        else if (s.score == this.score) {//Same score, newest
            if (s.created.isAfter(this.created)) {
                return 1;
            } else {
                return -1;
            }
        }
        else return 0;
    }
}
