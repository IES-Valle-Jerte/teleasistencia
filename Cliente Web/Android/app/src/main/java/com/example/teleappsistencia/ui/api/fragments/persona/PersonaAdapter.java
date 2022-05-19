package com.example.teleappsistencia.ui.api.fragments.persona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleappsistencia.R;
import com.example.teleappsistencia.ui.api.APIService;
import com.example.teleappsistencia.ui.api.ClienteRetrofit;
import com.example.teleappsistencia.ui.dialogs.AlertDialogBuilder;
import com.example.teleappsistencia.ui.clases.Persona;
import com.example.teleappsistencia.ui.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {

    private List<Persona> items;
    private PersonaViewHolder personaViewHolder;

    public static class PersonaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Campos respectivos de un item.
        public Context context;
        public TextView textView_nombreApellidos;
        public TextView textView_dni;
        public TextView textView_fechaNacimiento;
        private ImageButton imgBtn_modificar;
        private ImageButton imgBtn_ver;
        private ImageButton imgBtn_borrar;

        private Persona persona;

        public PersonaViewHolder(View v) {
            super(v);
            this.context = v.getContext();
            this.textView_nombreApellidos = v.findViewById(R.id.textView_nombreApellidos_persona);
            this.textView_dni = v.findViewById(R.id.textView_dni_persona);
            this.textView_fechaNacimiento = v.findViewById(R.id.textView_fechaNacimiento_persona);
            this.imgBtn_modificar = v.findViewById(R.id.imageButtonModificar);
            this.imgBtn_ver = v.findViewById(R.id.imageButtonVer);
            this.imgBtn_borrar = v.findViewById(R.id.imageButtonBorrar);
        }

        public void setOnClickListeners() {
            this.imgBtn_modificar.setOnClickListener(this);
            this.imgBtn_ver.setOnClickListener(this);
            this.imgBtn_borrar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();

            switch (view.getId()) {
                case R.id.imageButtonModificar:
                    // Llamar al Fragment ModificarPersonaFragment.
                    ModificarPersonaFragment fragmentModificar = ModificarPersonaFragment.newInstance(this.persona);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragmentModificar).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonVer:
                    // Llamar al Fragment ConsultarPersonaFragment.
                    ConsultarPersonaFragment fragmentConsultar = ConsultarPersonaFragment.newInstance(this.persona);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragmentConsultar).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonBorrar:
                    borrarPersona();
                    break;
            }
        }

        private void borrarPersona() {
            APIService apiService = ClienteRetrofit.getInstance().getAPIService();

            Call<Response<String>> call = apiService.deletePersona(persona.getId(), "Bearer " + Utils.getToken().getAccess());
            call.enqueue(new Callback<Response<String>>() {
                @Override
                public void onResponse(Call<Response<String>> call, Response<Response<String>> response) {
                    if (response.isSuccessful()) {
                        Response<String> respuesta = response.body();
                        AlertDialogBuilder.crearInfoAlerDialog(context, context.getString(R.string.infoAlertDialog_eliminado_persona));
                    } else {
                        AlertDialogBuilder.crearErrorAlerDialog(context, Integer.toString(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<Response<String>> call, Throwable t) {
                    t.printStackTrace();
                    System.out.println(t.getMessage());
                }
            });
        }

        public void setPersona(Persona persona) {
            this.persona = persona;
        }
    }

    public PersonaAdapter(List<Persona> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PersonaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_card_persona, viewGroup, false);
        personaViewHolder = new PersonaViewHolder(v);
        return personaViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonaViewHolder viewHolder, int i) {
        viewHolder.setOnClickListeners();
        viewHolder.textView_nombreApellidos.setText(items.get(i).getNombre() + viewHolder.context.getString(R.string.espacio_en_blanco) + items.get(i).getApellidos());
        viewHolder.textView_dni.setText(items.get(i).getDni());
        viewHolder.textView_fechaNacimiento.setText(items.get(i).getFechaNacimiento());
        personaViewHolder.setPersona(items.get(i));
    }
}
