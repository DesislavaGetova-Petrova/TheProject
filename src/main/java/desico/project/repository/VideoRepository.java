package desico.project.repository;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity,String> {
List<VideoEntity> findByChapterName(ChapterNameEntity chapterNameEntity);
VideoEntity findByVideoName(String videoName);
}
