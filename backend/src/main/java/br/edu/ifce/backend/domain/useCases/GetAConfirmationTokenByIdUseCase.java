package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.ConfirmationToken;
import br.edu.ifce.backend.domain.ports.driven.ConfirmationTokenRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAConfirmationTokenById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAConfirmationTokenByIdUseCase implements GetAConfirmationTokenById {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ConfirmationToken execute(Long id) {
        return confirmationTokenRepository.findById(id);
    }
}
