import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ActivatedRoute, Router} from "@angular/router";
import {CargaTipoSituacionService} from "../../../servicios/carga-tipo-situacion.service";
import {ITipoSituacion} from "../../../interfaces/i-tipo-situacion";
import {TipoSituacion} from "../../../clases/tipo-situacion";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-crear-tipo-situacion',
  templateUrl: './crear-tipo-situacion.component.html',
  styleUrls: ['./crear-tipo-situacion.component.scss']
})
export class CrearTipoSituacionComponent implements OnInit {
  public situacion: ITipoSituacion;
  public nombreSituacion: string;

  constructor(private titleService: Title, private route: ActivatedRoute, private router: Router, private cargaSituacion: CargaTipoSituacionService) { }

  ngOnInit(): void {
    this.titleService.setTitle('Crear nueva situación');
    this.situacion = new TipoSituacion();
    this.nombreSituacion = '';
  }

  nuevaSituacion(): void{
    this.cargaSituacion.nuevoTipoSituacion(this.situacion).subscribe(
      e => {
        console.log('Situación creada');
        this.router.navigate(['/situaciones']);
      },
      error => {
        console.log(error);
      }
    );
  }

}
