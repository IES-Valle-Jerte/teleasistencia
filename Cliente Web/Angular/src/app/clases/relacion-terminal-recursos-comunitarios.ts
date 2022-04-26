
import {IRecursoComunitario} from "../interfaces/i-recurso-comunitario";
import {ITerminal} from "../interfaces/i-terminal";
import {IRelacionTerminalRecursoComunitarios} from "../interfaces/i-relacion-terminal-recurso-comunitarios";

export class RelacionTerminalRecursosComunitarios implements IRelacionTerminalRecursoComunitarios{
  id: number;
  id_terminal: ITerminal;
  id_recurso_comunitario: IRecursoComunitario;

}
