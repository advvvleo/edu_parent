package com.la.eduservice.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /*
     ***mp进行修改的时候会执行改方法
     *** pram1:需要进行填充的字段名称(开启了驼峰转换），pram2：填充的值，pram3:元数据
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("进入insertFill方法");
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
        //this.setFieldValByName("version",1,metaObject);

    }
    //mp进行修改操作的时候，这个方法会执行
    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("进入updateFill方法");
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
