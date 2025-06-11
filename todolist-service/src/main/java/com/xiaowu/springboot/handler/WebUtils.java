package com.xiaowu.springboot.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/12/24 20:30
 */
public class WebUtils {

    public static String renderString(HttpServletResponse res, String str) {

        try{
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json;charset=UTF-8");
            res.setStatus(200);
            res.getWriter().print(str);
        }
        catch(Exception e){
            e.printStackTrace();
        }
            return null;
    }
}
