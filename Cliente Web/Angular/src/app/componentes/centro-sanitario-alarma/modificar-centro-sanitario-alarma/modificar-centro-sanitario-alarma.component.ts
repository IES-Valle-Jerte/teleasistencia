import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {
  CargaCentroSanitarioAlarmaService
} from "../../../servicios/centro-sanitario-alarma/carga-centro-sanitario-alarma.service";
import {CentroSanitarioAlarma} from "../../../clases/centro-sanitario-alarma";
import {Alarma} from "../../../clases/alarma";
import {CentroSanitario} from "../../../clases/centro-sanitario";

@Component({
  selector: 'app-modificar-centro-sanitario-alarma',
  templateUrl: './modificar-centro-sanitario-alarma.component.html',
  styleUrls: ['./modificar-centro-sanitario-alarma.component.scss']
})
export class ModificarCentroSanitarioAlarmaComponent implements OnInit {
  public centroSanitarioAlarma: CentroSanitarioAlarma;
  public idCentroSanitarioAlarma: number
  public alarmas: Alarma[];
  public centrosSanitarios: CentroSanitario[];

  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargarCentroSanitarioAlarma: CargaCentroSanitarioAlarmaService) { }

  ngOnInit(): void {
    this.centroSanitarioAlarma = this.route.snapshot.data['centros_sanitarios_alarma'];
    this.idCentroSanitarioAlarma = this.route.snapshot.params['id'];
    this.alarmas = this.route.snapshot.data['alarmas'];
    this.centrosSanitarios = this.route.snapshot.data['centros_sanitarios']
    this.titleService.setTitle('Modificar centro sanitario en alarma' + this.idCentroSanitarioAlarma);


    this.centroSanitarioAlarma.id_alarma = this.centroSanitarioAlarma.id_alarma.id;
    this.centroSanitarioAlarma.id_centro_sanitario = this.centroSanitarioAlarma.id_centro_sanitario.id;

  }
  optionSelectedAlarma(i: number): void {
    document.getElementsByClassName('relacion_alarma_option')[i].setAttribute('selected', '');
  }
  optionSelectedCentro(i: number): void {
    document.getElementsByClassName('relacion_centro_option')[i].setAttribute('selected', '');
  }
  modificarCentroSanitarioAlarma(): void {
    this.cargarCentroSanitarioAlarma.modificarCentroSanitarioAlarma(this.centroSanitarioAlarma).subscribe(
      e => {
        console.log('Centro sanitario en alarma ' + e.id + ' modificada');
        console.log(this.centroSanitarioAlarma)
      },
      error => {
        console.log(error);
      }
    )
  }

}
