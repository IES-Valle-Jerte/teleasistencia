package com.example.teleappsistencia.ui.api.fragments.dispositivos_aux;

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
import com.example.teleappsistencia.ui.clases.DispositivoAuxiliar;
import com.example.teleappsistencia.ui.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DispositivosAuxiliaresAdapter extends RecyclerView.Adapter<DispositivosAuxiliaresAdapter.DispositivosAuxiliaresViewHolder> {

    private List<DispositivoAuxiliar> items;
    private DispositivosAuxiliaresViewHolder dispositivosAuxiliarerViewHolder;

    public static class DispositivosAuxiliaresViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Campos respectivos de un item.
        public Context context;
        public TextView textView_id;
        private ImageButton imgBtn_modificar;
        private ImageButton imgBtn_ver;
        private ImageButton imgBtn_borrar;

        private DispositivoAuxiliar dispositivoAuxiliar;

        public DispositivosAuxiliaresViewHolder(View v) {
            super(v);
            this.context = v.getContext();
            this.textView_id = v.findViewById(R.id.textView_id_dispositivoAuxiliar);
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
                    // Llamar al Fragment ModificarDispositivosAuxiliaresFragment.
                    ModificarDispositivosAuxiliaresFragment fragmentModificar = ModificarDispositivosAuxiliaresFragment.newInstance(this.dispositivoAuxiliar);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragmentModificar).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonVer:
                    // Llamar al Fragment ConsultarDispositivosAuxiliaresFragment.
                    ConsultarDispositivosAuxiliaresFragment fragmentConsultar = ConsultarDispositivosAuxiliaresFragment.newInstance(this.dispositivoAuxiliar);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragmentConsultar).addToBackStack(null).commit();
                    break;
                case R.id.imageButtonBorrar:
                    borrarDispositivoAuxiliar();
                    break;
            }
        }

        private void borrarDispositivoAuxiliar() {
            APIService apiService = ClienteRetrofit.getInstance().getAPIService();

            Call<Response<String>> call = apiService.deleteDispositivosAuxiliar(dispositivoAuxiliar.getId(), "Bearer " + Utils.getToken().getAccess());
            call.enqueue(new Callback<Response<String>>() {
                @Override
                public void onResponse(Call<Response<String>> call, Response<Response<String>> response) {
                    if (response.isSuccessful()) {
                        Response<String> respuesta = response.body();
                        AlertDialogBuilder.crearInfoAlerDialog(context, context.getString(R.string.infoAlertDialog_eliminado_dispositivoAuxiliar));
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

        public void setDispositivoAuxiliar(DispositivoAuxiliar dispositivoAuxiliar) {
            this.dispositivoAuxiliar = dispositivoAuxiliar;
        }
    }

    public DispositivosAuxiliaresAdapter(List<DispositivoAuxiliar> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public DispositivosAuxiliaresViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_card_dispositivos_auxiliares, viewGroup, false);
        dispositivosAuxiliarerViewHolder = new DispositivosAuxiliaresViewHolder(v);
        return dispositivosAuxiliarerViewHolder;
    }

    @Override
    public void onBindViewHolder(DispositivosAuxiliaresViewHolder viewHolder, int i) {
        viewHolder.setOnClickListeners();
        viewHolder.textView_id.setText(viewHolder.context.getString(R.string.id_con_dos_puntos) + items.get(i).getId());
        dispositivosAuxiliarerViewHolder.setDispositivoAuxiliar(items.get(i));
    }
}
