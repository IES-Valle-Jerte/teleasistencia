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

}
