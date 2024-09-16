package com.Project.ELearning.Repositores;

import com.Project.ELearning.Entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends JpaRepository<Video,String> {
}
