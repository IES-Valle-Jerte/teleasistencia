import {NgModule} from '@angular/core';
import {BrowserModule, Title} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ListaUsersComponent} from './componentes/user/lista-users/lista-users.component';
import {ItemUserComponent} from './componentes/user/item-user/item-user.component';
import {DetallesUserComponent} from './componentes/user/modificar-user/detalles-user.component';
import {NuevoUserComponent} from './componentes/user/crear-user/nuevo-user.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {HomeComponent} from './componentes/home/home.component';
import {CargaUserService} from './servicios/carga-user.service';
import {ItemClasificacionAlarmaComponent} from './componentes/clasificacion-alarma/item-clasificacion-alarma/item-clasificacion-alarma.component';
import {DetallesClasificacionAlarmaComponent} from './componentes/clasificacion-alarma/modificar-clasificacion-alarma/detalles-clasificacion-alarma.component';
import {NuevaClasificacionAlarmaComponent} from './componentes/clasificacion-alarma/crear-clasificacion-alarma/nueva-clasificacion-alarma.component';
import {ListaClasificacionesAlarmasComponent} from './componentes/clasificacion-alarma/lista-clasificaciones-alarmas/lista-clasificaciones-alarmas.component';
import {ListaTiposCentrosSanitariosComponent} from './componentes/tipo-centro-sanitario/lista-tipos-centros-sanitarios/lista-tipos-centros-sanitarios.component';
import {ItemTipoCentroSanitarioComponent} from './componentes/tipo-centro-sanitario/item-tipo-centro-sanitario/item-tipo-centro-sanitario.component';
import {DetallesTipoCentroSanitarioComponent} from './componentes/tipo-centro-sanitario/modificar-tipo-centro-sanitario/detalles-tipo-centro-sanitario.component';
import {NuevoTipoCentroSanitarioComponent} from './componentes/tipo-centro-sanitario/crear-tipo-centro-sanitario/nuevo-tipo-centro-sanitario.component';
import {CargaClasificacionAlarmaService} from './servicios/carga-clasificacion-alarma.service';
import {CargaTipoCentroSanitarioService} from './servicios/carga-tipo-centro-sanitario.service';
import {ListaTiposRecursosComunitariosComponent} from './componentes/tipo-recurso-comunitario/lista-tipos-recursos-comunitarios/lista-tipos-recursos-comunitarios.component';
import {ItemTipoRecursoComunitarioComponent} from './componentes/tipo-recurso-comunitario/item-tipo-recurso-comunitario/item-tipo-recurso-comunitario.component';
import {DetallesTipoRecursoComunitarioComponent} from './componentes/tipo-recurso-comunitario/modificar-tipo-recurso-comunitario/detalles-tipo-recurso-comunitario.component';
import {NuevoTipoRecursoComunitarioComponent} from './componentes/tipo-recurso-comunitario/crear-tipo-recurso-comunitario/nuevo-tipo-recurso-comunitario.component';
import {CargaTipoRecursoComunitarioService} from './servicios/carga-tipo-recurso-comunitario.service';
import {ListaTiposModalidadesPacientesComponent} from './componentes/tipo-modalidad-paciente/lista-tipos-modalidades-pacientes/lista-tipos-modalidades-pacientes.component';
import {ItemTipoModalidadPacienteComponent} from './componentes/tipo-modalidad-paciente/item-tipo-modalidad-paciente/item-tipo-modalidad-paciente.component';
import {DetallesTipoModalidadPacienteComponent} from './componentes/tipo-modalidad-paciente/modificar-tipo-modalidad-paciente/detalles-tipo-modalidad-paciente.component';
import {NuevoTipoModalidadPacienteComponent} from './componentes/tipo-modalidad-paciente/crear-tipo-modalidad-paciente/nuevo-tipo-modalidad-paciente.component';
import {CargaTipoModalidadPacienteService} from './servicios/carga-tipo-modalidad-paciente.service';
import {ListaTiposAlarmasComponent} from './componentes/tipo-alarma/lista-tipos-alarmas/lista-tipos-alarmas.component';
import {ItemTipoAlarmaComponent} from './componentes/tipo-alarma/item-tipo-alarma/item-tipo-alarma.component';
import {DetallesTipoAlarmaComponent} from './componentes/tipo-alarma/modificar-tipo-alarma/detalles-tipo-alarma.component';
import {NuevoTipoAlarmaComponent} from './componentes/tipo-alarma/crear-tipo-alarma/nuevo-tipo-alarma.component';
import {CargaTipoAlarmaService} from './servicios/carga-tipo-alarma.service';
import {ListaCentrosSanitariosComponent} from './componentes/centro-sanitario/lista-centros-sanitarios/lista-centros-sanitarios.component';
import {ItemCentroSanitarioComponent} from './componentes/centro-sanitario/item-centro-sanitario/item-centro-sanitario.component';
import {DetallesCentroSanitarioComponent} from './componentes/centro-sanitario/modificar-centro-sanitario/detalles-centro-sanitario.component';
import {NuevoCentroSanitarioComponent} from './componentes/centro-sanitario/crear-centro-sanitario/nuevo-centro-sanitario.component';
import {ListaRecursosComunitariosComponent} from './componentes/recurso-comunitario/lista-recursos-comunitarios/lista-recursos-comunitarios.component';
import {ItemResursoComunitarioComponent} from './componentes/recurso-comunitario/item-resurso-comunitario/item-resurso-comunitario.component';
import {DetallesRecursoComunitarioComponent} from './componentes/recurso-comunitario/modificar-recurso-comunitario/detalles-recurso-comunitario.component';
import {NuevoRecursoComunitarioComponent} from './componentes/recurso-comunitario/crear-recurso-comunitario/nuevo-recurso-comunitario.component';
import {ListaPersonasComponent} from './componentes/persona/lista-personas/lista-personas.component';
import {ItemPersonaComponent} from './componentes/persona/item-persona/item-persona.component';
import {DetallesPersonaComponent} from './componentes/persona/modificar-persona/detalles-persona.component';
import {NuevaPersonaComponent} from './componentes/persona/crear-persona/nueva-persona.component';
import {ListaDireccionesComponent} from './componentes/direccion/lista-direcciones/lista-direcciones.component';
import {ItemDireccionComponent} from './componentes/direccion/item-direccion/item-direccion.component';
import {DetallesDireccionComponent} from './componentes/direccion/modificar-direccion/detalles-direccion.component';
import {NuevaDireccionComponent} from './componentes/direccion/crear-direccion/nueva-direccion.component';
import {CargaDireccionService} from './servicios/carga-direccion.service';
import {CargaCentroSanitarioService} from './servicios/carga-centro-sanitario.service';
import {CargaPersonaService} from './servicios/carga-persona.service';
import {CargaRecursoComunitarioService} from './servicios/carga-recurso-comunitario.service';
import {PantallaLoginComponent} from './componentes/pantalla-login/pantalla-login.component';
import {HeaderComponent} from './componentes/header/header.component';
import {FooterComponent} from './componentes/footer/footer.component';
import {BotonesLoginComponent} from './componentes/botones-login/botones-login.component';
import { ListaRelacionTerminalRecursosComunitariosComponent } from './componentes/relacion-terminal-recursos-comunitarios/lista-relacion-terminal-recursos-comunitarios/lista-relacion-terminal-recursos-comunitarios.component';
import { ItemRelacionTerminalRecursosComunitariosComponent } from './componentes/relacion-terminal-recursos-comunitarios/item-relacion-terminal-recursos-comunitarios/item-relacion-terminal-recursos-comunitarios.component';
import { NuevaRelacionTerminalRecursosComunitariosComponent } from './componentes/relacion-terminal-recursos-comunitarios/nueva-relacion-terminal-recursos-comunitarios/nueva-relacion-terminal-recursos-comunitarios.component';
import { ModificarRelacionTerminalRecursosComunitariosComponent } from './componentes/relacion-terminal-recursos-comunitarios/modificar-relacion-terminal-recursos-comunitarios/modificar-relacion-terminal-recursos-comunitarios.component';
import {
  CargaRelacionTerminalRecursosComunitariosService
} from "./servicios/carga-relacion-terminal-recursos-comunitarios.service";
import {InterceptorService} from "./interceptors/interceptor.service";


@NgModule({
  declarations: [
    AppComponent,
    ListaUsersComponent,
    ItemUserComponent,
    DetallesUserComponent,
    NuevoUserComponent,
    HomeComponent,
    ItemClasificacionAlarmaComponent,
    DetallesClasificacionAlarmaComponent,
    NuevaClasificacionAlarmaComponent,
    ListaClasificacionesAlarmasComponent,
    ListaTiposCentrosSanitariosComponent,
    ItemTipoCentroSanitarioComponent,
    DetallesTipoCentroSanitarioComponent,
    NuevoTipoCentroSanitarioComponent,
    ListaTiposRecursosComunitariosComponent,
    ItemTipoRecursoComunitarioComponent,
    DetallesTipoRecursoComunitarioComponent,
    NuevoTipoRecursoComunitarioComponent,
    ListaTiposModalidadesPacientesComponent,
    ItemTipoModalidadPacienteComponent,
    DetallesTipoModalidadPacienteComponent,
    NuevoTipoModalidadPacienteComponent,
    ListaTiposAlarmasComponent,
    ItemTipoAlarmaComponent,
    DetallesTipoAlarmaComponent,
    NuevoTipoAlarmaComponent,
    ListaCentrosSanitariosComponent,
    ItemCentroSanitarioComponent,
    DetallesCentroSanitarioComponent,
    NuevoCentroSanitarioComponent,
    ListaRecursosComunitariosComponent,
    ItemResursoComunitarioComponent,
    DetallesRecursoComunitarioComponent,
    NuevoRecursoComunitarioComponent,
    ListaPersonasComponent,
    ItemPersonaComponent,
    DetallesPersonaComponent,
    NuevaPersonaComponent,
    ListaDireccionesComponent,
    ItemDireccionComponent,
    DetallesDireccionComponent,
    NuevaDireccionComponent,
    PantallaLoginComponent,
    HeaderComponent,
    FooterComponent,
    BotonesLoginComponent,
    ListaRelacionTerminalRecursosComunitariosComponent,
    ItemRelacionTerminalRecursosComunitariosComponent,
    NuevaRelacionTerminalRecursosComunitariosComponent,
    ModificarRelacionTerminalRecursosComunitariosComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    CargaUserService,
    CargaClasificacionAlarmaService,
    CargaTipoCentroSanitarioService,
    CargaTipoRecursoComunitarioService,
    CargaTipoModalidadPacienteService,
    CargaTipoAlarmaService,
    CargaDireccionService,
    CargaCentroSanitarioService,
    CargaRecursoComunitarioService,
    CargaPersonaService,
    CargaRelacionTerminalRecursosComunitariosService,
    Title,
    {
      provide:HTTP_INTERCEPTORS,
      useClass:InterceptorService,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
