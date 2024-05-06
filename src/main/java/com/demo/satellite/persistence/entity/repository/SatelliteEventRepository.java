package com.demo.satellite.persistence.entity.repository;

import com.demo.satellite.persistence.entity.SatelliteEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatelliteEventRepository extends JpaRepository<SatelliteEvent, Long>
{
    List<SatelliteEvent> findBySatelliteName(String satelliteName);
}
