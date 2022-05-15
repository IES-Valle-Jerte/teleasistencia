import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {OrdenacionTablasService} from "../../../servicios/ordenacion-tablas.service";
import {ICopiaSeguridad} from "../../../interfaces/i-copia-seguridad";

@Component({
  selector: 'app-lista-copia-seguridad',
  templateUrl: './lista-copia-seguridad.component.html',
  styleUrls: ['./lista-copia-seguridad.component.scss']
})
export class ListaCopiaSeguridadComponent implements OnInit {
  public copias_seguridad: ICopiaSeguridad[];
  numPaginacion: number = 1;
  inputBusqueda: any = '';

  constructor(private route: ActivatedRoute, private titleService: Title, private ordTabla: OrdenacionTablasService) { }

  ngOnInit(): void {

    this.titleService.setTitle('Gestion copia de seguridad.');
  }

  ordenacionTabla(indice: number, tipo: string){
    this.ordTabla.ordenacionService(indice, tipo);
  }

}
