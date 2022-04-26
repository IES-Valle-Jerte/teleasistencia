import {ITipoVivienda} from "./i-tipo-vivienda";
import {ITerminal} from "./i-terminal";

export interface IPaciente {
  id: number,
  tiene_ucr: boolean,
  numero_expediente: string,
  numero_seguridad_social: string,
  prestacion_otros_servicios_sociales: string,
  observaciones_medicas: string,
  intereses_y_actividades: string,
  id_terminal: ITerminal,
  id_tipo_vivienda: ITipoVivienda
}
