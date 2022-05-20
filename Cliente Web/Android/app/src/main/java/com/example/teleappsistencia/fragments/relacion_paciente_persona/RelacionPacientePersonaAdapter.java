package com.example.teleappsistencia.fragments.relacion_paciente_persona;

import android.content.Context;
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
import com.example.teleappsistencia.clases.Persona;
import com.example.teleappsistencia.clases.RelacionPacientePersona;
import com.example.teleappsistencia.fragments.relacion_terminal_recurso_comunitario.ConsultarRelacionTerminalRecursoComunitarioFragment;

import java.util.List;

public class RelacionPacientePersonaAdapter extends RecyclerView.Adapter<RelacionPacientePersonaAdapter.RelacionPacientePersonaViewholder> {
    private List<com.example.teleappsistencia.clases.RelacionPacientePersona> items;
    private RelacionPacientePersonaViewholder relacionPacientePersonaViewholder;

    public static class RelacionPacientePersonaViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Campos respectivos de un item.
        public Context context;
        private ImageButton imageButtonModificarRelacionPacientePersona;
        private ImageButton imageButtonVerRelacionPacientePersona;
        private ImageButton imageButtonBorrarRelacionPacientePersona;
        private TextView tipoRelacionCard;
        private TextView prioridadCard;
        private TextView disponibilidadCard;
        private TextView pacienteRelacionCard;
        private TextView personaRelacionCard;
        private RelacionPacientePersona relacionPacientePersona;

        public RelacionPacientePersonaViewholder(View v) {
            super(v);
            this.context = v.getContext();
            this.imageButtonModificarRelacionPacientePersona = v.findViewById(R.id.imageButtonModificarRelacionPacientePersona);
            this.imageButtonVerRelacionPacientePersona = v.findViewById(R.id.imageButtonVerRelacionPacientePersona);
            this.imageButtonBorrarRelacionPacientePersona = v.findViewById(R.id.imageButtonBorrarRelacionPacientePersona);
            this.tipoRelacionCard = v.findViewById(R.id.tipoRelacionCard);
            this.prioridadCard = v.findViewById(R.id.prioridadCard);
            this.disponibilidadCard = v.findViewById(R.id.disponibilidadCard);
            this.pacienteRelacionCard = v.findViewById(R.id.pacienteRelacionCard);
            this.personaRelacionCard = v.findViewById(R.id.personaRelacionCard);
            this.setOnClickListeners();

        }

        public void setOnClickListeners() {
            this.imageButtonModificarRelacionPacientePersona.setOnClickListener(this);
            this.imageButtonVerRelacionPacientePersona.setOnClickListener(this);
            this.imageButtonBorrarRelacionPacientePersona.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();

            switch (view.getId()) {
                case R.id.imageButtonModificarRelacionPacientePersona:
                    ModificarRelacionPacientePersonaFragment modificarPacienteFragment = ModificarRelacionPacientePersonaFragment.newInstance(this.relacionPacientePersona);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, modificarPacienteFragment).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonVerRelacionPacientePersona:
                    ConsultarRelacionPacientePersonaFragment consultarPacienteFragment = ConsultarRelacionPacientePersonaFragment.newInstance(this.relacionPacientePersona);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, consultarPacienteFragment).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonBorrarRelacionPacientePersona:

                    break;
            }
        }

        public void setRelacionPacientePersonaViewholder(RelacionPacientePersona relacionPacientePersonaViewholder) {
            this.relacionPacientePersona = relacionPacientePersonaViewholder;
        }

    }

    public RelacionPacientePersonaAdapter(List<RelacionPacientePersona> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RelacionPacientePersonaViewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_relacion_paciente_persona_card, viewGroup, false);
        relacionPacientePersonaViewholder = new RelacionPacientePersonaViewholder(v);
        return relacionPacientePersonaViewholder;
    }

    @Override
    public void onBindViewHolder(RelacionPacientePersonaViewholder viewHolder, int i) {
        viewHolder.setOnClickListeners();
        viewHolder.setRelacionPacientePersonaViewholder(items.get(i));
        viewHolder.tipoRelacionCard.setText(items.get(i).getTipoRelacion());
        viewHolder.prioridadCard.setText("Prioridad: " + String.valueOf(items.get(i).getPrioridad()));
        viewHolder.disponibilidadCard.setText(items.get(i).getDisponibilidad());
        Paciente paciente = (Paciente) Utilidades.getObjeto(items.get(i).getIdPaciente(), "Paciente");
        viewHolder.pacienteRelacionCard.setText("SS del paciente: " + paciente.getNumeroSeguridadSocial());
        Persona persona = (Persona) Utilidades.getObjeto(items.get(i).getIdPersona(), "Persona");
        viewHolder.personaRelacionCard.setText("Persona de contacto: " +persona.getNombre() + " " + persona.getApellidos());
        relacionPacientePersonaViewholder.setRelacionPacientePersonaViewholder(items.get(i));
    }
}
