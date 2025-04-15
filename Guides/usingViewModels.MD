# Usando ViewModels

- Implemente uma classe que herda da classe ViewModel:

```java
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    // Adicione public final MutableLiveData<TYPE> aqui para dados que armazenam o status da UI.

    // ...

}
```

- No método onCreate(), recupere e use a ViewModel.

```java
// ...

   private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ...

        // 
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // ...

        counterLabel = getString(R.string.counter_label);
        // observe int
        viewModel.counter.observe(this, v -> tv2.setText(String.format(counterLabel, v)));

        // observe String associated with editText
        viewModel.textInput.observe(this, text -> {
            if (!binding.editText1.getText().toString().equals(text)) {
                binding.editText1.setText(text);
            }
        });

        // for each text change in the editText, update the viewModel liveData
        binding.editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.textInput.setValue(charSequence.toString());
            }
        });

        binding.button1.setOnClickListener(view -> viewModel.incrementCounter());



        button2.setOnClickListener(v -> {
            String content = viewModel.textInput.getValue();
            Log.i("MainActivity", "Edit text content: " + content);
            editText1.setText("");
            Toast.makeText(MainActivity.this,
                    "Edit text content: " + content,
                    Toast.LENGTH_SHORT).show();
            viewModel.clearEditText();
        });

        //..

}
```