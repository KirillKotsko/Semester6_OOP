package ua.kotsko.lab.services;

import ua.kotsko.lab.dto.response.TourResponse;
import ua.kotsko.lab.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;

    public List<TourResponse> findAll() {
        return tourRepository.findByOrderById().stream()
                .map(entity ->
                        TourResponse.builder()
                                .id(entity.getId())
                                .name(entity.getName())
                                .price(entity.getPrice())
                                .count(entity.getCount())
                                .build())
                .toList();
    }
}
