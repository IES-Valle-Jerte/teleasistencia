import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {
  CargaRecursosComunitariosAlarmaService
} from "../../../servicios/recursos-comunitarios-alarma/carga-recursos-comunitarios-alarma.service";
import {RecursosComunitariosAlarma} from "../../../clases/recursos-comunitarios-alarma";
import {Alarma} from "../../../clases/alarma";
import {RecursoComunitario} from "../../../clases/recurso-comunitario";

@Component({
  selector: 'app-crear-recurso-comunitario-alarma',
  templateUrl: './crear-recurso-comunitario-alarma.component.html',
  styleUrls: ['./crear-recurso-comunitario-alarma.component.scss']
})
export class CrearRecursoComunitarioAlarmaComponent implements OnInit {
  public recursoComunitarioAlarma: RecursosComunitariosAlarma;
  public alarmas: Alarma[];
  public recursosComunitarios: RecursoComunitario[];
  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargarRecursoComunitarioAlarma: CargaRecursosComunitariosAlarmaService) { }

  ngOnInit(): void {
    this.recursoComunitarioAlarma = new RecursosComunitariosAlarma();
    this.alarmas = this.route.snapshot.data['alarmas'];
    this.recursosComunitarios = this.route.snapshot.data['recursos_comunitarios']
    this.titleService.setTitle('Crear recurso comunitario en alarma' );

  }
  crearRecursoComunitarioAlarma(): void {
    this.cargarRecursoComunitarioAlarma.nuevaRecursosComunitariosAlarma(this.recursoComunitarioAlarma).subscribe(
      e => {
        console.log('Recurso comunitario en alarma ' + e.id + ' modificada');
        console.log(this.recursoComunitarioAlarma)
      },
      error => {
        console.log(error);
      }
    )
  }
}
