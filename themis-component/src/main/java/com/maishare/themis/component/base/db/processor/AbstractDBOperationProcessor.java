/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bravo.gaia.commons.domain.Pair;

import com.maishare.themis.component.base.db.operation.DBOperation;
import com.maishare.themis.component.base.domain.CompareItem;
import com.maishare.themis.component.base.domain.CompareOperator;

/**
 * BD操作处理器模板
 * @author moushaokun
 * @version @Id: AbstractDBOperationProcessor.java, v 0.1 2020年03月20日 18:32 moushaokun Exp $
 */
public abstract class AbstractDBOperationProcessor<T extends DBOperation, R> implements DBOperationProcessor<T, R> {

    public Pair<String, List<Object>> getConditionStrAndParam(Map<String, CompareItem> conditions) {
        Pair<String, List<Object>> result = new Pair<>();
        StringBuilder sb = new StringBuilder();
        List<Object> params = new ArrayList<>();

        conditions.forEach((k, v) -> {
            if(sb.length() > 1){
                sb.append(" and ");
            }

            sb.append(k);
            sb.append(" ");
            sb.append(v.getOperator().getSymbol());

            if((v.getOperator() == CompareOperator.IN || v.getOperator() == CompareOperator.NOT_IN)
                    && v.getValue() instanceof List){
                sb.append(" (");

                StringBuilder innerBuilder = new StringBuilder();
                ((List) v.getValue()).forEach(o -> {
                    innerBuilder.append(" ?,");
                    params.add(o);
                });

                sb.append(innerBuilder.substring(0, innerBuilder.length() -1));
                sb.append(") ");
            }else{
                sb.append(" ? ");
                params.add(getConditionValue(v));
            }
        });

        result.setFirst(sb.toString());
        result.setSecond(params);

        return result;
    }

    public Object getConditionValue(CompareItem item){
        if (item.getOperator() == CompareOperator.LIKE
                || item.getOperator() == CompareOperator.NOT_LIKE) {
            return "%" + item.getValue().toString() + "%";

        } else if (item.getOperator() == CompareOperator.LEFT_LIKE
                || item.getOperator() == CompareOperator.NOT_LEFT_LIKE) {
            return item.getValue().toString() + "%";

        } else if (item.getOperator() == CompareOperator.RIGHT_LIKE
                || item.getOperator() == CompareOperator.NOT_RIGHT_LIKE) {
            return "%" + item.getValue().toString();
        }

        return item.getValue();
    }

}

