package model.translator.web.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import model.translator.web.DTO.ModelResponseDTO;
import model.translator.web.DTO.TranslateInputDTO;
import model.translator.web.services.TranslationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/v1")
public class TranslatorController {

    @Autowired
    private TranslationService translation;

    @PostMapping("/translate")
    public ResponseEntity<ModelResponseDTO> translate(@Valid @RequestBody TranslateInputDTO data) throws Exception {
        try {
            ModelResponseDTO output = this.translation.translate(data);
            return ResponseEntity.ok().body(output);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
