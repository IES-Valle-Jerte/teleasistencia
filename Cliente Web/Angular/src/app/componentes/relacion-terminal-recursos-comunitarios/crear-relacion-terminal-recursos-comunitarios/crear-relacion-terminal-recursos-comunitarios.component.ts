import { Component, OnInit } from '@angular/core';
import {IRelacionTerminalRecursoComunitarios} from "../../../interfaces/i-relacion-terminal-recurso-comunitarios";
import {ITerminal} from "../../../interfaces/i-terminal";
import {IRecursoComunitario} from "../../../interfaces/i-recurso-comunitario";
import {IPersona} from "../../../interfaces/i-persona";
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {
  CargaRelacionTerminalRecursosComunitariosService
} from "../../../servicios/relacion-terminal-recurso-comunitario/carga-relacion-terminal-recursos-comunitarios.service";
import {CargaRecursoComunitarioService} from "../../../servicios/carga-recurso-comunitario.service";
import {CargaPersonaService} from "../../../servicios/carga-persona.service";

@Component({
  selector: 'app-crear-relacion-terminal-recursos-comunitarios',
  templateUrl: './crear-relacion-terminal-recursos-comunitarios.component.html',
  styleUrls: ['./crear-relacion-terminal-recursos-comunitarios.component.scss']
})
export class CrearRelacionTerminalRecursosComunitariosComponent implements OnInit {
  public relacion_terminal_recursos_comunitarios: IRelacionTerminalRecursoComunitarios;
  public idRelacion: number;
  public relacion_terminal: ITerminal;
  public relacion_recurso_comunitario: IRecursoComunitario;
  public relacion_terminal_persona: IPersona;

  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargaRelacionTerminalRecursosComunitarios: CargaRelacionTerminalRecursosComunitariosService,
              private cargaRecursoComunitario: CargaRecursoComunitarioService, private cargaPersona: CargaPersonaService) { }

  ngOnInit(): void {
    this. relacion_terminal_recursos_comunitarios = this.route.snapshot.data['relacion_terminal_recursos_comunitarios'];
    this.idRelacion = this.route.snapshot.params['id'];
    this.relacion_terminal = this.relacion_terminal_recursos_comunitarios.id_terminal;
    this.relacion_recurso_comunitario = this.relacion_terminal_recursos_comunitarios.id_recurso_comunitario;
    this.relacion_terminal_persona = this.relacion_terminal_recursos_comunitarios.id_terminal.id_titular.id_persona;
    this.titleService.setTitle('Modificar relacion de recurso ' + this.idRelacion);
  }
  crearCentroSanitario(): void {
    this.relacion_terminal_recursos_comunitarios.id_terminal.id_titular.id_persona = this.relacion_terminal_persona
    this.relacion_terminal_recursos_comunitarios.id_recurso_comunitario = this.relacion_recurso_comunitario
    this.cargaRelacionTerminalRecursosComunitarios.modificarRelacionRecurso(this.relacion_terminal_recursos_comunitarios).subscribe(
      e => {
        console.log('Relacion ' + e.id + ' creada');
        console.log(this.relacion_terminal_recursos_comunitarios)
      },
      error => {
        console.log(error);
      }
    );
  }
}
