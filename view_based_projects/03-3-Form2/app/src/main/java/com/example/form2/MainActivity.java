package com.example.form2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.form2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<String> cidades = new ArrayList<>();
    CidadeDAO dao = new CidadeDAO();
    ArrayAdapter<String> cidadesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<CharSequence> estadoAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.estados_array,
                android.R.layout.simple_spinner_item
        );
        binding.estadoSpinner.setAdapter(estadoAdapter);

        cidadesAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                cidades
                );
        binding.cidadeSpinner.setAdapter(cidadesAdapter);

        //
        binding.estadoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cidades.clear();
                List<String> newCidades = dao.getCidadeBy(
                        binding.estadoSpinner.getSelectedItem().toString()
                );
                cidades.addAll(newCidades);
                cidadesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });
    }
}