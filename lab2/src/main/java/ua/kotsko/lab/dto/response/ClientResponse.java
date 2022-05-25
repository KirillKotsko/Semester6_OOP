package ua.kotsko.lab.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponse {
    private Long id;
    private String username;
    private Long amount;
}
