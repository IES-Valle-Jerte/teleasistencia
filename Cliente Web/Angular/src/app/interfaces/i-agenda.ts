import {IPaciente} from "./i-paciente";
import {ITipoAgenda} from "./i-tipo-agenda";
import {IPersona} from "./i-persona";

export interface IAgenda {
  id: number;
  id_paciente: IPaciente;
  id_tipo_agenda: ITipoAgenda;
  id_persona: IPersona;
  fecha_registro: Date;
  fecha_prevista: Date;
  fecha_resolucion: Date;
  observaciones: string;
}
