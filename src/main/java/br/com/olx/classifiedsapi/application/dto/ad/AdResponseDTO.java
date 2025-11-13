package br.com.olx.classifiedsapi.application.dto.ad;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AdResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private String category;
    private BigDecimal price;
    private UUID ownerId;
}