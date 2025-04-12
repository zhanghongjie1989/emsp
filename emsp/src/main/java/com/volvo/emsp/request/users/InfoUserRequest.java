package com.volvo.emsp.request.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("查询用户详情请求体")
public class InfoUserRequest {
    @NotBlank
    @Length(min = 1)
    @ApiModelProperty("用户的ID")
    private String id;
}
