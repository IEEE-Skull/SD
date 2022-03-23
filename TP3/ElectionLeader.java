import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;
//Regles de Reecritufre:
//R1: N(1) --- N(x) ----> F(0) --- N(x) x > 1
//R2: N(1) --- F(0) ----> E(0) --- F(0)
public class ElectionLeader extends LC1_Algorithm {
    String[] etatsVoisins;
    @Override
    public Object clone() {
        return new ElectionLeader();
    }

    @Override
    public String getDescription() {
        return "TP3 Spanning tree algorithm using LC1. Election Etoile Ouverte\n" + "Rule : A---N ---> A---A";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        etatsVoisins = new String[getArity()];
        for (int i = 0; i < getArity(); i++) {
            etatsVoisins[i] = "N";
        }
    }

    @Override
    protected void onStarCenter() {
        if (getLocalProperty("label").equals("N")) {
            int VoisinsCounter = 0;
            for (int i = 0; i < getActiveDoors().size(); i++) {
                if (getNeighborProperty(getActiveDoors().get(i), "label").toString().equals("N")) {
                    VoisinsCounter = VoisinsCounter + 1;
                }
            }
            if (VoisinsCounter == 1) {
                setLocalProperty("label", "F");
            }else if(VoisinsCounter == 0){
                setLocalProperty("label", "E");
            }
        }
    }
}

