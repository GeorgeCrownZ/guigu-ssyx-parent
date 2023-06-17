package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    //1 角色列表（条件分页查询）
    @ApiOperation(value = "角色条件分页查询")
    @GetMapping("{current}/{limit}")
    public Result<IPage<Role>> pageList(@PathVariable("current") Long current,
                           @PathVariable("limit") Long limit,
                           RoleQueryVo roleQueryVo) {
        //1 创建page对象，传递当前页和每页记录数
        //  current：当前页
        //  limit：每页显示记录数
        Page<Role> pageParam = new Page<>(current, limit);
        //2 调用service方法实现条件分页查询
        return Result.ok(roleService.selectRolePage(pageParam, roleQueryVo));
    }

    //2 根据id查询角色
    @ApiOperation(value = "根据id查询角色")
    @GetMapping("/get/{id}")
    public Result<Role> get(@PathVariable("id") Long id) {
        return Result.ok(roleService.getById(id));
    }

    //3 添加角色
    @ApiOperation(value = "添加角色")
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody Role role) {
        if(roleService.save(role)) {
            return Result.ok();
        }
        return Result.fail();
    }

    //4 修改角色
    @ApiOperation(value = "修改角色")
    @PutMapping("/update")
    public Result<Boolean> updateById(@RequestBody Role role) {
        if(roleService.updateById(role)) {
            return Result.ok();
        }
        return Result.fail();
    }

    //5 根据id删除角色
    @ApiOperation(value = "根据id删除角色")
    @DeleteMapping("/remove/{id}")
    public Result<Boolean> remove(@PathVariable("id") Long id) {
        if(roleService.removeById(id)) {
            return Result.ok();
        }
        return Result.fail();
    }

    //6 批量删除角色
    @ApiOperation(value = "批量删除角色")
    @DeleteMapping("/batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<Long> ids) {
        if(roleService.removeByIds(ids)) {
            return Result.ok();
        }
        return Result.fail();
    }
}
