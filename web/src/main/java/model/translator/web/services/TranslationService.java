package model.translator.web.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.translator.web.DTO.ModelResponseDTO;
import model.translator.web.DTO.TranslateInputDTO;
import model.translator.web.infra.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TranslationService {

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private ObjectMapper objectMapper;

    public ModelResponseDTO translate(TranslateInputDTO data) throws Exception {
        String input = objectMapper.writeValueAsString(data);
        String key = "input::queue::" + Instant.now().toString();

        redisManager.setData(input, key);
        redisManager.publishOnQueue(key, "input_text::queue");

        String output = redisManager.consumeQueue("output_text::queue" + key);
        return  objectMapper.readValue(output, ModelResponseDTO.class);
    }
}
