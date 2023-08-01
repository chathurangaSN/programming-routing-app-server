package com.sachith.programmerroutingappserver.repository;

import com.sachith.programmerroutingappserver.model.MyRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRouteRepository extends JpaRepository<MyRoute, Long> {
}
