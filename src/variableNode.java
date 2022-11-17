import java.util.AbstractMap;
import java.util.HashMap;

public class variableNode {
    private HashMap<String, Integer> variables = new HashMap<String, Integer>();
    private variableNode father;

    public variableNode(String type, int value, variableNode father) {
        this.variables.put(type, value);
        this.father = father;
    }

    public AbstractMap<String, Integer> getVariables(){
        return this.variables;
    }

    public void addVariable(String type, int value){
        this.variables.put(type, value);
    }
}
