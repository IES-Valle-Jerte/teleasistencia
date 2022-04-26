import {IRelacionPacientePersona} from "../interfaces/i-relacion-paciente-persona";
import {IPaciente} from "../interfaces/i-paciente";
import {IPersona} from "../interfaces/i-persona";

export class RelacionPacientePersona implements IRelacionPacientePersona{
  id: number;
  tipo_relacion: string;
  tiene_llaves_vivienda: boolean;
  disponibilidad: string;
  observaciones: string;
  prioridad: number;
  id_paciente: IPaciente;
  id_persona: IPersona;
}
