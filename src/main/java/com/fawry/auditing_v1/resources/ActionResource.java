package com.fawry.auditing_v1.resources;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fawry.auditing_v1.dtos.ActionDto;
import com.fawry.auditing_v1.helpers.Validator;
import com.fawry.auditing_v1.models.Action;
import com.fawry.auditing_v1.rabbitMQ.Publisher;
import com.fawry.auditing_v1.sevices.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RequestMapping("/actions")
@RestController
public class ActionResource {
/*    @GetMapping("/be")
    public ResponseEntity<List<Action>> getActionsByBeId(@RequestParam Long id){
        return new ResponseEntity(actionService.listActionsByBeId(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Action>> getActionsByUserId(@RequestParam Long id){
        return new ResponseEntity(actionService.listActionsByUserId(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/actiontype")
    public ResponseEntity<List<Action>> getActionsByActionTypeId(@RequestParam Long id){
        return new ResponseEntity(actionService.listActionsByActionTypeId(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/application")
    public ResponseEntity<List<Action>> getActionsByApplicationId(@RequestParam Long id){
        return new ResponseEntity(actionService.listActionsByApplicationId(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/param")
    public ResponseEntity<List<Param>> getActionsByParamValue(@RequestParam String value){
        return new ResponseEntity(actionService.listActionsByParamValue(value), HttpStatus.ACCEPTED);
    }
    @GetMapping("/parami")
    public ResponseEntity<List<Param>> getActionsByParamIdentifier(@RequestParam String identifier){
        return new ResponseEntity(actionService.listActionsByParamIdentifier(identifier), HttpStatus.ACCEPTED);
    }
    @GetMapping("/multipleSearch")
    public ResponseEntity<List<Action>> getMActions(@RequestParam Map<String,String> params){
        return new ResponseEntity(actionService.findActions(params), HttpStatus.ACCEPTED);
    }*/
    //===========================================new start=================================================
    @Autowired
    private ActionService actionService;
    @Autowired
    private Validator validator;
    @Autowired
    private Publisher publisher;
    @PostMapping
    public ResponseEntity<String> addAction(@RequestBody final ActionDto actionDto) throws JsonProcessingException {
        try{
            validator.validateActionRequestBody(actionDto);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(actionDto);
            publisher.send(json);
            return new ResponseEntity("", HttpStatus.CREATED);
        }catch (InvalidFormatException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,e.getMessage());
        }catch (InvalidDataAccessApiUsageException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,e.getMessage());
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }

    }
    @GetMapping()
    public ResponseEntity<List<Action>> findActions(@RequestParam Map<String,String> params){
        return new ResponseEntity(actionService.findActions(params), HttpStatus.ACCEPTED);
    }
}
