import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {ITipoAgenda} from "../../interfaces/i-tipo-agenda";

@Component({
  selector: 'app-tipo-agenda',
  templateUrl: './tipo-agenda.component.html',
  styleUrls: ['./tipo-agenda.component.scss']
})
export class TipoAgendaComponent implements OnInit {
  public tipos_agenda: ITipoAgenda[];

  constructor(private route: ActivatedRoute, private titleService: Title) {
  }

  ngOnInit(): void {
    this.tipos_agenda = this.route.snapshot.data['tipos_agenda'];
    this.titleService.setTitle('Tipos agenda');
  }

}
