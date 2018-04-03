package edu.upenn.cis573.graphs;

import java.util.ArrayList;
import java.util.List;

public class ReliablePathFinder extends PathFinder {
	
	private Graph g;
	List<Integer> bfsPath = new ArrayList<Integer>() ;
	List<Integer> dfsPath = new ArrayList<Integer>() ;
	List<Integer> validPath = new ArrayList<Integer>() ;

	
	public ReliablePathFinder(Graph g) {
		super(g);
		this.g = g;
	}

	/*
	 * Implement this method using a Recovery Block and Retry Block as described
	 * in the assignment specification.
	 */
	public List<Integer> findPath(int src, int dest) throws PathNotFoundException {
	
		Thread dfsThread = new Thread () {
			public void run () {
				
				dfsPath = dfs(src, dest) ;
				
				}
		};
		Thread bfsThread = new Thread () {
			public void run () {

				bfsPath = bfs(src, dest) ;
				
			}
		};
		
		dfsThread.start();
		bfsThread.start();
		
		try {
			
			dfsThread.join();
			 
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		
		}
		try {
			
			bfsThread.join();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		
		}
		if(checkPath(src, dest, dfsPath)){
			
			return dfsPath;
			
		} else if (checkPath(src, dest, bfsPath)){
			
			return bfsPath;
			
		}

		//RETRY BLOCK

		if(checkPath(src, dest, dfsPath) && (checkPath(src, dest, bfsPath))) {
			
			dfsPath = dfs(dest, src) ;
			
			if(checkPath(dest, src, dfsPath)) {
				
				for (int i = 0; i < dfsPath.size(); i++) {
					
					validPath.add(dfsPath.get(dfsPath.size() - 1 - i)) ;
				
				}
				
			} else {
				
				throw new PathNotFoundException();
			
			}
		}
		
		return validPath;

	}
	
	/*
	 * Implement this acceptance test as described in the assignment specification.
	 */
	public boolean checkPath(int src, int dest, List<Integer> path) {
		
		if(!(path.get(0) == (src))) {
			
			return false ;
			
		} else if(!(path.get(path.size()-1) == (dest))) {
			
			return false ;
			
		} 

		int current = 0;
		int next = 0 ;
		
		for (int i = 0; i < path.size()-1; i++){ 
			
			current = path.get(i) ;
			next = path.get(i+1) ;
			
			ArrayList<Integer> adjcent = new ArrayList<Integer>();
			
			for(Integer j: g.adj(current)){
				
				adjcent.add(j);
				
			}
			if(!adjcent.contains(next)){
				
				return false;
				
			}
				
		}
		return true;


	}


}
