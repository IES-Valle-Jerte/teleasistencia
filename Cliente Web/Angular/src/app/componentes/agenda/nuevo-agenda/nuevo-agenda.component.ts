import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ActivatedRoute, Router} from "@angular/router";
import {IAgenda} from "../../../interfaces/i-agenda";
import {Agenda} from "../../../clases/agenda";
import {CargaAgendaService} from "../../../servicios/carga-agenda.service";
import {ITipoAgenda} from "../../../interfaces/i-tipo-agenda";
import {IPersona} from "../../../interfaces/i-persona";

@Component({
  selector: 'app-nuevo-agenda',
  templateUrl: './nuevo-agenda.component.html',
  styleUrls: ['./nuevo-agenda.component.scss']
})
export class NuevoAgendaComponent implements OnInit {

  public agenda: IAgenda;
  public tipos_agenda: ITipoAgenda[];
  public personas_contacto: IPersona[];

  constructor(private titleService: Title, private route: ActivatedRoute, private cargaAgendas: CargaAgendaService, private router: Router) { }

  ngOnInit(): void {
    this.tipos_agenda = this.route.snapshot.data['tipos_agenda'];
    this.personas_contacto = this.route.snapshot.data['personas'];
    this.titleService.setTitle('Nuevo agenda');
    this.agenda = new Agenda();
  }

  optionSelected(i: number): void {
    document.getElementsByClassName('tipo_agenda_option')[i].setAttribute('selected', '');
  }

  nuevoAgenda() {
    this.cargaAgendas.nuevoAgenda(this.agenda).subscribe(
      e => {
        console.log('Agenda creada');
        console.log(this.agenda);
        this.router.navigate(['/agenda']);
      },
      error => {
        console.log(error);
      }
    );
  }

}
