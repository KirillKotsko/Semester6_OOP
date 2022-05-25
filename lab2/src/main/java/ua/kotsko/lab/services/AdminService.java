package ua.kotsko.lab.services;

import ua.kotsko.lab.dto.request.AddTourRequest;
import ua.kotsko.lab.dto.response.AdminResponse;
import ua.kotsko.lab.entity.Admin;
import ua.kotsko.lab.entity.Tour;
import ua.kotsko.lab.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AuthorizationService authorizationService;
    private final TourRepository tourRepository;

    public AdminResponse getAdmin() {
        Admin adminEntity = authorizationService.getCurrentAdmin();
        return AdminResponse.builder()
                .id(adminEntity.getId())
                .username(adminEntity.getUsername())
                .build();
    }

    @SneakyThrows
    public void refresh(AddTourRequest request) {
        Tour tourEntity = tourRepository.getById(request.getId());
        tourEntity.setCount(tourEntity.getCount() + 1);
        tourRepository.save(tourEntity);
    }
}
