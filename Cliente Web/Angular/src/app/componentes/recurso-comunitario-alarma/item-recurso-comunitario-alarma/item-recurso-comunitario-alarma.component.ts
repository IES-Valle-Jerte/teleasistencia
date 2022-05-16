import {Component, Input, OnInit} from '@angular/core';
import {RecursosComunitariosAlarma} from "../../../clases/recursos-comunitarios-alarma";

@Component({
  selector: 'app-item-recurso-comunitario-alarma, [app-item-recurso-comunitario-alarma]',
  templateUrl: './item-recurso-comunitario-alarma.component.html',
  styleUrls: ['./item-recurso-comunitario-alarma.component.scss']
})
export class ItemRecursoComunitarioAlarmaComponent implements OnInit {
  @Input() public recursoComunitarioAlarma: RecursosComunitariosAlarma
  constructor() { }

  ngOnInit(): void {
  }

}
