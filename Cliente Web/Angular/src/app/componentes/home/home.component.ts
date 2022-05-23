import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {CargaDesarrolladorTecnologiaService} from "../../servicios/carga-desarrollador-tecnologia.service";
import {IConvocatoria} from "../../interfaces/i-desarrollador-tecnologia";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit {
  public desarrolladores: IConvocatoria[]
  constructor(private titleService: Title, private cargaDesarrolladorTecnologia: CargaDesarrolladorTecnologiaService)  {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Inicio');
    this.cargaDesarrolladorTecnologia.getDesarrolladorTecnologia().subscribe(
      e=>{
        this.desarrolladores = e;
        console.log(this.desarrolladores)
      }
    )
  }
  mostrarDescripcion(){
    let parrafoDescripcion = document.getElementById("mostrarDescripcion");
    parrafoDescripcion.classList.toggle('d-none');
  }
}
