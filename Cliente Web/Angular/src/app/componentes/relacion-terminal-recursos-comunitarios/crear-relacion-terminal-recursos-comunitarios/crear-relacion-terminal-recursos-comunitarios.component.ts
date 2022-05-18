import { Component, OnInit } from '@angular/core';
import {RelacionTerminalRecursoComunitarios} from "../../../clases/relacion-terminal-recurso-comunitarios";
import {Terminal} from "../../../clases/terminal";
import {RecursoComunitario} from "../../../clases/recurso-comunitario";
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {
  CargaRelacionTerminalRecursosComunitariosService
} from "../../../servicios/relacion-terminal-recurso-comunitario/carga-relacion-terminal-recursos-comunitarios.service";

@Component({
  selector: 'app-crear-relacion-terminal-recursos-comunitarios',
  templateUrl: './crear-relacion-terminal-recursos-comunitarios.component.html',
  styleUrls: ['./crear-relacion-terminal-recursos-comunitarios.component.scss']
})
export class CrearRelacionTerminalRecursosComunitariosComponent implements OnInit {
  public relacion_terminal_recursos_comunitarios: RelacionTerminalRecursoComunitarios;
  public relaciones_recursos_comunitarios: RecursoComunitario[];
  public relaciones_terminales: Terminal[];

  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargaRelacionTerminalRecursosComunitarios: CargaRelacionTerminalRecursosComunitariosService) { }

  ngOnInit(): void {
    this.titleService.setTitle('Nueva relacion de terminal y recurso comunitario');
    this.relacion_terminal_recursos_comunitarios = new RelacionTerminalRecursoComunitarios();
    this.relaciones_terminales = this.route.snapshot.data['relaciones_terminales']
    this.relaciones_recursos_comunitarios = this.route.snapshot.data['relaciones_recursos_comunitarios']
  }
  nuevaRelacionTerminalRecurso(): void {
    this.cargaRelacionTerminalRecursosComunitarios.nuevaRelacionRecurso(this.relacion_terminal_recursos_comunitarios).subscribe(
      e => {
        console.log('Relacion creada');
        console.log(this.relacion_terminal_recursos_comunitarios)
        this.router.navigate(['/relacion_terminal_recurso_comunitario'])
      },
      error => {
        console.log(error);
      }
    );
  }
}
