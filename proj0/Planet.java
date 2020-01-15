public class Planet{
	/** All variables and methods decleared public; 
	    All methods should be non-static */
	public Double xxPos;
	public Double yyPos;
	public Double xxVel;
	public Double yyVel;
	public Double mass;
	public String imgFileName;
	final static Double G = 6.67*1.0e-11;
	/** two constructors for planet 
		
	 */ 
	public Planet(Double xP, Double yP, Double xV, Double yV, Double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass  = m;
		imgFileName = img;

	}

	public Planet(Planet p){
			this(p.getXp(),p.getYp(),p.getXv(),p.getYv(),p.getM(),p.getImg());
	}

	public Double getXp(){
		return xxPos;
	}

	public Double getYp(){
		return yyPos;
	}

	public Double getXv(){
		return xxVel;
	}

	public Double getYv(){
		return yyVel;
	}

	public Double getM(){
		return mass;
	}

	public String getImg(){
		return imgFileName;
	}
	/*return a double equal to the
	 distance between the supplied planet;
	  the planet that is doing the calculation**/
	public Double calcDistance(Planet r){
		return  Math.pow(Math.pow((r.xxPos-xxPos),2)+Math.pow((r.yyPos-yyPos),2),0.5);
	}
	/** takes in a planet, 
	and returns a double describing the force exerted on this planet by the given planet. */
	public Double calcForceExertedBy(Planet r){
		return G*mass*r.mass/Math.pow(calcDistance(r),2); 
	}
	/**take in an array of Planets and calculate the net X and net Y 
	force exerted by all planets  */
	public Double calcNetForceExertedByX(Planet[] all){
		Double netX = 0.0;
		for(int i=0;i<all.length; ++i){
			if (equals(all[i])){
				continue;
			}
			else{
				netX += calcForceExertedBy(all[i])*(all[i].xxPos-xxPos)/calcDistance(all[i]);
			}
		}
		return netX;
	}

	public Double calcNetForceExertedByY(Planet[] all){
		Double netY = 0.0;
		for(Planet each : all){
			if (this.equals(each)){
				continue;
			}
			else{
				netY += calcForceExertedBy(each)*(each.yyPos-yyPos)/calcDistance(each);
			}
		}
		return netY;
	}
	/**  */

	public void update(Double dt, Double fx, Double fy){
		// Step 1 : calculate the acceleration
		Double ax = fx/mass;
		Double ay = fy/mass;
		//Step 2 : Calculate the new velocity
		this.xxVel = xxVel + ax * dt;
		this.yyVel = yyVel + ay * dt;
		// Step 3 : Calculate the new position
		this.xxPos = xxPos + dt * xxVel;
		this.yyPos = yyPos + dt * yyVel;
	}

	public void draw(){
		String imgPath = "./images/"+imgFileName;
		//StdDraw.setScale(-100,100);
		StdDraw.picture(xxPos,yyPos,imgPath);
		StdDraw.show();

	}

	


}
