/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.domain;

import com.maishare.themis.component.base.convert.TypeConverterExecutor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * 比较操作符
 * @author moushaokun
 * @version @Id: CompareOperator.java, v 0.1 2020年03月19日 12:21 moushaokun Exp $
 */
public enum CompareOperator {
    LESS_THAN("less_than", "<") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            return new BigDecimal(String.valueOf(leftValue)).compareTo(new BigDecimal(String.valueOf(rightValue))) < 0;
        }
    }, LESS_EQUAL("less_equal", "<=") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            return new BigDecimal(String.valueOf(leftValue)).compareTo(new BigDecimal(String.valueOf(rightValue))) <= 0;
        }
    }, GREATER_EQUAL("greater_equal", ">=") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            return new BigDecimal(String.valueOf(leftValue)).compareTo(new BigDecimal(String.valueOf(rightValue))) >= 0;
        }
    }, GREATER_THAN("greater_than", ">") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
           return new BigDecimal(String.valueOf(leftValue)).compareTo(new BigDecimal(String.valueOf(rightValue))) > 0;
        }
    },EQUAL("equal", "=") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            if (leftValue instanceof Date) {
                return typeConverter.convert(leftValue, Date.class).compareTo(typeConverter.convert(rightValue, Date.class)) == 0;
            } else {
                return typeConverter.convert(leftValue, rightValue.getClass()).equals(rightValue);
            }
        }
    },NOT_EQUAL("not_equal", "<>") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            return !EQUAL.compare(leftValue, rightValue);
        }
    },LIKE("like", "like") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            return String.valueOf(leftValue).contains(String.valueOf(rightValue));
        }
    },NOT_LIKE("not_like", "not like") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            return !LIKE.compare(leftValue, rightValue);
        }
    },LEFT_LIKE("left_like", "like") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            return String.valueOf(leftValue).startsWith(String.valueOf(rightValue));
        }
    },NOT_LEFT_LIKE("not_left_like", "not like") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            return !LEFT_LIKE.compare(leftValue, rightValue);
        }
    },RIGHT_LIKE("right_like", "like") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            return String.valueOf(leftValue).endsWith(String.valueOf(rightValue));
        }
    },NOT_RIGHT_LIKE("not_right_like", "not like") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            return !RIGHT_LIKE.compare(leftValue, rightValue);
        }
    },IN("in", "in") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            boolean flag = false;

            if (rightValue instanceof Collection) {
                for (Object obj : ((Collection) rightValue)) {
                    flag = EQUAL.compare(leftValue, obj);
                    if(flag) break;
                }
            } else if (rightValue.getClass().isArray()) {
                Object[] objs = (Object[]) rightValue;
                for (Object obj : objs) {
                    flag = EQUAL.compare(leftValue, obj);
                    if(flag) break;
                }
            }

            return flag;
        }
    },NOT_IN("not_in", "not in") {
        @Override
        public boolean compare(Object leftValue, Object rightValue) {
            boolean flag = true;

            if (rightValue instanceof Collection) {
                for (Object obj : ((Collection) rightValue)) {
                    flag = !EQUAL.compare(leftValue, obj);
                    if(!flag) break;
                }
            } else if (rightValue.getClass().isArray()) {
                Object[] objs = (Object[]) rightValue;
                for (Object obj : objs) {
                    flag = !EQUAL.compare(leftValue, obj);
                    if(!flag) break;
                }
            }

            return flag;
        }
    };


    public abstract boolean compare(Object leftValue, Object rightValue);

    private static TypeConverterExecutor typeConverter = TypeConverterExecutor.getInstance();

    @Getter
    private String code;

    @Getter
    private String symbol;

    private CompareOperator(String code, String symbol){
        this.code = code;
        this.symbol = symbol;
    }

    public static CompareOperator get(String type) {
        for (CompareOperator fileType : values()) {
            if (fileType.toString().toUpperCase().equals(type.toUpperCase())) {
                return fileType;
            }
        }
        return null;
    }

//    public static void main(String[] args) throws ParseException {
//        System.out.println("===============>" + LESSER);
//        System.out.println(LESSER.compare(1,10));//true
//        System.out.println(LESSER.compare(1 + "",10));//true
//        System.out.println(LESSER.compare(9.9,10));//true
//        System.out.println(LESSER.compare(10,10));//false
//        System.out.println(LESSER.compare(10.5678,10));//false
//
//        System.out.println("===============>" + LESSER_OR_EQUAL);
//        System.out.println(LESSER_OR_EQUAL.compare(9,10));//true
//        System.out.println(LESSER_OR_EQUAL.compare(10,10));//true
//        System.out.println(LESSER_OR_EQUAL.compare(11,10));//false
//
//        System.out.println("===============>" + GREATER);
//        System.out.println(GREATER.compare(9,10));//false
//        System.out.println(GREATER.compare(10,10));//false
//        System.out.println(GREATER.compare(11,10));//true
//
//        System.out.println("===============>" + GREATER_OR_EQUAL);
//        System.out.println(GREATER_OR_EQUAL.compare(9,10));//false
//        System.out.println(GREATER_OR_EQUAL.compare(10,10));//true
//        System.out.println(GREATER_OR_EQUAL.compare(11,10));//true
//
//        System.out.println("===============>" + EQUAL);
//        System.out.println(EQUAL.compare(9,10L));//false
//        System.out.println(EQUAL.compare(10L,10));//true
//        System.out.println(EQUAL.compare(11L,10));//false
//        System.out.println(EQUAL.compare("abcd123","abcd123"));//true
//        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(EQUAL.compare(sdft.parse("2020-03-03 12:00:00"), new Timestamp(sdft.parse("2020-03-03 12:00:00").getTime())));//
//
//        System.out.println("===============>" + NOT_EQUAL);
//        System.out.println(NOT_EQUAL.compare(9,10L));//true
//        System.out.println(NOT_EQUAL.compare(10L,10));//false
//        System.out.println(NOT_EQUAL.compare(11L,10));//true
//        System.out.println(NOT_EQUAL.compare("abcd123","abcd123"));//false
//        System.out.println(NOT_EQUAL.compare(sdft.parse("2020-03-03 12:00:00"),new Timestamp(sdft.parse("2020-03-03 12:00:00").getTime())));//
//
//        System.out.println("===============>" + LIKE);
//        System.out.println(LIKE.compare(989,989));//true
//        System.out.println(LIKE.compare(989,"989"));//true
//        System.out.println(LIKE.compare("989",989));//true
//        System.out.println(LIKE.compare("989","989"));//true
//        System.out.println(LIKE.compare("989","9891"));//false
//
//        System.out.println("===============>" + NOT_LIKE);
//        System.out.println(NOT_LIKE.compare(989,989));//false
//        System.out.println(NOT_LIKE.compare(989,"989"));//false
//        System.out.println(NOT_LIKE.compare("989",989));//false
//        System.out.println(NOT_LIKE.compare("989","989"));//false
//        System.out.println(NOT_LIKE.compare("989","9891"));//true
//
//        System.out.println("===============>" + LEFT_LIKE);
//        System.out.println(LEFT_LIKE.compare(989,9));//true
//        System.out.println(LEFT_LIKE.compare(989,"8"));//false
//        System.out.println(LEFT_LIKE.compare("989",9));//true
//        System.out.println(LEFT_LIKE.compare("989","9"));//true
//
//        System.out.println("===============>" + NOT_LEFT_LIKE);
//        System.out.println(NOT_LEFT_LIKE.compare(989,9));//false
//        System.out.println(NOT_LEFT_LIKE.compare(989,"8"));//true
//        System.out.println(NOT_LEFT_LIKE.compare("989",9));//false
//        System.out.println(NOT_LEFT_LIKE.compare("989","9"));//false
//
//        System.out.println("===============>" + RIGHT_LIKE);
//        System.out.println(RIGHT_LIKE.compare(989,9));//true
//        System.out.println(RIGHT_LIKE.compare(989,"8"));//false
//        System.out.println(RIGHT_LIKE.compare("989",9));//true
//        System.out.println(RIGHT_LIKE.compare("989","9"));//true
//
//        System.out.println("===============>" + NOT_RIGHT_LIKE);
//        System.out.println(NOT_RIGHT_LIKE.compare(989,9));//false
//        System.out.println(NOT_RIGHT_LIKE.compare(989,"8"));//true
//        System.out.println(NOT_RIGHT_LIKE.compare("989",9));//false
//        System.out.println(NOT_RIGHT_LIKE.compare("989","9"));//false
//
//        System.out.println("===============>" + IN);
//        System.out.println(IN.compare(989, Lists.newArrayList(100, 999)));//false
//        System.out.println(IN.compare(989, Lists.newArrayList(989, 999)));//true
//
//        System.out.println("===============>" + NOT_IN);
//        System.out.println(NOT_IN.compare(989, Sets.newLinkedHashSet(100, 999)));//true
//        System.out.println(NOT_IN.compare(989, new String[]{"989", "999"}));//false
//    }

}

