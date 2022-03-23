import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;
//Regles de Reecritufre:
//R1: N --- A ----> A ___ A (Choisi un seul voisin A)
//R2: A --- N ---> A ___ A (Tout ces voisins N)
public class ArbreRecouvrant extends LC2_Algorithm {
    String[] etatsVoisins;
    @Override
    public Object clone() {
        return new ArbreRecouvrant();
    }

    @Override
    public String getDescription() {
        return "TP4 Spanning tree algorithm using LC1. Arbre Recourvrant\n"+"Rule : A---N ---> A---A";
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
        if(getLocalProperty("label").equals("A")){
            int fatherDoor=-1;
            for(int i=0;i<getActiveDoors().size();i++){
                if(getNeighborProperty(getActiveDoors().get(i),"label").toString().equals("N")){
                    fatherDoor = getActiveDoors().get(i);
                    break;
                }
            }
            if(fatherDoor != -1){
                setNeighborProperty(fatherDoor,"label","A");
                setDoorState(new MarkedState(true),fatherDoor);
            }else{
                localTermination();
            }
        }
    }
}
