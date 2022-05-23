import {IAgenda} from "../interfaces/i-agenda";
import {IPaciente} from "../interfaces/i-paciente";
import {ITipoAgenda} from "../interfaces/i-tipo-agenda";
import {IPersona} from "../interfaces/i-persona";

export class Agenda implements IAgenda {
  id: number;
  id_paciente: IPaciente;
  id_tipo_agenda: ITipoAgenda;
  id_persona: IPersona;
  fecha_registro: Date;
  fecha_prevista: Date;
  fecha_resolucion: Date;
  observaciones: string;
}
