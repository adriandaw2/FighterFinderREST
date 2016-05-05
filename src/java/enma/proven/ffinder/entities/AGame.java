package enma.proven.ffinder.entities;

/**
 *
 * @author Alumne
 */
public class AGame {
    private int id;
    private String name;

    public AGame() {
    }

    public AGame(int id) {
        this.id = id;
    }

    public AGame(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

   @Override
    public boolean equals(Object obj) {
	boolean b = false;
            if (obj == null) b = false;
            else {
		if (obj instanceof AGame) {
                    if (this == obj) b = true;
                    else {
			AGame other = (AGame) obj;
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
        StringBuilder sb = new StringBuilder();

        sb.append("\n[GAME]{" );
        sb.append("\n[id]:");
        sb.append(id);
        sb.append(", [name]:");
        sb.append(name);
        sb.append(", [password]:");
        return sb.toString();
    }
    
    
}
