package com.la.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.la.commonutils.R;
import com.la.eduservice.entity.EduTeacher;
import com.la.eduservice.entity.vo.TeacherQuery;
import com.la.eduservice.exceptionhandler.ExceptionEntity;
import com.la.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author LA
 * @since 2021-04-20
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/eduteacher")
@CrossOrigin
public class EduTeacherController {
    //注入service对象
    @Autowired
    private EduTeacherService teacherService;
    //1. 查询讲师
    @GetMapping("findAll")
    @ApiOperation(value = "所有讲师列表")
    public R findAllTeachers(){
        final List<EduTeacher> teachers = teacherService.list(null);
        try {
            int a = 10/0;
        }
        catch(Exception e) {
            throw new ExceptionEntity(20001,"出现自定义异常");
        }
        return R.ok().data("item",teachers);
    }
    //2. 逻辑删除
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        if(teacherService.removeById(id))
            return R.ok();
        else
            return R.error();
    }
    //3. 分页查询讲师
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacger/{current}/{size}")//通过路径传值，传入两个值
    public R pageTeacger(@PathVariable long current,@PathVariable long size){
        Page<EduTeacher> teachers = new Page<>(current, size);
        //将查询到的所有集合压入到teachers
        teacherService.page(teachers,null);
        //总页数
        long total = teachers.getTotal();
        //得到记录
        List<EduTeacher> records = teachers.getRecords();
        //将total和records都存到R对象中
        return R.ok().data("total",total).data("rows",records);
    }
    //4. 多条件组合查询 @RequestBody(required = false) 只能用于Post数据，required = false代表这个值可以没有
    @ApiOperation(value = "分页讲师条件查询结果")
    @PostMapping("pageTeacherCondition/{current}/{size}")
    public  R pageTeacherCondition(@PathVariable long current, @PathVariable long size,
                                   @RequestBody(required = false)TeacherQuery teacherQuery){
        //1. 得到page对象
        Page<EduTeacher> teachers = new Page<>(current, size);
        //2. 构建查询器
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        //3. 得到查询条件的各个条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //4. 判断条件
        if (!ObjectUtils.isEmpty(name)) {
            teacherQueryWrapper.like("name", name);
        }
        if (!ObjectUtils.isEmpty(level) ) {
            teacherQueryWrapper.eq("level", level);
        }
        if (!ObjectUtils.isEmpty(begin)) {
            teacherQueryWrapper.ge("gmt_create", begin);
        }
        if (!ObjectUtils.isEmpty(end)) {
            teacherQueryWrapper.le("gmt_create", end);
        }
        //实现按照修改时间排序
        teacherQueryWrapper.orderByDesc("gmt_modified");
        //5. 将查询到的所有集合压入到teachers
        teacherService.page(teachers,teacherQueryWrapper);
        //6. 总页数
        long total = teachers.getTotal();
        //7. 得到记录
        List<EduTeacher> records = teachers.getRecords();
        //8. 将total和records都存到R对象中
        return R.ok().data("total",total).data("rows",records);
    }
    //5. 添加讲师
    @ApiOperation(value = "新增讲师")
    @PostMapping("save")
    public R save(@ApiParam(name = "teacher", value = "讲师对象", required = true)
        @RequestBody EduTeacher teacher){
        final boolean save = teacherService.save(teacher);
        if(save)
            return R.ok();
        else
            return R.error();
    }
    //6. 根据id查询讲师
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacherbyId(@ApiParam(name = "id", value = "讲师ID", required = true)
        @PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }
    //7. 根据id修改
    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("updateTeacher")
    public R updateById(@ApiParam(name = "teacher", value = "讲师对象", required = true) @RequestBody EduTeacher teacher){
        if(teacherService.updateById(teacher))
            return R.ok();
        else
            return R.error();
    }
}

