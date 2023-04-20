import java.sql.Array;

public class Player {
    //Esta clase sirve para controlar los id's y equipos de cada jugador
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player(int id) {
        this.id = id;
    }


    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    int id;




    String equipo;
}
