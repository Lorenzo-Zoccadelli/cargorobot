%====================================================================================
% cargorobot description   
%====================================================================================
request( richiestaCarico, richiestaCaricoCarico(PID) ).
reply( richiestaCaricoAccettata, richiestaCaricoAccettata(ESITO) ).  %%for richiestaCarico
reply( richiestaCaricoRifiutata, richiestaCaricoRifiutata(ESITO) ).  %%for richiestaCarico
request( registrazioneProdotto, registrazioneProdotto(NOME,PESO) ).
reply( esitoRegistrazioneProdotto, esitoRegistrazioneProdotto(PID) ).  %%for registrazioneProdotto
event( rilevazioneAnomalia, rilevazioneAnomalia(X) ).
event( risoluzioneAnomalia, risoluzioneAnomalia(X) ).
%====================================================================================
context(ctx_productservice, "localhost",  "TCP", "8080").
context(ctx_cargoservice, "localhost",  "TCP", "8081").
context(ctx_cargorobot, "localhost",  "TCP", "8082").
context(ctx_ioport, "localhost",  "TCP", "8083").
context(ctx_webgui, "localhost",  "TCP", "8084").
 qactor( productservice, ctx_productservice, "it.unibo.productservice.Productservice").
 static(productservice).
  qactor( cargoservice, ctx_cargoservice, "it.unibo.cargoservice.Cargoservice").
 static(cargoservice).
  qactor( cargorobot, ctx_cargorobot, "it.unibo.cargorobot.Cargorobot").
 static(cargorobot).
  qactor( ioport, ctx_ioport, "it.unibo.ioport.Ioport").
 static(ioport).
  qactor( webgui, ctx_webgui, "it.unibo.webgui.Webgui").
 static(webgui).

