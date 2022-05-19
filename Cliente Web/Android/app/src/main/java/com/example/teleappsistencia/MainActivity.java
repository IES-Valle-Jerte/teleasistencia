package com.example.teleappsistencia;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.teleappsistencia.ui.clases.Usuario;
import com.example.teleappsistencia.ui.api.fragments.direccion.InsertarDireccionFragment;
import com.example.teleappsistencia.ui.api.fragments.direccion.ListarDireccionFragment;
import com.example.teleappsistencia.ui.api.fragments.dispositivos_aux.InsertarDispositivosAuxiliaresFragment;
import com.example.teleappsistencia.ui.api.fragments.dispositivos_aux.ListarDispositivosAuxiliaresFragment;
import com.example.teleappsistencia.ui.api.fragments.grupos.InsertarGruposFragment;
import com.example.teleappsistencia.ui.api.fragments.grupos.ListarGruposFragment;
import com.example.teleappsistencia.ui.api.fragments.historico_tipo_situacion.InsertarHistoricoTipoSituacionFragment;
import com.example.teleappsistencia.ui.api.fragments.historico_tipo_situacion.ListarHistoricoTipoSituacionFragment;
import com.example.teleappsistencia.ui.api.fragments.persona.InsertarPersonaFragment;
import com.example.teleappsistencia.ui.api.fragments.persona.ListarPersonaFragment;
import com.example.teleappsistencia.ui.api.fragments.tipo_situacion.InsertarTipoSituacionFragment;
import com.example.teleappsistencia.ui.api.fragments.tipo_situacion.ListarTipoSituacionFragment;
import com.example.teleappsistencia.ui.api.fragments.tipo_vivienda.InsertarTipoViviendaFragment;
import com.example.teleappsistencia.ui.api.fragments.tipo_vivienda.ListarTipoViviendaFragment;
import com.example.teleappsistencia.ui.api.fragments.usuarios.InsertarUsuariosFragment;
import com.example.teleappsistencia.ui.api.fragments.usuarios.ListarUsuariosFragment;
import com.example.teleappsistencia.ui.expandable_list.ExpandableListAdapter;
import com.example.teleappsistencia.ui.expandable_list.MenuModel;
import com.example.teleappsistencia.ui.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private List<MenuModel> headerList = new ArrayList<>();
    private HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    private TextView textView_nombre_usuarioLogged;
    private TextView textView_email_usuarioLogged;
    private ImageView imageView_fotoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setVisibility(View.INVISIBLE);

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


        // Realizo una petición a la API para cargar la cabecera del menu con los datos del usuario logueado.
        loadMenuHeader();
    }


    /**
     * Método que carga los datos del usuario en la cabezera del menú.
     */
    private void loadMenuHeader() {
        // Recogo el NavigationView para poder asignar los datos.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        textView_nombre_usuarioLogged = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView_nombre_usuarioLogged);
        textView_email_usuarioLogged = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView_email_usuarioLogged);
        imageView_fotoPerfil = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView_usuario);

        Usuario usuario = Utils.getUserLogged();    // Recogo el usuario de la clase Utils.
        String espacio = getString(R.string.espacio_en_blanco);
        if (usuario != null) {  // Si existe el usuario.
            // Le asigno el nombre y apellidos.
            textView_nombre_usuarioLogged.setText(usuario.getFirstName() + espacio + usuario.getLastName());
            textView_email_usuarioLogged.setText(usuario.getEmail());
            if(usuario.getImagen() != null) {  // Si el usuario cuenta con una imagen.
                String img_url = usuario.getImagen().getUrl(); // Recogo la imagen del usuario.
                Picasso.get()       // LLamo a Picasso para poder asignar una imagen por URL.
                        .load(img_url)  // Cargo la URL.
                        .error(R.drawable.rounded_default_user) // Si sucede un error se utiliza la imagen por defecto.
                        .into(imageView_fotoPerfil); // Carga la imagen en el imageView.
            }
        }
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {
        String[] childNames = {getResources().getString(R.string.insertar), getResources().getString(R.string.listar)};
        List<MenuModel> childModelsList;
        MenuModel menuModel;

        // Menu Personas.
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel(getResources().getString(R.string.menu_persona), true, true, null);
        headerList.add(menuModel);
        childModelsList.add(new MenuModel(childNames[0], false, false, new InsertarPersonaFragment()));
        childModelsList.add(new MenuModel(childNames[1], false, false, new ListarPersonaFragment()));

        if (menuModel.hasChildren()) {
            childList.put(menuModel, childModelsList);
        } else {
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
        } else {
            childList.put(menuModel, null);
        }

        if(Utils.isAdmin()) {
            // Menu Usuarios.
            childModelsList = new ArrayList<>();
            menuModel = new MenuModel(getResources().getString(R.string.menu_usuarios), true, true, null);
            headerList.add(menuModel);
            childModelsList.add(new MenuModel(childNames[0], false, false, new InsertarUsuariosFragment()));
            childModelsList.add(new MenuModel(childNames[1], false, false, new ListarUsuariosFragment()));

            if (menuModel.hasChildren()) {
                childList.put(menuModel, childModelsList);
            } else {
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
            } else {
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
            } else {
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
            } else {
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
            } else {
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
            } else {
                childList.put(menuModel, null);
            }
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