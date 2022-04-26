import {IDispositivosAuxiliaresTerminal} from "../interfaces/i-dispositivos-auxiliares-terminal";
import {ITerminal} from "../interfaces/i-terminal";
import {ITipoAlarma} from "../interfaces/i-tipo-alarma";

export class DispositivosAuxiliaresTerminal implements IDispositivosAuxiliaresTerminal{
  id: number;
  id_terminal: ITerminal;
  id_tipo_alarma: ITipoAlarma;
}
