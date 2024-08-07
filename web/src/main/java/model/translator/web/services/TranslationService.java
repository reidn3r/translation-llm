package model.translator.web.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.translator.web.DTO.ModelResponseDTO;
import model.translator.web.DTO.TranslateInputDTO;
import model.translator.web.infra.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class TranslationService {

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private ObjectMapper objectMapper;

    public ModelResponseDTO translate(TranslateInputDTO data) throws Exception {
        String input = objectMapper.writeValueAsString(data);
        String key = "input::id::" + Instant.now().toString();

        redisManager.setData(input, key);
        redisManager.publishOnQueue(key, "input_id::queue");

        CompletableFuture<String> LLMResponse = await(key);
        String LLMOuput = LLMResponse.get(60*5, TimeUnit.SECONDS);
        return  objectMapper.readValue(LLMOuput, ModelResponseDTO.class);
    }

    @Async
    public CompletableFuture<String> await(String key){
        return CompletableFuture.supplyAsync(() -> {
            String data = null;
            while (true) {
                data = redisManager.getData(key + "::output");
                if (data != null) {
                    return data;
                }
                try {
                    Thread.sleep(1000); // Polling interval
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Polling interrupted", e);
                }
            }
        });
    }



}
