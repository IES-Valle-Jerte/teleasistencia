import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { Terminal } from "../../../clases/terminal";

@Component({
  selector: 'app-lista-terminal',
  templateUrl: './lista-terminal.component.html',
  styleUrls: ['./lista-terminal.component.scss']
})
export class ListaTerminalComponent implements OnInit {
  public terminales: Terminal[]
  constructor(private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    this.terminales = this.route.snapshot.data['terminales']
    this.titleService.setTitle('Terminal');
  }

}
