%====================================================================================
% cargorobot description   
%====================================================================================
request( richiestaCaricamentoSlot, richiestaCaricamentoSlot(SlotJson) ).
reply( slotCaricato, slotCaricato(X) ).  %%for richiestaCaricamentoSlot
reply( caricamentoFallito, caricamentoFallito(X) ).  %%for richiestaCaricamentoSlot
event( rilevazioneAnomalia, rilevazioneAnomalia(X) ).
event( risoluzioneAnomalia, risoluzioneAnomalia(X) ).
request( engage, engage(CALLER,STEPTIME) ).
reply( engagedone, engagedone(ARG) ).  %%for engage
reply( engagerefused, engagerefused(ARG) ).  %%for engage
dispatch( disengage, disengage(ARG) ).
dispatch( setdirection, dir(D) ).
dispatch( setrobotstate, setpos(X,Y,Dir) ).
dispatch( setplanbuildelay, setplanbuildelay(X) ).
request( doplan, doplan(PATH,STEPTIME) ).
reply( doplandone, doplandone(ARG) ).  %%for doplan
reply( doplanfailed, doplanfailed(ARG) ).  %%for doplan
request( step, step(TIME) ).
reply( stepdone, stepdone(V) ).  %%for step
reply( stepfailed, stepfailed(DURATION,CAUSE) ).  %%for step
request( moverobot, moverobot(TARGETX,TARGETY,STEPTIME) ).
reply( moverobotdone, moverobotok(ARG) ).  %%for moverobot
reply( moverobotfailed, moverobotfailed(PLANDONE,PLANTODO) ).  %%for moverobot
request( tuneAtHome, tuneAtHome(X) ).
reply( tuneDone, tuneDone(X) ).  %%for tuneAtHome
event( alarm, alarm(X) ).
%====================================================================================
context(ctx_cargorobot, "localhost",  "TCP", "9092").
context(ctx_basicrobot, "basicrobot",  "TCP", "8020").
 qactor( basicrobot, ctx_basicrobot, "external").
  qactor( cargorobot, ctx_cargorobot, "it.unibo.cargorobot.Cargorobot").
 static(cargorobot).
