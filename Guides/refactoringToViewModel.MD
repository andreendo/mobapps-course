# Refatorar o app PesquisaDeInteresseApp para usar ViewModel

- Copiar o projeto https://github.com/andreendo/mobapps-course/tree/master/03-3-PesquisaDeInteresseApp.

- Abrir o projeto no Android Studio

- Criar uma classe ViewModel e recuperar ela na MainActivity

```java
// MainViewModel.java
public class MainViewModel extends ViewModel { }

// MainActivity.java
    //..
    private MainViewModel mainViewModel;

    //..
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // recupera a viewModel
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }
```

- O texto preenchido atualmente no EditText deve ser mapeado para um `MutableLiveData<String>`. 

```java
// MainViewModel.java
    private MutableLiveData<String> nome = new MutableLiveData<>();

    // Boa prática: retornar LiveData permite observação, alterações devem
    //              acontecer dentro do ViewModel
    public LiveData<String> getNome() {
        return nome;
    }

    public void setNome(String pNome) {
        nome.setValue(pNome);
    }

// MainActivity.java
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

```

- Opções do Spinner e o valor atualmente selecionado no spinner devem ficar na ViewModel. 

```java
// MainViewModel.java
    private MutableLiveData<List<String>> regioes;
    private MutableLiveData<String> selectedRegiao = new MutableLiveData<>();

    //..

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


// MainActivity.java
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
```

- O radioButton selecionado atualmente armazenado na ViewModel. Inicialmente, vamos deixar os RadioButtons sem seleção (`android:checked="false"` em `activity_main.xml`). 

```java
// MainViewModel.java
    private MutableLiveData<Integer> selectedFaixa = new MutableLiveData<>(0);
    
    public void setSelectedFaixa(int selected) {
        selectedFaixa.setValue(selected);
    }

// MainActivity.java
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

```
- Os checkboxes devem ser atualizados com base em uma lista de preferências (associada com a faixa selecionada atualmente).

```java
// MainViewModel.java
    private MutableLiveData<List<Preferencia>> preferencias = new MutableLiveData<>(new ArrayList<>());

    //.. 

    public LiveData<List<Preferencia>> getPreferencias() {
        return preferencias;
    }


// MainActivity.java
        //..
        mainViewModel.getPreferencias().observe(this, preferencias -> {
            preencherCheckBoxList(preferencias);
        });

```

- Mover o objeto `PreferenciaDAO` para o ViewModel, e atualizar o `setSelectedFaixa(..)` para atualizar a lista de preferências.


```java
// MainViewModel.java
    private PreferenciaDAO dao = new PreferenciaDAO();

    public void setSelectedFaixa(int selected) {
        selectedFaixa.setValue(selected);

        // recupera a lista de preferências com base na faixa selecionada. 
        List<Preferencia> prefs = dao.getByFaixa(selected);
        preferencias.setValue(prefs);
    }
// MainActivity.java

```

- Armazenar os itens selecionados nos check boxes.


```java
// MainViewModel.java
    private HashSet<String> selectedItems = new HashSet<>();

        public void setSelectedFaixa(int selected) {
        selectedFaixa.setValue(selected);

        List<Preferencia> prefs = dao.getByFaixa(selected);
        preferencias.setValue(prefs);

        // limpar os itens aqui
        selectedItems.clear();
    }

    public void addCheckedItem(String item, boolean isChecked) {
        if (isChecked) {
            selectedItems.add(item);
        } else {
            selectedItems.remove(item);
        }
    }
 
// MainActivity.java
private void preencherCheckBoxList(List<Preferencia> prefsByFaixa) {
        binding.prefsLinearLayout.removeAllViews();
        for (Preferencia p : prefsByFaixa) {
            CheckBox cbox = new CheckBox(MainActivity.this);
            cbox.setId(p.getId());
            cbox.setText(p.getNome());
            // adicionar este listener
            cbox.setOnCheckedChangeListener((checkBox, isChecked) -> {
                mainViewModel.addCheckedItem(checkBox.getText().toString(), isChecked);
            });
            binding.prefsLinearLayout.addView(cbox);
        }
    }

```

- Refatorar para a mensagem do Toast ser montada dentro do ViewModel.

```java
// MainViewModel.java
    private MutableLiveData<String> toastMessage = new MutableLiveData<>("");

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

// MainActivity.java
        //Toast
        mainViewModel.getToastMessage().observe(this, message -> {
            if (!message.isBlank()) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        // button
        binding.gravarButton.setOnClickListener(v -> mainViewModel.showCurrentData());
```
