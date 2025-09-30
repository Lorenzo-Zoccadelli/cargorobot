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
with Diagram('cargoserviceArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_productservice', graph_attr=nodeattr):
          productservice=Custom('productservice(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_cargoservice', graph_attr=nodeattr):
          cargoservice=Custom('cargoservice','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_cargorobot', graph_attr=nodeattr):
          cargorobot=Custom('cargorobot(ext)','./qakicons/externalQActor.png')
     sys >> Edge( label='risoluzioneAnomalia', **evattr, decorate='true', fontcolor='darkgreen') >> cargoservice
     cargoservice >> Edge(color='magenta', style='solid', decorate='true', label='<getProduct<font color="darkgreen"> getProductAnswer</font> &nbsp; >',  fontcolor='magenta') >> productservice
     cargoservice >> Edge(color='magenta', style='solid', decorate='true', label='<richiestaCaricamentoSlot<font color="darkgreen"> slotCaricato caricamentoFallito</font> &nbsp; >',  fontcolor='magenta') >> cargorobot
diag
