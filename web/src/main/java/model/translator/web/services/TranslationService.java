package model.translator.web.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.translator.web.DTO.ModelResponseDTO;
import model.translator.web.DTO.TranslateInputDTO;
import model.translator.web.infra.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private ObjectMapper objectMapper;

    public ModelResponseDTO translate(TranslateInputDTO data) throws Exception {
        System.out.println("translate data: " + data);
        String data2string = objectMapper.writeValueAsString(data);


        System.out.println("translate data string: " + data2string);
        redisManager.publishOnQueue(data2string, "input_text::queue");
        String output = redisManager.consumeQueue("output_text::queue");
        System.out.println("translate redis output: " + output);

        return  objectMapper.readValue(output, ModelResponseDTO.class);
    }
}
