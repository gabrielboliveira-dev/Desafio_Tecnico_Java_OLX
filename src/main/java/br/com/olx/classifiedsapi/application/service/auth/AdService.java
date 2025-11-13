package br.com.olx.classifiedsapi.application.service.auth;

import br.com.olx.classifiedsapi.application.dto.ad.AdResponseDTO;
import br.com.olx.classifiedsapi.application.dto.ad.CreateAdDTO;
import br.com.olx.classifiedsapi.application.exception.ForbiddenException;
import br.com.olx.classifiedsapi.application.exception.ResourceNotFoundException;
import br.com.olx.classifiedsapi.domain.entity.Ad;
import br.com.olx.classifiedsapi.domain.entity.User;
import br.com.olx.classifiedsapi.domain.repository.AdRepository;
import br.com.olx.classifiedsapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;

    @Transactional
    public AdResponseDTO createAd(CreateAdDTO dto) {
        User currentUser = getCurrentUser();
        Ad ad = new Ad();
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setPrice(dto.getPrice());
        ad.setCategory(dto.getCategory());
        ad.setUser(currentUser);
        adRepository.save(ad);
        return mapToDTO(ad);
    }

    @Transactional
    public void deleteAd(UUID adId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new ResourceNotFoundException("Anúncio não encontrado"));
        User currentUser = getCurrentUser();
        if (!ad.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("Você não tem permissão para apagar este anúncio.");
        }
        adRepository.delete(ad);
    }

    @Transactional(readOnly = true)
    public List<AdResponseDTO> findAllAds() {
        return adRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AdResponseDTO> findMyAds() {
        User currentUser = getCurrentUser();
        return adRepository.findAllByUserId(currentUser.getId()).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilizador não encontrado no contexto."));
    }

    private AdResponseDTO mapToDTO(Ad ad) {
        AdResponseDTO dto = new AdResponseDTO();
        dto.setId(ad.getId());
        dto.setTitle(ad.getTitle());
        dto.setDescription(ad.getDescription());
        dto.setPrice(ad.getPrice());
        dto.setCategory(ad.getCategory());
        dto.setOwnerId(ad.getUser().getId());
        return dto;
    }
}