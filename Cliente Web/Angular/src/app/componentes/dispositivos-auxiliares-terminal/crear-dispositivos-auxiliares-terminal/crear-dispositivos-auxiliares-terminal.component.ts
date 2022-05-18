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
  selector: 'app-crear-dispositivos-auxiliares-terminal',
  templateUrl: './crear-dispositivos-auxiliares-terminal.component.html',
  styleUrls: ['./crear-dispositivos-auxiliares-terminal.component.scss']
})
export class CrearDispositivosAuxiliaresTerminalComponent implements OnInit {
  public dispositivoAuxiliarTerminal: DispositivosAuxiliaresTerminal;
  public terminales: Terminal[];
  public tipo_alarmas: TipoAlarma[];

  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router,
              private cargarDispositivosAuxiliaresTerminal: CargaDispositivosAuxiliaresTerminalService) { }

  ngOnInit(): void {
    this.dispositivoAuxiliarTerminal = new DispositivosAuxiliaresTerminal();
    this.terminales = this.route.snapshot.data['terminales'];
    this.tipo_alarmas = this.route.snapshot.data['tipo_alarmas'];
    this.titleService.setTitle('Crear dispositivo auxiliare terminal');

  }
  crearDispositivoAuxiliarTerminal(): void {
    this.cargarDispositivosAuxiliaresTerminal.nuevoDispositivoAuxiliarTerminal(this.dispositivoAuxiliarTerminal).subscribe(
      e => {

        console.log('Dispositivo Auxiliar Terminal ' + e.id + ' modificado');
        console.log(this.dispositivoAuxiliarTerminal)
        this.router.navigate(['/dispositivos_auxiliares_terminal'])
      },
      error => {
        console.log(error);
      }
    )
  }

}
