import {Component, Input, OnInit} from '@angular/core';
import { DispositivosAuxiliaresTerminal } from "../../../clases/dispositivos-auxiliares-terminal";

@Component({
  selector: 'app-item-dispositivos-auxiliares-terminal, [app-item-dispositivos-auxiliares-terminal]',
  templateUrl: './item-dispositivos-auxiliares-terminal.component.html',
  styleUrls: ['./item-dispositivos-auxiliares-terminal.component.scss']
})
export class ItemDispositivosAuxiliaresTerminalComponent implements OnInit {
  @Input() public dispositivoAuxiliarTerminal: DispositivosAuxiliaresTerminal;
  constructor() { }

  ngOnInit(): void {
  }

}
