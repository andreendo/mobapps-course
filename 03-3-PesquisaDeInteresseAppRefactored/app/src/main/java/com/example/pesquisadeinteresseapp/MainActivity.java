package com.example.pesquisadeinteresseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pesquisadeinteresseapp.databinding.ActivityMainBinding;
import com.example.pesquisadeinteresseapp.model.Preferencia;
import com.example.pesquisadeinteresseapp.model.PreferenciaDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // editText nome
        mainViewModel.getNome().observe(this, text -> {
            if (!binding.nomeEditText.getText().toString().equals(text))
                binding.nomeEditText.setText(text);
        });
        binding.nomeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainViewModel.setNome(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        // Spinner
        mainViewModel.getRegioes().observe(this, regioes -> {
            ArrayAdapter<String> regAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, regioes);
            binding.regiaoSpinner.setAdapter(regAdapter);
        });
        binding.regiaoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mainViewModel.setSelectedRegiao(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // radio buttons
        binding.faixaEtariaRadioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            int selectedFaixa = 0;
            if (checkedId == R.id.radioBt0)
                selectedFaixa = 1;
            else if (checkedId == R.id.radioBt1)
                selectedFaixa = 2;
            else if (checkedId == R.id.radioBt2)
                selectedFaixa = 3;

            mainViewModel.setSelectedFaixa(selectedFaixa);
        });

        //checkboxes
        mainViewModel.getPreferencias().observe(this, preferencias -> {
            preencherCheckBoxList(preferencias);
        });

        //Toast
        mainViewModel.getToastMessage().observe(this, message -> {
            if (!message.isBlank()) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        // button
        binding.gravarButton.setOnClickListener(v -> mainViewModel.showCurrentData());
    }

    private void preencherCheckBoxList(List<Preferencia> prefsByFaixa) {
        binding.prefsLinearLayout.removeAllViews();
        for (Preferencia p : prefsByFaixa) {
            CheckBox cbox = new CheckBox(MainActivity.this);
            cbox.setId(p.getId());
            cbox.setText(p.getNome());
            cbox.setOnCheckedChangeListener((checkBox, isChecked) -> {
                mainViewModel.addCheckedItem(checkBox.getText().toString(), isChecked);
            });
            binding.prefsLinearLayout.addView(cbox);
        }
    }
}