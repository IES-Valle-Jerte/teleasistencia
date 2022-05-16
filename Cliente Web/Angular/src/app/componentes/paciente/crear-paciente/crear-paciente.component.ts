import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import { Terminal } from "../../../clases/terminal";
import { Persona } from "../../../clases/persona";
import { TipoModalidadPaciente } from "../../../clases/tipo-modalidad-paciente";
import { Paciente } from "../../../clases/paciente";
import { CargaPacienteService } from "../../../servicios/paciente/carga-paciente.service";


@Component({
  selector: 'app-crear-paciente',
  templateUrl: './crear-paciente.component.html',
  styleUrls: ['./crear-paciente.component.scss']
})
export class CrearPacienteComponent implements OnInit {
  public paciente: Paciente
  public idPaciente: number
  public terminales: Terminal[]
  public personas: Persona[]
  public tipo_modalidades_pacientes: TipoModalidadPaciente[]

  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router,
              private cargarPacientes: CargaPacienteService) { }

  ngOnInit(): void {
    this.paciente = this.route.snapshot.data['paciente'];
    this.idPaciente = this.route.snapshot.params['id'];
    this.terminales = this.route.snapshot.data['terminales'];
    this.personas = this.route.snapshot.data['personas'];
    this.tipo_modalidades_pacientes = this.route.snapshot.data['tipo_modalidades_pacientes'];

  }
  nuevoPaciente(): void {
    this.cargarPacientes.nuevoPaciente(this.paciente).subscribe(
      e => {
        console.log('Paciente Cread');
        console.log(this.paciente);
        this.router.navigate(['/pacientes'])
      },
      error => {
        console.log(error);
      }
    )
  }

}
