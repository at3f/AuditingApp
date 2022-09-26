package com.fawry.auditing_v1.resources;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RequestMapping("/actions")
@RestController
public class ActionResource {
    @Autowired
    private ActionService actionService;
    @Autowired
    private Validator validator;
    @Autowired
    private Publisher publisher;
    @PostMapping
    public ResponseEntity<String> addAction(@RequestBody final ActionDto actionDto){
        try{
            validator.validateActionRequestBody(actionDto);
            publisher.send(actionDto);
            return new ResponseEntity("", HttpStatus.CREATED);
        } catch (InvalidDataAccessApiUsageException e){
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
