%====================================================================================
% cargoservice description   
%====================================================================================
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
%====================================================================================
context(ctx_productservice, "productservice",  "TCP", "8111").
context(ctx_cargoservice, "localhost",  "TCP", "9091").
context(ctx_cargorobot, "cargorobot",  "TCP", "9092").
 qactor( productservice, ctx_productservice, "external").
  qactor( cargorobot, ctx_cargorobot, "external").
  qactor( cargoservice, ctx_cargoservice, "it.unibo.cargoservice.Cargoservice").
 static(cargoservice).
