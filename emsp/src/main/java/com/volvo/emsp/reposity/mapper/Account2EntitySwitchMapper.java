package com.volvo.emsp.reposity.mapper;


import com.volvo.emsp.domain.Account;
import com.volvo.emsp.reposity.entity.AccountEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Account2EntitySwitchMapper {
    AccountEntity map2(Account account);
    Account map2(AccountEntity account);
    List<Account> map2(List<AccountEntity> accountEntities);
}
