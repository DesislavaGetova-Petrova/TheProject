package desico.project.repository;

import desico.project.model.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<VideoEntity,String> {
    @Query("select e.videoName from VideoEntity as e ")
    List<String> findAllNames();

    Optional<VideoEntity> findByVideoName(String name);
}
