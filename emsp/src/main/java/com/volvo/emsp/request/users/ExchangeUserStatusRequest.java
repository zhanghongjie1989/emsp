package com.volvo.emsp.request.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
@ApiModel("变更用户状态请求体")
public class ExchangeUserStatusRequest {
    @NotBlank
    @ApiModelProperty("用户的ID")
    private String id;

    @NotBlank
    @ApiModelProperty("用户的状态")
    private String status;
}
