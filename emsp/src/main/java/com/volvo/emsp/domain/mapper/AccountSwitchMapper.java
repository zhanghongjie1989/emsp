package com.volvo.emsp.domain.mapper;

import com.volvo.emsp.domain.Account;
import com.volvo.emsp.controller.request.AccountRequest;
import com.volvo.emsp.controller.response.AccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountSwitchMapper {


    Account map2(AccountRequest request);

    AccountResponse map2(Account account);

    List<AccountResponse> map2(List<Account> accountsByLastUpdated);
}
