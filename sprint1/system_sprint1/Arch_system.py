### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('systemArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_productservice', graph_attr=nodeattr):
          productservice=Custom('productservice(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_cargorobot', graph_attr=nodeattr):
          cargorobot=Custom('cargorobot','./qakicons/symActorWithobjSmall.png')
          cargoservice=Custom('cargoservice','./qakicons/symActorWithobjSmall.png')
          sonarmock=Custom('sonarmock','./qakicons/symActorWithobjSmall.png')
          mockgui=Custom('mockgui','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_basicrobot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_client', graph_attr=nodeattr):
          client_esterno=Custom('client_esterno','./qakicons/symActorWithobjSmall.png')
     cargorobot >> Edge( label='alarm', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='risoluzioneAnomalia', **evattr, decorate='true', fontcolor='darkgreen') >> cargorobot
     sys >> Edge( label='containerRilevato', **evattr, decorate='true', fontcolor='darkgreen') >> cargoservice
     sys >> Edge( label='risoluzioneAnomalia', **evattr, decorate='true', fontcolor='darkgreen') >> cargoservice
     sonarmock >> Edge( label='containerRilevato', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonarmock >> Edge( label='rilevazioneAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonarmock >> Edge( label='risoluzioneAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     cargorobot >> Edge(color='magenta', style='solid', decorate='true', label='<engage<font color="darkgreen"> engagedone engagerefused</font> &nbsp; moverobot<font color="darkgreen"> moverobotdone moverobotfailed</font> &nbsp; tuneAtHome<font color="darkgreen"> tuneDone</font> &nbsp; >',  fontcolor='magenta') >> basicrobot
     client_esterno >> Edge(color='magenta', style='solid', decorate='true', label='<createProduct<font color="darkgreen"> createdProduct</font> &nbsp; >',  fontcolor='magenta') >> productservice
     cargoservice >> Edge(color='magenta', style='solid', decorate='true', label='<getProduct<font color="darkgreen"> getProductAnswer</font> &nbsp; >',  fontcolor='magenta') >> productservice
     client_esterno >> Edge(color='magenta', style='solid', decorate='true', label='<richiestaCaricamentoSlot<font color="darkgreen"> slotCaricato caricamentoFallito slotCaricato caricamentoFallito</font> &nbsp; >',  fontcolor='magenta') >> cargoservice
     cargoservice >> Edge(color='magenta', style='solid', decorate='true', label='<richiestaCaricamentoSlot<font color="darkgreen"> slotCaricato caricamentoFallito slotCaricato caricamentoFallito</font> &nbsp; >',  fontcolor='magenta') >> cargorobot
     cargorobot >> Edge(color='blue', style='solid',  decorate='true', label='<setplanbuildelay &nbsp; >',  fontcolor='blue') >> basicrobot
diag
