import {Component, Input, OnInit} from '@angular/core';
import {Terminal} from "../../../clases/terminal";

@Component({
  selector: 'app-item-terminal, [app-item-terminal]',
  templateUrl: './item-terminal.component.html',
  styleUrls: ['./item-terminal.component.scss']
})
export class ItemTerminalComponent implements OnInit {
  @Input() public terminal: Terminal;
  constructor() { }

  ngOnInit(): void {
  }

}
