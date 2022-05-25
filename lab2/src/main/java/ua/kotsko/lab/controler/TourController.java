package ua.kotsko.lab.controler;

import ua.kotsko.lab.dto.response.TourResponse;
import ua.kotsko.lab.services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;

    @GetMapping("/servlet_war_exploded/tour")
    public List<TourResponse> getCurrentAdmin() {
        return tourService.findAll();
    }
}
