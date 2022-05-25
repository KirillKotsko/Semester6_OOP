package ua.kotsko.lab.configuration;

import ua.kotsko.lab.repository.ClientRepository;
import ua.kotsko.lab.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AllUsersRoleDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<? extends UserDetails> client = adminRepository.findByUsername(username);
        if (client.isPresent()) {
            return client.get();
        }
        Optional<? extends UserDetails> superAdmin = clientRepository.findByUsername(username);
        if (superAdmin.isPresent()) {
            return superAdmin.get();
        }
        throw new RuntimeException();
    }
}
