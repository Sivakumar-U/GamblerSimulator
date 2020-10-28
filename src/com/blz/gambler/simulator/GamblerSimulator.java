package com.blz.gambler.simulator;

import java.util.Random;

public class GamblerSimulator {
	public static final int STAKE = 100;
	public static final int BET = 1;
	public static final float STAKE_VALUE = 0.5f;
	int stake = 100, lossAmount, winAmount;

	public int winOrLoss() {
		Random rand = new Random();
		int num = rand.nextInt(2);
		if (num == 1) {
			System.out.println("***GAMBLER WON***");
			stake++;
		} else {
			System.out.println("***GAMBLER LOST***");
			stake--;
		}
		return stake;
	}
	
	public int resignStake() {
		lossAmount = (int)Math.round(STAKE * STAKE_VALUE);
		winAmount = (int)Math.round(STAKE + (STAKE * STAKE_VALUE));
		boolean stop = true;
		stake = STAKE;
		while(stop == true) {
			winOrLoss();
			if(stake == lossAmount) {
				stop = false;
			}
			if(stake == winAmount){
				stop = false;
			}
		}
		return stake;
	}

	public static void main(String[] args) {
		System.out.println("WELCOME TO GAMBLER SIMULATION PROBLEM");
		GamblerSimulator game = new GamblerSimulator();
		System.out.println("Remaining Stake: " + game.resignStake());
	}

}
