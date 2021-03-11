public class NBody {


	public static Double readRadius(String path) {

		In in = new In(path);
		int numberOfPlanet = in.readInt();
		double rediusOfUniverse = in.readDouble();
		return rediusOfUniverse;

	}

	public static Planet[] readPlanets(String path) {

		In in = new In(path);
		int numberOfPlanet = in.readInt();
		double rediusOfUniverse = in.readDouble();
		Planet[] result = new Planet[numberOfPlanet];
		int i = 0;
		while (i < 5) {
			double xp = in.readDouble();
			double yp = in.readDouble();
			double xv = in.readDouble();
			double yv = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			Planet planet = new Planet(xp, yp, xv, yv, m, img);
			result[i] = planet;
			++i;
		}

		return result;
	}


	public static void drawBackground(Double x , Double y) {
		String  imageToDraw = "./images/starfield.jpg";
		StdDraw.setScale(x, y);
		StdDraw.picture(0, 0, imageToDraw);
		StdDraw.show();
	}

	public static void main(String[] args) {

		if (args.length == 3) {
			Double T = Double.valueOf(args[0]);
			Double dt = Double.valueOf(args[1]);
			String filename = args[2];
			Double radius =readRadius(filename);
			drawBackground(radius,-radius);
			Planet[] planets = readPlanets(filename);
			//System.out.println(planets.length);
			//System.out.println(redius);
			int i = 0;
			while(i < planets.length){
			//System.out.println(planets[i].xxPos);
			planets[i].draw();
					i = i +1;
				}
			StdOut.printf("%d\n",planets.length);
			StdOut.printf("%.2e\n", radius);
			for(int j = 0; j < planets.length; j++){
				StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
						planets[j].xxPos, planets[j].yyPos, planets[j].xxVel,
						planets[j].yyVel, planets[j].mass, planets[j].imgFileName);
			}
		//StdDraw.clear();
		//System.out.println("start!!!");
		StdDraw.enableDoubleBuffering(); //enableDoubleBuffering
		Double time = 0.0;
		while (time < T){
			Double[] xForces = new Double[planets.length];
			Double[] yForces = new Double[planets.length];
			for(int x =0 ; x < planets.length; x++){
				xForces[x] = 0.0;
				yForces[x] = 0.0;
			}
			
			for(int ii = 0 ;  ii< planets.length; ii++){				
					xForces[ii] += planets[ii].calcNetForceExertedByX(planets);
					yForces[ii] += planets[ii].calcNetForceExertedByY(planets);				
			}
			StdDraw.clear();
			drawBackground(radius,-radius);
			
			for(int j=0; j < planets.length;j++){
				planets[j].update(dt,xForces[j],yForces[j]);
				planets[j].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			time +=  dt;
		}

	}	

	}


}