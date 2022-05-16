import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import { Alarma } from "../../../clases/alarma";
import { TipoAlarma } from "../../../clases/tipo-alarma";
import { Paciente } from "../../../clases/paciente";
import { Terminal } from "../../../clases/terminal";
import { CargaAlarmaService} from "../../../servicios/alarmas/carga-alarma.service";

@Component({
  selector: 'app-crear-alarma',
  templateUrl: './crear-alarma.component.html',
  styleUrls: ['./crear-alarma.component.scss']
})
export class CrearAlarmaComponent implements OnInit {
  public alarma: Alarma;
  public tipos_alarmas: TipoAlarma[];
  public terminales: Terminal[];
  public pacientes_ucr: Paciente[];
  public fecha_actual = new Date();
  public anno_actual = this.fecha_actual.getFullYear();
  public mes_actual = this.fecha_actual.getMonth() + 1;
  public dia_actual = this.fecha_actual.getDate();


  constructor(private titleService: Title, private route: ActivatedRoute, private cargaAlarma: CargaAlarmaService, private router: Router) { }

  ngOnInit(): void {
    this.titleService.setTitle('Nueva Alarma');
    this.alarma = new Alarma();
    this.tipos_alarmas = this.route.snapshot.data['tipos_alarmas'];
    this.alarma.id_teleoperador = null;
    this.terminales = this.route.snapshot.data['terminales'];
    this.pacientes_ucr = this.route.snapshot.data['pacientes_ucr'];
  }
  nuevaAlarma(): void {
    this.cargaAlarma.nuevaAlarma(this.alarma).subscribe(
      e => {
        console.log('Alarma Creada');
        console.log(this.alarma);
        this.router.navigate(['/alarmas'])
      },
        error => {
          console.log(error);
        }
    )
  }
}
