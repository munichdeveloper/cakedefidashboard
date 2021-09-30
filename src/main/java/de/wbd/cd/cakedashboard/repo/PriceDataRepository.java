package de.wbd.cd.cakedashboard.repo;

import de.wbd.cd.cakedashboard.entity.PriceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDataRepository extends JpaRepository<PriceData, Long> {

}
