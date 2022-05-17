import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ActivatedRoute, Router} from "@angular/router";
import {IHistoricoTipoSituacion} from "../../../interfaces/i-historico-tipo-situacion";
import {CargaHistoricoTipoSituacionService} from "../../../servicios/carga-historico-tipo-situacion.service";
import {HistoricoTipoSituacion} from "../../../clases/historico-tipo-situacion";
import {ITipoSituacion} from "../../../interfaces/i-tipo-situacion";
import {ITerminal} from "../../../interfaces/i-terminal";

@Component({
  selector: 'app-crear-historico-tipo-situacion',
  templateUrl: './crear-historico-tipo-situacion.component.html',
  styleUrls: ['./crear-historico-tipo-situacion.component.scss']
})
export class CrearHistoricoTipoSituacionComponent implements OnInit {

  public historico_tipo_situacion: IHistoricoTipoSituacion;
  public tipos_situaciones: ITipoSituacion[];
  public terminales: ITerminal[];

  constructor(private titleService: Title,
              private route: ActivatedRoute,
              private cargaHistoricoTipoSituacion: CargaHistoricoTipoSituacionService,
              private router: Router) { }

  ngOnInit(): void {
    this.titleService.setTitle('Nuevo tipo agenda');
    this.historico_tipo_situacion = new HistoricoTipoSituacion();
    this.tipos_situaciones = this.route.snapshot.data['tipos_situaciones'];
    this.terminales = this.route.snapshot.data['terminales'];
  }

  crearHistoricoTipoSituacion() {
    this.cargaHistoricoTipoSituacion.nuevoHistoricoTipoSituacion(this.historico_tipo_situacion).subscribe(
      e => {
        console.log('Historico tipo situación');
        console.log(this.historico_tipo_situacion);
        this.router.navigate(['/historico_situaciones']);
      },
      error => {
        console.log(error);
      }
    );
  }

}
