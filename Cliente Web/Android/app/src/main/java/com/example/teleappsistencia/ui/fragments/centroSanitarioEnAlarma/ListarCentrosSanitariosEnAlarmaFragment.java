package com.example.teleappsistencia.ui.fragments.centroSanitarioEnAlarma;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.teleappsistencia.R;
import com.example.teleappsistencia.modelos.Alarma;
import com.example.teleappsistencia.modelos.CentroSanitarioEnAlarma;
import com.example.teleappsistencia.modelos.Token;
import com.example.teleappsistencia.servicios.APIService;
import com.example.teleappsistencia.servicios.ClienteRetrofit;
import com.example.teleappsistencia.ui.fragments.alarma.AlarmaAdapter;
import com.example.teleappsistencia.utilidades.Constantes;
import com.example.teleappsistencia.utilidades.Utilidad;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarCentrosSanitariosEnAlarmaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarCentrosSanitariosEnAlarmaFragment extends Fragment {


    private ArrayList<CentroSanitarioEnAlarma> lCentrosEnAlarma;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public ListarCentrosSanitariosEnAlarmaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.     *
     *
     * @return A new instance of fragment ListarCentrosSanitariosEnAlarmaFragment.
     */
    public static ListarCentrosSanitariosEnAlarmaFragment newInstance() {
        ListarCentrosSanitariosEnAlarmaFragment fragment = new ListarCentrosSanitariosEnAlarmaFragment();
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
        View root = inflater.inflate(R.layout.fragment_listar_centros_sanitarios_en_alarma, container, false);

        // Obtener el Recycler.
        recycler = (RecyclerView) root.findViewById(R.id.listRecyclerView);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout.
        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);

        //Cargamos un adaptador vacío mientras se carga la lista desde la API REST
        this.lCentrosEnAlarma = new ArrayList<>();
        adapter = new CentroSanitarioEnAlarmaAdapter(lCentrosEnAlarma);
        recycler.setAdapter(adapter);

        //Cargamos lista desde la API REST
        cargarLista();


        return root;
    }

    /**
     * Este método carga la lista que vamos a mostrar desde la API REST y una vez que se ha cargada
     * se las añade al adapter y se las cargamos al recycler.
     */
    private void cargarLista(){
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<Object>> call = apiService.getCentrosSanitariosEnAlarma(Constantes.BEARER_ESPACIO + Token.getToken().getAccess());
        call.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                if(response.isSuccessful()){
                    List<Object> lObjetos = response.body();
                    lCentrosEnAlarma = (ArrayList<CentroSanitarioEnAlarma>) Utilidad.getObjeto(lObjetos, Constantes.AL_CENTRO_SANITARIO_ALARMA);
                    adapter = new CentroSanitarioEnAlarmaAdapter(lCentrosEnAlarma);
                    recycler.setAdapter(adapter);
                }else{
                    Toast.makeText(getContext(), Constantes.ERROR_ + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                Toast.makeText(getContext(), Constantes.ERROR_+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}