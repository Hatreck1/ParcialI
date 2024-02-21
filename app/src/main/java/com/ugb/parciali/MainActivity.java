package com.ugb.parciali;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TabHost tbh;
    Spinner spn;
    TextView tempVal, tempValAgua;
    Button btn, btnCalcular;
    conversores miObj = new conversores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cambiar color barra de estado
        cambiarColorBarraEstado(getResources().getColor(R.color.azul));

        //valores para los edittext
        EditText txtAgua= (EditText)findViewById(R.id.txtnum1);
        EditText txtArea= (EditText)findViewById(R.id.txtAreaCantidad);

        tbh = findViewById(R.id.tbhConversores);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("agua").setIndicator("AGUA").setContent(R.id.tabAgua));
        tbh.addTab(tbh.newTabSpec("area").setIndicator("AREA").setContent(R.id.tabArea));

        //Area
        btnCalcular = findViewById(R.id.btnAreaConvertir);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Area
                if(txtArea.getText().toString().isEmpty()){
                    txtArea.setError("Campo obligatorio");

                } else{
                    spn = findViewById(R.id.spnAreaDe);
                    int de = spn.getSelectedItemPosition();

                    spn = findViewById(R.id.spnAreaA);
                    int a = spn.getSelectedItemPosition();

                    tempVal = findViewById(R.id.txtAreaCantidad);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    double resp = miObj.convertir(0, de, a, cantidad);
                    Toast.makeText(getApplicationContext(), "Respuesta: "+ resp, Toast.LENGTH_LONG).show();
                }
            }
        });


        //Agua
        btn = findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Area
                if(txtAgua.getText().toString().isEmpty()){
                    txtAgua.setError("Campo obligatorio");

                } else{

                   // CalculadoraAgua();


                }
            }
        });


    }

    public class CalculadoraAgua {

        public  double calcularValorPagar(int metrosConsumidos) {
            double valorPagar = 0;

            if (metrosConsumidos >= 1 && metrosConsumidos <= 18) {
                valorPagar = 6;
            } else if (metrosConsumidos >= 19 && metrosConsumidos <= 28) {
                int exceso = metrosConsumidos - 18;
                valorPagar = 6 + (exceso * 0.45);
            } else if (metrosConsumidos >= 29) {
                int exceso28 = metrosConsumidos - 28;
                int exceso18 = 28 - 18;
                valorPagar = 6 + (exceso28 * 0.65) + (exceso18 * 0.45);
            }

            return valorPagar;
        }

        public  void main(String[] args) {
            int metrosConsumidos = 38;
            double valorAPagar = calcularValorPagar(metrosConsumidos);
            System.out.println("Valor a pagar: $" + valorAPagar);
        }
    }


    private void cambiarColorBarraEstado(int color) {
        // Verificar si la versión del SDK es Lollipop o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    } //fin cambiar colorbarraestado

    class conversores{
        double[][] valores = {
                {1, 75220.68, 10000, 8439, 6988, 0.6988, 16} //Area

        };
        public double convertir(int opcion, int de, int a, double cantidad){
            return valores[opcion][a] / valores[opcion][de] * cantidad;
        }
    }
}
