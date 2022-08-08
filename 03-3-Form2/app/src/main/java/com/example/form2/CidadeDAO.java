package com.example.form2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CidadeDAO {
    public List<String> getCidadeBy(String estado) {
        if(estado.equals("São Paulo")) {
            return Arrays.asList(
                    "São Paulo",
                    "Arraquara",
                    "São Carlos",
                    "Álvares Machado"
            );
        }

        //implementar aqui algumas cidades para cada estado

        return new ArrayList<>();
    }
}
