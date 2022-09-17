package com.fawry.auditing_v1.repositories;

import com.fawry.auditing_v1.models.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class ActionCustomRepositoryImpl implements ActionCustomRepository {
    @PersistenceContext
    private EntityManager em;


    public ActionCustomRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public ActionCustomRepositoryImpl() {
    }

    public List<Action> findActions(Map<String,String> args){
        final JPAQuery<Action> query = new JPAQuery<>(em);
        final QAction action = QAction.action;
        final QParam param = QParam.param;
//        final int pageLimit = 10;
//        int pageOffset = 0;

        query.from(action);
        if(args.get("appid")!=null) {
            query.where(action.application.id.eq(Long.parseLong(args.get("appid"))));
        };
        if(args.get("username")!=null){
            query.where(action.user.value.contains(args.get("username")));
        }
        if(args.get("bename")!=null){
            query.where(action.be.value.contains(args.get("bename")));
        }
/*        if(args.get("paramvalue")!=null){
            query.where(action.params.any().value.contains(args.get("paramvalue")));
        }*/
        if(args.get("actiontypeid")!=null){
            query.where(action.actionType.id.eq(Long.parseLong(args.get("actiontypeid"))));
        }
        if(args.get("paramtypeid")!=null&&args.get("paramvalue")!=null){
            query.join(action.params,param);
            query.where(param.value.contains(args.get("paramvalue")),param.paramType.id.eq(Long.parseLong(args.get("paramtypeid"))));
        }

//        pageOffset = args.get("page")!=null? Integer.parseInt(args.get("page")) : 0;
//        return query.limit(pageLimit).offset(pageOffset*pageLimit).fetch();

        return query.fetch();
    }
}
