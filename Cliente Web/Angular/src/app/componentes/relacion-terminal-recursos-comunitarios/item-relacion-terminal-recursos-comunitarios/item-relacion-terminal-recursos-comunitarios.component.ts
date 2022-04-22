import {Component, Input, OnInit} from '@angular/core';
import {IRelacionTerminalRecursoComunitarios} from "../../../interfaces/i-relacion-terminal-recurso-comunitarios";


@Component({
  selector: 'app-item-relacion-terminal-recursos-comunitarios, [app-item-relacion-terminal-recursos-comunitarios]',
  templateUrl: './item-relacion-terminal-recursos-comunitarios.component.html',
  styleUrls: ['./item-relacion-terminal-recursos-comunitarios.component.scss']
})
export class ItemRelacionTerminalRecursosComunitariosComponent implements OnInit {
  @Input() public relacionRecurso: IRelacionTerminalRecursoComunitarios;
  constructor() { }

  ngOnInit(): void {
  }

}
