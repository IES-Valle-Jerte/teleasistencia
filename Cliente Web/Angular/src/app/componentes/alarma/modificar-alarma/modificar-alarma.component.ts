import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { Alarma } from "../../../clases/alarma";
import { User } from "../../../clases/user";
import { CargaAlarmaService } from "../../../servicios/alarmas/carga-alarma.service";


@Component({
  selector: 'app-modificar-alarma',
  templateUrl: './modificar-alarma.component.html',
  styleUrls: ['./modificar-alarma.component.scss']
})
export class ModificarAlarmaComponent implements OnInit {
  public alarma: Alarma
  public idAlarma: number
  public teleoperadores: User[];


  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargarAlarmas: CargaAlarmaService) { }

  ngOnInit(): void {
    this.alarma = this.route.snapshot.data['alarma'];
    this.idAlarma = this.route.snapshot.params['id'];
    this.teleoperadores = this.route.snapshot.data['teleoperadores']

  }
  optionSelected(i: number): void {
    document.getElementsByClassName('user_option')[i].setAttribute('selected', '');
  }
  modificarAlarma(): void {
    this.cargarAlarmas.modificarAlarma(this.alarma).subscribe(
      e => {
        console.log('Alarma ' + e.id + ' modificada');
        console.log(this.alarma)
      },
      error => {
             console.log(error)
      }
    );
  }
}
