import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { CargaTerminalesService } from "../../../servicios/terminal/carga-terminales.service";
import { Terminal } from "../../../clases/terminal";
import { Paciente } from "../../../clases/paciente";
import { TipoVivienda } from "../../../clases/tipo-vivienda";

@Component({
  selector: 'app-modificar-terminal',
  templateUrl: './modificar-terminal.component.html',
  styleUrls: ['./modificar-terminal.component.scss']
})
export class ModificarTerminalComponent implements OnInit {

  public terminal: Terminal
  public idTerminal: number
  public titulares: Paciente[]
  public tipo_vivienda: TipoVivienda[]


  constructor(private route: ActivatedRoute, private titleService: Title, private router: Router, private cargarTerminales: CargaTerminalesService) { }

  ngOnInit(): void {
    this.terminal = this.route.snapshot.data['terminales'];
    this.idTerminal = this.route.snapshot.params['id'];
    this.titulares = this.route.snapshot.data['titulares'];
    this.tipo_vivienda = this.route.snapshot.data['tipo_vivienda']
    this.terminal.id_titular = this.terminal.id_titular.id;
    this.terminal.id_tipo_vivienda = this.terminal.id_tipo_vivienda.id
  }
  optionSelectedTitular(i: number): void {
    document.getElementsByClassName('titular_option')[i].setAttribute('selected', '');
  }
  optionSelectedTipoVivienda(i: number): void {
    document.getElementsByClassName('tipo_vivienda_option')[i].setAttribute('selected', '');
  }
  modificarTerminal(): void {
    this.cargarTerminales.modificarTerminal(this.terminal).subscribe(
      e => {
        console.log('Terminal ' +e.id + ' modificado');
        console.log(this.terminal)
      },
      error => {
             console.log(error)
      }
    )
  }
  mostrar() {
    console.log(this.terminal)
  }
}


