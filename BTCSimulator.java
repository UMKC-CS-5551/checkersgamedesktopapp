import java.util.Scanner;
import java.lang.Math; 

public class BTCSimulator {

	public static void main(String[] args) {
		New n= new New();
		blockMining bm =new blockMining();
		transaction t= new transaction();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the USERNAME:");
		n.UName = s.nextLine();
		n.getNew();
		System.out.println("\r\n\r\nEnter Block Name to get reward");
		bm.blockName= s.nextLine();
		System.out.println("Enter the block number");
		bm.bNum= s.nextInt();
		bm.getblockMining();
		System.out.println("Enter the block name to transfer the BTC");
		t.rNmae= s.nextLine();
		System.out.println("Enter the noof BTC to transfer");
		t.amt= s.nextInt();
	    t.gettransaction();
	}
}

class New{
  String UName;
  long balance;
	void getNew(){
		System.out.println("Username:"+UName);
		System.out.println("Balance:0.00 BTC.");
		System.out.println("The above is based on the longest Blockchain");
		System.out.println("Public key: " + Math.random());
		System.out.println("\r\nWhat you can do now:\r\n"
				+ "\r\n"
				+ "Mine a block to receive your first Bitcoin as a reward\r\n"
				+ "Sign transactions and send Bitcoin to other wallets\r\n"
				+ "Create your own private blockchain and use the simulator with non-public groups or school classes\r\n"
				+ "Create fake transactions under a false name and try to obtain Bitcoin by fraud\r\n"
				+ "Perform a 51% attack to subsequently manipulate the blockchain\r\n"
				+ "Tell other people about it. The more understand how Bitcoin works, the better.");
		
	}
}
 class blockMining extends New{
	 String blockName;
	 int rew;
	 int bNum;
	  void getblockMining() {
		  System.out.println("BlockName"+blockName);
		  System.out.println("Transfer from"+blockName+"---->"+new New().UName);
		  if (bNum==20) {
		  System.out.println("Transferd reward is 20 BTC");
		  System.out.println("Transaction ID is: "+ Math.random());
	  }else {
		  System.out.println("Transferd  reward is 50 BTC");
		  System.out.println("Transaction ID is: "+ Math.random());
	  }
   }
 }
  class transaction extends blockMining {
	  public String rNmae;
	String rName;
	  int amt,b;
	  void gettransaction() {
		  b=rew-amt;
		  System.out.println(amt+"BTC" + "is transfered to "+rName);
		  System.out.println("Transaction Id is:"+ Math.random());
		  System.out.println("The balance in your account is "+ b+"BTC");
	  }
  }


