package ua.kotsko.lab.services;

import ua.kotsko.lab.dto.response.ClientResponse;
import ua.kotsko.lab.entity.Client;
import ua.kotsko.lab.entity.Tour;
import ua.kotsko.lab.dto.request.OrderTourRequest;
import ua.kotsko.lab.repository.ClientRepository;
import ua.kotsko.lab.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final AuthorizationService authorizationService;
    private final ClientRepository clientRepository;
    private final TourRepository tourRepository;

    public List<ClientResponse> getAll() {
        return clientRepository.findAll().stream()
                .map(client ->
                        ClientResponse.builder()
                                .id(client.getId())
                                .username(client.getUsername())
                                .amount(client.getAmount())
                                .build())
                .toList();
    }

    public ClientResponse getClient() {
        Client client = authorizationService.getCurrentClient();
        return ClientResponse.builder()
                .id(client.getId())
                .username(client.getUsername())
                .amount(client.getAmount())
                .build();
    }

    public void order(OrderTourRequest request) {
        Client client = authorizationService.getCurrentClient();
        Tour tourEntity = tourRepository.getById(request.getId());
        tourEntity.setCount(tourEntity.getCount() - 1);
        client.setAmount(client.getAmount() - tourEntity.getPrice());
        tourRepository.save(tourEntity);
        clientRepository.save(client);
    }
}
