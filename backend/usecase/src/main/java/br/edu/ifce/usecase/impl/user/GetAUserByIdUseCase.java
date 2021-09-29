package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.usecase.ports.driven.AddressRepository;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.GetAUserById;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetAUserByIdUseCase implements GetAUserById {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public User execute(Long id) {
        return userRepository.find(id)
                .map(user -> {
                    var userAddress = addressRepository.find(user.getId()).orElse(null);

                    user.setAddress(userAddress);

                    return user;
                })
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }
}
