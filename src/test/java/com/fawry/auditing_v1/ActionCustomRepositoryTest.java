package com.fawry.auditing_v1;

import com.fawry.auditing_v1.models.*;
import com.fawry.auditing_v1.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.*;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ActionCustomRepositoryTest {
    final String USER_NAME = "ahmed";
    final String BE_VALUE = "vodafone";
    final String BE_VALUE2 = "Orange";
    final String APP_ID = "1";
    final String APP_ID4 = "4";
    final String ACTION_TYPE_ID = "1";

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ActionCustomRepository actionRepositoryDsl() {
            return new ActionCustomRepositoryImpl();
        }
    }

    @Autowired
    private TestEntityManager em;
    @Autowired
    private ActionCustomRepository actionCustomRepository;

    private Map<String,String> args;

    @BeforeEach
    public void setUp() throws Exception {
        /*ActionType actionType = new ActionType();
        actionType.setCode("tsytvrhd");
        em.persist(actionType);
        em.flush();*/

        args = new HashMap<>();
    }

    @Test
    public void shouldAllActionsMatchAllArguments(){
        args.put("username",USER_NAME);
        args.put("bename",BE_VALUE2);
        args.put("appid",APP_ID4);
        args.put("actiontypeid",ACTION_TYPE_ID);
        args.put("paramtypeid","3");
        args.put("paramvalue","iphone");
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getUser).extracting(User::getValue).containsOnly(USER_NAME);
        assertThat(actions).extracting(Action::getBe).extracting(Be::getValue).containsOnly(BE_VALUE2);
        assertThat(actions).extracting(Action::getApplication).extracting(Application::getId).containsOnly(Long.valueOf(APP_ID4));
        assertThat(actions).extracting(Action::getActionType).extracting(ActionType::getId).containsOnly(Long.valueOf(ACTION_TYPE_ID));
        assertThat(actions).flatExtracting(Action::getParams).extracting(Param::getValue).contains("Iphone 6","Iphone 7","Iphone 9+","Iphone x","Iphone X Max");
    }

    @Test
    public void shouldAllActionsHave_UserWhichValueIsAhmed(){
        args.put("username",USER_NAME);
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getUser).extracting(User::getValue).containsOnly(USER_NAME);
    }
    @Test
    public void shouldAllActionsHave_BeWhichValueIsVodafone(){
        args.put("bename",BE_VALUE);
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getBe).extracting(Be::getValue).containsOnly(BE_VALUE).doesNotContain(BE_VALUE2);
    }
    @Test
    public void shouldAllActionsHave_BeWhichValueContainsSubString(){
        args.put("bename","range");
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getBe).extracting(Be::getValue).contains(BE_VALUE2);
    }
    @Test
    public void shouldAllActionsHave_AppWhichIdIs1(){
        args.put("appid",APP_ID);
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getApplication).extracting(Application::getId).containsOnly(Long.valueOf(APP_ID));
    }
    @Test
    public void shouldAllActionsHave_ActionTypeWhichIdIs1(){
        args.put("actiontypeid",ACTION_TYPE_ID);
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getActionType).extracting(ActionType::getId).containsOnly(Long.valueOf(ACTION_TYPE_ID));
    }
    @Test
    public void shouldAllActionsHave_ParamWhichValueHasSubString_WithParamTypeWhichIdIs3(){
        args.put("paramtypeid","3");
        args.put("paramvalue","iphone");
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).flatExtracting(Action::getParams).extracting(Param::getValue).contains("Iphone 6","Iphone 7","Iphone 9+","Iphone x","Iphone X Max");
    }
}
