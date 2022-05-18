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
  selector: 'app-modificar-relacion-usuario-centro',
  templateUrl: './modificar-relacion-usuario-centro.component.html',
  styleUrls: ['./modificar-relacion-usuario-centro.component.scss']
})
export class ModificarRelacionUsuarioCentroComponent implements OnInit {
  public relacionUsuarioCentro: RelacionUsuarioCentro;
  public idRelacionUsuarioCentro: number;
  public pacientes: Paciente[];
  public centrosSanitario: CentroSanitario[];


  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router,
              private cargarRelacionUsuarioCentro: CargaRelacionUsuarioCentroService) { }

  ngOnInit(): void {
    this.relacionUsuarioCentro = this.route.snapshot.data['relacion_usuario_centro'];
    this.idRelacionUsuarioCentro = this.route.snapshot.params['id'];
    this.pacientes = this.route.snapshot.data['pacientes'];
    this.centrosSanitario = this.route.snapshot.data['centros_sanitarios']
    this.titleService.setTitle('Modificar relacion usuario centro ' + this.idRelacionUsuarioCentro);

    this.relacionUsuarioCentro.id_centro_sanitario = this.relacionUsuarioCentro.id_centro_sanitario.id;
    this.relacionUsuarioCentro.id_paciente = this.relacionUsuarioCentro.id_paciente.id;
  }
  optionSelectedCentroSanitario(i: number): void {
    document.getElementsByClassName('relacion_centro_sanitario_option')[i].setAttribute('selected', '');
  }
  optionSelectedPersonaPaciente(i: number): void {
    document.getElementsByClassName('relacion_paciente_option')[i].setAttribute('selected', '');
  }
  modificarRelacionUsuarioCentro():void {
    this.cargarRelacionUsuarioCentro.modificarRelacionUsuarioCentro(this.relacionUsuarioCentro).subscribe(
      e => {

        console.log('Relacion usuario centro ' + e.id + ' modificada');
        console.log(this.relacionUsuarioCentro)
        this.router.navigate(['/relaciones_usuario_centro'])
      },
      error => {
        console.log(error);
      }
    )
  }
}
