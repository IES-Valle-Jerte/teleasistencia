import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { Paciente } from "../../../clases/paciente";

@Component({
  selector: 'app-lista-paciente',
  templateUrl: './lista-paciente.component.html',
  styleUrls: ['./lista-paciente.component.scss']
})
export class ListaPacienteComponent implements OnInit {

  public pacientes: Paciente[]

  constructor(private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    this.pacientes = this.route.snapshot.data['pacientes']
    this.titleService.setTitle('Paciente');
  }

}
