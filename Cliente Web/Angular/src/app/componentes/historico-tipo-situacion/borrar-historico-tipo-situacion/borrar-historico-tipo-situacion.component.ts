import { Component, OnInit } from '@angular/core';
import {ITipoAgenda} from "../../../interfaces/i-tipo-agenda";
import {ActivatedRoute, Router} from "@angular/router";
import {CargaTipoAgendaService} from "../../../servicios/carga-tipo-agenda.service";
import {IHistoricoTipoSituacion} from "../../../interfaces/i-historico-tipo-situacion";
import {CargaHistoricoTipoSituacionService} from "../../../servicios/carga-historico-tipo-situacion.service";

@Component({
  selector: 'app-borrar-historico-tipo-situacion',
  templateUrl: './borrar-historico-tipo-situacion.component.html',
  styleUrls: ['./borrar-historico-tipo-situacion.component.scss']
})
export class BorrarHistoricoTipoSituacionComponent implements OnInit {
  public historico_tipo_situacion: IHistoricoTipoSituacion;

  constructor(private route: ActivatedRoute, private cargaHistoricoTipoSituacion: CargaHistoricoTipoSituacionService, private router: Router) { }

  ngOnInit(): void {
    this.historico_tipo_situacion = this.route.snapshot.data['historico_tipo_situacion'];
  }

  borrarHistoricoTipoDeSituacion(): void {
    this.cargaHistoricoTipoSituacion.borrarHistoricoTipoSituacion(this.historico_tipo_situacion.id).subscribe(
      e => {
        console.log(this.historico_tipo_situacion);
        this.router.navigate(['/historico_situaciones']);
      },
      error => {
        console.log(error);
      }
    );
  }

}
