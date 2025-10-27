%====================================================================================
% ioport description   
%====================================================================================
event( rilevazioneDistanza, rilevazioneDistanza(X) ).
event( rilDistContainer, rilDistContainer(X) ).
event( rilDistAnomalia, rilDistAnomalia(X) ).
event( rilDistVuoto, rilDistFineAnomalia(X) ).
event( rilevazioneAnomalia, rilevazioneAnomalia(X) ).
event( risoluzioneAnomalia, risoluzioneAnomalia(X) ).
event( containerRilevato, containerRilevato(X) ).
dispatch( continue, continue(X) ).
%====================================================================================
context(ctx_cargoservice, "cargoservice",  "TCP", "9091").
context(ctx_cargorobot, "cargorobot",  "TCP", "9092").
context(ctx_ioport, "localhost",  "TCP", "9093").
 qactor( cargoservice, ctx_cargoservice, "external").
  qactor( corgorobot, ctx_cargorobot, "external").
  qactor( lettore_sonar_fisico, ctx_ioport, "it.unibo.lettore_sonar_fisico.Lettore_sonar_fisico").
 static(lettore_sonar_fisico).
 qactor( ioport, ctx_ioport, "it.unibo.ioport.Ioport").
 static(ioport).
  qactor( sonar, ctx_ioport, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( lettore_sonar_fisico, ctx_ioport, "it.unibo.lettore_sonar_fisico.Lettore_sonar_fisico").
 static(lettore_sonar_fisico).
  qactor( led, ctx_ioport, "it.unibo.led.Led").
 static(led).
