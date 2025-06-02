%====================================================================================
% cargorobot_system description   
%====================================================================================
request( requestRegistration, registration(NAME,WEIGHT) ).
request( requestCargo, cargo_request(PID) ).
dispatch( putInSlot, put_in_slot(SLOT) ).
dispatch( positioned, positioned(X) ).
event( alarm, alarm(X) ).
%====================================================================================
context(ctx_robot, "localhost",  "TCP", "8020").
context(ctx_services, "localhost",  "TCP", "8021").
 qactor( product_service, ctx_services, "it.unibo.product_service.Product_service").
 static(product_service).
  qactor( cargo_service, ctx_services, "it.unibo.cargo_service.Cargo_service").
 static(cargo_service).
  qactor( virtualrobot, ctx_robot, "it.unibo.virtualrobot.Virtualrobot").
 static(virtualrobot).
