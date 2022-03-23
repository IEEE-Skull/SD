import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;
//Regles de Reecritufre:
//R1: N(1) --- N(x) ----> F(0) --- N(x-1) x > 1
//R2: N(1) --- N(1) ----> E(0) --- F(0)
public class SpanningTree extends LC0_Algorithm{
    String[] etatsVoisins;
    @Override
    public Object clone() {
        return new SpanningTree();
    }

    @Override
    public String getDescription() {
        return "TP2 Spanning tree algorithm using LCO. Leader Election\n"+"Rule : A---N ---> A---A";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("counter",getArity());
        setLocalProperty("label",vertex.getLabel());
        putProperty("Affichage",getLocalProperty("counter").toString(), SimulationConstants.PropertyStatus.DISPLAYED);
        etatsVoisins = new String[getArity()];
        for(int i=0;i<getArity();i++){
            etatsVoisins[i] = "N";
        }
    }

    @Override
    protected void onStarCenter() {
        etatsVoisins[neighborDoor] = getNeighborProperty("label").toString();
        if(getLocalProperty("label").equals("N") && Integer.parseInt(getLocalProperty("counter").toString()) == 1 && getNeighborProperty("label").equals("N") && Integer.parseInt(getNeighborProperty("counter").toString()) > 1){
            setLocalProperty("label","F");
            setLocalProperty("counter",0);
            setNeighborProperty("counter", Integer.parseInt(getNeighborProperty("counter").toString()) - 1);
            putProperty("Affichage",getLocalProperty("counter").toString(), SimulationConstants.PropertyStatus.DISPLAYED);
        }else if(getLocalProperty("label").equals("N") && getNeighborProperty("label").equals("N") && Integer.parseInt(getLocalProperty("counter").toString()) == 1 && Integer.parseInt(getNeighborProperty("counter").toString()) == 1){
            setLocalProperty("label","E");
            setNeighborProperty("label","F");
            setLocalProperty("counter",0);
            setNeighborProperty("counter",0);
        }
        putProperty("Affichage",getLocalProperty("counter").toString(), SimulationConstants.PropertyStatus.DISPLAYED);
    }
}
