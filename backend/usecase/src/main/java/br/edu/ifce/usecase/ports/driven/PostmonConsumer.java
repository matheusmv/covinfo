package br.edu.ifce.usecase.ports.driven;

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
