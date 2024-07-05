package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import umc.spring.domain.Store;


public interface MissionRepository extends JpaRepository<Mission,Long> {
    Slice<Mission> findAllByStore(Store store, PageRequest pageRequest);
}
