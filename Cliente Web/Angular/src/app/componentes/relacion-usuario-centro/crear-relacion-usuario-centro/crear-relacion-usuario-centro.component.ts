import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {
  CargaRelacionUsuarioCentroService
} from "../../../servicios/relacion-usuario-centro/carga-relacion-usuario-centro.service";
import {RelacionUsuarioCentro} from "../../../clases/relacion-usuario-centro";
import {Paciente} from "../../../clases/paciente";
import {CentroSanitario} from "../../../clases/centro-sanitario";

@Component({
  selector: 'app-crear-relacion-usuario-centro',
  templateUrl: './crear-relacion-usuario-centro.component.html',
  styleUrls: ['./crear-relacion-usuario-centro.component.scss']
})
export class CrearRelacionUsuarioCentroComponent implements OnInit {
  public relacionUsuarioCentro: RelacionUsuarioCentro;
  public pacientes: Paciente[];
  public centrosSanitario: CentroSanitario[];
  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router,
              private cargarRelacionUsuarioCentro: CargaRelacionUsuarioCentroService) { }

  ngOnInit(): void {
    this.relacionUsuarioCentro = new RelacionUsuarioCentro();
    this.pacientes = this.route.snapshot.data['pacientes'];
    this.centrosSanitario = this.route.snapshot.data['centros_sanitarios']
    this.titleService.setTitle('Crear relacion usuario centro' );
  }
  nuevaRelacionUsuarioCentro(): void {
    this.cargarRelacionUsuarioCentro.nuevaRelacionUsuarioCentro(this.relacionUsuarioCentro).subscribe(
      e => {
        console.log('Relacion creada');
        console.log(this.relacionUsuarioCentro)
      },
      error => {
        console.log(error);
      }
    )
  }
}
