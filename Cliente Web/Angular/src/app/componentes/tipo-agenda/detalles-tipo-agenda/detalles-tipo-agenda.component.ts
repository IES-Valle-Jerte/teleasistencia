import { Component, OnInit } from '@angular/core';
import {ITipoAgenda} from "../../../interfaces/i-tipo-agenda";
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {CargaTipoAgendaService} from "../../../servicios/carga-tipo-agenda.service";

@Component({
  selector: 'app-detalles-tipo-agenda',
  templateUrl: './detalles-tipo-agenda.component.html',
  styleUrls: ['./detalles-tipo-agenda.component.scss']
})
export class DetallesTipoAgendaComponent implements OnInit {

  public tipo_agenda: ITipoAgenda;
  public idTipoAgenda: number;

  constructor(private route: ActivatedRoute, private titleService: Title, private cargaTipoAgendaService: CargaTipoAgendaService, private router: Router) {
  }

  ngOnInit(): void {
    this.tipo_agenda = this.route.snapshot.data['tipo_agenda'];
    this.idTipoAgenda = this.route.snapshot.params['id'];
    this.titleService.setTitle('Modificar tipo agenda ' + this.idTipoAgenda);
  }

  modificarTipoAgenda(): void {
    this.cargaTipoAgendaService.modificarTipoAgenda(this.tipo_agenda).subscribe(
      e => {
        console.log('Tipo agenda ' + e.id + ' modificado');
        console.log(this.tipo_agenda);
        this.router.navigate(['/tipo_agenda']);
      },
      error => {
        console.log(error);
      }
    );
  }

}
