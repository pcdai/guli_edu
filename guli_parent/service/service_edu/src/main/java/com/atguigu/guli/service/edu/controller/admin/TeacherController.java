package com.atguigu.guli.service.edu.controller.admin;

import com.atguigu.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherVo;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-04-01
 */
@Api("讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @ApiOperation("所有讲师列表")
    @GetMapping("/list")
    public R listAll() {
        return R.ok().data("list", teacherService.list());
    }
    @ApiOperation(value = "根据ID删除讲师", notes = "根据ID删除讲师，逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(@PathVariable String id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("讲师分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页数", required = true) @PathVariable long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable long limit,
                      @ApiParam("查询对象") TeacherVo teacherVo) {
        Page<Teacher> teacherPage = new Page<>(page, limit);
        Page<Teacher> pageModel = teacherService.selectPage(teacherPage, teacherVo);
        List<Teacher> teacherList = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("rows", teacherList);
    }

    @ApiOperation("新增讲师")
    @PostMapping
    public R save(@ApiParam("讲师对象") @RequestBody Teacher teacher) {
        boolean result = teacherService.save(teacher);
        return R.ok().message("保存成功");
    }

    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R update(@ApiParam("讲师对象") @RequestBody Teacher teacher) {
        boolean result = teacherService.updateById(teacher);
        if (result) {
            return R.ok().message("更新成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id获取讲师信息")
    @GetMapping("get/{id}")
    public R getById(@ApiParam("讲师对象") @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null) {
            return R.ok().data("item", teacher);
        }
        return R.error().message("数据不存在");
    }
}

