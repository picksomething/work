package cn.picksomething.showhand;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ShowHand {
	//define the max num of players which this game support
	private final int PLAY_NUM = 5;
	//define all the type and numerical value
	private String[] types = {"方块", "梅花", "红心", "黑桃"};
	private String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", 
			"10", "J", "Q", "K", "A", };
	//cards are the rest of desk
	private List<String> cards = new LinkedList<String>();
	//define all the players
	private String[] players = new String[PLAY_NUM];
	//num of cards on each of the players' hand
	private List<String>[] playersCards = new List[PLAY_NUM];
	
	/**
	 * initialize the cards, put 52 cards on desk 
	 * and use shuffle to random these cards
	 * */
	public void initCards(){
		for(int i = 0; i < types.length; i++){
			for(int j = 0; j< values.length; j++){
				cards.add(types[i] + values[j]);
			}
		}
		//random
		Collections.shuffle(cards);
	}
	
	/**
	 * initialize the players
	 * and assign name for players
	 * */
	public void initPlaryers(String ... names){
		if(names.length > PLAY_NUM || names.length < 2){
			//verify the num of players more reasonable
			System.out.println("玩家数量不对！");
			return ;
		}
		else{
			//initialize the players' names
			for(int i = 0; i < names.length; i++){
				players[i] = names[i];
			}
		}
	}
	
	/**
	 * initialize num of cards on players' hands
	 * when start games every of player's cards
	 * is empty,program use a 0 length of LinkedList
	 * to show
	 * */
	public void initPlayerCards(){
		for(int i = 0 ; i < players.length; i++){
			if(players[i] != null && !players[i].equals("")){
				playersCards[i] = new LinkedList<String>();
			}
		}
	}
	
	/**
	 * output all cards just for test*/
	public void showAllCards(){
		for(String card : cards){
			System.out.println(card);
		}
	}
	
	/**
	 * assign cards to player
	 * @param first represent the first player to assign
	 * */
	public void deliverCard(String first){
		//call search method to find the index of first in the array
		int firstPos = search( first);
		if(firstPos == -1){
			System.out.println("你指定的玩家不存在！");
		}
		else{
			for(int i = firstPos; i < PLAY_NUM; i++){
				if(players[i] != null){
					playersCards[i].add(cards.get(0));
					cards.remove(0);
				}
			}
			for(int i = 0; i < firstPos; i ++){
				if(players[i] != null){
					playersCards[i].add(cards.get(0));
					cards.remove(0);
				}
			}
		}
	}
	
	/**
	 * output every player's cards*/
	public void showPlayerCards(){
		for(int i = 0; i < PLAY_NUM; i++){
			//when player not null
			if(players[i] != null){
				//output name of players
				System.out.println(players[i]+": ");
				//output cards of players
				for(String card : playersCards[i]){
					System.out.println(card + "\t");
				}
				System.out.println("\n");
			}
		}
	}
	
	/**
	 * find the index by first in the players array
	 * */
	public int search(String first) {
		for(int i = 0; i < players.length; i++){
			if(players[i] == first){
				return i;
			}
		}
				return -1;
	}
	
	public static void main(String[] args){
		ShowHand sh = new ShowHand();
		sh.initPlaryers("picksomething", "computer");
		sh.initCards();
		sh.initPlayerCards();
		//test all cards just use for test
		sh.showAllCards();
		System.out.println("-------------------");
		//deliverCard from player picksomething
		sh.deliverCard("picksomething");
		sh.showPlayerCards();
		// add some function:
		//player of the first card point is biggest start wager
		//whether other players follow
		//whether only one player rest
		//if only one card on desk all player start compare points of their cards
		sh.deliverCard("picksomething");
		sh.showPlayerCards();
	}
}
