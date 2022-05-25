package ua.kotsko.lab.controler;

import ua.kotsko.lab.dto.request.AddTourRequest;
import ua.kotsko.lab.dto.response.AdminResponse;
import ua.kotsko.lab.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/servlet_war_exploded/current-admin")
    public AdminResponse getCurrentAdmin() {
        return adminService.getAdmin();
    }

    @PutMapping("/servlet_war_exploded/refresh")
    public void refresh(@RequestBody AddTourRequest request) {
        adminService.refresh(request);
    }
}
