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
     with Cluster('ctx_sonar', graph_attr=nodeattr):
          sonar=Custom('sonar','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_webgui', graph_attr=nodeattr):
          webgui=Custom('webgui','./qakicons/symActorWithobjSmall.png')
     sys >> Edge( label='risoluzioneAnomalia', **evattr, decorate='true', fontcolor='darkgreen') >> cargoservice
     sys >> Edge( label='risoluzioneAnomalia', **evattr, decorate='true', fontcolor='darkgreen') >> cargorobot
     sonar >> Edge( label='rilevazioneAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonar >> Edge( label='risoluzioneAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> sys
diag
