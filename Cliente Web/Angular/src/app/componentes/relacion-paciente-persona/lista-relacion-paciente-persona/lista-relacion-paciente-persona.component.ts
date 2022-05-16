import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Title} from '@angular/platform-browser';
import { RelacionPacientePersona } from "../../../clases/relacion-paciente-persona";

@Component({
  selector: 'app-lista-relacion-paciente-persona',
  templateUrl: './lista-relacion-paciente-persona.component.html',
  styleUrls: ['./lista-relacion-paciente-persona.component.scss']
})
export class ListaRelacionPacientePersonaComponent implements OnInit {
  public relaciones_pacientes_personas: RelacionPacientePersona[]
  constructor(private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    this.relaciones_pacientes_personas = this.route.snapshot.data['relaciones_pacientes_personas'];
    this.titleService.setTitle('Relacion Paciente Persona');
  }

}
