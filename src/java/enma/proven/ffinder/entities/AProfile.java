package enma.proven.ffinder.entities;

/**
 *
 * @author Alumne
 */
public class AProfile {
    private int id;
    private String profileType;

    public AProfile() {
    }

    public AProfile(int id) {
        this.id = id;
    }

    public AProfile(int id, String profileType) {
        this.id = id;
        this.profileType = profileType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
	boolean b = false;
            if (obj == null) b = false;
            else {
		if (obj instanceof AProfile) {
                    if (this == obj) b = true;
                    else {
			AProfile other = (AProfile) obj;
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
        return "AProfile{" + "id=" + id + ", profileType=" + profileType + '}';
    }

    
    
}
