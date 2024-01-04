package com.tsb.transaction.dto;



import com.tsb.transaction.exception.CustomException;
import com.tsb.transaction.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObjectDtoMapper {

    private static final Logger log = LogManager.getLogger(ObjectDtoMapper.class);
    private static ModelMapper modelMapper;

    @Autowired
    private ObjectDtoMapper(ModelMapper modelMapper) {
        ObjectDtoMapper.modelMapper = modelMapper;
    }


    public static <T> T dtoMapper(Object object, Class<T> type) {
        String json = JsonUtil.toJson(object);

        try {
            return JsonUtil.toObject(json, type);
        } catch (Exception ex) {
            log.info("Error in ObjectDtoMapper class in method dtoMapper: {}", ex.getMessage());
            throw new CustomException("451", "Something went wrong");
        }
    }

    public static <S, T> List<T> listMapper(List<S> source, Class<T> targetClass) {
        try {
            return source
                    .stream()
                    .map(element -> modelMapper.map(element, targetClass))
                    .toList();
        } catch (Exception ex) {
            log.info("Error in ObjectDtoMapper class in method listMapper: {}", ex.getMessage());
            throw new CustomException("451", "Something went wrong");
        }
    }

}
