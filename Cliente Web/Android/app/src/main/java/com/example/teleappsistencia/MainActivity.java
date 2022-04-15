package com.example.teleappsistencia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.example.teleappsistencia.clases.Token;
import com.example.teleappsistencia.clases.UsuarioSistema;
import com.example.teleappsistencia.fragments.InsertarTipoViviendaFragment;
import com.example.teleappsistencia.fragments.ListarTipoViviendaFragment;
import com.example.teleappsistencia.ui.tipo_situacion.TipoSituacionFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private List<MenuModel> headerList = new ArrayList<>();
    private HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    private Token token;

    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.token = (Token) getIntent().getExtras().get("token");

        // Cargo el servicio que se encarga de realizar las peticiones.
        loadApiService();
        // Realizo una petición a la API para cargar la cabecera del menu con los datos del usuario logueado.
        loadMenuHeader();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadApiService(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                //Si la conexión del servidor es lenta, no intenta de nuevo y evita una nueva petición (OKHTTP si la conexión es lenta, intenta de nuevo)
                .retryOnConnectionFailure(Boolean.FALSE)
                .build();

        /*client.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest;

                newRequest = request.newBuilder()
                        .addHeader("Authorization", "Bearer " + token.getRefresh())
                        .build();

                return chain.proceed(newRequest);
            }
        });*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:3333/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.apiService = retrofit.create(APIService.class);
    }

    private void loadMenuHeader(){
        String username = getIntent().getExtras().getString("usuario");
        Call<List<UsuarioSistema>> call = apiService.getUserByUsername(username, "Bearer " + token.getAccess());
        call.enqueue(new Callback<List<UsuarioSistema>>() {
            @Override
            public void onResponse(Call<List<UsuarioSistema>> call, Response<List<UsuarioSistema>> response) {
                if(response.isSuccessful()) {
                    List<UsuarioSistema> usuariosList = response.body();
                    UsuarioSistema usuarioSistema = usuariosList.get(0);
                    TextView nombreUsuario = (TextView) findViewById(R.id.textView_nombre_usuario);
                    TextView emailUsuario = (TextView) findViewById(R.id.textView_email_usuario);

                    nombreUsuario.setText(usuarioSistema.getFirstName() + " " + usuarioSistema.getLastName());
                    emailUsuario.setText(usuarioSistema.getEmail());
                } else{
                    System.out.println(response.raw());
                }
            }

            @Override
            public void onFailure(Call<List<UsuarioSistema>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        /* Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } /*else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {
        String[] childNames = {getResources().getString(R.string.insertar), getResources().getString(R.string.listar)};
        List<MenuModel> childModelsList;
        MenuModel menuModel;

        // Menu Usuarios.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_usuarios), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, null));
        childModelsList.add(new MenuModel(childNames[1], false, false, null));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Personas.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_persona), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, null));
        childModelsList.add(new MenuModel(childNames[1], false, false, null));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Dirección.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_direccion), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, null));
        childModelsList.add(new MenuModel(childNames[1], false, false, null));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Grupos.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_grupos), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, null));
        childModelsList.add(new MenuModel(childNames[1], false, false, null));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Dispositivos Auxiliares.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_dispositivos_auxiliares_terminal), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, null));
        childModelsList.add(new MenuModel(childNames[1], false, false, null));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Tipo Vivienda.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_tipo_vivienda), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, new InsertarTipoViviendaFragment()));
        childModelsList.add(new MenuModel(childNames[1], false, false, new ListarTipoViviendaFragment()));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Tipo Situación.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_tipo_situacion), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, null));
        childModelsList.add(new MenuModel(childNames[1], false, false, null));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Histórico Tipo Situación.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_historico_tipo_situacion), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, null));
        childModelsList.add(new MenuModel(childNames[1], false, false, null));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup()) {
                    if (!headerList.get(groupPosition).hasChildren()) {
                        /*
                        MenuModel model = headerList.get(groupPosition);
                        Fragment fragment = new Fragment(model.getLayout());

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_fragment, fragment)
                                .addToBackStack(null)
                                .commit();
                         */
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fragment, model.getFragment())
                            .addToBackStack(null)
                            .commit();
                }

                return false;
            }
        });
    }
}