package enma.proven.ffinder.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alumne
 */
@XmlRootElement
public class AUser {
    private int id;
    private String nick;
    private String email;
    private String password;
    private String ubication;
    private int skill;
    private boolean avaible;
    private boolean showinmap;
    private float glat;
    private float glon;
    private int idProfile;
    private int idObjective;
    private String objectiveMsg;
    
    //empty contructor
    public AUser() {
    }
    
    //only id constructor
    public AUser(int id) {
        this.id = id;
    }
    
    //login constructor
    public AUser(int id, String nick, String email) {
        this.id = id;
        this.nick = nick;
        this.email = email;
    }
    
    //mod user constructor
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
    
    //search user constructor
    public AUser(int id, String nick, String email, int skill, boolean avaible, boolean showinmap, float glat, float glon, int idObjective) {
        this.id = id;
        this.nick = nick;
        this.email = email;
        this.skill = skill;
        this.avaible = avaible;
        this.showinmap = showinmap;
        this.glat = glat;
        this.glon = glon;
        this.idObjective = idObjective;
    }
    
    //full constructor
    public AUser(int id, String nick, String email, String password, int skill, boolean avaible, boolean showinmap, float glat, float glon, int idProfile, int idObjective) {
        this.id = id;
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.skill = skill;
        this.avaible = avaible;
        this.showinmap = showinmap;
        this.glat = glat;
        this.glon = glon;
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

    public boolean isAvaible() {
        return avaible;
    }

    public void setAvaible(boolean avaible) {
        this.avaible = avaible;
    }

    public boolean isShowinmap() {
        return showinmap;
    }

    public void setShowinmap(boolean showinmap) {
        this.showinmap = showinmap;
    }

    public float getGlat() {
        return glat;
    }

    public void setGlat(float glat) {
        this.glat = glat;
    }

    public float getGlon() {
        return glon;
    }

    public void setGlon(float glon) {
        this.glon = glon;
    }
    
    
    
    public int getIdObjective() {
        return idObjective;
    }

    public void setIdObjective(int idObjective) {
        this.idObjective = idObjective;
    }

    public String getObjectiveMsg() {
        return objectiveMsg;
    }

    public void setObjectiveMsg(String objectiveMsg) {
        this.objectiveMsg = objectiveMsg;
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
