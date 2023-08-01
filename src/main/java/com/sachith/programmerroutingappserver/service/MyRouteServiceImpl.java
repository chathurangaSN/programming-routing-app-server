package com.sachith.programmerroutingappserver.service;

import com.sachith.programmerroutingappserver.dto.Response;
import com.sachith.programmerroutingappserver.model.MyRoute;
import com.sachith.programmerroutingappserver.repository.MyRouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MyRouteServiceImpl implements MyRouteService {

    @Autowired
    private MyRouteRepository myRouteRepository;

    @Autowired
    private Response response;

    @Override
    public ResponseEntity<String> greet() {
        return new ResponseEntity<String>("Hello greeting form : Programmer Routing Application", HttpStatus.OK);
    }

    public ResponseEntity<Object> findAll() {
        List<MyRoute> myRouteList = myRouteRepository.findAll();
        if(myRouteList.isEmpty()){
            Date date = new Date();
            response.setTimestamp(date.toString());
            response.setStatus(HttpStatus.NOT_FOUND.toString().substring(0,3));
            response.setPath("server/route");
            response.setError("NOT_FOUND");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(myRouteList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findById(Long id) {
        Optional<MyRoute> optionalMyRoute = myRouteRepository.findById(id);
        if(optionalMyRoute.isPresent()){
            MyRoute myRoute = optionalMyRoute.get();
            return new ResponseEntity<>(myRoute,HttpStatus.OK);
        } else{
            Date date = new Date();
            response.setTimestamp(date.toString());
            response.setStatus(HttpStatus.NOT_FOUND.toString().substring(0,3));
            response.setPath("server/route/"+id);
            response.setError("NOT_FOUND");
        }
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND) ;
    }

    @Override
    public ResponseEntity<Object> save(MyRoute myRoute) {
        try {
            MyRoute savedMyRoute = myRouteRepository.save(myRoute);
            return new ResponseEntity<>("Saved",HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Failed",e);
            return new ResponseEntity<>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> edit(MyRoute myRoute, Long id) {

        MyRoute updatedMyRoute = null;
        MyRoute oldRoute = null;
        try {
            if(findById(id).getBody() instanceof MyRoute){
                oldRoute =(MyRoute) findById(id).getBody();
            }

        } catch (Exception e){
            log.error("Failed",e);
        }

        if(oldRoute != null){
            if(!oldRoute.getName().isEmpty()){
                oldRoute.setName(myRoute.getName());
            }
        } else{
            Date date = new Date();
            response.setTimestamp(date.toString());
            response.setStatus(HttpStatus.NOT_FOUND.toString().substring(0,3));
            response.setPath("server/route/"+id);
            response.setError("NOT_FOUND");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        try {
            updatedMyRoute = myRouteRepository.save(oldRoute);
        } catch (Exception e){
            Date date = new Date();
            response.setTimestamp(date.toString());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0,3));
            response.setPath("server/route/"+id);
            response.setError("INTERNAL_SERVER_ERROR");
            log.error("Failed",e);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updatedMyRoute,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteById(long id) {
        try {
            myRouteRepository.deleteById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        } catch (Exception e){
            log.error("Failed",e);
            return new ResponseEntity<>("Failed",HttpStatus.NOT_FOUND);
        }
    }
}
