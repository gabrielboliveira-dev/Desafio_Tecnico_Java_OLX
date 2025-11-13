package br.com.olx.classifiedsapi.presentation.controller;

import br.com.olx.classifiedsapi.application.dto.ad.AdResponseDTO;
import br.com.olx.classifiedsapi.application.dto.ad.CreateAdDTO;
import br.com.olx.classifiedsapi.application.service.auth.AdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    @PostMapping
    public ResponseEntity<AdResponseDTO> createAd(@Valid @RequestBody CreateAdDTO dto) {
        AdResponseDTO response = adService.createAd(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/my")
    public ResponseEntity<List<AdResponseDTO>> getMyAds() {
        return ResponseEntity.ok(adService.findMyAds());
    }

    @GetMapping
    public ResponseEntity<List<AdResponseDTO>> getAllAds() {
        return ResponseEntity.ok(adService.findAllAds());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable UUID id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }
}