# Habilitar o viewBinding

- No arquivo build.gradle.kts (Module: app), faça a alteração a seguir:

```kotlin
android {
    namespace = "com.example.actionbar2025"
    compileSdk = 35

    // ...
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // adicione as 3 linhas a seguir
    buildFeatures {
        viewBinding = true
    }
}
```

- Use o viewBinding em um fragment:

```java
public class FirstFragment extends Fragment {

    // classe gerada para cada layout
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // instancia o objeto viewBinding
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // accesse os componentes UI como atributos do objeto.
        binding.buttonFirst.setOnClickListener(v -> {
            // ...
        });
        //..
    }
    
    // ..
}
```

- Use o viewBinding em uma activity:

```java
public class MainActivity extends AppCompatActivity {

    // declarar aqui
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // instancia o objeto viewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // REMOVER - setContentView(R.layout.activity_main);

        // accesse os componentes UI como atributos do objeto.
        binding.firstName.setText("Hello");

        // ..
    }

    // ..
}
```