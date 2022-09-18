package com.fawry.auditing_v1;

import com.fawry.auditing_v1.models.*;
import com.fawry.auditing_v1.repositories.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@EnableJpaRepositories(basePackageClasses = ActionRepository.class)
@EntityScan(basePackageClasses = Action.class)
public class ActionCustomRepositoryTest {
    final String USER_NAME1 = "ahmed";
    final String USER_NAME2 = "aly";
    final String BE_VALUE1 = "vodafone";
    final String BE_VALUE2 = "Orange";
    Application application1;
    Application application2;
    Be be1;
    Be be2;
    ParamType paramType1;
    ActionType actionType1;
    ActionType actionType2;
    Param param1;
    Param param2;
    User user1;
    User user2;
    Action action1;
    Action action2;
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
    public void init(){
        user1 = new User();
        user1.setValue(USER_NAME1);
        user1 = em.persist(user1);
        user2 = new User();
        user2.setValue(USER_NAME2);
        user2 = em.persist(user2);

        application1 = new Application();
        application1.setName("app1");
        application1 = em.persist(application1);
        application2 = new Application();
        application2.setName("app2");
        application2 = em.persist(application2);

        be1 = new Be();
        be1.setValue(BE_VALUE1);
        be1 = em.persist(be1);
        be2 = new Be();
        be2.setValue(BE_VALUE2);
        be2 = em.persist(be2);

        actionType1 = new ActionType();
        actionType1.setCode("order_created");
        actionType1 = em.persist(actionType1);
        actionType2 = new ActionType();
        actionType2.setCode("order_refunded");
        actionType2 = em.persist(actionType2);

        paramType1 = new ParamType();
        paramType1.setCode("product");
        paramType1 = em.persist(paramType1);

        action1 = new Action();
        action1.setBe(be1);
        action1.setUser(user1);
        action1.setActionType(actionType1);
        action1.setApplication(application1);
        action1 = em.persist(action1);
        action2 = new Action();
        action2.setBe(be2);
        action2.setUser(user2);
        action2.setActionType(actionType2);
        action2.setApplication(application2);
        action2 = em.persist(action2);

        param1 = new Param();
        param1.setParamType(paramType1);
        param1.setValue("Iphone 6");
        param1.setAction(action1);
        param1 = em.persistAndFlush(param1);
        param2 = new Param();
        param2.setParamType(paramType1);
        param2.setValue("Nokia 7");
        param2.setAction(action2);
        param2 = em.persist(param2);

        args = new HashMap<>();
    }

    @Test
    public void shouldAllActionsMatchAllArguments(){
        args.put("username",user1.getValue());
        args.put("bename",be1.getValue());
        args.put("appid",application1.getId()+"");
        args.put("actiontypeid",actionType1.getId()+"");
        args.put("paramtypeid",paramType1.getId()+"");
        args.put("paramvalue","Iphone");
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getUser).extracting(User::getValue).containsOnly(user1.getValue());
        assertThat(actions).extracting(Action::getBe).extracting(Be::getValue).containsOnly(be1.getValue());
        assertThat(actions).extracting(Action::getApplication).extracting(Application::getId).containsOnly(application1.getId());
        assertThat(actions).extracting(Action::getActionType).extracting(ActionType::getId).containsOnly(actionType1.getId());
        assertThat(actions).containsOnly(action1);
    }

    @Test
    public void shouldAllActionsHave_UserWhichValueIsAhmed(){
        args.put("username",USER_NAME1);
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getUser).extracting(User::getValue).containsOnly(USER_NAME1);
    }
    @Test
    public void shouldAllActionsHave_BeWhichValueIsVodafone(){
        args.put("bename",BE_VALUE1);
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getBe).extracting(Be::getValue).containsOnly(BE_VALUE1).doesNotContain(BE_VALUE2);
    }
    @Test
    public void shouldAllActionsHave_BeWhichValueContainsSubString(){
        args.put("bename","range");
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getBe).extracting(Be::getValue).contains(BE_VALUE2);
    }
    @Test
    public void shouldAllActionsHave_AppWhichIdIs1(){
        args.put("appid",application1.getId()+"");
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getApplication).extracting(Application::getId).containsOnly(application1.getId());
    }
    @Test
    public void shouldAllActionsHave_ActionTypeWhichIdIs1(){
        args.put("actiontypeid",actionType1.getId()+"");
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).extracting(Action::getActionType).extracting(ActionType::getId).containsOnly(Long.valueOf(actionType1.getId()));
    }
    @Test
    public void shouldAllActionsHave_ParamWhichValueHasSubString_WithParamTypeId(){
        args.put("paramtypeid",paramType1.getId()+"");
        args.put("paramvalue","Iphone");
        List<Action> actions = actionCustomRepository.findActions(args);

        assertThat(actions).containsOnly(action1);
    }
}
