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
  selector: 'app-modificar-persona-contacto-alarma',
  templateUrl: './modificar-persona-contacto-alarma.component.html',
  styleUrls: ['./modificar-persona-contacto-alarma.component.scss']
})
export class ModificarPersonaContactoAlarmaComponent implements OnInit {
  public personaContactoAlarma: PersonaContactoAlarma;
  public idPersonaContactoAlarma: number;
  public alarmas: Alarma[];
  public personas_contacto: Persona[];
  public fecha_actual = new Date();
  public anno_actual = this.fecha_actual.getFullYear();
  public mes_actual = this.fecha_actual.getMonth() + 1;
  public dia_actual = this.fecha_actual.getDate();

  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargarPersonaContactoAlarma: CargaPersonaContactoAlarmaService) { }

  ngOnInit(): void {
    this.personaContactoAlarma = this.route.snapshot.data['persona_contacto_alarma'];
    this.idPersonaContactoAlarma = this.route.snapshot.params['id'];
    this.alarmas = this.route.snapshot.data['alarmas']
    this.personas_contacto = this.route.snapshot.data['personas_contactos']
    this.titleService.setTitle('Modificar Persona de contacto en alarma' + this.idPersonaContactoAlarma);

    this.personaContactoAlarma.id_alarma = this.personaContactoAlarma.id_alarma.id;
    this.personaContactoAlarma.id_persona_contacto = this.personaContactoAlarma.id_persona_contacto.id;
  }
  optionSelectedAlarma(i: number): void {
    document.getElementsByClassName('relacion_alarma_option')[i].setAttribute('selected', '');
  }
  optionSelectedPersonaContacto(i: number): void {
    document.getElementsByClassName('relacion_persona_contacto_option')[i].setAttribute('selected', '');
  }
  modificarPersonaContactoAlarma(): void {
    this.cargarPersonaContactoAlarma.modificarPersonaContactoAlarma(this.personaContactoAlarma).subscribe(
      e => {

        console.log('Persona de contacto en alarma ' + e.id + ' modificada');
        console.log(this.personaContactoAlarma)
        this.router.navigate(['/persona_contacto_alarma'])
      },
      error => {
        console.log(error);
      }
    );

  }
}
