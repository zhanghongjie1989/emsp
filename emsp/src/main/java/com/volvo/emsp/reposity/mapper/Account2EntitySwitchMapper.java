package com.volvo.emsp.reposity.mapper;


import com.volvo.emsp.domain.Account;
import com.volvo.emsp.reposity.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Account2EntitySwitchMapper {
    AccountEntity map2(Account account);
    Account map2(AccountEntity account);
}
