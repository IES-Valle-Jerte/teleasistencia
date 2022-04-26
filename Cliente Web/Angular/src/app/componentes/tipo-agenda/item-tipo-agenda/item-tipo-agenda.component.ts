import {Component, Input, OnInit} from '@angular/core';
import {ITipoAgenda} from "../../../interfaces/i-tipo-agenda";

@Component({
  selector: 'app-item-tipo-agenda, [app-item-tipo-agenda]',
  templateUrl: './item-tipo-agenda.component.html',
  styleUrls: ['./item-tipo-agenda.component.scss']
})
export class ItemTipoAgendaComponent implements OnInit {
  @Input() public tipo_agenda: ITipoAgenda;
  constructor() { }

  ngOnInit(): void {
  }

}
