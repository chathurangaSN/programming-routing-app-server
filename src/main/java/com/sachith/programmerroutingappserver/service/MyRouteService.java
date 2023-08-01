package com.sachith.programmerroutingappserver.service;

import com.sachith.programmerroutingappserver.model.MyRoute;
import org.springframework.http.ResponseEntity;

public interface MyRouteService {

    ResponseEntity<String> greet();

    ResponseEntity<Object> findAll();

    ResponseEntity<Object> findById(Long id);

    ResponseEntity<Object> save(MyRoute myRoute);

    ResponseEntity<Object> edit(MyRoute myRoute,Long id);

    ResponseEntity<String> deleteById(long id);

}
