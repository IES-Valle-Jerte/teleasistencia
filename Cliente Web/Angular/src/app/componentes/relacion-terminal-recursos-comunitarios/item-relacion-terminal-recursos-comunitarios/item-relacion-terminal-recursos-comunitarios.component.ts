import {Component, Input, OnInit} from '@angular/core';
import { RelacionTerminalRecursoComunitarios} from "../../../clases/relacion-terminal-recurso-comunitarios";

@Component({
  selector: 'app-item-relacion-terminal-recursos-comunitarios, [app-item-relacion-terminal-recursos-comunitarios]',
  templateUrl: './item-relacion-terminal-recursos-comunitarios.component.html',
  styleUrls: ['./item-relacion-terminal-recursos-comunitarios.component.scss']
})
export class ItemRelacionTerminalRecursosComunitariosComponent implements OnInit {
  @Input() public relacionRecurso: RelacionTerminalRecursoComunitarios;
  constructor() { }

  ngOnInit(): void {
  }

}
