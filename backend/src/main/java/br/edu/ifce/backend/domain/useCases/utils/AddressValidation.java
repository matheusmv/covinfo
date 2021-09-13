package br.edu.ifce.backend.domain.useCases.utils;

import br.edu.ifce.backend.domain.entities.Address;

import java.util.Optional;
import java.util.function.Function;

import static br.edu.ifce.backend.domain.useCases.utils.AddressValidationResult.NEIGHBORHOOD_NOT_VALID;
import static br.edu.ifce.backend.domain.useCases.utils.AddressValidationResult.STREET_NOT_VALID;
import static br.edu.ifce.backend.domain.useCases.utils.AddressValidationResult.SUCCESS;
import static br.edu.ifce.backend.domain.useCases.utils.AddressValidationResult.ZIP_NOT_VALID;

public interface AddressValidation extends Function<Address, AddressValidationResult> {
    static AddressValidation zipIsValid() {
        return address -> {
            boolean zipNotNullAndNotEmptyAndValidFormat = Optional.ofNullable(address)
                    .map(Address::getZip)
                    .filter(zip -> !zip.isBlank() && zip.matches("^[0-9]{5}[-]?[0-9]{3}$"))
                    .isPresent();

            return zipNotNullAndNotEmptyAndValidFormat ? SUCCESS : ZIP_NOT_VALID;
        };
    }

    static AddressValidation neighborhoodIsValid() {
        return address -> {
            boolean neighborhoodNotNullAndNotEmpty = Optional.ofNullable(address)
                    .map(Address::getNeighborhood)
                    .filter(neighborhood -> !neighborhood.isBlank())
                    .isPresent();

            return neighborhoodNotNullAndNotEmpty ? SUCCESS : NEIGHBORHOOD_NOT_VALID;
        };
    }

    static AddressValidation streetIsValid() {
        return address -> {
            boolean streetNotNullAndNotEmpty = Optional.ofNullable(address)
                    .map(Address::getStreet)
                    .filter(street -> !street.isBlank())
                    .isPresent();

            return streetNotNullAndNotEmpty ? SUCCESS : STREET_NOT_VALID;
        };
    }

    default AddressValidation and(AddressValidation validation) {
        return address -> {
            AddressValidationResult result = this.apply(address);
            return result.equals(SUCCESS) ? validation.apply(address) : result;
        };
    }
}
