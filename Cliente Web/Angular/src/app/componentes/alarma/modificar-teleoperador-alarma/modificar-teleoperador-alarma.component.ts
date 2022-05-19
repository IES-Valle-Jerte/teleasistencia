import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { Alarma } from "../../../clases/alarma";
import { User } from "../../../clases/user";
import { CargaAlarmaService } from "../../../servicios/alarmas/carga-alarma.service";

@Component({
  selector: 'app-modificar-teleoperador-alarma',
  templateUrl: './modificar-teleoperador-alarma.component.html',
  styleUrls: ['./modificar-teleoperador-alarma.component.scss']
})
export class ModificarTeleoperadorAlarmaComponent implements OnInit {
  public alarma: Alarma
  public idAlarma: number
  public teleoperadores: User[];

  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargarAlarmas: CargaAlarmaService) { }

  ngOnInit(): void {
    this.alarma = this.route.snapshot.data['alarma'];
    this.idAlarma = this.route.snapshot.params['id'];
    this.teleoperadores = this.route.snapshot.data['teleoperadores'];
    this.alarma.id_teleoperador = this.alarma.id_teleoperador.id;

  }
  modificarTeleoperador(): void {
    this.cargarAlarmas.modificarAlarma(this.alarma).subscribe(
      e => {
        console.log('Alarma ' + e.id + ' modificada');
        console.log(this.alarma)
        this.router.navigate(['/alarmas'])
      },
      error => {
        console.log(error)
      }
    );
  }
}
