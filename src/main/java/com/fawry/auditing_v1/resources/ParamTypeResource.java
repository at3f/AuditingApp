package com.fawry.auditing_v1.resources;

import com.fawry.auditing_v1.models.ParamType;
import com.fawry.auditing_v1.sevices.ParamTypeService;
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
@RequestMapping("/paramtypes")
public class ParamTypeResource {
    @Autowired
    private ParamTypeService paramTypeService;

    @GetMapping
    public ResponseEntity<List<ParamType>> findParamTypes(){
        return new ResponseEntity<>(paramTypeService.findParamTypes(), HttpStatus.OK);
    }
}
