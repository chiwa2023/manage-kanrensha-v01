package mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.SaveFileStorage2025Entity;

/**
 * save_file_storage_2025接続用Repository
 */
public interface SaveFileStorage2025Repository extends JpaRepository<SaveFileStorage2025Entity, Integer> {

    /**
     * 最大コードを取得する
     *
     * @return 最大コードと持つEntityのOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SaveFileStorage2025Entity> findFirstByOrderBySaveFileStorageCodeDesc();

}
