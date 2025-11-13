package br.com.olx.classifiedsapi.application.dto.ad;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAdDTO {
    @NotBlank(message = "O título não pode estar em branco")
    private String title;

    private String description;

    @NotBlank(message = "A categoria não pode estar em branco")
    private String category;

    @NotNull(message = "O preço não pode ser nulo")
    @Positive(message = "O preço deve ser positivo")
    @DecimalMin(value = "0.01", message = "O preço deve ser no mínimo 0.01")
    private BigDecimal price;
}