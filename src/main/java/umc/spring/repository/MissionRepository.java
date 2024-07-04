package umc.spring.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;

import java.awt.print.Pageable;

public interface MissionRepository extends JpaRepository<Mission,Long> {
    Slice<Mission> findAllByStore(Store store, PageRequest pageRequest);
}
