import { Component, OnInit } from '@angular/core';
import {IRelacionTerminalRecursoComunitarios} from "../../../interfaces/i-relacion-terminal-recurso-comunitarios";
import {ActivatedRoute} from '@angular/router';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-lista-relacion-terminal-recursos-comunitarios',
  templateUrl: './lista-relacion-terminal-recursos-comunitarios.component.html',
  styleUrls: ['./lista-relacion-terminal-recursos-comunitarios.component.scss']
})
export class ListaRelacionTerminalRecursosComunitariosComponent implements OnInit {
  public relacionRecursos: IRelacionTerminalRecursoComunitarios[];
  constructor(private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    this.relacionRecursos = this.route.snapshot.data['relacion_terminal_recursos_comunitarios'];
    this.titleService.setTitle('Relacion Terminal Recurso Comunitarios');
  }

}
