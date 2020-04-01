package com.atguigu.guli.service.edu.service.impl;

import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherVo;
import com.atguigu.guli.service.edu.mapper.TeacherMapper;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-04-01
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Page<Teacher> selectPage(Page<Teacher> teacherPage, TeacherVo teacherVo) {
        //显示分页查询列表
            //1 排序：按照sort字段
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        // 2分页查询
        if (teacherVo==null){
            return baseMapper.selectPage(teacherPage,wrapper);
        }
        //3条件查询
        String name = teacherVo.getName();
        Integer level = teacherVo.getLevel();
        String joinDateBegin = teacherVo.getJoinDateBegin();
        String joinDateEnd = teacherVo.getJoinDateEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (level!=null){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(joinDateBegin)){
            wrapper.ge("join_date",joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)){
            wrapper.ge("join_date",joinDateBegin);
        }
        return baseMapper.selectPage(teacherPage,wrapper);
    }
}
