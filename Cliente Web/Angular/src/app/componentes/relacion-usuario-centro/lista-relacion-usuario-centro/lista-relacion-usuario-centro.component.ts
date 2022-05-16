import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Title } from "@angular/platform-browser";
import {RelacionUsuarioCentro} from "../../../clases/relacion-usuario-centro";

@Component({
  selector: 'app-lista-relacion-usuario-centro',
  templateUrl: './lista-relacion-usuario-centro.component.html',
  styleUrls: ['./lista-relacion-usuario-centro.component.scss']
})
export class ListaRelacionUsuarioCentroComponent implements OnInit {
  public relacionesUsuarioCentro: RelacionUsuarioCentro[];
  constructor(private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    this.relacionesUsuarioCentro = this.route.snapshot.data['relaciones_usuario_centro'];
    this.titleService.setTitle('Relacion Usuario Centro');
  }

}
