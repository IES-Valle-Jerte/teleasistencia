import {IAlarma} from "../interfaces/i-alarma";
import {ITipoAlarma} from "../interfaces/i-tipo-alarma";
import {IUsers} from "../interfaces/i-users";
import {IPaciente} from "../interfaces/i-paciente";
import {ITerminal} from "../interfaces/i-terminal";

export class Alarma implements IAlarma{
  id: number;
  estado_alarma: string;
  fecha_registro: Date;
  observaciones: string;
  resumen: string;
  id_tipo_alarma: ITipoAlarma;
  id_teleoperador: IUsers;
  id_paciente_ucr: IPaciente;
  id_terminal: ITerminal;



}
