%====================================================================================
% ioport description   
%====================================================================================
dispatch( accesioneLed, accesioneLed(X) ).
dispatch( spegnimentoLed, spegnimentoLed(X) ).
event( rilDistContainer, rilDistContainer(X) ).
event( rilDistAnomalia, rilDistAnomalia(X) ).
event( rilDistFineAnomalia, rilDistFineAnomalia(X) ).
event( rilevazioneAnomalia, rilevazioneAnomalia(X) ).
event( risoluzioneAnomalia, risoluzioneAnomalia(X) ).
event( containerRilevato, containerRilevato(X) ).
%====================================================================================
context(ctx_ioport, "localhost",  "TCP", "9093").
 qactor( ioport, ctx_ioport, "it.unibo.ioport.Ioport").
 static(ioport).
  qactor( sonar, ctx_ioport, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( led, ctx_ioport, "it.unibo.led.Led").
 static(led).
