import { Component, OnInit } from '@angular/core';
import {ITipoAgenda} from "../../../interfaces/i-tipo-agenda";
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {CargaHistoricoTipoSituacionService} from "../../../servicios/carga-historico-tipo-situacion.service";
import {IHistoricoTipoSituacion} from "../../../interfaces/i-historico-tipo-situacion";
import {ITipoSituacion} from "../../../interfaces/i-tipo-situacion";
import {ITerminal} from "../../../interfaces/i-terminal";

@Component({
  selector: 'app-modificar-historico-tipo-situacion',
  templateUrl: './modificar-historico-tipo-situacion.component.html',
  styleUrls: ['./modificar-historico-tipo-situacion.component.scss']
})
export class ModificarHistoricoTipoSituacionComponent implements OnInit {

  public historico_tipo_situacion: IHistoricoTipoSituacion;
  public idHistoricoTipoSituacion: number;
  public tipos_situaciones: ITipoSituacion[];
  public terminales: ITerminal[];


  constructor(private route: ActivatedRoute, private titleService: Title, private cargaHistoricoTipoSituacion: CargaHistoricoTipoSituacionService, private router: Router) {
  }

  ngOnInit(): void {
    this.historico_tipo_situacion = this.route.snapshot.data['historicos_situaciones'];
    this.idHistoricoTipoSituacion = this.route.snapshot.params['id'];
    this.titleService.setTitle('Modificar tipo agenda ' + this.idHistoricoTipoSituacion);
    this.tipos_situaciones = this.route.snapshot.data['tipos_situaciones'];
    this.terminales = this.route.snapshot.data['terminales'];
  }

  modificarHistoricoTipoDeSituacion(): void {
    this.cargaHistoricoTipoSituacion.modificarHistoricoTipoSituacion(this.historico_tipo_situacion).subscribe(
      e => {
        console.log('Historico tipo situación ' + e.id + ' modificado');
        console.log(this.historico_tipo_situacion);
        this.router.navigate(['/historico_situaciones']);
      },
      error => {
        console.log(error);
      }
    );
  }

}
