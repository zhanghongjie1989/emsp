package com.volvo.emsp.domain.mapper;


import com.volvo.emsp.domain.Account;
import com.volvo.emsp.domain.Card;
import com.volvo.emsp.reposity.entity.AccountEntity;
import com.volvo.emsp.request.AccountRequest;
import com.volvo.emsp.request.CardRequest;
import com.volvo.emsp.response.AccountResponse;
import com.volvo.emsp.response.CardResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardSwitchMapper {



    Card map2(CardRequest request);


    CardResponse map2(Card card);

    Page<CardResponse> map2(List<Card> cardsByLastUpdated);
}
