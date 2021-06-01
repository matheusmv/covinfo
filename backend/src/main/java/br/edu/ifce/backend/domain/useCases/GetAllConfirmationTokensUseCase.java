package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.ConfirmationToken;
import br.edu.ifce.backend.domain.ports.driven.ConfirmationTokenRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAllConfirmationTokens;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllConfirmationTokensUseCase implements GetAllConfirmationTokens {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public List<ConfirmationToken> execute() {
        return confirmationTokenRepository.listAll();
    }
}
