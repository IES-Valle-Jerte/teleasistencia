package com.example.teleappsistencia.ui.fragments.usuarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleappsistencia.R;
import com.example.teleappsistencia.servicios.APIService;
import com.example.teleappsistencia.servicios.ClienteRetrofit;
import com.example.teleappsistencia.utilidades.dialogs.AlertDialogBuilder;
import com.example.teleappsistencia.modelos.Grupo;
import com.example.teleappsistencia.modelos.Usuario;
import com.example.teleappsistencia.utilidades.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosViewHolder> {

    private List<Usuario> items;
    private UsuariosViewHolder usuariosViewHolder;

    public static class UsuariosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Campos respectivos de un item.
        public Context context;
        public TextView textView_nombreUsuario;
        public TextView textView_nombreApellidos;
        public TextView textView_email;
        public TextView textView_grupo;
        private ImageButton imgBtn_modificar;
        private ImageButton imgBtn_ver;
        private ImageButton imgBtn_borrar;

        private Usuario usuario;

        public UsuariosViewHolder(View v) {
            super(v);
            this.context = v.getContext();
            this.textView_nombreUsuario = v.findViewById(R.id.textView_nombreUsuario_usuario);
            this.textView_nombreApellidos = v.findViewById(R.id.textView_nombreApellidos_usuario);
            this.textView_email = v.findViewById(R.id.textView_email_usuario);
            this.textView_grupo = v.findViewById(R.id.textView_grupo_usuario);
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
                    // Llamar al Fragment ModificarUsuariosFragment.
                    ModificarUsuariosFragment fragmentModificar = ModificarUsuariosFragment.newInstance(this.usuario);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragmentModificar).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonVer:
                    // Llamar al Fragment ConsultarUsuariosFragment.
                    ConsultarUsuariosFragment fragmentConsultar = ConsultarUsuariosFragment.newInstance(this.usuario);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragmentConsultar).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonBorrar:
                    borrarUsuario();
                    break;
            }
        }

        private void borrarUsuario() {
            APIService apiService = ClienteRetrofit.getInstance().getAPIService();

            Call<Response<String>> call = apiService.deleteUser(usuario.getPk(), "Bearer " + Utils.getToken().getAccess());
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

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }
    }

        public UsuariosAdapter(List<Usuario> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public UsuariosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_card_usuarios, viewGroup, false);
        usuariosViewHolder = new UsuariosViewHolder(v);
        return usuariosViewHolder;
    }

    @Override
    public void onBindViewHolder(UsuariosViewHolder viewHolder, int i) {
        List<Grupo> grupos = (List<Grupo>) items.get(i).getGroups();
        Grupo grupo;
        if(!grupos.isEmpty()) {
           grupo = (Grupo) Utils.getObjeto(grupos.get(0), viewHolder.context.getString(R.string.grupo_class));
           viewHolder.textView_grupo.setText(grupo.getName());
        } else {
            viewHolder.textView_grupo.setText(viewHolder.context.getString(R.string.string_vacio));
        }

        viewHolder.setOnClickListeners();
        viewHolder.textView_nombreUsuario.setText(items.get(i).getUsername());
        viewHolder.textView_nombreApellidos.setText(items.get(i).getFirstName() + viewHolder.context.getString(R.string.espacio_en_blanco) + items.get(i).getLastName());
        viewHolder.textView_email.setText(items.get(i).getEmail());
        usuariosViewHolder.setUsuario(items.get(i));
    }
}
