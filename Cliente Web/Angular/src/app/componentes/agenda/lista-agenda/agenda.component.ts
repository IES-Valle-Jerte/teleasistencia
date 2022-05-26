import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import {CargaAgendaService} from "../../../servicios/carga-agenda.service";
import {ActivatedRoute, ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {IAgenda} from "../../../interfaces/i-agenda";
import {OrdenacionTablasService} from "../../../servicios/ordenacion-tablas.service";
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
  fechaString = '';



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
    this.fechaString = + this.fechaToday.getDate() + ' de ' + this.getNombreMes(this.fechaToday.getMonth()) + ' de '
      + this.fechaToday.getFullYear();
  }

  ordenacionTabla(indice: number, tipo: string){
    this.ordTabla.ordenacionService(indice, tipo);
  }

  buscarPorFecha(event) {
    let fechaSeparada = event.split('-');
    this.cargaAgendaService.getAgendasPorFechaPrevista(event).subscribe(
      e => {
        const datos: any = e;
        this.inputFechaBusqueda = event;
        if (e) {
          this.agendasDelDia = datos;
          console.log(fechaSeparada[1]);
          this.fechaString = + fechaSeparada[2] + ' de '
            + this.getNombreMesActualizarFecha(fechaSeparada[1]) + ' de '
            + fechaSeparada[0];
          if(datos && datos.length > 0) {
            this.agendasDelDia = this.agendasDelDia.filter(el => {
              return el;
            });
          }
        }
        document.getElementById("campoBusqueda").focus();
      },
      error => {
        console.log(error);
      }
    );
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

  // Método para conseguir el nombre del mes usando el número que nos devuelve la función getMonth()
  getNombreMesActualizarFecha (numMes: string) {
    let mes = '';
    switch (numMes) {
      case '01':
        mes = 'enero';
        break;
      case '02':
        mes = 'febrero';
        break;
      case '03':
        mes = 'marzo';
        break;
      case '04':
        mes = 'abril';
        break;
      case '05':
        mes = 'mayo';
        break;
      case '06':
        mes = 'junio';
        break;
      case '07':
        mes = 'julio';
        break;
      case '08':
        mes = 'agosto';
        break;
      case '09':
        mes = 'septiembre';
        break;
      case '10':
        mes = 'octubre';
        break;
      case '11':
        mes = 'noviembre';
        break;
      case '12':
        mes = 'diciembre';
        break;
    }
    return mes;
  }
}
