package api;

import com.dropbox.client2.ProgressListener;

public class CProgressListener extends ProgressListener{
	
	int lastpoint = 0;
	Boolean[] tenpar = new Boolean[10];
	
	@Override
	public void onProgress(long arg0, long arg1) {
		// TODO Auto-generated method stub
		int progress = (int)(((float)arg0 / (float)arg1)*100);
		int point = (int)(((float)arg0 / (float)arg1)*40);
		int ten = (int)Math.floor((float)progress/(float)10)*10;
		for (int i = 0; i < point-lastpoint; i++) {
			System.out.print(".");
			if(ten < progress && ten>0 && tenpar[(ten/10)-1] == null){
				System.out.print(ten);
				tenpar[(ten/10)-1] = true;
			}
		}
		lastpoint = point;
	}
	
	public long progressInterval(){
		return (long)1000;
	}

}
