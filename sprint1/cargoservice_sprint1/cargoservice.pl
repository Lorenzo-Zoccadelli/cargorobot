%====================================================================================
% cargoservice description   
%====================================================================================
request( richiestaCarico, richiestaCaricoCarico(PID) ).
reply( richiestaCaricoAccettata, richiestaCaricoAccettata(ESITO) ).  %%for richiestaCarico
reply( richiestaCaricoRifiutata, richiestaCaricoRifiutata(ESITO) ).  %%for richiestaCarico
event( rilevazioneAnomalia, rilevazioneAnomalia(X) ).
event( risoluzioneAnomalia, risoluzioneAnomalia(X) ).
%====================================================================================
context(ctx_productservice, "productservice",  "TCP", "8000").
context(ctx_cargoservice, "localhost",  "TCP", "8081").
 qactor( productservice, ctx_productservice, "external").
  qactor( cargoservice, ctx_cargoservice, "it.unibo.cargoservice.Cargoservice").
 static(cargoservice).
