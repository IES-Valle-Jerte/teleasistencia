import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { CargaRelacionTerminalRecursosComunitariosService } from '../../../servicios/relacion-terminal-recurso-comunitario/carga-relacion-terminal-recursos-comunitarios.service';
import { RecursoComunitario } from "../../../clases/recurso-comunitario";
import { Terminal } from "../../../clases/terminal";
import { RelacionTerminalRecursoComunitarios } from "../../../clases/relacion-terminal-recurso-comunitarios";



@Component({
  selector: 'app-modificar-relacion-terminal-recursos-comunitarios',
  templateUrl: './modificar-relacion-terminal-recursos-comunitarios.component.html',
  styleUrls: ['./modificar-relacion-terminal-recursos-comunitarios.component.scss']
})
export class ModificarRelacionTerminalRecursosComunitariosComponent implements OnInit {
  public relacion_terminal_recursos_comunitarios: RelacionTerminalRecursoComunitarios;
  public idRelacion: number;
  public relaciones_recursos_comunitarios: RecursoComunitario[];
  public relaciones_terminales: Terminal[];



  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargaRelacionTerminalRecursosComunitarios: CargaRelacionTerminalRecursosComunitariosService) { }

  ngOnInit(): void {
    this.relacion_terminal_recursos_comunitarios = this.route.snapshot.data['relacion_terminal_recursos_comunitarios'];
    this.idRelacion = this.route.snapshot.params['id'];
    this.relaciones_terminales = this.route.snapshot.data['relaciones_terminales']
    this.relaciones_recursos_comunitarios = this.route.snapshot.data['relaciones_recursos_comunitarios']
    this.titleService.setTitle('Modificar relacion de recurso ' + this.idRelacion);

    this.relacion_terminal_recursos_comunitarios.id_terminal = this.relacion_terminal_recursos_comunitarios.id_terminal.id
    this.relacion_terminal_recursos_comunitarios.id_recurso_comunitario = this.relacion_terminal_recursos_comunitarios.id_recurso_comunitario.id
  }


  optionSelected1(i: number): void {
    document.getElementsByClassName('relacion_terminal_option')[i].setAttribute('selected', '');
  }
  optionSelected2(i: number): void {
    document.getElementsByClassName('relacion_recurso_option')[i].setAttribute('selected', '');
  }
  modificarRelacion(): void {
   this.cargaRelacionTerminalRecursosComunitarios.modificarRelacionRecurso(this.relacion_terminal_recursos_comunitarios).subscribe(
      e => {

        console.log('Relacion ' + e.id + ' modificada');
        console.log(this.relacion_terminal_recursos_comunitarios)
      },
      error => {
        console.log(error);
      }
    );
  }


}
