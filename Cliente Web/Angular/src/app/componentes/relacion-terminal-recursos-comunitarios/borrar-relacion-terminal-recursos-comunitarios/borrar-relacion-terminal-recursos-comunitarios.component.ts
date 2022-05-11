import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import { CargaRelacionTerminalRecursosComunitariosService } from "../../../servicios/relacion-terminal-recurso-comunitario/carga-relacion-terminal-recursos-comunitarios.service";
import { RelacionTerminalRecursoComunitarios } from "../../../clases/relacion-terminal-recurso-comunitarios";


@Component({
  selector: 'app-borrar-relacion-terminal-recursos-comunitarios',
  templateUrl: './borrar-relacion-terminal-recursos-comunitarios.component.html',
  styleUrls: ['./borrar-relacion-terminal-recursos-comunitarios.component.scss']
})
export class BorrarRelacionTerminalRecursosComunitariosComponent implements OnInit {
  public relacion_terminal_Recurso: RelacionTerminalRecursoComunitarios

  constructor(private route: ActivatedRoute, private cargaRelacionTerminalRecurso: CargaRelacionTerminalRecursosComunitariosService, private router: Router) { }

  ngOnInit(): void {
    this.relacion_terminal_Recurso = this.route.snapshot.data['relacion-terminal-recurso-comunitario']
  }

  borrarRelacionTerminalRecurso(): void {
    this.cargaRelacionTerminalRecurso.borrarRelacionRecurso(this.relacion_terminal_Recurso).subscribe(
      e => {
        console.log(this.relacion_terminal_Recurso);
        this.router.navigate(['/relacion-terminal-recurso-comunitario']);
      },
      error => {
        console.log(error)
      }
    )
  }

}
