import {IAlarma} from "./i-alarma";
import {IPersona} from "./i-persona";

export interface IPersonaContactoAlarma {
  id: number;
  fecha_registro: Date;
  acuerdo_alcanzado: string;
  id_alarma: IAlarma;
  id_persona_contacto: IPersona;
}
