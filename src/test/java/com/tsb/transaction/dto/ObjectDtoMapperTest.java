package com.tsb.transaction.dto;


import com.tsb.transaction.dto.transactiondto.TransactionResponseDto;
import com.tsb.transaction.model.transaction.Data;
import com.tsb.transaction.model.transaction.TransactionResponse;
import com.tsb.transaction.util.JsonUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockitoExtension.class)
class ObjectDtoMapperTest {

    @Mock
    ObjectDtoMapper objectDtoMapper;
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void dtoMapper(){
        TransactionResponseDto respObj = new TransactionResponseDto();
        TransactionResponse transactionObj= new TransactionResponse(Data.builder().build());
        try(MockedStatic<JsonUtil> utilities = Mockito.mockStatic(JsonUtil.class)){
           utilities.when(() -> JsonUtil.toObject(JsonUtil.toJson(transactionObj), TransactionResponseDto.class)).thenReturn(respObj);
        }
        assertInstanceOf(TransactionResponseDto.class,JsonUtil.toObject(JsonUtil.toJson(transactionObj), TransactionResponseDto.class));

    }

    @Test
    void listMapper() {
        List<TransactionResponseDto> respObj = new ArrayList<>();
        TransactionResponse transactionObj= new TransactionResponse(Data.builder().build());
        List<TransactionResponse> accountObjList = Arrays.asList(transactionObj);
        respObj=accountObjList.stream()
                .map(e -> modelMapper.map(e, TransactionResponseDto.class)).toList();

        assertInstanceOf(TransactionResponseDto.class, respObj.get(0));


    }

    @Test
    void dtoMapper_test() throws Exception{
        TransactionResponseDto respObj = new TransactionResponseDto();
        TransactionResponse transactionObj= new TransactionResponse(Data.builder().build());
        try(MockedStatic<ObjectDtoMapper> utilities = Mockito.mockStatic(ObjectDtoMapper.class)){
            utilities.when(() -> ObjectDtoMapper.dtoMapper(JsonUtil.toJson(transactionObj), TransactionResponseDto.class)).thenReturn(respObj);
            assertInstanceOf(TransactionResponseDto.class,ObjectDtoMapper.dtoMapper(JsonUtil.toJson(transactionObj), TransactionResponseDto.class));
        }


    }
    @Test
    void listMapper_test() {
        List<TransactionResponseDto> respObj = new ArrayList<>();
        TransactionResponse transactionObj= new TransactionResponse(Data.builder().build());
        List<TransactionResponse> transactionObjList = Arrays.asList(transactionObj);
        try(MockedStatic<ObjectDtoMapper> utilities = Mockito.mockStatic(ObjectDtoMapper.class)) {
            utilities.when(() ->ObjectDtoMapper.listMapper(transactionObjList, TransactionResponseDto.class)).thenReturn(respObj);

        }

    }
}
