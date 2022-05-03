import { Component, OnInit } from '@angular/core';
import {IAlarma} from "../../../interfaces/i-alarma";
import {ActivatedRoute} from '@angular/router';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-lista-alarmas',
  templateUrl: './lista-alarmas.component.html',
  styleUrls: ['./lista-alarmas.component.scss']
})
export class ListaAlarmasComponent implements OnInit {
  public alarmas : IAlarma[];
  constructor(private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    this.alarmas = this.route.snapshot.data['alarmas'];
    this.titleService.setTitle('Alarmas');
  }

}
