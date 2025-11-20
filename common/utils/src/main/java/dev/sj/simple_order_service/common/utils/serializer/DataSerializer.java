package dev.sj.simple_order_service.common.utils.serializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSerializer {
    private static final ObjectMapper objectMapper = initialize();

    private static ObjectMapper initialize() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule()) // java 8 의 시간/날짜 LocalDateTime 등의 처리 지원
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) // 알 수 없는 JSON 필드를 무시하고 역직렬화 성공
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // 날짜를 ISO 8601 문자열로 직렬화
    }

    public static <T> T deserialize(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("### DataSerializer > deserialize : data={}, clazz={}", json, clazz, e);
            return null;
        }
    }

    public static <T> T deserialize(Object object, Class<T> clazz) {
        return objectMapper.convertValue(object, clazz);
    }

    public static String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("### DataSerializer > serialize : object={}", object, e);
            return null;
        }
    }
}
