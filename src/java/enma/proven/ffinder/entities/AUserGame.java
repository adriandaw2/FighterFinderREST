package enma.proven.ffinder.entities;

/**
 *
 * @author Alumne
 */
public class AUserGame {
    private int id;
    private int idUser;
    private int idGame;

    public AUserGame() {
    }

    public AUserGame(int id) {
        this.id = id;
    }

    public AUserGame(int id, int idUser, int idGame) {
        this.id = id;
        this.idUser = idUser;
        this.idGame = idGame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
        hash = 59 * hash + this.id;
        return hash;
    }

@Override
    public boolean equals(Object obj) {
	boolean b = false;
            if (obj == null) b = false;
            else {
		if (obj instanceof AUserGame) {
                    if (this == obj) b = true;
                    else {
			AUserGame other = (AUserGame) obj;
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
        return "AUserGame{" + "id=" + id + ", idUser=" + idUser + ", idGame=" + idGame + '}';
    }

    
    
}
