package com.example.pesquisadeinteresseapp;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pesquisadeinteresseapp.model.Preferencia;
import com.example.pesquisadeinteresseapp.model.PreferenciaDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<String> nome = new MutableLiveData<>();
    private MutableLiveData<List<String>> regioes;
    private MutableLiveData<String> selectedRegiao = new MutableLiveData<>();
    private MutableLiveData<Integer> selectedFaixa = new MutableLiveData<>(0);
    private MutableLiveData<List<Preferencia>> preferencias = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<String> toastMessage = new MutableLiveData<>("");
    private HashSet<String> selectedItems = new HashSet<>();

    private PreferenciaDAO dao = new PreferenciaDAO();

    // Boa prática: retornar LiveData permite observação, alterações devem
    //              acontecer dentro do ViewModel
    public LiveData<String> getNome() {
        return nome;
    }

    public void setNome(String pNome) {
        nome.setValue(pNome);
    }

    public LiveData<List<String>> getRegioes() {
        if (regioes == null) {
            List<String> tempRegioes = Arrays.asList(
                    "--",
                    "Centro Oeste",
                    "Nordeste",
                    "Norte",
                    "Sudeste",
                    "Sul"
            );
            regioes = new MutableLiveData<>();
            regioes.setValue(tempRegioes);
            selectedRegiao.setValue(tempRegioes.get(0));
        }

        return regioes;
    }

    public void setSelectedRegiao(int posicao) {
        selectedRegiao.setValue(regioes.getValue().get(posicao));
        Log.d("MainViewModel", regioes.getValue().get(posicao));
    }

    public LiveData<String> getSelectedRegiao() {
        return selectedRegiao;
    }

    public void setSelectedFaixa(int selected) {
        selectedFaixa.setValue(selected);

        List<Preferencia> prefs = dao.getByFaixa(selected);
        preferencias.setValue(prefs);

        selectedItems.clear();
    }

    public LiveData<List<Preferencia>> getPreferencias() {
        return preferencias;
    }

    public void addCheckedItem(String item, boolean isChecked) {
        if (isChecked) {
            selectedItems.add(item);
        } else {
            selectedItems.remove(item);
        }
        Log.d("MainViewModel", selectedItems.toString());
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void showCurrentData() {
        StringBuilder msg = new StringBuilder();
        msg.append(nome.getValue() + "\n");
        msg.append(selectedRegiao.getValue() + "\n");
        msg.append(selectedFaixa.getValue() + "\n");
        msg.append("preferences: " + selectedItems.toString());
        toastMessage.setValue(msg.toString());
    }
}
