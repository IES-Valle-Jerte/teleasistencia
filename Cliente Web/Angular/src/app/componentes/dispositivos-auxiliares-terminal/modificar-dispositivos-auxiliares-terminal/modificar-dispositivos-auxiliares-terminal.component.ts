import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {
  CargaDispositivosAuxiliaresTerminalService
} from "../../../servicios/dispositivos-auxiliares-terminal/carga-dispositivos-auxiliares-terminal.service";
import {DispositivosAuxiliaresTerminal} from "../../../clases/dispositivos-auxiliares-terminal";
import {Terminal} from "../../../clases/terminal";
import {TipoAlarma} from "../../../clases/tipo-alarma";

@Component({
  selector: 'app-modificar-dispositivos-auxiliares-terminal',
  templateUrl: './modificar-dispositivos-auxiliares-terminal.component.html',
  styleUrls: ['./modificar-dispositivos-auxiliares-terminal.component.scss']
})
export class ModificarDispositivosAuxiliaresTerminalComponent implements OnInit {
  public dispositivoAuxiliarTerminal: DispositivosAuxiliaresTerminal;
  public idDispositivoAuxiliarTerminal: number
  public terminales: Terminal[];
  public tipo_alarmas: TipoAlarma[];

  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router,
              private cargarDispositivosAuxiliaresTerminal: CargaDispositivosAuxiliaresTerminalService) { }

  ngOnInit(): void {
    this.dispositivoAuxiliarTerminal = this.route.snapshot.data['dispositivo_auxiliar_terminal'];
    this.idDispositivoAuxiliarTerminal = this.route.snapshot.params['id'];
    this.terminales = this.route.snapshot.data['terminales'];
    this.tipo_alarmas = this.route.snapshot.data['tipo_alarmas'];
    this.titleService.setTitle('Modificar dispositivo auxiliare terminal' + this.idDispositivoAuxiliarTerminal);

    this.dispositivoAuxiliarTerminal.id_terminal = this.dispositivoAuxiliarTerminal.id_terminal.id;
    this.dispositivoAuxiliarTerminal.id_tipo_alarma = this.dispositivoAuxiliarTerminal.id_tipo_alarma.id
  }
  optionSelectedAlarma(i: number): void {
    document.getElementsByClassName('relacion_alarma_option')[i].setAttribute('selected', '');
  }
  optionSelectedTerminal(i: number): void {
    document.getElementsByClassName('relacion_terminal_option')[i].setAttribute('selected', '');
  }
  modificarDispositivoAuxiliarTerminal(): void {
    this.cargarDispositivosAuxiliaresTerminal.modificarDispositivoAuxiliarTerminal(this.dispositivoAuxiliarTerminal).subscribe(
      e => {

        console.log('Dispositivo Auxiliar Terminal ' + e.id + ' modificado');
        console.log(this.dispositivoAuxiliarTerminal)
      },
      error => {
        console.log(error);
      }
    )
  }
}
