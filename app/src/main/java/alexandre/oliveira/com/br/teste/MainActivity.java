package alexandre.oliveira.com.br.teste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import alexandre.oliveira.com.br.maskverification.Mask;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.teste);
        Mask.insereMascara(Mask.TYPE_MASK.CEP, editText);

    }
}
