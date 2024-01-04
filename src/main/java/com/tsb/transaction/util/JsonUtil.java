package com.tsb.transaction.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.tsb.transaction.exception.CustomException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Throwable throwable) {
            log.info("Error in JsonUtil class in method toJson: {}", throwable.getMessage());
            throw new CustomException("451", "Something went wrong");
        }
    }

    public static <T> List<T> toObjectOfList(String json, TypeReference<List<T>> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (Exception ex) {
            log.info("Error in JsonUtil class in method toObject: {}", ex.getMessage());
            throw new CustomException("451", "Something went wrong");
        }


    }

    public static <T> T toObject(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (Throwable throwable) {
            log.info("Error in JsonUtil class in method toObject: {}", throwable.getMessage());
            throw new CustomException("451", "Something went wrong");
        }


    }


//    public static <T> T toObjectFromFile(Class<T> classType,ConsentType consentType){
//
//        try {
//            String path = "";
//            if (classType ==  UserInfoRequest.class) {
//                path = "mock/json/userInfo/UserInfoRequestMock.json";
//            }
//            if (classType ==   UserInfoResponse.class) {
//                path = "mock/json/userInfo/UserInfoSuccessResponse.json";
//            }
//            if (classType ==   UserInfoError.class) {
//                path = "mock/json/userInfo/UserInfoErrorResponseMock.json";
//            }
//
//            if (classType ==  ConsentResponse.class){
//
//                if (consentType == ConsentType.CONSENT_READ_consents){
//                    path = "mock/json/consent/response/READ_consents.json";
//                }
//                if (consentType == ConsentType.CONSENT_READ_a_consent){
//                    path = "mock/json/consent/response/READ_a_consent.json";
//                }
//
////                if (consentType == ConsentType.CONSENT_DELETE_a_consent){
////                    path = "mock/json/consent/DELETE_a_consent.json";
////                }
//
//            }
//
//            if (classType ==  ConsentResponsePostPutPatch.class){
//                if (consentType == ConsentType.CONSENT_CREATE_a_consent){
//                    path = "mock/json/consent/response/CREATE_a_consent.json";
//                }
//                if (consentType == ConsentType.CONSENT_REPLACE_a_consent){
//                    path = "mock/json/consent/response/REPLACE_a_consent.json";
//                }
//                if (consentType == ConsentType.CONSENT_UPDATE_a_consent){
//                    path = "mock/json/consent/response/UPDATE_a_consent.json";
//                }
//
//            }
//
//            if (classType == DefinitionsResponse.class){
//                if (consentType == ConsentType.DEFINITIONS_READ_definitions){
//                    path = "mock/json/definitions/READ_definitions.json";
//                }
//
//                if (consentType == ConsentType.DEFINITIONS_READ_a_definition){
//                    path = "mock/json/definitions/READ_a_definition.json";
//                }
//
//                if (consentType == ConsentType.DEFINITIONS_READ_localizations){
//                    path = "mock/json/definitions/READ_localizations.json";
//                }
//                if (consentType == ConsentType.DEFINITIONS_READ_a_localization){
//                    path = "mock/json/definitions/READ_a_localization.json";
//                }
//
//            }
//
//            if (classType == ConsentRequest.class){
//                path = "mock/json/consent/request/Consent_Request.json";
//            }
//
//
//            URL url = BoilerApplication.class.getClassLoader().getResource(path);
//            return objectMapper.readValue(new File(url.getFile()), classType);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
