package hufs.sweepyswipe.repository;

import hufs.sweepyswipe.domain.TrashItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrashItemRepository extends JpaRepository<TrashItem, Long> {
}
