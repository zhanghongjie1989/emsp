package com.volvo.emsp.reposity.mapper;


import com.volvo.emsp.domain.Account;
import com.volvo.emsp.domain.Card;
import com.volvo.emsp.reposity.entity.AccountEntity;
import com.volvo.emsp.reposity.entity.CardEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface Card2EntitySwitchMapper {

    CardEntity map2(Card card);

    Card map2(CardEntity card);

    Card map2(Optional<CardEntity> byUid);

    List<Card> map2(List<CardEntity> byUpdatedAt);
}
