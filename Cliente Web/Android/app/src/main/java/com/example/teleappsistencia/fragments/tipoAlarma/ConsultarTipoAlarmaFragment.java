package com.example.teleappsistencia.fragments.tipoAlarma;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teleappsistencia.R;
import com.example.teleappsistencia.api.clases.ClasificacionAlarma;
import com.example.teleappsistencia.api.clases.TipoAlarma;
import com.example.teleappsistencia.utilidad.Utilidad;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultarTipoAlarmaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarTipoAlarmaFragment extends Fragment {


    private static final String ARG_TIPOALARMA = "TipoAlarma";
    private TipoAlarma tipoAlarma;
    private TextView textViewConsultarIdTipoAlarma;
    private TextView textViewConsultarNombreTipoAlarma;
    private TextView textViewConsultarCodigoTipoAlarma;
    private TextView textViewConsultarEsDispositivoTipoAlarma;
    private TextView textViewConsultarNombreClasificacionTipoAlarma;
    private TextView textViewConsultarCodigoClasificacionTipoAlarma;


    public ConsultarTipoAlarmaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConsultarTipoAlarmaFragment.
     */

    public static ConsultarTipoAlarmaFragment newInstance(TipoAlarma tipoAlarma) {
        ConsultarTipoAlarmaFragment fragment = new ConsultarTipoAlarmaFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIPOALARMA, tipoAlarma);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipoAlarma = (TipoAlarma) getArguments().getSerializable(ARG_TIPOALARMA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultar_tipo_alarma, container, false);

        // Capturar los elementos del layout
        capturarElementos(view);

        //Cargamos los datos
        if(this.tipoAlarma != null){
            cargarDatos();
        }

        return view;
    }

    private void capturarElementos(View view){
        this.textViewConsultarIdTipoAlarma = (TextView) view.findViewById(R.id.textViewConsultarIdTipoAlarma);
        this.textViewConsultarNombreTipoAlarma = (TextView) view.findViewById(R.id.textViewConsultarNombreTipoAlarma);
        this.textViewConsultarCodigoTipoAlarma = (TextView) view.findViewById(R.id.textViewConsultarCodigoTipoAlarma);
        this.textViewConsultarEsDispositivoTipoAlarma = (TextView) view.findViewById(R.id.textViewConsultarEsDispositivoTipoAlarma);
        this.textViewConsultarNombreClasificacionTipoAlarma = (TextView) view.findViewById(R.id.textViewConsultarNombreClasificacionTipoAlarma);
        this.textViewConsultarCodigoClasificacionTipoAlarma = (TextView) view.findViewById(R.id.textViewConsultarCodigoClasificacionTipoAlarma);
    }

    private void cargarDatos(){
        ClasificacionAlarma clasificacionAlarma = (ClasificacionAlarma) Utilidad.getObjeto(this.tipoAlarma.getClasificacionAlarma(), "ClasificacionAlarma");
        this.textViewConsultarIdTipoAlarma.setText(String.valueOf(this.tipoAlarma.getId()));
        this.textViewConsultarNombreTipoAlarma.setText(this.tipoAlarma.getNombre());
        this.textViewConsultarCodigoTipoAlarma.setText(this.tipoAlarma.getCodigo());
        this.textViewConsultarEsDispositivoTipoAlarma.setText(Utilidad.trueSifalseNo(this.tipoAlarma.isEsDispositivo()));
        this.textViewConsultarNombreClasificacionTipoAlarma.setText(clasificacionAlarma.getNombre());
        this.textViewConsultarCodigoClasificacionTipoAlarma.setText(clasificacionAlarma.getCodigo());
    }
}