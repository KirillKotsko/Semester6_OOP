package ua.kotsko.lab.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourEntity {
    private Long id;
    private String name;
    private Long price;
    private Long count;
}
