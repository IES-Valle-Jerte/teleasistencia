import {IIdTerminal} from "./i-id-terminal";
import {IIdRecursoComunitario} from "./i-id-recurso-comunitario";

export interface IRelacionTerminalRecursoComunitarios {
  id: number,
  id_terminal: IIdTerminal
  id_recurso_comunitario: IIdRecursoComunitario
}
