package enma.proven.ffinder.entities;

/**
 *
 * @author Alumne
 */
public class AObjective {
    private int id;
    private String message;

    public AObjective() {
    }

    public AObjective(int id) {
        this.id = id;
    }

    public AObjective(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        return hash;
    }

     @Override
    public boolean equals(Object obj) {
	boolean b = false;
            if (obj == null) b = false;
            else {
		if (obj instanceof AObjective) {
                    if (this == obj) b = true;
                    else {
			AObjective other = (AObjective) obj;
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
        sb.append("Objective{");
        sb.append("[ID: ");
        sb.append(id);
        sb.append("],[Message: ");
        sb.append(message);
        sb.append("]}");
        return sb.toString();
    }
    
    
}
