import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {IHistoricoAgenda} from "../../../interfaces/i-historico-agenda";
import {IAgenda} from "../../../interfaces/i-agenda";
import {CargaHistoricoAgendaService} from "../../../servicios/carga-historico-agenda.service";


@Component({
  selector: 'app-modificar-historico-agenda',
  templateUrl: './modificar-historico-agenda.component.html',
  styleUrls: ['./modificar-historico-agenda.component.scss']
})
export class ModificarHistoricoAgendaComponent implements OnInit {

  public historico_agenda: IHistoricoAgenda;
  public id_historico_agenda: number;
  public agendas: IAgenda[];
  public teleoperadores: any[];

  constructor(
    private route: ActivatedRoute,
    private titleService: Title,
    private cargaHistoricoAgenda: CargaHistoricoAgendaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.historico_agenda = this.route.snapshot.data['historico_agenda'];
    this.id_historico_agenda = this.route.snapshot.params['id'];
    this.agendas = this.route.snapshot.data['agendas'];
    this.teleoperadores = this.route.snapshot.data['teleoperadores'];
    this.historico_agenda.id_agenda = this.historico_agenda.id_agenda.id;
    this.historico_agenda.id_teleoperador = this.historico_agenda.id_teleoperador.id;
  }

  modificarHistoricoDeAgenda(): void {
    this.cargaHistoricoAgenda.modificarHistoricoAgenda(this.historico_agenda).subscribe(
      e => {
        console.log('Historico agenda ' + e.id + ' modificado');
        console.log(this.historico_agenda);
        this.router.navigate(['/historico_agenda']);
      },
      error => {
        console.log(error);
      }
    );
  }

  optionSelected(i: number): void {
    document.getElementsByClassName('historico_agenda_option')[i].setAttribute('selected', '');
  }

  optionSelectedTeleoperador(i: number): void {
    document.getElementsByClassName('historico_agenda_option_teleoperador')[i].setAttribute('selected', '');
  }

}
