package com.example.teleappsistencia.fragments.paciente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.teleappsistencia.R;
import com.example.teleappsistencia.clases.Paciente;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultarPacienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarPacienteFragment extends Fragment{

    private Paciente paciente;
    private TextView textViewConsultarIdTerminal;
    private TextView textViewConsultarIdPersona;
    private TextView textViewConsultarTieneUCR;
    private TextView textViewConsultarNumeroExpediente;
    private TextView textViewConsultarNumeroSeguridadSocial;
    private TextView textViewConsultarPrestacionOtrosServiciosSociales;
    private TextView textViewConsultarObservacionesMedicas;
    private TextView textViewConsultarInteresesActividades;
    private TextView textViewConsultarModalidadPaciente;

    public ConsultarPacienteFragment() {
        // Required empty public constructor
    }

    public static ConsultarPacienteFragment newInstance(Paciente paciente) {
        ConsultarPacienteFragment fragment = new ConsultarPacienteFragment();
        Bundle args = new Bundle();
        args.putSerializable("objetoPaciente", paciente);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paciente = (Paciente) getArguments().getSerializable("objetoPaciente");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_consultar_paciente, container, false);
        textViewConsultarIdTerminal = root.findViewById(R.id.textViewConsultarIdTerminal);
        textViewConsultarIdPersona = root.findViewById(R.id.textViewConsultarIdPersona);
        textViewConsultarTieneUCR = root.findViewById(R.id.textViewConsultarTieneUCR);
        textViewConsultarNumeroExpediente = root.findViewById(R.id.textViewConsultarNumeroExpediente);
        textViewConsultarNumeroSeguridadSocial = root.findViewById(R.id.textViewConsultarNumeroSeguridadSocial);
        textViewConsultarPrestacionOtrosServiciosSociales = root.findViewById(R.id.textViewConsultarPrestacionOtrosServiciosSociales);
        textViewConsultarObservacionesMedicas = root.findViewById(R.id.textViewConsultarObservacionesMedicas);
        textViewConsultarInteresesActividades = root.findViewById(R.id.textViewConsultarInteresesActividades);

        textViewConsultarIdTerminal.setText(String.valueOf(paciente.getId()));
        if (paciente.isTieneUcr()) {
            textViewConsultarTieneUCR.setText("El paciente tiene UCR");
        } else {
            textViewConsultarTieneUCR.setText("El paciente no tiene UCR");
        }
        textViewConsultarNumeroExpediente.setText(paciente.getNumeroExpediente());
        textViewConsultarNumeroSeguridadSocial.setText(paciente.getNumeroSeguridadSocial());
        textViewConsultarPrestacionOtrosServiciosSociales.setText(paciente.getPrestacionOtrosServiciosSociales());
        textViewConsultarObservacionesMedicas.setText(paciente.getObservacionesMedicas());
        textViewConsultarInteresesActividades.setText(paciente.getInteresesYActividades());


        // Inflate the layout for this fragment
        return root;
    }

}