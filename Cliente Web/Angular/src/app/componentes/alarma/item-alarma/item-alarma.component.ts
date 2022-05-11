import { Component, Input, OnInit } from '@angular/core';
import { Alarma } from "../../../clases/alarma";


@Component({
  selector: 'app-item-alarma, [app-item-alarma]',
  templateUrl: './item-alarma.component.html',
  styleUrls: ['./item-alarma.component.scss']
})
export class ItemAlarmaComponent implements OnInit {
  @Input() public alarma: Alarma;
  constructor() { }

  ngOnInit(): void {
  }
  obtenerNombre(): string {
    if (this.alarma.id_teleoperador != null)
      return this.alarma.id_teleoperador.first_name
    else
      return ""
  }
}
