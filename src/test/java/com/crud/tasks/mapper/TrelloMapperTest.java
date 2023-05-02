package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloMapperTest {

    @Test
    public void trelloMapperToDtoTest() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));

        TrelloCard trelloCard = new TrelloCard("Card name", "testing card", "top", "455");

        //When
        List<TrelloListDto> mappedTrelloListsDto = trelloMapper.mapToListDto(trelloLists);
        List<TrelloBoardDto> mappedTrelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        TrelloCardDto mappedTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(mappedTrelloListsDto.get(0).getName(), "test_list");
        assertEquals(mappedTrelloBoardsDto.get(0).getLists().get(0).isClosed(), false);
        assertEquals(mappedTrelloBoardsDto.get(0).getName(), "test");
        assertEquals(mappedTrelloCardDto.getDescription(), "testing card");
    }

    @Test
    public void trelloMapperFromDtoTest() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("1", "test", trelloListsDto));

        TrelloCardDto trelloCardDto = new TrelloCardDto("Card name", "testing card", "top", "455");

        //When
        List<TrelloList> mappedTrelloLists = trelloMapper.mapToList(trelloListsDto);
        List<TrelloBoard> mappedTrelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        TrelloCard mappedTrelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(mappedTrelloLists.get(0).getName(), "test_list");
        assertEquals(mappedTrelloBoards.get(0).getLists().get(0).isClosed(), false);
        assertEquals(mappedTrelloBoards.get(0).getName(), "test");
        assertEquals(mappedTrelloCard.getDescription(), "testing card");
    }

}