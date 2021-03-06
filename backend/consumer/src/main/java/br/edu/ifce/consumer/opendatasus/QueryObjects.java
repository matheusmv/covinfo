package br.edu.ifce.consumer.opendatasus;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class QueryObjects {

    private static final String BRASIL = "BRASIL";

    private final Map<String, Map<String, Map<String, List<Map<String, Map<String, String>>>>>> query = new HashMap<>();
    private final List<Map<String, Map<String, String>>> queryParameters = new ArrayList<>();

    private final Gson jsonParser = new Gson();

    public String countryQuery() {
        queryParameters.add(Map.of("match", Map.of("paciente_endereco_nmPais", BRASIL)));

        query.put("query", Map.of("bool", Map.of("must", queryParameters)));

        return jsonParser.toJson(query);
    }

    public String stateQuery(String stateAcronym) {
        queryParameters.add(Map.of("match", Map.of("paciente_endereco_nmPais", BRASIL)));
        queryParameters.add(Map.of("match", Map.of("estabelecimento_uf", stateAcronym)));

        query.put("query", Map.of("bool", Map.of("must", queryParameters)));

        return jsonParser.toJson(query);
    }

    public String cityQuery(String stateAcronym, String cityName) {
        queryParameters.add(Map.of("match", Map.of("paciente_endereco_nmPais", BRASIL)));
        queryParameters.add(Map.of("match", Map.of("estabelecimento_uf", stateAcronym)));
        queryParameters.add(Map.of("match", Map.of("estabelecimento_municipio_nome", cityName)));

        query.put("query", Map.of("bool", Map.of("must", queryParameters)));

        return jsonParser.toJson(query);
    }

    public String medicalCareUnitsQuery(String stateName, String cityName) {
        queryParameters.add(Map.of("match", Map.of("estado", stateName)));
        queryParameters.add(Map.of("match", Map.of("municipio", cityName)));

        query.put("query", Map.of("bool", Map.of("must", queryParameters)));

        return jsonParser.toJson(query);
    }
}
