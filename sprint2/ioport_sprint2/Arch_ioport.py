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
with Diagram('ioportArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_cargoservice', graph_attr=nodeattr):
          cargoservice=Custom('cargoservice(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_cargorobot', graph_attr=nodeattr):
          corgorobot=Custom('corgorobot(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_ioport', graph_attr=nodeattr):
          lettore_sonar_fisico=Custom('lettore_sonar_fisico','./qakicons/symActorWithobjSmall.png')
          sonar=Custom('sonar','./qakicons/symActorWithobjSmall.png')
          ioport=Custom('ioport','./qakicons/symActorWithobjSmall.png')
          led=Custom('led','./qakicons/symActorWithobjSmall.png')
     lettore_sonar_fisico >> Edge( label='rilevazioneDistanza', **eventedgeattr, decorate='true', fontcolor='red') >> sonar
     sonar >> Edge( label='rilDistContainer', **eventedgeattr, decorate='true', fontcolor='red') >> ioport
     sonar >> Edge( label='rilDistAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> ioport
     sonar >> Edge( label='rilDistVuoto', **eventedgeattr, decorate='true', fontcolor='red') >> ioport
     sys >> Edge( label='rilDistContainer', **evattr, decorate='true', fontcolor='darkgreen') >> ioport
     sys >> Edge( label='rilDistAnomalia', **evattr, decorate='true', fontcolor='darkgreen') >> ioport
     ioport >> Edge( label='containerRilevato', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     ioport >> Edge( label='rilevazioneAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     ioport >> Edge( label='risoluzioneAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> sys
diag
