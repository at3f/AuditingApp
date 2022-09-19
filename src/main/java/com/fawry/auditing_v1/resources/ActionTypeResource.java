package com.fawry.auditing_v1.resources;

import com.fawry.auditing_v1.models.ActionType;
import com.fawry.auditing_v1.sevices.ActionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/actiontypes")
public class ActionTypeResource {
    @Autowired
    private ActionTypeService actionTypeService;

    @GetMapping
    public ResponseEntity<List<ActionType>> findActionTypes(){
        return new ResponseEntity<>(actionTypeService.findActionTypes(), HttpStatus.OK);
    }
}
