import { Component, OnInit } from '@angular/core';
import {IHistoricoTipoSituacion} from "../../interfaces/i-historico-tipo-situacion";
import {ActivatedRoute} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {OrdenacionTablasService} from "../../servicios/ordenacion-tablas.service";

@Component({
  selector: 'app-historico-tipo-situacion',
  templateUrl: './historico-tipo-situacion.component.html',
  styleUrls: ['./historico-tipo-situacion.component.scss']
})
export class HistoricoTipoSituacionComponent implements OnInit {

  public historicos_tipos_situacion: IHistoricoTipoSituacion[];
  numPaginacion: number = 1;
  inputBusqueda: any = '';

  constructor(private route: ActivatedRoute, private titleService: Title, private ordTabla: OrdenacionTablasService) { }

  ngOnInit(): void {
    this.historicos_tipos_situacion = this.route.snapshot.data['historicos_situaciones'];
    this.titleService.setTitle('Histórico tipos situacion');
  }

  ordenacionTabla(indice: number, tipo: string){
    this.ordTabla.ordenacionService(indice, tipo);
  }

}
