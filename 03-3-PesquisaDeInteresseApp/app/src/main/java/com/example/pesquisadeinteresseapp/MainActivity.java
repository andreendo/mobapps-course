package com.example.pesquisadeinteresseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pesquisadeinteresseapp.databinding.ActivityMainBinding;
import com.example.pesquisadeinteresseapp.model.Preferencia;
import com.example.pesquisadeinteresseapp.model.PreferenciaDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PreferenciaDAO dao = new PreferenciaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //default values
        preencherCheckBoxList(dao.getByFaixa(1));

        // Spinner
		/*
		//preencher o Spinner dinamicamente
		ArrayList<String> reg = new ArrayList<>();
		reg.add("Sul");
		reg.add("Sudeste");
		ArrayAdapter<String> regAdapter =
		new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, reg);
		*/
        ArrayAdapter<CharSequence> regAdapter = ArrayAdapter.createFromResource(
                this, R.array.regiao_array, android.R.layout.simple_spinner_item);
        binding.regiaoSpinner.setAdapter(regAdapter);

        // radio buttons
        binding.radioBt0.setOnClickListener(onRadioClickListener);
        binding.radioBt1.setOnClickListener(onRadioClickListener);
        binding.radioBt2.setOnClickListener(onRadioClickListener);

        // button
        binding.gravarButton.setOnClickListener(view -> {
            StringBuilder msg = new StringBuilder();
            msg.append(binding.nomeEditText.getText().toString() + "\n");
            msg.append(binding.regiaoSpinner.getSelectedItem() + "\n");
            msg.append("0: " + binding.radioBt0.isChecked() + "; ");
            msg.append("1: " + binding.radioBt1.isChecked() + "; ");
            msg.append("2: " + binding.radioBt2.isChecked() + ";\n");
            msg.append("preferences: ");
            for (int i = 0; i < binding.prefsLinearLayout.getChildCount(); i++) {
                CheckBox cbox = (CheckBox) binding.prefsLinearLayout.getChildAt(i);
                if (cbox.isChecked()) {
                    msg.append(cbox.getText() + " ");
                }
            }
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        });
    }

    private View.OnClickListener onRadioClickListener = v -> {
        //boolean checked = ((RadioButton) v).isChecked();

        //if (checked) {
            switch (v.getId()) {
                case R.id.radioBt0:
                    preencherCheckBoxList(dao.getByFaixa(1));
                    break;
                case R.id.radioBt1:
                    preencherCheckBoxList(dao.getByFaixa(2));
                    break;
                case R.id.radioBt2:
                    preencherCheckBoxList(dao.getByFaixa(3));
                    break;
            }
        //}
    };

    private void preencherCheckBoxList(ArrayList<Preferencia> prefsByFaixa) {
        binding.prefsLinearLayout.removeAllViews();
        for (Preferencia p : prefsByFaixa) {
            CheckBox cbox = new CheckBox(MainActivity.this);
            cbox.setId(p.getId());
            cbox.setText(p.getNome());
            binding.prefsLinearLayout.addView(cbox);
        }
    }
}