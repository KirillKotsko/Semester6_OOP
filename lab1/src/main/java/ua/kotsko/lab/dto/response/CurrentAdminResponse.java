package ua.kotsko.lab.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrentAdminResponse {
    private Long id;
    private String username;
}
