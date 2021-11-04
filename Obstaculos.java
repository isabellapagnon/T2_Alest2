public class Obstaculos {

    private String name;
    private int time;

    public Obstaculos(String name, int time){
        super();
        this.name = name;
        this.time = time;
    }

    public Obstaculos(String obstaculo){
        String aux[];
        aux = obstaculo.split("_");
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
