package com.dftdlaMp.util;

/**
 * @author 14501
 */
public class BaseContext {

    private static ThreadLocal<Long> id=new ThreadLocal<>();

    public static void setId(Long id){
        BaseContext.id.set(id);
    }

    public static Long getId() {
        return id.get();
    }

}
