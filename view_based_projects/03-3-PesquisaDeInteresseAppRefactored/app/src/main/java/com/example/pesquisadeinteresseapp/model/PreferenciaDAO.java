package com.example.pesquisadeinteresseapp.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PreferenciaDAO {
    ArrayList<Preferencia> listOfPrefs = new ArrayList<Preferencia>();

    public PreferenciaDAO() {
        //faixa 1
        Preferencia p = new Preferencia(1, "Hello Kitty", 1);
        listOfPrefs.add(p);
        p = new Preferencia(2, "Ben 10", 1);
        listOfPrefs.add(p);
        p = new Preferencia(3, "Rebeldes", 1);
        listOfPrefs.add(p);
        p = new Preferencia(4, "Chiquititas", 1);
        listOfPrefs.add(p);
        p = new Preferencia(5, "Transformers", 1);
        listOfPrefs.add(p);

        //faixa 2
        p = new Preferencia(6, "Game of Thrones", 2);
        listOfPrefs.add(p);
        p = new Preferencia(7, "Dragon Ball", 2);
        listOfPrefs.add(p);
        p = new Preferencia(8, "Jaspion", 2);
        listOfPrefs.add(p);

        //faixa 3
        p = new Preferencia(9, "Pantanal", 3);
        listOfPrefs.add(p);
        p = new Preferencia(10, "Faustao", 3);
        listOfPrefs.add(p);
        p = new Preferencia(11, "Zorra Total", 3);
        listOfPrefs.add(p);
        p = new Preferencia(12, "Ta no Ar", 3);
        listOfPrefs.add(p);
    }

    public ArrayList<Preferencia> getByFaixa(int faixa) {
        ArrayList<Preferencia> ret = new ArrayList<Preferencia>();
        for(Preferencia p : listOfPrefs)
            if(p.getFaixa() == faixa)
                ret.add(p);

        return ret;
    }
}
