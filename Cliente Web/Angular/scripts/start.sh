#! /bin/bash

cd /teleasistencia/Cliente/Angular
nohup ng serve --host api-rest.teleasistencia.isvjp.es --port 443  --ssl --ssl-key ./certificados/teleasistencia.key --ssl-cert ./certificados/teleasistencia.cert  </dev/null &> ../logs/logs_nohup_$(date +"%F").out  2> ../logs/logs_nohup_error_$(date +"%F").out &
