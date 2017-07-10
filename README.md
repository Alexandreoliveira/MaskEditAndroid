
[![](https://jitpack.io/v/Alexandreoliveira/MaskEditAndroid.svg)](https://jitpack.io/#Alexandreoliveira/MaskEditAndroid)

### Biblioteca para inserir mascaras em campos EditText no Android em tempo de execução



##### Exemplo de utilização

<br/>

``` JAVA

public class MainActivity extends AppCompatActivity {

    private EditText cnpj;
    private EditText cpf;
    private EditText data;
    private EditText hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.cnpj = (EditText) findViewById(R.id.cnpj);
        Mask.insereMascara(Mask.TYPE_MASK.CNPJ, this.cnpj);

        this.cpf = (EditText) findViewById(R.id.cpf);
        Mask.insereMascara(Mask.TYPE_MASK.CPF, this.cpf);

        this.data = (EditText) findViewById(R.id.data);
        Mask.insereMascara(Mask.TYPE_MASK.DATA, this.data);

        this.hora = (EditText) findViewById(R.id.hora);
        Mask.insereMascara(Mask.TYPE_MASK.HORA_C, this.hora);

    }
}

```

##### Foto



