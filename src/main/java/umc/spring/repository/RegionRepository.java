package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.Region;

public interface RegionRepository extends JpaRepository<Region,Long> {
    @Query("SELECT r FROM Region r WHERE r.regionName = :regionName")
    Region findRegionIdByRegionName(@Param("regionName")String regionName);
}
