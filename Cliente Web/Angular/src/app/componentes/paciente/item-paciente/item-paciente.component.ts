import {Component, Input, OnInit} from '@angular/core';
import { Paciente } from "../../../clases/paciente";

@Component({
  selector: 'app-item-paciente, [app-item-paciente]',
  templateUrl: './item-paciente.component.html',
  styleUrls: ['./item-paciente.component.scss']
})
export class ItemPacienteComponent implements OnInit {

  @Input() public paciente: Paciente;

  constructor() { }

  ngOnInit(): void {
  }
  comprobarUcr(): string {
    if (this.paciente.tiene_ucr == true) {
      return 'Sí'
    }
    return  'No'
      }
}
