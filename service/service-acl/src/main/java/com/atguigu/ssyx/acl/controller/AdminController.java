package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.common.util.MD5;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;

    //1 用户列表
    @ApiOperation(value = "用户列表")
    @GetMapping("/{current}/{limit}")
    public Result<IPage<Admin>> list(@PathVariable Long current,
                              @PathVariable Long limit,
                              AdminQueryVo adminQueryVo) {
        Page<Admin> pageParam = new Page<>(current, limit);
        return Result.ok(adminService.selectPageUser(pageParam, adminQueryVo));
    }

    //2 id查询用户
    @ApiOperation(value = "根据id查询")
    @GetMapping("/get/{id}")
    public Result<Admin> getById(@PathVariable Long id) {
        return Result.ok(adminService.getById(id));
    }

    //3 添加用户
    @ApiOperation(value = "添加用户")
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody Admin admin) {
        admin.setPassword(MD5.encrypt(admin.getPassword()));
        return Result.ok(adminService.save(admin));
    }

    //4 修改用户
    @ApiOperation(value = "修改用户")
    @PutMapping("/update")
    public Result<Boolean> updateById(@RequestBody Admin admin) {
        return Result.ok(adminService.updateById(admin));
    }

    //5 删除用户
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.ok(adminService.removeById(id));
    }

    //6 批量删除用户
    @ApiOperation(value = "批量删除用户")
    @DeleteMapping("/batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<Long> ids) {
        return Result.ok(adminService.removeByIds(ids));
    }
}
