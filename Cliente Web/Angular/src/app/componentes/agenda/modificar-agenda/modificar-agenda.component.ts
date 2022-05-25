import { Component, OnInit } from '@angular/core';
import {IAgenda} from "../../../interfaces/i-agenda";
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {ITipoAgenda} from "../../../interfaces/i-tipo-agenda";
import {IPaciente} from "../../../interfaces/i-paciente";
import {IPersona} from "../../../interfaces/i-persona";
import {CargaAgendaService} from "../../../servicios/carga-agenda.service";


@Component({
  selector: 'app-modificar-agenda',
  templateUrl: './modificar-agenda.component.html',
  styleUrls: ['./modificar-agenda.component.scss']
})
export class ModificarAgendaComponent implements OnInit {

  public agenda: IAgenda;
  public idAgenda: number;
  public tipos_agenda: ITipoAgenda[];
  public pacientes: IPaciente[];
  public personas_contacto: IPersona[];

  constructor(
    private route: ActivatedRoute,
    private titleService: Title,
    private cargaAgendaService: CargaAgendaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.agenda = this.route.snapshot.data['agenda'];
    this.tipos_agenda = this.route.snapshot.data['tipos_agenda'];
    this.idAgenda = this.route.snapshot.params['id'];
    this.pacientes = this.route.snapshot.data['pacientes'];
    this.personas_contacto = this.route.snapshot.data['personas_contacto'];
    this.titleService.setTitle('Modificar agenda ' + this.idAgenda);

    this.agenda.id_tipo_agenda = this.agenda.id_tipo_agenda.id;
    this.agenda.id_paciente = this.agenda.id_paciente.id;
    this.agenda.id_persona = this.agenda.id_persona.id;
  }

  modificarEventoAgenda(): void {
    this.cargaAgendaService.modificarAgenda(this.agenda).subscribe(
      e => {
        console.log('Agenda ' + e.id + ' modificada');
        console.log(this.agenda);
        this.router.navigate(['/agenda']);
      },
      error => {
        console.log(error);
      }
    );
  }

  optionSelected(i: number): void {
    document.getElementsByClassName('agenda_option')[i].setAttribute('selected', '');
  }
}
