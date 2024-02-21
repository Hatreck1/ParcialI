package com.ugb.parciali;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TabHost tbh;
    Spinner spn;
    TextView tempVal;
    Button btn;
    conversores miObj = new conversores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cambiar color barra de estado
        cambiarColorBarraEstado(getResources().getColor(R.color.azul));

        tbh = findViewById(R.id.tbhConversores);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("agua").setIndicator("AGUA").setContent(R.id.tabAgua));
        tbh.addTab(tbh.newTabSpec("area").setIndicator("AREA").setContent(R.id.tabArea));
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
