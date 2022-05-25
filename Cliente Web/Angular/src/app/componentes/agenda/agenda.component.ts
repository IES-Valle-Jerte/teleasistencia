import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import {CargaAgendaService} from "../../servicios/carga-agenda.service";
import {ActivatedRoute, ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {IAgenda} from "../../interfaces/i-agenda";
import {OrdenacionTablasService} from "../../servicios/ordenacion-tablas.service";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-agenda',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.scss']
})
export class AgendaComponent implements OnInit {

  public agendasDelDia: IAgenda[] = [];
  public fechaToday = new Date();
  numPaginacion: number = 1;
  inputBusqueda: any = '';
  inputFechaBusqueda: any = '';



  constructor(
    private modal: NgbModal,
    private cargaAgendaService: CargaAgendaService,
    private route: ActivatedRoute,
    private titleService: Title,
    private ordTabla: OrdenacionTablasService,
    private router: Router
  ) {}

  ngOnInit() {
    this.agendasDelDia = this.route.snapshot.data['agendasDelDia'];
    console.log(this.agendasDelDia);
  }

  ordenacionTabla(indice: number, tipo: string){
    this.ordTabla.ordenacionService(indice, tipo);
  }

  ngOnChanges(event) {

  }

  buscarPorFecha(event) {
    this.cargaAgendaService.getAgendasPorFechaPrevista(event).subscribe(
      e => {
        const datos: any = e;
        this.inputFechaBusqueda = event;
        if (e) {
          if(datos && datos.length > 0) {
            setTimeout(() => {
              // @ts-ignore
              this.agendasDelDia = datos;
              this.agendasDelDia = this.agendasDelDia.filter(el => {
                return el;
              });
              console.log(this.agendasDelDia);
            }, 1)
          }
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  // Método para conseguir el nombre del día en string usando el número que nos devuelve la función getDay()
  getNombreDia(numDia: number) {
    let dia = '';
    switch (numDia) {
      case 0:
        dia = 'Domingo';
        break;
      case 1:
        dia = 'Lunes';
        break;
      case 2:
        dia = 'Martes';
        break;
      case 3:
        dia = 'Miércoles';
        break;
      case 4:
        dia = 'Jueves';
        break;
      case 5:
        dia = 'Viernes';
        break;
      case 6:
        dia = 'Sábado';
        break;
    }
    return dia;
  }

  // Método para conseguir el nombre del mes usando el número que nos devuelve la función getMonth()
  getNombreMes (numMes: number) {
    let mes = '';
    switch (numMes) {
      case 0:
        mes = 'enero';
        break;
      case 1:
        mes = 'febrero';
        break;
      case 2:
        mes = 'marzo';
        break;
      case 3:
        mes = 'abril';
        break;
      case 4:
        mes = 'mayo';
        break;
      case 5:
        mes = 'junio';
        break;
      case 6:
        mes = 'julio';
        break;
      case 7:
        mes = 'agosto';
        break;
      case 8:
        mes = 'septiembre';
        break;
      case 9:
        mes = 'octubre';
        break;
      case 10:
        mes = 'noviembre';
        break;
      case 11:
        mes = 'diciembre';
        break;
    }
    return mes;
  }
}
