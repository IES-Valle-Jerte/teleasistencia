import {Component, OnInit} from '@angular/core';
import {IRecursoComunitario} from '../../../interfaces/i-recurso-comunitario';
import {ActivatedRoute} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {OrdenacionTablasService} from "../../../servicios/ordenacion-tablas.service";

@Component({
  selector: 'app-lista-recursos-comunitarios',
  templateUrl: './lista-recursos-comunitarios.component.html',
  styleUrls: ['./lista-recursos-comunitarios.component.scss']
})

export class ListaRecursosComunitariosComponent implements OnInit {
  public recursos_comunitarios: IRecursoComunitario[];
  numPaginacion: number = 1;
  inputBusqueda: any = '';

  constructor(private route: ActivatedRoute, private titleService: Title, private ordTabla: OrdenacionTablasService) {
  }

  ngOnInit(): void {
    this.recursos_comunitarios = this.route.snapshot.data['recursos_comunitarios'];
    this.titleService.setTitle('Recursos comunitarios');
  }

  ordenacionTabla(indice: number, tipo: string){
    this.ordTabla.ordenacionService(indice, tipo);
  }

}
