import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {IRelacionTerminalRecursoComunitarios} from "../../../interfaces/i-relacion-terminal-recurso-comunitarios";
import {CargaRelacionTerminalRecursosComunitariosService} from '../../../servicios/carga-relacion-terminal-recursos-comunitarios.service';
import {ITerminal} from "../../../interfaces/i-terminal";
import {IRecursoComunitario} from "../../../interfaces/i-recurso-comunitario";
import {IPersona} from "../../../interfaces/i-persona";


@Component({
  selector: 'app-modificar-relacion-terminal-recursos-comunitarios',
  templateUrl: './modificar-relacion-terminal-recursos-comunitarios.component.html',
  styleUrls: ['./modificar-relacion-terminal-recursos-comunitarios.component.scss']
})
export class ModificarRelacionTerminalRecursosComunitariosComponent implements OnInit {
  public relacion_terminal_recursos_comunitarios: IRelacionTerminalRecursoComunitarios;
  public idRelacion: number;
  public relacion_terminal: ITerminal;
  public relacion_recurso_comunitario: IRecursoComunitario;
  public relacion_terminal_persona: IPersona;


  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargaRelacionTerminalRecursosComunitarios: CargaRelacionTerminalRecursosComunitariosService) { }

  ngOnInit(): void {
    this. relacion_terminal_recursos_comunitarios = this.route.snapshot.data['relacion_terminal_recursos_comunitarios'];
    this.idRelacion = this.route.snapshot.params['id'];
    this.relacion_terminal = this.relacion_terminal_recursos_comunitarios.id_terminal;
    this.relacion_recurso_comunitario = this.relacion_terminal_recursos_comunitarios.id_recurso_comunitario;
    this.relacion_terminal_persona = this.relacion_terminal_recursos_comunitarios.id_terminal.id_titular.id_persona;
    this.titleService.setTitle('Modificar relacion de recurso ' + this.idRelacion);
  }
  modificarRelacion(): void {
    this.cargaRelacionTerminalRecursosComunitarios.modificarRelacionRecurso(this.relacion_terminal_recursos_comunitarios).subscribe(
      e => {
        this.modificarRelacion();
        console.log('Relacion ' + e.id + ' modificada');
        console.log(this.relacion_terminal_recursos_comunitarios)
      },
      error => {
        console.log(error);
      }
    );
  }
  mostrar():void {
    console.log(this.relacion_terminal_recursos_comunitarios.id)
  }
}
