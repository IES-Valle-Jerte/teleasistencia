import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ITipoAgenda} from "../../../interfaces/i-tipo-agenda";
import {CargaTipoAgendaService} from "../../../servicios/carga-tipo-agenda.service";

@Component({
  selector: 'app-borrar-tipo-agenda',
  templateUrl: './borrar-tipo-agenda.component.html',
  styleUrls: ['./borrar-tipo-agenda.component.scss']
})
export class BorrarTipoAgendaComponent implements OnInit {
  public tipo_agenda: ITipoAgenda;

  constructor(private route: ActivatedRoute, private cargaTiposAgenda: CargaTipoAgendaService, private router: Router) { }

  ngOnInit(): void {
    this.tipo_agenda = this.route.snapshot.data['tipo_agenda'];
  }

  borrarTipoAgenda(): void {
    this.cargaTiposAgenda.borrarTipoAgenda(this.tipo_agenda.id).subscribe(
      e => {
        console.log(this.tipo_agenda);
        this.router.navigate(['/tipo_agenda']);
      },
      error => {
        console.log(error);
      }
    );
  }
}
