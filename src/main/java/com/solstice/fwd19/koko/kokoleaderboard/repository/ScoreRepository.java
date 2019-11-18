package com.solstice.fwd19.koko.kokoleaderboard.repository;

import com.solstice.fwd19.koko.kokoleaderboard.entity.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {
}
