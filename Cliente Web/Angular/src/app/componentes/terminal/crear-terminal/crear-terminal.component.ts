import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Title } from "@angular/platform-browser";
import { Terminal } from "../../../clases/terminal";
import { Paciente} from "../../../clases/paciente";
import { TipoVivienda } from "../../../clases/tipo-vivienda";
import { CargaTerminalesService } from "../../../servicios/terminal/carga-terminales.service";


@Component({
  selector: 'app-crear-terminal',
  templateUrl: './crear-terminal.component.html',
  styleUrls: ['./crear-terminal.component.scss']
})
export class CrearTerminalComponent implements OnInit {
  public terminal: Terminal;
  public titulares: Paciente[];
  public tipo_vivienda: TipoVivienda[];
  constructor(private titleService: Title, private route: ActivatedRoute, private cargarTerminales: CargaTerminalesService, private router: Router) { }

  ngOnInit(): void {
    this.titleService.setTitle('Nuevo Terminal');
    this.terminal = new Terminal();
    this.titulares = this.route.snapshot.data['titulares'];
    this.tipo_vivienda = this.route.snapshot.data['tipos_vivienda']
  }
  nuevoTerminal(): void {
    this.cargarTerminales.nuevoTerminal(this.terminal).subscribe(
      e => {
        console.log('Alarma Creada')
        console.log(this.terminal)
        this.router.navigate(['/terminales'])
      },
        error => {
              console.log(error)
        }

    )
  }
}
