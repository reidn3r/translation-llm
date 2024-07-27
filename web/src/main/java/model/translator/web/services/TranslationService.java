package model.translator.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.translator.web.DTO.ContainerResponseDTO;
import model.translator.web.DTO.TranslateInputDTO;
import model.translator.web.infra.DockerManager;

@Service
public class TranslationService {

    @Autowired
    private DockerManager dockerManager;

    public ContainerResponseDTO translate(TranslateInputDTO data) throws Exception {
        String input_lang = data.input_language();
        String output_lang = data.output_language();
        String text = data.text();

        return dockerManager.run(input_lang, output_lang, text);
    }
}
