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
with Diagram('cargorobotArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_productservice', graph_attr=nodeattr):
          productservice=Custom('productservice','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_cargoservice', graph_attr=nodeattr):
          cargoservice=Custom('cargoservice','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_cargorobot', graph_attr=nodeattr):
          cargorobot=Custom('cargorobot','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_ioport', graph_attr=nodeattr):
          ioport=Custom('ioport','./qakicons/symActorWithobjSmall.png')
          sonar=Custom('sonar','./qakicons/symActorWithobjSmall.png')
          led=Custom('led','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_webgui', graph_attr=nodeattr):
          webgui=Custom('webgui','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_client_esterno', graph_attr=nodeattr):
          client_esterno=Custom('client_esterno','./qakicons/symActorWithobjSmall.png')
     ioport >> Edge( label='rilevazioneAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     ioport >> Edge( label='risoluzioneAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     client_esterno >> Edge(color='magenta', style='solid', decorate='true', label='<registrazioneProdotto<font color="darkgreen"> esitoRegistrazioneProdotto</font> &nbsp; >',  fontcolor='magenta') >> productservice
     client_esterno >> Edge(color='magenta', style='solid', decorate='true', label='<richiestaCarico<font color="darkgreen"> richiestaCaricoAccettata richiestaCaricoRifiutata</font> &nbsp; >',  fontcolor='magenta') >> cargoservice
diag
