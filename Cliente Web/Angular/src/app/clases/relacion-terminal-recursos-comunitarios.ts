
import {IIdRecursoComunitario} from "../interfaces/i-id-recurso-comunitario";
import {IIdTerminal} from "../interfaces/i-id-terminal";
import {IRelacionTerminalRecursoComunitarios} from "../interfaces/i-relacion-terminal-recurso-comunitarios";

export class RelacionTerminalRecursosComunitarios implements IRelacionTerminalRecursoComunitarios{
  id: number;
  id_terminal: IIdTerminal;
  id_recurso_comunitario: IIdRecursoComunitario;

}
