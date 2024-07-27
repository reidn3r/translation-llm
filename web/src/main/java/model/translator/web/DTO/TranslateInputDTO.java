package model.translator.web.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TranslateInputDTO(
    @NotNull @NotBlank String input_language,
    @NotNull @NotBlank String output_language,
    @NotNull @NotBlank String text
){}
