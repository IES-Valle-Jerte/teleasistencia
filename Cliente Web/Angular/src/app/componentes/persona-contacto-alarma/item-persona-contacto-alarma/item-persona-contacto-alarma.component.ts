import { Component,Input, OnInit } from '@angular/core';
import {PersonaContactoAlarma} from "../../../clases/persona-contacto-alarma";

@Component({
  selector: 'app-item-persona-contacto-alarma, [app-item-persona-contacto-alarma]',
  templateUrl: './item-persona-contacto-alarma.component.html',
  styleUrls: ['./item-persona-contacto-alarma.component.scss']
})
export class ItemPersonaContactoAlarmaComponent implements OnInit {
  @Input() public personaContactoAlarma: PersonaContactoAlarma
  constructor() { }

  ngOnInit(): void {
  }

}
