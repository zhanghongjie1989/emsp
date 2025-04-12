package com.volvo.emsp.request.users;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author ppliu
 * @version 1.0
 * @date 2022/10/21 11:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank
    @ApiModelProperty("用户的ItCode")
    private String itCode;
    @ApiModelProperty("用户的状态")
    private String status;
    @ApiModelProperty("用户头像")
    private String avatar;
    @ApiModelProperty("用户的部门")
    private String department;
    @ApiModelProperty("直系领导")
    private String lineManager;
    @ApiModelProperty("platform状态")
    private String platformStatus;
    @ApiModelProperty("名称")
    private String displayName;
    @ApiModelProperty("角色")
    private List<String> roleIds;
}
