package com.volvo.emsp.domain.mapper;


import com.volvo.emsp.domain.Card;
import com.volvo.emsp.controller.request.CardRequest;
import com.volvo.emsp.controller.response.CardResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardSwitchMapper {



    Card map2(CardRequest request);


    CardResponse map2(Card card);

    List<CardResponse> map2(List<Card> cardsByLastUpdated);
}
