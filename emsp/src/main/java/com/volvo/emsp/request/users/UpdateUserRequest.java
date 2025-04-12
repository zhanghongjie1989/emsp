package com.volvo.emsp.request.users;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    @NotBlank
    @ApiModelProperty("用户ID")
    private String id;
    @NotBlank
    @ApiModelProperty("用户的itCode")
    private String itCode;
    @NotBlank
    @ApiModelProperty("用户的状态")
    private String status;
    @NotBlank
    @ApiModelProperty("用户的头像")
    private String avatar;
    @ApiModelProperty("用户的部门")
    private String department;
    @ApiModelProperty("直系领导")
    private String lineManager;
    @ApiModelProperty("platform状态")
    private String platformStatus;
    @ApiModelProperty("名称")
    private String displayName;
}
