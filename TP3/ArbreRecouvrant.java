import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;
//Regles de Reecritufre:
//R1: N --- A ----> A ___ A (Choisi un seul voisin A)
public class ArbreRecouvrant extends LC1_Algorithm {
    String[] etatsVoisins;
    int fatherDoor=-1;
    @Override
    public Object clone() {
        return new ArbreRecouvrant();
    }

    @Override
    public String getDescription() {
        return "TP3 Spanning tree algorithm using LC1. Arbre Recourvrant\n"+"Rule : A---N ---> A---A";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label",vertex.getLabel());
        etatsVoisins = new String[getArity()];
        for(int i=0;i<getArity();i++){
            etatsVoisins[i] = "N";
        }
    }

    @Override
    protected void onStarCenter() {
        if(getLocalProperty("label").equals("N")){
            for(int i=0;i<getActiveDoors().size();i++){
                if(getNeighborProperty(getActiveDoors().get(i),"label").toString().equals("A")){
                    fatherDoor = getActiveDoors().get(i);
                    break;
                }
            }
            setLocalProperty("label","A");
            setDoorState(new MarkedState(true),fatherDoor);
        }
    }
}
