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
event( rilevazioneDistanza, rilevazioneDistanza(X) ).
event( rilDistContainer, rilDistContainer(X) ).
event( rilDistAnomalia, rilDistAnomalia(X) ).
event( rilDistVuoto, rilDistFineAnomalia(X) ).
event( rilevazioneAnomalia, rilevazioneAnomalia(X) ).
event( risoluzioneAnomalia, risoluzioneAnomalia(X) ).
event( containerRilevato, containerRilevato(X) ).
dispatch( continue, continue(X) ).
dispatch( statoStiva, statoStiva(STATO) ).
%====================================================================================
context(ctx_productservice, "productservice",  "TCP", "8111").
context(ctx_cargorobot, "cargorobot",  "TCP", "9092").
context(ctx_basicrobot, "basicrobot",  "TCP", "8020").
context(ctx_client, "client",  "TCP", "10000").
context(ctx_cargoservice, "client",  "TCP", "9091").
context(ctx_ioport, "client",  "TCP", "9093").
context(ctx_webgui, "client",  "TCP", "9094").
 qactor( productservice, ctx_productservice, "external").
  qactor( basicrobot, ctx_basicrobot, "external").
  qactor( cargorobot, ctx_cargorobot, "it.unibo.cargorobot.Cargorobot").
 static(cargorobot).
  qactor( cargoservice, ctx_cargoservice, "it.unibo.cargoservice.Cargoservice").
 static(cargoservice).
  qactor( lettore_sonar_fisico, ctx_ioport, "it.unibo.lettore_sonar_fisico.Lettore_sonar_fisico").
 static(lettore_sonar_fisico).
  qactor( sonar, ctx_ioport, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( ioport, ctx_ioport, "it.unibo.ioport.Ioport").
 static(ioport).
  qactor( led, ctx_ioport, "it.unibo.led.Led").
 static(led).
  qactor( webgui, ctx_webgui, "it.unibo.webgui.Webgui").
 static(webgui).
  qactor( client_esterno, ctx_client, "it.unibo.client_esterno.Client_esterno").
 static(client_esterno).
