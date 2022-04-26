import {ICentroSanitarioAlarma} from "../interfaces/i-centro-sanitario-alarma";
import {IAlarma} from "../interfaces/i-alarma";
import {ICentroSanitario} from "../interfaces/i-centro-sanitario";

export class CentroSanitarioAlarma implements ICentroSanitarioAlarma{
  id: number;
  fecha_registro: Date;
  persona: string;
  acuerdo_alcanzado: string;
  id_alarma: IAlarma;
  id_centro_sanitario: ICentroSanitario;
}
