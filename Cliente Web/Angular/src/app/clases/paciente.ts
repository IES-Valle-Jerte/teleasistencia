import {IPaciente} from "../interfaces/i-paciente";
import {ITerminal} from "../interfaces/i-terminal";
import {ITipoVivienda} from "../interfaces/i-tipo-vivienda";
import {IPersona} from "../interfaces/i-persona";

export class Paciente implements IPaciente{
  id: number;
  tiene_ucr: boolean;
  numero_expediente: string;
  numero_seguridad_social: string;
  prestacion_otros_servicios_sociales: string;
  observaciones_medicas: string;
  intereses_y_actividades: string;
  id_terminal: ITerminal;
  id_persona: IPersona;
  id_tipo_vivienda: ITipoVivienda;
}
