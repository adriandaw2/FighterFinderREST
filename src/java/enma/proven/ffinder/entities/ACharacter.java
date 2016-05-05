package enma.proven.ffinder.entities;

/**
 *
 * @author Alumne
 */
public class ACharacter {
    private int id;
    private String name;
    private int idGame;

    public ACharacter() {
    }

    public ACharacter(int id) {
        this.id = id;
    }

    public ACharacter(int id, String name, int idGame) {
        this.id = id;
        this.name = name;
        this.idGame = idGame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
	boolean b = false;
            if (obj == null) b = false;
            else {
		if (obj instanceof ACharacter) {
                    if (this == obj) b = true;
                    else {
			ACharacter other = (ACharacter) obj;
			b = this.id != other.id;
                    }
		}
		else 
		b = false;
		}
		return b;		
	}

    @Override
    public String toString() {
        return "ACharacter{" + "id=" + id + ", name=" + name + ", idGame=" + idGame + '}';
    }
    
    
}
