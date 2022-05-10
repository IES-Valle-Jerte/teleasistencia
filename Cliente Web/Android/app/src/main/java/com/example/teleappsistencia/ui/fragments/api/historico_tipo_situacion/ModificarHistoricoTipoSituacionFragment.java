package com.example.teleappsistencia.ui.fragments.api.historico_tipo_situacion;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utils;
import com.example.teleappsistencia.ui.clases.HistoricoTipoSituacion;
import com.example.teleappsistencia.ui.clases.Terminal;
import com.example.teleappsistencia.ui.clases.TipoSituacion;
import com.example.teleappsistencia.ui.fragments.dialogs.DatePickerFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarHistoricoTipoSituacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarHistoricoTipoSituacionFragment extends Fragment {

    private int id;

    private Button btn_insertar;
    private EditText editText_fecha;
    private Spinner spinner_terminal;
    private Spinner spinner_tipoSituacion;

    private TextView textView_error_fecha;

    public ModificarHistoricoTipoSituacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id
     * @return A new instance of fragment InsertarHistoricoTipoSituacionFragment.
     */
    public static ModificarHistoricoTipoSituacionFragment newInstance(int id) {
        ModificarHistoricoTipoSituacionFragment fragment = new ModificarHistoricoTipoSituacionFragment();
        Bundle args = new Bundle();
        args.putInt(fragment.getString(R.string.id), id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            id = getArguments().getInt(getString(R.string.id), 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modificar_historico_tipo_situacion, container, false);

        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_historicoTipoSituacion);
        this.spinner_tipoSituacion = (Spinner) view.findViewById(R.id.spinner_tipoSituacion_historicoTipoSituacion);
        this.spinner_terminal = (Spinner) view.findViewById(R.id.spinner_terminal_historicoTipoSituacion);
        this.editText_fecha = (EditText) view.findViewById(R.id.editText_fecha_historicoTipoSituacion);

        inicializarSpinnerTerminal();
        inicializarSpinnerTipoSituacion();

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarHistoricoTipoSituacion()){
                    modificarHistoricoTipoSituacion();
                }
            }
        });

        /* Creo un dialog datePicker para recoger la fecha */
        this.editText_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDatePickerDialog();
            }
        });

        inicializarListeners();

        return view;
    }

    private void mostrarDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String selectedDate = year + "-" + Utils.twoDigitsDate(month+1) + "-" + Utils.twoDigitsDate(day);
                editText_fecha.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void inicializarSpinnerTerminal(){
        APIService apiService = Utils.inicializarApiService(getContext());

        Call<List<Terminal>> call = apiService.getTerminales("Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<List<Terminal>>() {
            @Override
            public void onResponse(Call<List<Terminal>> call, Response<List<Terminal>> response) {
                if (response.isSuccessful()) {
                    List<Terminal> terminalList = response.body();
                    System.out.println(terminalList);
                    spinner_terminal.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, terminalList));
                }
            }

            @Override
            public void onFailure(Call<List<Terminal>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    private void inicializarSpinnerTipoSituacion(){
        APIService apiService = Utils.inicializarApiService(getContext());

        Call<List<TipoSituacion>> call = apiService.getTipoSituacion("Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<List<TipoSituacion>>() {
            @Override
            public void onResponse(Call<List<TipoSituacion>> call, Response<List<TipoSituacion>> response) {
                if (response.isSuccessful()) {
                    List<TipoSituacion> tipoSituacionList = response.body();
                    System.out.println(tipoSituacionList);
                    spinner_tipoSituacion.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, tipoSituacionList));
                }
            }

            @Override
            public void onFailure(Call<List<TipoSituacion>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    private void modificarHistoricoTipoSituacion() {
        String fecha = this.editText_fecha.getText().toString();
        TipoSituacion tipoSituacion = (TipoSituacion) this.spinner_tipoSituacion.getSelectedItem();
        Terminal terminal = (Terminal) this.spinner_terminal.getSelectedItem();

        HistoricoTipoSituacion historicoTipoSituacion = new HistoricoTipoSituacion(fecha, tipoSituacion.getId(), terminal.getId());

        APIService apiService = Utils.inicializarApiService(getContext());
        Call<Object> call = apiService.modifyHistoricoTipoSituacion(id, historicoTipoSituacion, "Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object historicoTipoSituacion = response.body();
                    System.out.println(historicoTipoSituacion);
                    Toast.makeText(getContext(), "Se ha modificado el historico tipo situacion.", Toast.LENGTH_SHORT).show();
                    borrarEditTexts();
                } else {
                    Toast.makeText(getContext(), "Error al modificar el historico tipo situacion. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    private void borrarEditTexts() {
        this.editText_fecha.setText(getString(R.string.espacio_en_blanco));
        this.textView_error_fecha.setVisibility(View.GONE);
    }

    private boolean validarHistoricoTipoSituacion() {
        boolean validFecha, validTerminal, validTipoSituacion;

        validFecha = validarFecha(editText_fecha.getText().toString());
        validTerminal = validarTerminal();
        validTipoSituacion = validarTipoSituacion();

        if((validFecha) && (validTerminal) && (validTipoSituacion)){
            return true;
        } else {
            return false;
        }
    }

    private void inicializarListeners() {
        this.textView_error_fecha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarFecha(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public boolean validarFecha(String nombre) {
        boolean valid = false;
        if ((nombre.isEmpty()) || (nombre.trim().equals(""))) {     // Reviso si la fecha está vacia.
            textView_error_fecha.setText(R.string.textview_nombre_obligatorio);
            textView_error_fecha.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacia entonces le asigno el texto de que es obligatoria y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_fecha.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    private boolean validarTerminal() {
        if(spinner_terminal.getSelectedItem() != null) {
            return true;
        } else{
            return false;
        }
    }

    private boolean validarTipoSituacion() {
        if(spinner_tipoSituacion.getSelectedItem() != null) {
            return true;
        } else{
            return false;
        }
    }
}