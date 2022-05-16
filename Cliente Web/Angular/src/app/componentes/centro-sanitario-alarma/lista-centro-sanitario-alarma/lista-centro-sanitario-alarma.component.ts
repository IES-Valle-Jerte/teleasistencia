import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import {CentroSanitarioAlarma} from "../../../clases/centro-sanitario-alarma";

@Component({
  selector: 'app-lista-centro-sanitario-alarma',
  templateUrl: './lista-centro-sanitario-alarma.component.html',
  styleUrls: ['./lista-centro-sanitario-alarma.component.scss']
})
export class ListaCentroSanitarioAlarmaComponent implements OnInit {
  public centrosSanitariosAlarma: CentroSanitarioAlarma[];
  constructor(private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    this.centrosSanitariosAlarma = this.route.snapshot.data['centros_sanitarios_alarma'];
    this.titleService.setTitle('Centros sanitarios en alarma');
  }

}
