package com.volvo.emsp.request.users;

import com.volvo.common.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("查询用户列表请求体")
public class QueryUserRequest extends BasePageQuery {
    @ApiModelProperty("用户ITCode")
    private String itCode;
    @ApiModelProperty("用户状态")
    private List<String> status;
    @ApiModelProperty("用户角色")
    private List<String> role;
    @ApiModelProperty("创建开始时间")
    private String createStartTime;    //时间戳
    @ApiModelProperty("创建结束时间")
    private String createEndTime;    //时间戳
}
