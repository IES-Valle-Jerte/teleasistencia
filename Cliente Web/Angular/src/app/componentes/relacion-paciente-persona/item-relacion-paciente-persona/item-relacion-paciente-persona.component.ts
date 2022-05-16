import {Component, Input, OnInit} from '@angular/core';
import { RelacionPacientePersona } from "../../../clases/relacion-paciente-persona";

@Component({
  selector: 'app-item-relacion-paciente-persona, [app-item-relacion-paciente-persona]',
  templateUrl: './item-relacion-paciente-persona.component.html',
  styleUrls: ['./item-relacion-paciente-persona.component.scss']
})
export class ItemRelacionPacientePersonaComponent implements OnInit {
  @Input() public relacionPacientePersona: RelacionPacientePersona
  constructor() { }

  ngOnInit(): void {
  }

}
