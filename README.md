
[![](https://jitpack.io/v/Alexandreoliveira/MaskEditAndroid.svg)](https://jitpack.io/#Alexandreoliveira/MaskEditAndroid)

### Biblioteca para inserir mascaras em campos EditText no Android em tempo de execução



##### Exemplo de utilização

<br/>

``` JAVA

public class MainActivity extends AppCompatActivity {

    EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt = (EditText) findViewById(R.id.mascara);
        Mask.insereMascara(Mask.TYPE_MASK.CNPJ, txt);

    }
}

```


##### Foto

