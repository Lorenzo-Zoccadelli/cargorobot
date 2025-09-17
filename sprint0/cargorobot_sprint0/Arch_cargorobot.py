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
     with Cluster('ctx_productervice', graph_attr=nodeattr):
          productservice=Custom('productservice','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_cargoservice', graph_attr=nodeattr):
          cargoservice=Custom('cargoservice','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_cargorobot', graph_attr=nodeattr):
          cargorobot=Custom('cargorobot','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_sonar', graph_attr=nodeattr):
          sonar=Custom('sonar','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctx_webgui', graph_attr=nodeattr):
          webgui=Custom('webgui','./qakicons/symActorWithobjSmall.png')
     cargoservice >> Edge( label='aggiornamentoStiva', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='fineCaricamentoContainer', **evattr, decorate='true', fontcolor='darkgreen') >> cargoservice
     sys >> Edge( label='risoluzioneAnomalia', **evattr, decorate='true', fontcolor='darkgreen') >> cargoservice
     cargorobot >> Edge( label='fineCaricamentoContainer', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='risoluzioneAnomalia', **evattr, decorate='true', fontcolor='darkgreen') >> cargorobot
     sonar >> Edge( label='risoluzioneAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonar >> Edge( label='rilevazioneAnomalia', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='aggiornamentoStiva', **evattr, decorate='true', fontcolor='darkgreen') >> webgui
     cargoservice >> Edge(color='magenta', style='solid', decorate='true', label='<recuperaProdotto<font color="darkgreen"> dettagliProdotto prodottoNonTrovato</font> &nbsp; >',  fontcolor='magenta') >> productservice
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<caricamentoContainer &nbsp; >',  fontcolor='blue') >> cargoservice
     cargoservice >> Edge(color='blue', style='solid',  decorate='true', label='<caricamentoContainer &nbsp; >',  fontcolor='blue') >> cargorobot
diag
