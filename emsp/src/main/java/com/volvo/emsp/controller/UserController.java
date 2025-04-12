package com.volvo.emsp.controller;

import com.volvo.application.mapper.UserSwitchRequestCommandDtoMapper;
import com.lenovo.iop.user.auth.application.result.users.*;
import com.volvo.application.result.users.*;
import com.volvo.application.service.UserService;
import com.lenovo.iop.user.auth.common.Auth;
import com.volvo.common.util.CookieUtil;
import com.lenovo.iop.user.auth.infrastructure.client.request.QueryAllUsersRequest;
import com.lenovo.iop.user.auth.infrastructure.client.request.QueryByEmailRequest;
import com.lenovo.iop.user.auth.infrastructure.client.result.SystemUserMasterDataResult;
import com.lenovo.iop.user.auth.representation.request.users.*;
import com.volvo.representation.request.users.*;
import com.volvo.representation.response.CommonResponse;
import com.volvo.common.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    private final UserSwitchRequestCommandDtoMapper userSwitchRequestCommandDtoMapper;

    @Operation(summary = "新增用户")
    @PostMapping("/create")
    @Auth(value = "/users/create")
    public CommonResponse<CreateUserResult> createUser(@RequestBody @Valid CreateUserRequest request) {
        return CommonResponse.success(userService.createUser(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }
    @PostMapping("/queryAllUsers")
    public CommonResponse<List<SystemUserMasterDataResult>> queryAllUsers(@RequestBody QueryAllUsersRequest request) {
        return CommonResponse.success(userService.queryAllUsers(request));
    }
    @PostMapping("/queryUserByEmail")
    public CommonResponse<List<SystemUserMasterDataResult>> queryUserByEmail(@RequestBody QueryByEmailRequest request) {
        return CommonResponse.success(userService.queryUserByEmail(request));
    }

    /*@Operation(summary = "批量新增用户")
    @PostMapping("/batch-create")
    @Auth(value = "/users/batch-create")
    public CommonResponse<BatchCreateUserResult> batchCreateUser(@RequestBody BatchCreateUserRequest request) {
        return CommonResponse.success(userService.batchCreateUser(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }*/

    @Operation(summary = "查询用户信息")
    @PostMapping("/info")
    @Auth(value = "/users/info")
    public CommonResponse<UserDetailResult> infoUser(@RequestBody @Valid InfoUserRequest request) {
        return CommonResponse.success(userService.infoUser(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }

    @Operation(summary = "更新用户")
    @PostMapping("/update")
//    @Auth(value = "/users/update")
    public CommonResponse<UpdateUserResult> updateUser(@RequestBody @Valid UpdateUserRequest request) {
        return CommonResponse.success(userService.updateUser(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }

    @Operation(summary = "删除用户")
    @PostMapping("/delete")
    @Auth(value = "/users/delete")
    public CommonResponse<DeleteUserResult> deleteUser(@RequestBody @Valid DeleteUserRequest request) {
        return CommonResponse.success(userService.deleteUser(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }

    @Operation(summary = "查询用户列表")
    @PostMapping("/list")
    @Auth(value = "/users/list")
    public CommonResponse<PageResult<UserDetailResult>> queryUsers(@RequestBody QueryUserRequest request) {
        return CommonResponse.success(userService.queryUsers(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }
    @Operation(summary = "查询admin用户全量",description = "后端调用")
    @PostMapping("/queryAdminBySchema")
    public CommonResponse<List<UserDetailResult>> queryAdminBySchema(@RequestBody QueryAdminRequest request) {
        return CommonResponse.success(userService.queryAdminUsers(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }

    @Operation(summary = "给用户绑定角色")
    @PostMapping("/bind-roles")
    @Auth(value = "/users/bind-roles")
    public CommonResponse<BindRoleResult> bindRoles(@RequestBody @Valid BindRoleRequest request) {
        return CommonResponse.success(userService.bindRole(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }

    @Operation(summary = "给用户解绑角色")
    @PostMapping("/unbind-roles")
//    @Auth(value = "/users/unbind-roles")
    public CommonResponse<UnBindRoleResult> unbindRoles(@RequestBody @Valid UnBindRoleRequest request) {
        return CommonResponse.success(userService.unBindRoles(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }

    @Operation(summary = "查询当前用户的所有权限")
    @PostMapping("/permissions")
    public CommonResponse<UserDetailResult> queryPermissions(@RequestBody @Valid QueryUserPermissionsRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        CookieUtil.updateCookieAge(httpServletRequest, httpServletResponse);
        return CommonResponse.success(userService.queryUserByOperatorId(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }

    @Operation(summary = "变更用户状态")
    @PostMapping("/status-update")
    @Auth(value = "/users/status-update")
    public CommonResponse<ExchangeUserStatusResult> exchangeStatus(@RequestBody @Valid ExchangeUserStatusRequest request) {
        return CommonResponse.success(userService.exchangeStatus(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }

    @Operation(summary = "变更用户platform状态")
    @PostMapping("/platform-status-edit")
    @Auth(value = "/users/platform-status-edit")
    public CommonResponse<ExchangeUserPlatformStatusResult> exchangePlatformStatus(@RequestBody @Valid ExchangeUserPlatformStatusRequest request) {
        return CommonResponse.success(userService.exchangePlatformStatus(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }

    @Operation(summary = "模板解析")
    @PostMapping("/upload")
    public CommonResponse<List<SystemUserMasterDataResult>> uploadReceiver(@RequestPart("file") MultipartFile file) {
        return CommonResponse.success(userService.uploadReceiver(file));
    }

    @Operation(summary = "批量新增用户")
    @PostMapping("/batch-create")
    @Auth(value = "/users/batch-create")
    public CommonResponse<String> createUserbatch(@RequestBody @Valid List<CreateUserRequest> request) {
        userService.createUserBatch(userSwitchRequestCommandDtoMapper.mapToCommandList(request));
        return CommonResponse.success("SUCCESS");
    }


}
