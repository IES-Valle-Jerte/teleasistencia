import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {IRelacionTerminalRecursoComunitarios} from "../../../interfaces/i-relacion-terminal-recurso-comunitarios";
import {CargaRelacionTerminalRecursosComunitariosService} from '../../../servicios/relacion-terminal-recurso-comunitario/carga-relacion-terminal-recursos-comunitarios.service';
import {ITerminal} from "../../../interfaces/i-terminal";
import {IRecursoComunitario} from "../../../interfaces/i-recurso-comunitario";
import {IPersona} from "../../../interfaces/i-persona";
import {CargaRecursoComunitarioService} from "../../../servicios/carga-recurso-comunitario.service";
import {CargaPersonaService} from "../../../servicios/carga-persona.service";


@Component({
  selector: 'app-modificar-relacion-terminal-recursos-comunitarios',
  templateUrl: './modificar-relacion-terminal-recursos-comunitarios.component.html',
  styleUrls: ['./modificar-relacion-terminal-recursos-comunitarios.component.scss']
})
export class ModificarRelacionTerminalRecursosComunitariosComponent implements OnInit {
  public relacion_terminal_recursos_comunitarios: IRelacionTerminalRecursoComunitarios;
  public idRelacion: number;
  // public relacion_terminal: ITerminal;
  public relaciones_recursos_comunitarios: IRecursoComunitario[];
  public relaciones_terminales: ITerminal[];




  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargaRelacionTerminalRecursosComunitarios: CargaRelacionTerminalRecursosComunitariosService,
              private cargaRecursoComunitario: CargaRecursoComunitarioService, private cargaPersona: CargaPersonaService) { }

  ngOnInit(): void {
    this.relacion_terminal_recursos_comunitarios = this.route.snapshot.data['relacion_terminal_recursos_comunitarios'];
    this.idRelacion = this.route.snapshot.params['id'];
    this.relaciones_terminales = this.route.snapshot.data['relaciones_terminales']
    this.relaciones_recursos_comunitarios = this.route.snapshot.data['relaciones_recursos_comunitarios']
    // this.relacion_terminal = this.relacion_terminal_recursos_comunitarios.id_terminal;
    // this.relacion_recurso_comunitario = this.relacion_terminal_recursos_comunitarios.id_recurso_comunitario;
    // this.relacion_terminal_persona = this.relacion_terminal_recursos_comunitarios.id_terminal.id_titular.id_persona;
    this.titleService.setTitle('Modificar relacion de recurso ' + this.idRelacion);
  }
  // modificarPersona(): void {
  //   this.cargaPersona.modificarPersona(this.relacion_terminal_persona).subscribe(
  //     e => {
  //       this.router.navigate(['/relacion_terminal_recursos_comuntarios'])
  //     }
  //   )
  // }
  // modificarRecurso(): void {
  //   this.cargaRecursoComunitario.modificarRecursoComunitario(this.relacion_recurso_comunitario).subscribe(
  //     e => {
  //       this.router.navigate(['/relacion_terminal_recursos_comuntarios'])
  //     }
  //   )
  // }
  optionSelected1(i: number): void {
    document.getElementsByClassName('relacion_terminal_option')[i].setAttribute('selected', '');
  }
  optionSelected2(i: number): void {
    document.getElementsByClassName('relacion_recurso_option')[i].setAttribute('selected', '');
  }
  modificarRelacion(): void {
    // this.relacion_terminal_recursos_comunitarios.id_terminal.id_titular.id_persona = this.relacion_terminal_persona
    // this.relacion_terminal_recursos_comunitarios.id_recurso_comunitario = this.relacion_recurso_comunitario
    this.cargaRelacionTerminalRecursosComunitarios.modificarRelacionRecurso(this.relacion_terminal_recursos_comunitarios).subscribe(
      e => {
        // this.modificarPersona();
        // this.modificarRecurso();
        console.log('Relacion ' + e.id + ' modificada');
        console.log(this.relacion_terminal_recursos_comunitarios)
      },
      error => {
        console.log(error);
      }
    );
  }

}
