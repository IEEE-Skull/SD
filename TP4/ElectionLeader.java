import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.EdgeState;
import visidia.simulation.process.edgestate.MarkedState;
//Regles de Reecritufre:
//R1: N(1) --- N(x) ----> F(0) --- N(x) x = 1
//R2: N(1) --- F(0) ----> E(0) --- F(0)
public class ElectionLeader extends LC2_Algorithm {
    String[] etatsVoisins;
    Boolean Transform = true;
    @Override
    public Object clone() {
        return new ElectionLeader();
    }

    @Override
    public String getDescription() {
        return "TP4 Spanning tree algorithm using LC1. Election Etoile Fermee\n" + "Rule : A---N ---> A---A";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("counter", getArity());
        putProperty("Affichage", getLocalProperty("counter").toString(), SimulationConstants.PropertyStatus.DISPLAYED);
        etatsVoisins = new String[getArity()];
        for (int i = 0; i < getArity(); i++) {
            etatsVoisins[i] = "N";
        }
    }

    @Override
    protected void onStarCenter() {
        if (getLocalProperty("label").equals("N")) {
            if (Integer.parseInt(getLocalProperty("counter").toString()) == 0) {
                setLocalProperty("label", "E");
                localTermination();
            }
            int fatherDoor = -1;
            for (int i = 0; i < getActiveDoors().size(); i++) {
                if (getNeighborProperty(getActiveDoors().get(i), "label").toString().equals("N") && Integer.parseInt(getNeighborProperty(getActiveDoors().get(i), "counter").toString()) == 1) {
                    fatherDoor = getActiveDoors().get(i);
                    break;
                }
            }
            if (fatherDoor != -1) {
                setNeighborProperty(fatherDoor, "label", "F");
                setLocalProperty("counter", Integer.parseInt(getLocalProperty("counter").toString()) - 1);
                putProperty("Affichage", getLocalProperty("counter").toString(), SimulationConstants.PropertyStatus.DISPLAYED);
            }
        }else if(getLocalProperty("label").equals("F")){
            for (int i = 0; i < getActiveDoors().size(); i++) {
                if (getNeighborProperty(getActiveDoors().get(i), "label").toString().equals("N")) {
                    Transform = false;
                    break;
                }else{
                    Transform = true;
                }
            }
            if(Transform){
                localTermination();
            }
        }
        putProperty("Affichage", getLocalProperty("counter").toString(), SimulationConstants.PropertyStatus.DISPLAYED);
    }
}
