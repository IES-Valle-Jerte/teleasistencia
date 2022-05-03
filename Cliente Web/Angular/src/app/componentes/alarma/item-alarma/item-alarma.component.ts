import {Component, Input, OnInit} from '@angular/core';
import {IAlarma} from "../../../interfaces/i-alarma";


@Component({
  selector: 'app-item-alarma, [app-item-alarma]',
  templateUrl: './item-alarma.component.html',
  styleUrls: ['./item-alarma.component.scss']
})
export class ItemAlarmaComponent implements OnInit {
  @Input() public alarma: IAlarma;
  constructor() { }

  ngOnInit(): void {
  }

}
