public class Minions {

    private String name;
    private int time;

    public Minions(String name, int time){
        super();
        this.name = name;
        this.time = time;
    }

    public Minions(String minion){
        String aux[];
        aux = minion.split("_");
        setName(aux[0]);
        setTime(Integer.parseInt(aux[1]));

    }

    public String getName(){
        return name;
    }

    public Integer getTime(){
        return time;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTime(Integer time){
        this.time = time;
    }
}
