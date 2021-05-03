package com.la.eduservice.service.impl;

import com.la.commonutils.R;
import com.la.eduservice.entity.EduTeacher;
import com.la.eduservice.mapper.EduTeacherMapper;
import com.la.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author LA
 * @since 2021-04-20
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    public R insertV(){
        return R.ok();
    }

}
