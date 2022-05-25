package ua.kotsko.lab.services;

import ua.kotsko.lab.dto.response.TourResponse;
import ua.kotsko.lab.repositories.TourRepository;

import java.util.List;

public class TourService {
    private final TourRepository tourRepository = new TourRepository();

    public List<TourResponse> findAll() {
        return tourRepository.findAll().stream()
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
