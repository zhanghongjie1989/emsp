package com.volvo.emsp.request.users;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequest {
    @NotBlank
    @ApiModelProperty("用户的ID")
    private String id;
}
