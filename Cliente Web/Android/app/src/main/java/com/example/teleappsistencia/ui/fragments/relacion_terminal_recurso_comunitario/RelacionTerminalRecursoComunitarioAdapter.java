package com.example.teleappsistencia.ui.fragments.relacion_terminal_recurso_comunitario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleappsistencia.R;
import com.example.teleappsistencia.utilidades.Utilidad;
import com.example.teleappsistencia.modelos.RecursoComunitario;
import com.example.teleappsistencia.modelos.RelacionTerminalRecursoComunitario;
import com.example.teleappsistencia.modelos.Terminal;

import java.util.List;

public class RelacionTerminalRecursoComunitarioAdapter extends RecyclerView.Adapter<RelacionTerminalRecursoComunitarioAdapter.RelacionTerminalRecursoComunitarioViewholder> {
    private List<RelacionTerminalRecursoComunitario> items;
    private RelacionTerminalRecursoComunitarioViewholder relacionTerminalRecursoComunitarioViewholder;

    public static class RelacionTerminalRecursoComunitarioViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Campos respectivos de un item.
        public Context context;
        private ImageButton imageButtonModificarRelacionTerminalRecursoComunitario;
        private ImageButton imageButtonVerRelacionTerminalRecursoComunitario;
        private ImageButton imageButtonBorrarRelacionTerminalRecursoComunitario;
        private TextView idRelacionTerminalRecursoComunitario;
        private TextView numeroTerminalCard;
        private TextView recursoComunitarioCard;
        private RelacionTerminalRecursoComunitario relacionTerminalRecursoComunitario;

        public RelacionTerminalRecursoComunitarioViewholder(View v) {
            super(v);
            this.context = v.getContext();
            this.imageButtonModificarRelacionTerminalRecursoComunitario = v.findViewById(R.id.imageButtonModificarRelacionTerminalRecursoComunitario);
            this.imageButtonVerRelacionTerminalRecursoComunitario = v.findViewById(R.id.imageButtonVerRelacionTerminalRecursoComunitario);
            this.imageButtonBorrarRelacionTerminalRecursoComunitario = v.findViewById(R.id.imageButtonBorrarRelacionTerminalRecursoComunitario);
            this.idRelacionTerminalRecursoComunitario = v.findViewById(R.id.idRelacionTerminalRecursoComunitario);
            this.recursoComunitarioCard = v.findViewById(R.id.recursoComunitarioCard);
            this.numeroTerminalCard = v.findViewById(R.id.numeroTerminalCard);
        }

        public void setOnClickListeners() {
            this.imageButtonModificarRelacionTerminalRecursoComunitario.setOnClickListener(this);
            this.imageButtonVerRelacionTerminalRecursoComunitario.setOnClickListener(this);
            this.imageButtonBorrarRelacionTerminalRecursoComunitario.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();

            switch (view.getId()) {
                case R.id.imageButtonModificarRelacionPacientePersona:
                    ModificarRelacionTerminalRecursoComunitarioFragment modificarPacienteFragment = ModificarRelacionTerminalRecursoComunitarioFragment.newInstance(this.relacionTerminalRecursoComunitario);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, modificarPacienteFragment).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonVerRelacionPacientePersona:
                    ConsultarRelacionTerminalRecursoComunitarioFragment consultarPacienteFragment = ConsultarRelacionTerminalRecursoComunitarioFragment.newInstance(this.relacionTerminalRecursoComunitario);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, consultarPacienteFragment).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonBorrarRelacionPacientePersona:

                    break;
            }
        }

        public void setRelacionTerminalRecursoComunitario(RelacionTerminalRecursoComunitario relacionTerminalRecursoComunitario) {
            this.relacionTerminalRecursoComunitario = relacionTerminalRecursoComunitario;
        }
    }

    public RelacionTerminalRecursoComunitarioAdapter(List<RelacionTerminalRecursoComunitario> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RelacionTerminalRecursoComunitarioViewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_relacion_terminal_recurso_comunitario_card, viewGroup, false);
        relacionTerminalRecursoComunitarioViewholder = new RelacionTerminalRecursoComunitarioViewholder(v);
        return relacionTerminalRecursoComunitarioViewholder;
    }

    @Override
    public void onBindViewHolder(RelacionTerminalRecursoComunitarioViewholder viewHolder, int i) {
        viewHolder.setOnClickListeners();
        viewHolder.idRelacionTerminalRecursoComunitario.setText("ID: " + String.valueOf(items.get(i).getId()));
        Terminal terminal = (Terminal) Utilidad.getObjeto(items.get(i).getIdTerminal(), "Terminal");
        viewHolder.numeroTerminalCard.setText("Nº de terminal: " + terminal.getNumeroTerminal());
        RecursoComunitario recursoComunitario = (RecursoComunitario) Utilidad.getObjeto(items.get(i).getIdRecursoComunitario(), "RecursoComunitario");
        viewHolder.recursoComunitarioCard.setText("Nombre: " + recursoComunitario.getNombre());
    }
}
