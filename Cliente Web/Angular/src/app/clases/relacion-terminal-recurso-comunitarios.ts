import {IRelacionTerminalRecursoComunitarios} from "../interfaces/i-relacion-terminal-recurso-comunitarios";
import {ITerminal} from "../interfaces/i-terminal";
import {IRecursoComunitario} from "../interfaces/i-recurso-comunitario";

export class RelacionTerminalRecursoComunitarios implements IRelacionTerminalRecursoComunitarios{
  id: number;
  id_terminal: ITerminal;
  id_recurso_comunitario: IRecursoComunitario;
}
