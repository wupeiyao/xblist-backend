package com.xiaowu.springboot.annotation;



import com.xiaowu.springboot.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 公共字段自动填充
 * @author: xiaowu
 * @time: 2024/6/7 10:18
  */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    //数据库操作类型： update,insert
    OperationType value();
}
