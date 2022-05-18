import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {RelacionPacientePersona} from "../../../clases/relacion-paciente-persona";
import {Paciente} from "../../../clases/paciente";
import {Persona} from "../../../clases/persona";
import {
  CargaRelacionPacientePersonaService
} from "../../../servicios/relacion-paciente-persona/carga-relacion-paciente-persona.service";


@Component({
  selector: 'app-crear-relacion-paciente-persona',
  templateUrl: './crear-relacion-paciente-persona.component.html',
  styleUrls: ['./crear-relacion-paciente-persona.component.scss']
})
export class CrearRelacionPacientePersonaComponent implements OnInit {
  public relacionPacientePersona: RelacionPacientePersona;
  public pacientes: Paciente[];
  public personas: Persona[];

  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargarRelacionPacientePersona: CargaRelacionPacientePersonaService) { }

  ngOnInit(): void {
    this.relacionPacientePersona = new RelacionPacientePersona();
    this.pacientes = this.route.snapshot.data['pacientes']
    this.personas = this.route.snapshot.data['personas']
    this.titleService.setTitle('Nueva relacion de Paciente con persona');

  }
  nuevaRelacionPacientePersona() : void {
    this.cargarRelacionPacientePersona.nuevaRelacionPacientePersona(this.relacionPacientePersona).subscribe(
      e => {
        console.log('Relacion creada');
        console.log(this.relacionPacientePersona)
        this.router.navigate(['/relacion_paciente_persona'])
      },
      error => {
        console.log(error);
      }
  );
  }
}
