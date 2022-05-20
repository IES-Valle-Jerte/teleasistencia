package com.example.teleappsistencia.fragments.paciente;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utilidades;
import com.example.teleappsistencia.clases.Paciente;
import com.example.teleappsistencia.clases.TipoModalidadPaciente;

import java.util.List;

public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder> {
    private List<Paciente> items;
    private PacienteViewHolder pacienteViewHolder;

    public static class PacienteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Campos respectivos de un item.
        public Context context;
        private ImageButton imageButtonModificarPaciente;
        private ImageButton imageButtonVerPaciente;
        private ImageButton imageButtonBorrarPaciente;
        private TextView numSeguridadSocialCard;
        private TextView numeroExpedienteCard;
        private TextView tieneUCRCard;
        private TextView tipoModalidadPacienteCard;
        private Paciente paciente;

        public PacienteViewHolder(View v) {
            super(v);
            this.context = v.getContext();
            this.imageButtonModificarPaciente = v.findViewById(R.id.imageButtonModificarRelacionPacientePersona);
            this.imageButtonVerPaciente = v.findViewById(R.id.imageButtonVerRelacionPacientePersona);
            this.imageButtonBorrarPaciente = v.findViewById(R.id.imageButtonBorrarRelacionPacientePersona);
            this.numSeguridadSocialCard = v.findViewById(R.id.numSeguridadSocialCard);
            this.numeroExpedienteCard = v.findViewById(R.id.numeroExpedienteCard);
            this.tieneUCRCard = v.findViewById(R.id.tieneUCRCard);
            this.tipoModalidadPacienteCard = v.findViewById(R.id.tipoModalidadPacienteCard);
        }

        public void setOnClickListeners() {
            this.imageButtonModificarPaciente.setOnClickListener(this);
            this.imageButtonVerPaciente.setOnClickListener(this);
            this.imageButtonBorrarPaciente.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();

            switch (view.getId()) {
                case R.id.imageButtonModificarRelacionPacientePersona:
                    ModificarPacienteFragment modificarPacienteFragment = ModificarPacienteFragment.newInstance(this.paciente);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, modificarPacienteFragment).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonVerRelacionPacientePersona:
                    ConsultarPacienteFragment consultarPacienteFragment = ConsultarPacienteFragment.newInstance(this.paciente);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, consultarPacienteFragment).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonBorrarRelacionPacientePersona:

                    break;
            }
        }

        public void setPaciente(Paciente paciente) {
            this.paciente = paciente;
        }
    }

    public PacienteAdapter(List<Paciente> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PacienteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_paciente_card, viewGroup, false);
        pacienteViewHolder = new PacienteViewHolder(v);
        return pacienteViewHolder;
    }

    @Override
    public void onBindViewHolder(PacienteViewHolder viewHolder, int i) {
        viewHolder.setOnClickListeners();
        System.out.println(items.get(i));
        viewHolder.numSeguridadSocialCard.setText(items.get(i).getNumeroSeguridadSocial());
        viewHolder.numeroExpedienteCard.setText("Exp:" + items.get(i).getNumeroExpediente());
        if (items.get(i).isTieneUcr()) {
            viewHolder.tieneUCRCard.setText("El paciente tiene UCR");
            viewHolder.tieneUCRCard.setTextColor(Color.RED);
        } else {
            viewHolder.tieneUCRCard.setText("El paciente no tiene UCR");
            viewHolder.tieneUCRCard.setTextColor(Color.GREEN);
        }
        TipoModalidadPaciente tipoModalidadPaciente = (TipoModalidadPaciente) Utilidades.getObjeto(items.get(i).getTipoModalidadPaciente(), "TipoModalidadPaciente");
        viewHolder.tipoModalidadPacienteCard.setText(tipoModalidadPaciente.getNombre());
        //Falta por añadir los atributos del paciente
        /*viewHolder.textView_id.setText(viewHolder.context.getString(R.string.id_con_dos_puntos) + Integer.toString(items.get(i).getId()));
        viewHolder.textView_localidad.setText(items.get(i).getLocalidad());
        viewHolder.textView_provincia.setText(items.get(i).getProvincia());
        viewHolder.textView_direccion.setText(items.get(i).getDireccion());
        direccionViewHolder.setDireccion(items.get(i));*/
        pacienteViewHolder.setPaciente(items.get(i));
    }
}
