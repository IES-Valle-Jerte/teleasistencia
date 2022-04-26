import { Component, OnInit } from '@angular/core';
import {ITipoAgenda} from "../../../interfaces/i-tipo-agenda";
import {TipoAgenda} from "../../../clases/tipo-agenda";
import {Title} from "@angular/platform-browser";
import {ActivatedRoute, Router} from "@angular/router";
import {CargaTipoAgendaService} from "../../../servicios/carga-tipo-agenda.service";




@Component({
  selector: 'app-nuevo-tipo-agenda',
  templateUrl: './nuevo-tipo-agenda.component.html',
  styleUrls: ['./nuevo-tipo-agenda.component.scss']
})
export class NuevoTipoAgendaComponent implements OnInit {
  public tipo_agenda: ITipoAgenda;

  constructor(private titleService: Title, private route: ActivatedRoute, private cargaTiposAgenda: CargaTipoAgendaService, private router: Router) { }

  ngOnInit(): void {
    this.titleService.setTitle('Nuevo tipo agenda');
    this.tipo_agenda = new TipoAgenda();
  }

  nuevoTipoAgenda() {
    this.cargaTiposAgenda.nuevoTipoAgenda(this.tipo_agenda).subscribe(
      e => {
        console.log('Tipo agenda creado');
        console.log(this.tipo_agenda);
        this.router.navigate(['/tipo_agenda']);
      },
      error => {
        console.log(error);
      }
    );
  }
}
