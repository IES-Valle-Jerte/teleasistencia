import {Component, Input, OnInit} from '@angular/core';
import { RelacionUsuarioCentro } from "../../../clases/relacion-usuario-centro";

@Component({
  selector: 'app-item-relacion-usuario-centro, [app-item-relacion-usuario-centro]',
  templateUrl: './item-relacion-usuario-centro.component.html',
  styleUrls: ['./item-relacion-usuario-centro.component.scss']
})
export class ItemRelacionUsuarioCentroComponent implements OnInit {
  @Input() public relacionUsuarioCentro: RelacionUsuarioCentro;
  constructor() { }

  ngOnInit(): void {
  }

}
