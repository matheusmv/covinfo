package br.edu.ifce.domain.ports.driven;

import lombok.Getter;
import lombok.Setter;

public interface PostmonConsumer {

    ZipInformation getZipInformation(String zip);

    @Getter
    @Setter
    class ZipInformation {
        private String cep;
        private String cidade;
        private String estado;
    }
}
