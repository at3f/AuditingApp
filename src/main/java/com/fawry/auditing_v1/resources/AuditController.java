package com.fawry.auditing_v1.resources;

import com.fawry.auditing_v1.dtos.ActionDto;
import com.fawry.auditing_v1.helpers.Validator;
import com.fawry.auditing_v1.models.Action;
import com.fawry.auditing_v1.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RequestMapping("api/v1/action")
@RestController
public class AuditController {

    @Autowired
    private ActionService actionService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ActionTypeService actionTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private BeService beService;
    @Autowired
    private ParamService paramService;
    @Autowired
    private ParamTypeService paramTypeService;
    @Autowired
    private Validator validator;

    @PostMapping("/old")
    public ResponseEntity<Action> set(@RequestBody final ActionDto actionDto){

        /*validator.validateActionRequestBody(actionDto);

        Action action = new ActionMapperImpl().mapToAction(actionDto);
        User user = userService.findUserById(actionDto.getUserId()).get();
        ActionType actionType = actionTypeService.findActionType(actionDto.getActionTypeCode()).get();
        action.setApplication(applicationService.findApplicationById(actionDto.getApplicationId()).get());
        action.setActionType(actionType);
        action.setBe(beService.findBeById(actionDto.getBeId()).get());
        action.setUser(user);

        List<ParamDto> paramDtos = actionDto.getParamDtos();

        ParamDto userParamDto = new ParamDto();
        userParamDto.setName("user");
        userParamDto.setId(user.getId());
        userParamDto.setValue(user.getValue());
        paramDtos.add(userParamDto);
        String msg_en = actionDescription.createDescriptionFromTamplate(actionType.getMessage_template_en(),paramDtos);
        String msg_ar = actionDescription.createDescriptionFromTamplate(actionType.getMessage_template_ar(),paramDtos);
        action.setDescription_en(msg_en);
        action.setDescription_ar(msg_ar);
        actionDto.getParamDtos().removeIf(c->c.getName()=="user");

        Action actionSaved = actionService.save(action);

        List<Param> params = actionDto.getParamDtos().stream().map(c->{
            Param param = new ParamMapperImpl().mapToParam(c);
            param.setAction(actionService.findActionById(actionSaved.getId()));
            param.setParamType(paramTypeService.findParamTypeByCode(c.getName()).get());
            return param;
        }).collect(Collectors.toList());
        paramService.saveAll(params);*/

        return new ResponseEntity("actionSaved", HttpStatus.ACCEPTED);
    }

    /*@Autowired
    private Publisher publisher;
    @PostMapping
    public ResponseEntity<String> setQ(@RequestBody final ActionDto actionDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(actionDto);
        publisher.send(json);
        return new ResponseEntity<String>("", HttpStatus.CREATED);
    }*/

   /* @GetMapping
    public ResponseEntity<List<Action>> getAll(){
        return new ResponseEntity(actionService.getList(), HttpStatus.ACCEPTED);
    }


    @Autowired
    private ActionRepositoryDsl actionRepositoryDsl;
    @GetMapping("/dsl")
    public ResponseEntity<List<Action>> get(){
        return new ResponseEntity(actionRepositoryDsl.listActions(), HttpStatus.ACCEPTED);
    }*/
}
