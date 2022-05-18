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
  selector: 'app-modificar-recurso-comunitario-alarma',
  templateUrl: './modificar-recurso-comunitario-alarma.component.html',
  styleUrls: ['./modificar-recurso-comunitario-alarma.component.scss']
})
export class ModificarRecursoComunitarioAlarmaComponent implements OnInit {
  public recursoComunitarioAlarma: RecursosComunitariosAlarma;
  public idRecursoComunitarioAlarma: number;
  public alarmas: Alarma[];
  public recursosComunitarios: RecursoComunitario[];


  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargarRecursoComunitarioAlarma: CargaRecursosComunitariosAlarmaService) { }

  ngOnInit(): void {
    this.recursoComunitarioAlarma = this.route.snapshot.data['recursos_comunitarios_alarma'];
    this.idRecursoComunitarioAlarma = this.route.snapshot.params['id'];
    this.alarmas = this.route.snapshot.data['alarmas'];
    this.recursosComunitarios = this.route.snapshot.data['recursos_comunitarios']
    this.titleService.setTitle('Modificar recurso comunitario en alarma ' + this.idRecursoComunitarioAlarma);

    this.recursoComunitarioAlarma.id_alarma = this.recursoComunitarioAlarma.id_alarma.id;
    this.recursoComunitarioAlarma.id_recurso_comunitario = this.recursoComunitarioAlarma.id_recurso_comunitario.id
  }
  optionSelectedAlarma(i: number): void {
    document.getElementsByClassName('relacion_alarma_option')[i].setAttribute('selected', '');
  }
  optionSelectedRecurso(i: number): void {
    document.getElementsByClassName('relacion_recurso_option')[i].setAttribute('selected', '');
  }
  modificarRecursoComunitarioAlarma(): void {
    this.cargarRecursoComunitarioAlarma.modificarRecursosComunitariosAlarma(this.recursoComunitarioAlarma).subscribe(
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
