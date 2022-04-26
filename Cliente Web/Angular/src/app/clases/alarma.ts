import {IAlarma} from "../interfaces/i-alarma";
import {ITipoAlarma} from "../interfaces/i-tipo-alarma";
import {IUsers} from "../interfaces/i-users";

export class Alarma implements IAlarma{
  id: number;
  estado_alarma: string;
  fecha_registro: Date;
  observaciones: string;
  resumen: string;
  id_tipo_alarma: ITipoAlarma;
  id_teleoperador: IUsers;



}
