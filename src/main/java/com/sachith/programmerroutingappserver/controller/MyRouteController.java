package com.sachith.programmerroutingappserver.controller;

import com.sachith.programmerroutingappserver.dto.Response;
import com.sachith.programmerroutingappserver.model.MyRoute;
import com.sachith.programmerroutingappserver.service.MyRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("server")
@Slf4j
@CrossOrigin()
public class MyRouteController {

    @Autowired
    private MyRouteService myRouteService;

    @Autowired
    private Response response;

    @GetMapping()
    public ResponseEntity<String> greet() {
        log.info("enter to greet()");
        return myRouteService.greet();
    }

    @GetMapping("/route")
    public ResponseEntity<Object> findAll() {
        log.info("enter to findAll()");
        return myRouteService.findAll();
    }

    @GetMapping("/route/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id
    ) {
        log.info("enter to findById()");
        return myRouteService.findById(id);
    }

    @PostMapping("/route")
    public ResponseEntity<Object> save(@RequestBody(required = false) MyRoute myRoute) {
        log.info("enter to save()");
        try{
            if (myRoute == null){
                Date date = new Date();
                response.setTimestamp(date.toString());
                response.setStatus(HttpStatus.BAD_REQUEST.toString().substring(0,2));
                response.setPath("server/route");
                response.setError("BAD_REQUEST");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        } else {
                return myRouteService.save(myRoute);
            }

        } catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/route/{id}")
    public ResponseEntity<Object> edit(@RequestBody MyRoute myRoute,@PathVariable("id") Long id) {
        log.info("enter to edit()");
        return myRouteService.edit(myRoute,id);
    }

    @DeleteMapping("/route/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id
    ) {
        log.info("enter to findById()");
        return myRouteService.deleteById(id);
    }


}
