import {Component, Input, OnInit} from '@angular/core';
import {ICopiaSeguridad} from "../../../interfaces/i-copia-seguridad";

@Component({
  selector: 'app-item-copia-seguridad, [app-item-copia-seguridad]',
  templateUrl: './item-copia-seguridad.component.html',
  styleUrls: ['./item-copia-seguridad.component.scss']
})
export class ItemCopiaSeguridadComponent implements OnInit {
  @Input() public copia_seguridad: ICopiaSeguridad;

  constructor() { }

  ngOnInit(): void {
  }

}
