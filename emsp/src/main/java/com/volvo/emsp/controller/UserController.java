package com.volvo.emsp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public CommonResponse<CreateUserResult> createUser(@RequestBody @@Validated CreateUserRequest request) {
        return CommonResponse.success(userService.createUser(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }


    @Operation(summary = "查询用户信息")
    @PostMapping("/info")
    @Auth(value = "/users/info")
    public CommonResponse<UserDetailResult> infoUser(@RequestBody @Valid InfoUserRequest request) {
        return CommonResponse.success(userService.infoUser(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }

    @Operation(summary = "更新用户")
    @PostMapping("/update")
    public CommonResponse<UpdateUserResult> updateUser(@RequestBody @Valid UpdateUserRequest request) {
        return CommonResponse.success(userService.updateUser(userSwitchRequestCommandDtoMapper.mapTo(request)));
    }



}
