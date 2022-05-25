import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ActivatedRoute, Router} from "@angular/router";
import {CargaAgendaService} from "../../../servicios/carga-agenda.service";
import {CargaHistoricoAgendaService} from "../../../servicios/carga-historico-agenda.service";
import {IHistoricoAgenda} from "../../../interfaces/i-historico-agenda";
import {HistoricoAgenda} from "../../../clases/historico-agenda";
import {IAgenda} from "../../../interfaces/i-agenda";
import {toInteger} from "@ng-bootstrap/ng-bootstrap/util/util";

@Component({
  selector: 'app-nuevo-historico-agenda',
  templateUrl: './nuevo-historico-agenda.component.html',
  styleUrls: ['./nuevo-historico-agenda.component.scss']
})
export class NuevoHistoricoAgendaComponent implements OnInit {

  public historico_agenda: IHistoricoAgenda;
  public agendas: IAgenda[];
  public teleoperadores: any[];
  public agenda: IAgenda;

  constructor(
    private titleService: Title,
    private route: ActivatedRoute,
    private cargaHistoricoAgenda: CargaHistoricoAgendaService,
    private cargaAgendaService: CargaAgendaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.historico_agenda = new HistoricoAgenda();
    this.agendas = this.route.snapshot.data['agendas'];
    this.teleoperadores = this.route.snapshot.data['teleoperadores'];
    this.agenda = this.route.snapshot.data['agenda'];

    this.historico_agenda.id_agenda = this.agenda.id;
  }

  nuevoHistoricoAgenda() {
    this.cargaHistoricoAgenda.nuevoHistoricoAgenda(this.historico_agenda).subscribe(
      e => {
        this.modificarFechaResolucionAgenda();
      },
      error => {
        console.log(error);
      }
    );
  }

  modificarFechaResolucionAgenda() {
    let fecha_resolucion = new Date();

    // @ts-ignore
    this.agenda.fecha_resolucion = fecha_resolucion.getFullYear()
      + '-' + (fecha_resolucion.getMonth() + 1)
      + '-' + fecha_resolucion.getDate();

    this.agenda.id_tipo_agenda = this.agenda.id_tipo_agenda.id;
    this.agenda.id_paciente = this.agenda.id_paciente.id;
    this.agenda.id_persona = this.agenda.id_persona.id;


    this.cargaAgendaService.modificarAgenda(this.agenda).subscribe(
      e => {
        this.router.navigate(['/historico_agenda']);
      },
      error => {
        console.log(error);
      }
    );
  }

  optionSelected(i: number): void {
    document.getElementsByClassName('agenda_id_option')[i].setAttribute('selected', '');
  }
}
