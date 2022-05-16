import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { RecursosComunitariosAlarma } from "../../../clases/recursos-comunitarios-alarma";

@Component({
  selector: 'app-lista-recurso-comunitario-alarma',
  templateUrl: './lista-recurso-comunitario-alarma.component.html',
  styleUrls: ['./lista-recurso-comunitario-alarma.component.scss']
})
export class ListaRecursoComunitarioAlarmaComponent implements OnInit {
  public recursosComunitariosAlarma: RecursosComunitariosAlarma[];

  constructor(private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    this.recursosComunitariosAlarma = this.route.snapshot.data['recursos_comunitarios_alarma'];
    this.titleService.setTitle('Recurso Comunitario Alarma');
  }

}
