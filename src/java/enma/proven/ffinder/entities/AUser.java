package enma.proven.ffinder.entities;

/**
 *
 * @author Alumne
 */
public class AUser {
    private int id;
    private String nick;
    private String email;
    private String password;
    private String ubication;
    private int skill;
    private int idProfile;
    private int idObjective;

    public AUser() {
    }

    public AUser(int id) {
        this.id = id;
    }

    public AUser(int id, String nick, String email) {
        this.id = id;
        this.nick = nick;
        this.email = email;
    }
    
    public AUser(int id, String nick, String password, int idObjective) {
        this.id = id;
        this.nick = nick;
        this.password = password;
        this.idObjective = idObjective;
    }
    
    public AUser(String nick, String email, String password) {
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.skill = 3;
        this.idProfile = 2;
        this.idObjective = 1;
    }
    
    public AUser(int id, String nick, String email, String password, int skill, int idProfile, int idObjective) {
        this.id = id;
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.skill = skill;
        this.idProfile = idProfile;
        this.idObjective = idObjective;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }
    
    public int getIdObjective() {
        return idObjective;
    }

    public void setIdObjective(int idObjective) {
        this.idObjective = idObjective;
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
		if (obj instanceof AUser) {
                    if (this == obj) b = true;
                    else {
			AUser other = (AUser) obj;
			b = this.id != other.id;
                    }
		}
		else 
		b = false;
		}
		return b;		
	}


    /*@Override
    public String toString() {
        return "AUser{" + "id=" + id + ", nick=" + nick + ", email=" + email + ", password=" + password + ", idProfile=" + idProfile + ", idObjective=" + idObjective + '}';
    }*/

    
    @Override
    public String toString (){
	
        StringBuilder sb = new StringBuilder();

        sb.append("\n[USER]{" );
        sb.append("\n[id]:");
        sb.append(id);
        sb.append(", [nick]:");
        sb.append(nick);
        sb.append(", [password]:");
        sb.append(password);
        sb.append(", [email]:");
        sb.append(email);
        sb.append(", [skill]:");
        sb.append(skill);
        sb.append(", [idObjective]:");
        sb.append(idObjective);
        sb.append(", [idProfile]:");
        sb.append(idProfile);
        sb.append("}");
        return sb.toString();		
    }
    
    
}
