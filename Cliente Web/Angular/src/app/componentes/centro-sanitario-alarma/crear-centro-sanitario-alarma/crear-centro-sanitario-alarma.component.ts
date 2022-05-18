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
  selector: 'app-crear-centro-sanitario-alarma',
  templateUrl: './crear-centro-sanitario-alarma.component.html',
  styleUrls: ['./crear-centro-sanitario-alarma.component.scss']
})
export class CrearCentroSanitarioAlarmaComponent implements OnInit {
  public centroSanitarioAlarma: CentroSanitarioAlarma;
  public alarmas: Alarma[];
  public centrosSanitarios: CentroSanitario[];
  public fecha_actual = new Date();
  public anno_actual = this.fecha_actual.getFullYear();
  public mes_actual = this.fecha_actual.getMonth() + 1;
  public dia_actual = this.fecha_actual.getDate();

  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargarCentroSanitarioAlarma: CargaCentroSanitarioAlarmaService) { }

  ngOnInit(): void {
    this.centroSanitarioAlarma = new CentroSanitarioAlarma();
    this.alarmas = this.route.snapshot.data['alarmas'];
    this.centrosSanitarios = this.route.snapshot.data['centros_sanitarios']
    this.titleService.setTitle('Crear centro sanitario en alarma' );

  }
  nuevoCentroSanitarioAlarma(): void {
    this.cargarCentroSanitarioAlarma.nuevoCentroSanitarioAlarma(this.centroSanitarioAlarma).subscribe(
      e => {
        console.log('Centro Sanitario Alarma creado');
        console.log(this.centroSanitarioAlarma)
      },
      error => {
        console.log(error);
      }
    )
  }
}
