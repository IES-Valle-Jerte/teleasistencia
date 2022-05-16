import {Component, Input, OnInit} from '@angular/core';
import {CentroSanitarioAlarma} from "../../../clases/centro-sanitario-alarma";

@Component({
  selector: 'app-item-centro-sanitario-alarma, [app-item-centro-sanitario-alarma]',
  templateUrl: './item-centro-sanitario-alarma.component.html',
  styleUrls: ['./item-centro-sanitario-alarma.component.scss']
})
export class ItemCentroSanitarioAlarmaComponent implements OnInit {
  @Input() public centroSanitarioAlarma: CentroSanitarioAlarma;
  constructor() { }

  ngOnInit(): void {
  }

}
