import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {
  CargaPersonaContactoAlarmaService
} from "../../../servicios/persona-contacto-alarma/carga-persona-contacto-alarma.service";
import {PersonaContactoAlarma} from "../../../clases/persona-contacto-alarma";
import {Alarma} from "../../../clases/alarma";
import {Persona} from "../../../clases/persona";

@Component({
  selector: 'app-crear-persona-contacto-alarma',
  templateUrl: './crear-persona-contacto-alarma.component.html',
  styleUrls: ['./crear-persona-contacto-alarma.component.scss']
})
export class CrearPersonaContactoAlarmaComponent implements OnInit {
  public personaContactoAlarma: PersonaContactoAlarma;
  public alarmas: Alarma[];
  public personas_contacto: Persona[];
  public fecha_actual = new Date();
  public anno_actual = this.fecha_actual.getFullYear();
  public mes_actual = this.fecha_actual.getMonth() + 1;
  public dia_actual = this.fecha_actual.getDate();
  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargarPersonaContactoAlarma: CargaPersonaContactoAlarmaService) { }

  ngOnInit(): void {
    this.personaContactoAlarma = new PersonaContactoAlarma();
    this.alarmas = this.route.snapshot.data['alarmas']
    this.personas_contacto = this.route.snapshot.data['personas_contactos']
    this.titleService.setTitle('Crear Persona de contacto en alarma');
    this.personaContactoAlarma.fecha_registro = this.fecha_actual;
  }
  nuevaPersonaContactoAlarma(): void {
    this.cargarPersonaContactoAlarma.nuevaPersonaContactoAlarma(this.personaContactoAlarma).subscribe(
      e => {
        console.log('Persona de contacto en alarma creada');
        console.log(this.personaContactoAlarma)
        this.router.navigate(['/persona_contacto_alarma'])
      },
      error => {
        console.log(error);
      }
    )
  }
}
