# Salvar dados quando o app é pausado

- Em uma activity, implemente o método a seguir. Ele é chamado um pouco antes da Activity ser destruída, entre onPause() e onStop().

```java
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        
        outState.putInt("counter", counter);
    }
```

- No método onCreate(), use o objeto savedInstanceState.

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ...

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter");
        }

        // ...
}
```