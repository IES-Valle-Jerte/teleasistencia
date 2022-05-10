package com.example.teleappsistencia.ui.fragments.api.dispositivos_aux;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.MainActivity;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utils;
import com.example.teleappsistencia.ui.clases.DispositivoAuxiliar;
import com.example.teleappsistencia.ui.clases.Terminal;
import com.example.teleappsistencia.ui.clases.TipoAlarma;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertarDispositivosAuxiliaresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarDispositivosAuxiliaresFragment extends Fragment {

    private Button btn_insertar;
    private Spinner spinner_terminal;
    private Spinner spinner_tipoAlarma;

    public InsertarDispositivosAuxiliaresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertarDispositivosAuxiliaresFragment.
     */
    public static InsertarDispositivosAuxiliaresFragment newInstance() {
        InsertarDispositivosAuxiliaresFragment fragment = new InsertarDispositivosAuxiliaresFragment();
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
        View view = inflater.inflate(R.layout.fragment_insertar_dispositivos_auxiliares, container, false);

        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_dispositivosAux);
        this.spinner_terminal = (Spinner) view.findViewById(R.id.spinner_terminal_dispositivosAux);
        this.spinner_tipoAlarma = (Spinner) view.findViewById(R.id.spinner_tipoAlarma_dispositivosAux);

        inicializarSpinnerTerminal();
        inicializarSpinnerTipoAlarma();

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarDispositivoAuxiliar()) {
                    insertarDispositivoAuxiliar();
                }
            }
        });

        return view;
    }

    private void inicializarSpinnerTerminal() {
        APIService apiService = Utils.inicializarApiService(getContext());

        Call<List<Terminal>> call = apiService.getTerminales("Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<List<Terminal>>() {
            @Override
            public void onResponse(Call<List<Terminal>> call, Response<List<Terminal>> response) {
                if (response.isSuccessful()) {
                    List<Terminal> terminalList = response.body();
                    System.out.println(terminalList);
                    spinner_terminal.setAdapter(new ArrayAdapter<Terminal>(getContext(), R.layout.support_simple_spinner_dropdown_item, terminalList));
                }
            }

            @Override
            public void onFailure(Call<List<Terminal>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    private void inicializarSpinnerTipoAlarma() {
        APIService apiService = Utils.inicializarApiService(getContext());

        Call<List<TipoAlarma>> call = apiService.getTiposAlarmas("Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<List<TipoAlarma>>() {
            @Override
            public void onResponse(Call<List<TipoAlarma>> call, Response<List<TipoAlarma>> response) {
                if (response.isSuccessful()) {
                    List<TipoAlarma> tipoAlarmaList = response.body();
                    System.out.println(tipoAlarmaList);
                    spinner_tipoAlarma.setAdapter(new ArrayAdapter<TipoAlarma>(getContext(), R.layout.support_simple_spinner_dropdown_item, tipoAlarmaList));
                }
            }

            @Override
            public void onFailure(Call<List<TipoAlarma>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    private void insertarDispositivoAuxiliar() {
        Terminal terminal = (Terminal) this.spinner_terminal.getSelectedItem();
        TipoAlarma tipoAlarma = (TipoAlarma) this.spinner_tipoAlarma.getSelectedItem();

        DispositivoAuxiliar dispositivoAuxiliar = new DispositivoAuxiliar(terminal.getId(), tipoAlarma.getId());

        APIService apiService = Utils.inicializarApiService(getContext());

        Call<Object> call = apiService.addDispositivoAuxiliar(dispositivoAuxiliar, "Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object dispositivoAuxiliar = response.body();
                    System.out.println(dispositivoAuxiliar);
                    Toast.makeText(getContext(), "Se ha añadido un nuevo dispositivo auxiliar.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al añadir el nuevo dispositivo auxiliar. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    private boolean validarDispositivoAuxiliar() {
        boolean validTerminal, validarTipoAlarma;

        validTerminal = validarTerminal();
        validarTipoAlarma = validarTipoAlarma();

        if((validTerminal) && (validarTipoAlarma)){
            return true;
        } else {
            return false;
        }
    }

    private boolean validarTerminal() {
        if(spinner_terminal.getSelectedItem() != null) {
            return true;
        } else{
            return false;
        }
    }

    private boolean validarTipoAlarma() {
        if(spinner_tipoAlarma.getSelectedItem() != null) {
            return true;
        } else{
            return false;
        }
    }
}