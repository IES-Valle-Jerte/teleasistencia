package com.example.teleappsistencia.fragments.api.historico_tipo_situacion;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.MainActivity;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utils;
import com.example.teleappsistencia.clases.HistoricoTipoSituacion;
import com.example.teleappsistencia.clases.Terminal;
import com.example.teleappsistencia.clases.TipoSituacion;
import com.example.teleappsistencia.fragments.dialogs.DatePickerFragment;

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

    private Button btn_insertar;
    private EditText editText_fecha;
    private Spinner spinner_terminal;
    private Spinner spinner_tipoSituacion;

    public ModificarHistoricoTipoSituacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertarHistoricoTipoSituacionFragment.
     */
    public static ModificarHistoricoTipoSituacionFragment newInstance(String param1, String param2) {
        ModificarHistoricoTipoSituacionFragment fragment = new ModificarHistoricoTipoSituacionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insertar_historico_tipo_situacion, container, false);

        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_historicoTipoSituacion);
        this.spinner_tipoSituacion = (Spinner) view.findViewById(R.id.spinner_tipoSituacion_historicoTipoSituacion);
        this.spinner_terminal = (Spinner) view.findViewById(R.id.spinner_terminal_historicoTipoSituacion);
        this.editText_fecha = (EditText) view.findViewById(R.id.editText_fecha_historicoTipoSituacion);

        inicializarSpinnerTerminal();
        inicializarSpinnerTipoSituacion();

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarHistoricoTipoSituacion();
            }
        });

        this.editText_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDatePickerDialog();
            }
        });

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

        Call<List<Terminal>> call = apiService.getTerminales("Bearer " + MainActivity.token.getAccess());
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

        Call<List<TipoSituacion>> call = apiService.getTipoSituacion("Bearer " + MainActivity.token.getAccess());
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

    private void insertarHistoricoTipoSituacion() {
        String fecha = this.editText_fecha.getText().toString();
        TipoSituacion tipoSituacion = (TipoSituacion) this.spinner_tipoSituacion.getSelectedItem();
        Terminal terminal = (Terminal) this.spinner_terminal.getSelectedItem();

        HistoricoTipoSituacion historicoTipoSituacion = new HistoricoTipoSituacion(fecha, tipoSituacion.getId(), terminal.getId());

        APIService apiService = Utils.inicializarApiService(getContext());
        Call<HistoricoTipoSituacion> call = apiService.addHistoricoTipoSituacion(historicoTipoSituacion, "Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<HistoricoTipoSituacion>() {
            @Override
            public void onResponse(Call<HistoricoTipoSituacion> call, Response<HistoricoTipoSituacion> response) {
                if (response.isSuccessful()) {
                    HistoricoTipoSituacion historicoTipoSituacion = response.body();
                    System.out.println(historicoTipoSituacion);
                    Toast.makeText(getContext(), "Se ha añadido un nuevo historico tipo situacion.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al añadir el nuevo historico tipo situacion. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HistoricoTipoSituacion> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }
}