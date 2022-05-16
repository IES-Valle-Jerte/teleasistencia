import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { DispositivosAuxiliaresTerminal } from "../../../clases/dispositivos-auxiliares-terminal";

@Component({
  selector: 'app-lista-dispositivos-auxiliares-terminal',
  templateUrl: './lista-dispositivos-auxiliares-terminal.component.html',
  styleUrls: ['./lista-dispositivos-auxiliares-terminal.component.scss']
})
export class ListaDispositivosAuxiliaresTerminalComponent implements OnInit {
  public dispositivosAuxiliaresTerminal: DispositivosAuxiliaresTerminal[]
  constructor(private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    this.dispositivosAuxiliaresTerminal = this.route.snapshot.data['dispositivos_auxiliares_terminal'];
    this.titleService.setTitle('Dispositivos auxiliares en terminal');
  }

}
