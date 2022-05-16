import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import {PersonaContactoAlarma} from "../../../clases/persona-contacto-alarma";

@Component({
  selector: 'app-lista-persona-contacto-alarma',
  templateUrl: './lista-persona-contacto-alarma.component.html',
  styleUrls: ['./lista-persona-contacto-alarma.component.scss']
})
export class ListaPersonaContactoAlarmaComponent implements OnInit {
  public personasContactoAlarma: PersonaContactoAlarma[];

  constructor(private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    this.personasContactoAlarma = this.route.snapshot.data['personas_contacto_alarma'];
    this.titleService.setTitle('Personas en contacto en alarma');
  }

}
