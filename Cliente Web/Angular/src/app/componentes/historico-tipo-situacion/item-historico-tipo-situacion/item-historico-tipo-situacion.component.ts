import {Component, Input, OnInit} from '@angular/core';
import {IHistoricoTipoSituacion} from "../../../interfaces/i-historico-tipo-situacion";

@Component({
  selector: 'app-item-historico-tipo-situacion, [app-item-historico-tipo-situacion]',
  templateUrl: './item-historico-tipo-situacion.component.html',
  styleUrls: ['./item-historico-tipo-situacion.component.scss']
})
export class ItemHistoricoTipoSituacionComponent implements OnInit {
  @Input() public historico_tipo_situacion: IHistoricoTipoSituacion;
  constructor() { }

  ngOnInit(): void {
  }

}
