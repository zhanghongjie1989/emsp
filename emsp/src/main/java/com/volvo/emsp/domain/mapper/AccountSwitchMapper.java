package com.volvo.emsp.domain.mapper;


import com.volvo.emsp.domain.Account;
import com.volvo.emsp.reposity.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountSwitchMapper {


    Account map2(AccountEntity save);
}
