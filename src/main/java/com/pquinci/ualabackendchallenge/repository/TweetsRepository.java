package com.pquinci.ualabackendchallenge.repository;

import com.pquinci.ualabackendchallenge.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetsRepository extends JpaRepository<Tweet, Long>{
}
