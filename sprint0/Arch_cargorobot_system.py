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
with Diagram('cargorobot_systemArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_robot', graph_attr=nodeattr):
          cargorobot=Custom('cargorobot','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_services', graph_attr=nodeattr):
          product_service=Custom('product_service','./qakicons/symActorWithobjSmall.png')
          cargo_service=Custom('cargo_service','./qakicons/symActorWithobjSmall.png')
     sys >> Edge( label='sonarDetect', **evattr, decorate='true', fontcolor='darkgreen') >> cargo_service
     sys >> Edge( label='sonarError', **evattr, decorate='true', fontcolor='darkgreen') >> cargo_service
     cargo_service >> Edge( label='alarm', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     cargo_service >> Edge( label='alarmEnded', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     cargo_service >> Edge(color='blue', style='solid',  decorate='true', label='<putInSlot &nbsp; >',  fontcolor='blue') >> cargorobot
diag
