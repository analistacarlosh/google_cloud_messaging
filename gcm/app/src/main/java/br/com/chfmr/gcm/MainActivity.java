package br.com.chfmr.gcm;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(GcmHelper.googlePlayServicesEstaDisponivel(this)){
            GcmHelper.registrar(this, new GcmHelper.AoRegistrarDispositivo(){
            @Override
            public void aoRegistrar(String regId, boolean emSegundoPlano){
                if(regId != null){
                    Toast.makeText(MainActivity.this,
                            "Registrado comsucesso! \n" +  regId,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            R.string.erro_gplay_falha_registrar,
                            Toast.LENGTH_SHORT).show();
                }
            }
            });
        } else {
            Toast.makeText(this, R.string.erro_gplay_falha_registrar, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
