package be.eafcuccle.projint.backendfroland.persistence;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
  Collection<Section> findByCoursesId(Long courseId);
}
