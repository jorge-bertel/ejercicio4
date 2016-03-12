package com.example.jorgeandres.ejercicio4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contra1, contra2, corre;
    private RadioGroup boton1;
    private String  loggin, pass ,rpass, correo, sexo,  ciudad, ocios;
    private static String fecha="";
    private RadioButton opcion;
    private TextView resu;
    private Spinner spinner1;
    private List<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sexo="";
        ciudad="";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = (EditText) findViewById(R.id.rloggin);
        contra1 = (EditText) findViewById(R.id.rPassword);
        contra2 = (EditText) findViewById(R.id.rrPassword);
        corre = (EditText) findViewById(R.id.rcorreo);
        boton1 = (RadioGroup) findViewById(R.id.sexo);
        final Button boton = (Button) findViewById(R.id.rbu);
        resu = (TextView) findViewById(R.id.resul);
        DatosPorDefecto();
        boton1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rmen:
                        sexo = "masculino";
                        break;
                    case R.id.rwomen:
                        sexo = "femenino";
                        break;
                }
            }

        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuario.getText().toString().equals("") || contra1.getText().toString().equals("") || contra2.getText().toString().equals("") || corre.getText().toString().equals("") || sexo.equals("")|| ciudad.equals("")||fecha.equals("")) {
                    resu.setText(String.valueOf("faltan datos"));
                }
                else {
                    if (usuario.getText().toString().equals("") || contra1.getText().toString().equals("")) {
                        resu.setText(String.valueOf("usuario"));
                    }
                }




            }
        });
        spinner1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {

                        ciudad = parent.getItemAtPosition(position).toString();

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                       ciudad="";
                    }


                });


    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            fecha = String.valueOf(day)+"/"+String.valueOf(month) + "/" + String.valueOf(year);

        }
    }

    public void DatosPorDefecto() {
        spinner1 = (Spinner) findViewById(R.id.rspin);
        lista = new ArrayList<String>();
        spinner1 = (Spinner) this.findViewById(R.id.rspin);
        lista.add("Barranquilla");
        lista.add("Bogota");
        lista.add("Cartagena");
        lista.add("Medellin");
        lista.add("Monteria");
        lista.add("Sincelejo");
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adaptador);

    }

    public void onCheckboxClicked(View view) {

        if (((CheckBox) findViewById(R.id.rho1)).isChecked()) {

            ocios += "futbol";
        }
        if (((CheckBox) findViewById(R.id.rho2)).isChecked()) {

            ocios += "comer";
        }

        if (((CheckBox) findViewById(R.id.rho3)).isChecked()) {

            ocios += "cine";
        }

        if (((CheckBox) findViewById(R.id.rho4)).isChecked()) {

            ocios += "leer";
        }
    }

    public boolean isEmailValid(String email){
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    public void showDatePickerDialog (View v){
        DialogFragment newFragment=new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    }


