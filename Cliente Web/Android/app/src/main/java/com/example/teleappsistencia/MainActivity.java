package com.example.teleappsistencia;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.teleappsistencia.clases.Token;
import com.example.teleappsistencia.clases.Usuario;
import com.example.teleappsistencia.fragments.api.direccion.InsertarDireccionFragment;
import com.example.teleappsistencia.fragments.api.direccion.ListarDireccionFragment;
import com.example.teleappsistencia.fragments.api.dispositivos_aux.InsertarDispositivosAuxiliaresFragment;
import com.example.teleappsistencia.fragments.api.dispositivos_aux.ListarDispositivosAuxiliaresFragment;
import com.example.teleappsistencia.fragments.api.grupos.InsertarGruposFragment;
import com.example.teleappsistencia.fragments.api.grupos.ListarGruposFragment;
import com.example.teleappsistencia.fragments.api.historico_tipo_situacion.InsertarHistoricoTipoSituacionFragment;
import com.example.teleappsistencia.fragments.api.historico_tipo_situacion.ListarHistoricoTipoSituacionFragment;
import com.example.teleappsistencia.fragments.api.persona.InsertarPersonaFragment;
import com.example.teleappsistencia.fragments.api.persona.ListarPersonaFragment;
import com.example.teleappsistencia.fragments.api.tipo_situacion.InsertarTipoSituacionFragment;
import com.example.teleappsistencia.fragments.api.tipo_situacion.ListarTipoSituacionFragment;
import com.example.teleappsistencia.fragments.api.tipo_vivienda.InsertarTipoViviendaFragment;
import com.example.teleappsistencia.fragments.api.tipo_vivienda.ListarTipoViviendaFragment;
import com.example.teleappsistencia.fragments.api.usuarios.InsertarUsuariosFragment;
import com.example.teleappsistencia.fragments.api.usuarios.ListarUsuariosFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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

    public static Token token;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.api_base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.apiService = retrofit.create(APIService.class);
    }

    private void loadMenuHeader(){
        String username = getIntent().getExtras().getString("usuario");
        Call<List<Usuario>> call = apiService.getUserByUsername(username, "Bearer " + token.getAccess());
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()) {
                    List<Usuario> usuariosList = response.body();
                    Usuario usuario = usuariosList.get(0);
                    TextView nombreUsuario = (TextView) findViewById(R.id.textView_nombre_usuario);
                    TextView emailUsuario = (TextView) findViewById(R.id.textView_email_usuario);

                    nombreUsuario.setText(usuario.getFirstName() + " " + usuario.getLastName());
                    emailUsuario.setText((String) usuario.getEmail());
                } else{
                    System.out.println(response.raw());
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
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
        childModelsList.add(new MenuModel(childNames[0], false, false, new InsertarUsuariosFragment()));
        childModelsList.add(new MenuModel(childNames[1], false, false, new ListarUsuariosFragment()));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Personas.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_persona), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, new InsertarPersonaFragment()));
        childModelsList.add(new MenuModel(childNames[1], false, false, new ListarPersonaFragment()));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Dirección.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_direccion), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, new InsertarDireccionFragment()));
        childModelsList.add(new MenuModel(childNames[1], false, false, new ListarDireccionFragment()));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Grupos.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_grupos), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, new InsertarGruposFragment()));
        childModelsList.add(new MenuModel(childNames[1], false, false, new ListarGruposFragment()));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Dispositivos Auxiliares.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_dispositivos_auxiliares_terminal), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, new InsertarDispositivosAuxiliaresFragment()));
        childModelsList.add(new MenuModel(childNames[1], false, false, new ListarDispositivosAuxiliaresFragment()));

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
        childModelsList.add(new MenuModel(childNames[0], false, false, new InsertarTipoSituacionFragment()));
        childModelsList.add(new MenuModel(childNames[1], false, false, new ListarTipoSituacionFragment()));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else{
            childList.put(menuModel, null);
        }

        // Menu Histórico Tipo Situación.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_historico_tipo_situacion), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, new InsertarHistoricoTipoSituacionFragment()));
        childModelsList.add(new MenuModel(childNames[1], false, false, new ListarHistoricoTipoSituacionFragment()));

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
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }
}