import {ITipoAgenda} from "./i-tipo-agenda";

export interface IAgenda {
  id: number;
  id_paciente: number;
  id_tipo_agenda: ITipoAgenda;
  id_persona: number;
  fecha_registro: Date;
  fecha_prevista: Date;
  fecha_resolucion: Date;
  observaciones: string;
}
