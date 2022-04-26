import {ITipoAlarma} from "./i-tipo-alarma";
import {IUsers} from "./i-users";

export interface IAlarma {
  id: number,
  estado_alarma: string,
  fecha_registro: Date,
  observaciones: string,
  resumen: string,
  id_tipo_alarma: ITipoAlarma,
  id_teleoperador: IUsers,

}
