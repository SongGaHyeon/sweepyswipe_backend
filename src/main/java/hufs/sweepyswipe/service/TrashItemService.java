
package hufs.sweepyswipe.service;
import hufs.sweepyswipe.domain.TrashItem;
import hufs.sweepyswipe.repository.TrashItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TrashItemService {
    @Autowired
    private TrashItemRepository trashItemRepository;

    public List<TrashItem> findAll() {
        return trashItemRepository.findAll();
    }

    public Optional<TrashItem> findById(Long id) {
        return trashItemRepository.findById(id);
    }
}