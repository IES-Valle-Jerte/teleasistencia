import {IPersonaContactoAlarma} from "../interfaces/i-persona-contacto-alarma";
import {IAlarma} from "../interfaces/i-alarma";
import {IPersona} from "../interfaces/i-persona";

export class PersonaContactoAlarma implements IPersonaContactoAlarma{
  id: number;
  fecha_registro: Date;
  acuerdo_alcanzado: string;
  id_alarma: IAlarma;
  id_persona_contacto: IPersona;
}
