%====================================================================================
% system description   
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
request( richiestaCarico, richiestaCarico(PID) ).
reply( richiestaCaricoAccettata, richiestaCaricoAccettata(ESITO) ).  %%for richiestaCarico
reply( richiestaCaricoRifiutata, richiestaCaricoRifiutata(ESITO) ).  %%for richiestaCarico
event( rilevazioneAnomalia, rilevazioneAnomalia(X) ).
event( risoluzioneAnomalia, risoluzioneAnomalia(X) ).
event( containerRilevato, containerRilevato(X) ).
dispatch( endLocal, endLocal(X) ).
request( createProduct, product(String) ).
reply( createdProduct, productid(ID) ).  %%for createProduct
request( getProduct, product(ID) ).
reply( getProductAnswer, product(JSonString) ).  %%for getProduct
request( richiestaCaricamentoSlot, richiestaCaricamentoSlot(TARGETX,TARGETY) ).
reply( slotCaricato, slotCaricato(X) ).  %%for richiestaCaricamentoSlot
reply( caricamentoFallito, caricamentoFallito(X) ).  %%for richiestaCaricamentoSlot
request( resetStiva, resetStiva(X) ).
reply( esitoResetStiva, esitoResetStiva(X) ).  %%for resetStiva
%====================================================================================
context(ctx_productservice, "productservice",  "TCP", "8111").
context(ctx_cargorobot, "cargorobot",  "TCP", "9092").
context(ctx_basicrobot, "basicrobot",  "TCP", "8020").
context(ctx_client, "client",  "TCP", "10000").
context(ctx_cargoservice, "client",  "TCP", "9091").
 qactor( productservice, ctx_productservice, "external").
  qactor( basicrobot, ctx_basicrobot, "external").
  qactor( cargorobot, ctx_cargorobot, "it.unibo.cargorobot.Cargorobot").
 static(cargorobot).
  qactor( cargoservice, ctx_cargorobot, "it.unibo.cargoservice.Cargoservice").
 static(cargoservice).
  qactor( sonarmock, ctx_cargorobot, "it.unibo.sonarmock.Sonarmock").
 static(sonarmock).
  qactor( mockgui, ctx_cargorobot, "it.unibo.mockgui.Mockgui").
 static(mockgui).
  qactor( client_esterno, ctx_client, "it.unibo.client_esterno.Client_esterno").
 static(client_esterno).
