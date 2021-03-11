public class Planet{
	/** All variables and methods decleared public; 
	    All methods should be non-static */
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private final static double G = 6.67*1.0e-11;
	/** two constructors for planet 
		
	 */ 
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass  = m;
		imgFileName = img;

	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass =  p.mass;
		this.imgFileName =  p.imgFileName;


			//this(p.getXp(),p.getYp(),p.getXv(),p.getYv(),p.getM(),p.getImg());
	}

	
	/*return a double equal to the
	 distance between the supplied planet;
	  the planet that is doing the calculation**/
	public double calcDistance(Planet r){
		// Double xxPos = getXp();
		// Double yyPos = getYp();

		return  Math.pow(Math.pow((r.xxPos-xxPos),2)+Math.pow((r.yyPos-yyPos),2),0.5);
	}
	/** takes in a planet, 
	and returns a double describing the force exerted on this planet by the given planet. */
	public double calcForceExertedBy(Planet r){

		return G*mass*r.mass/Math.pow(calcDistance(r),2); 
	}

	public double calcForceExertedByX(Planet r){

		if((r.xxPos-xxPos)>=0){
			return calcForceExertedBy(r)*(r.xxPos - xxPos)/calcDistance(r);
		}else{
			return -calcForceExertedBy(r)*(-r.xxPos + xxPos)/calcDistance(r);
		}
	}

	public double calcForceExertedByY(Planet r){
		// double yyPos = getYp();
		// double G = getG();
		// double mass = getM();
		if((r.yyPos-yyPos)>=0){
			return G*mass*r.mass/Math.pow(calcDistance(r),2)*(r.yyPos-yyPos)/calcDistance(r);
		}else{
			return -G*mass*r.mass/Math.pow(calcDistance(r),2)*(-r.yyPos+yyPos)/calcDistance(r); 
		}
	}

	/**take in an array of Planets and calculate the net X and net Y 
	force exerted by all planets  */
	public double calcNetForceExertedByX(Planet[] all){
		double netX = 0.0;
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

	public double calcNetForceExertedByY(Planet[] all){
		double netY = 0.0;
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

	public void update(double dt, double fx, double fy){
		// Step 1 : calculate the acceleration
		// double mass = getM();
		double ax = fx/mass;
		double ay = fy/mass;
		//Step 2 : Calculate the new velocity
		
		this.xxVel =  xxVel + ax * dt;
		this.yyVel = yyVel + ay * dt;
		
		// Step 3 : Calculate the new position
		this.xxPos = xxPos + dt * xxVel;
		this.yyPos = yyPos + dt * yyVel;
		// setXp(xxPos);
		// setYp(yyPos);

	}

	public void draw(){
		String imgPath = "./images/"+imgFileName;
		//StdDraw.setScale(-100,100);
		StdDraw.picture(xxPos,yyPos,imgPath);
		StdDraw.show();

	}

	


}
