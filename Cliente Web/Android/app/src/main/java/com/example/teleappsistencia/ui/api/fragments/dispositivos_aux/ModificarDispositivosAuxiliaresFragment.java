package com.example.teleappsistencia.ui.api.fragments.dispositivos_aux;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.teleappsistencia.R;
import com.example.teleappsistencia.ui.api.APIService;
import com.example.teleappsistencia.ui.api.ClienteRetrofit;
import com.example.teleappsistencia.ui.dialogs.AlertDialogBuilder;
import com.example.teleappsistencia.ui.clases.DispositivoAuxiliar;
import com.example.teleappsistencia.ui.clases.Terminal;
import com.example.teleappsistencia.ui.clases.TipoAlarma;
import com.example.teleappsistencia.ui.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarDispositivosAuxiliaresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarDispositivosAuxiliaresFragment extends Fragment {

    private DispositivoAuxiliar dispositivoAuxiliar;

    private Button btn_insertar;
    private Button btn_volver;
    private Spinner spinner_terminal;
    private Spinner spinner_tipoAlarma;

    public ModificarDispositivosAuxiliaresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param dispositivoAuxiliar
     * @return A new instance of fragment InsertarDispositivosAuxiliaresFragment.
     */
    public static ModificarDispositivosAuxiliaresFragment newInstance(DispositivoAuxiliar dispositivoAuxiliar) {
        ModificarDispositivosAuxiliaresFragment fragment = new ModificarDispositivosAuxiliaresFragment();
        Bundle args = new Bundle();
        args.putSerializable(Utils.OBJECT, dispositivoAuxiliar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            dispositivoAuxiliar = (DispositivoAuxiliar) getArguments().getSerializable(Utils.OBJECT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modificar_dispositivos_auxiliares, container, false);

        this.btn_insertar = view.findViewById(R.id.btn_guardar_dispositivosAux);
        this.btn_volver = view.findViewById(R.id.btn_volver_dispositivosAux);
        this.spinner_terminal = view.findViewById(R.id.spinner_terminal_dispositivosAux);
        this.spinner_tipoAlarma = view.findViewById(R.id.spinner_tipoAlarma_dispositivosAux);

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

        this.btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    private void inicializarSpinnerTerminal() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();

        Call<List<Terminal>> call = apiService.getTerminales("Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<List<Terminal>>() {
            @Override
            public void onResponse(Call<List<Terminal>> call, Response<List<Terminal>> response) {
                if (response.isSuccessful()) {
                    List<Terminal> terminalList = response.body();
                    Terminal terminal = (Terminal) Utils.getObjeto(dispositivoAuxiliar.getTerminal(), getString(R.string.terminal_class));
                    terminalList.add(0, terminal);
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

    private void inicializarSpinnerTipoAlarma() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();

        Call<List<TipoAlarma>> call = apiService.getTiposAlarmas("Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<List<TipoAlarma>>() {
            @Override
            public void onResponse(Call<List<TipoAlarma>> call, Response<List<TipoAlarma>> response) {
                if (response.isSuccessful()) {
                    List<TipoAlarma> tipoAlarmaList = response.body();
                    TipoAlarma tipoAlarma = (TipoAlarma) Utils.getObjeto(dispositivoAuxiliar.getTipoAlarma(), getString(R.string.tipoAlarma_class));
                    tipoAlarmaList.add(0, tipoAlarma);
                    spinner_tipoAlarma.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, tipoAlarmaList));
                }
            }

            @Override
            public void onFailure(Call<List<TipoAlarma>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    /**
     * Método para modificar un dispositivo auxilar en terminal de la base de datos.
     * El método realiza una petición a la API con los datos proporcionados por el usuario.
     */
    private void insertarDispositivoAuxiliar() {
        Terminal terminal = (Terminal) this.spinner_terminal.getSelectedItem();
        TipoAlarma tipoAlarma = (TipoAlarma) this.spinner_tipoAlarma.getSelectedItem();

        DispositivoAuxiliar dispositivoAuxiliar = new DispositivoAuxiliar(terminal.getId(), tipoAlarma.getId());

        APIService apiService = ClienteRetrofit.getInstance().getAPIService();

        Call<Object> call = apiService.modifyDispositivoAuxiliar(this.dispositivoAuxiliar.getId(), dispositivoAuxiliar, "Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object dispositivoAuxiliar = response.body();
                    AlertDialogBuilder.crearInfoAlerDialog(getContext(), getString(R.string.infoAlertDialog_modificado_dispositivoAuxiliar));
                    getActivity().onBackPressed();
                } else {
                    AlertDialogBuilder.crearErrorAlerDialog(getContext(), Integer.toString(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    /**
     * Método que revisa si los datos de los EditText son válidos.
     * @return Devuelve true si es válido de lo contrario devuelve false.
     */
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