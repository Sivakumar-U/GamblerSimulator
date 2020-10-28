package com.blz.gambler.simulator;

import java.util.Random;

public class GamblerSimulator {
	public static final int STAKE = 100;
	public static final int BET = 1;
	public static final float STAKE_VALUE = 0.5f;
	public int stake = 100, lossAmount, winAmount, totalAmountEarned = 0;
	public int lostDays, wonDays = 0;

	public int winOrLoss() {
		Random rand = new Random();
		int num = rand.nextInt(2);
		if (num == 1) {
			// System.out.println("***GAMBLER WON***");
			stake++;
		} else {
			// System.out.println("***GAMBLER LOST***");
			stake--;
		}
		return stake;
	}

	public int resignStake(int day) {
		lossAmount = (int) Math.round(STAKE * STAKE_VALUE);
		winAmount = (int) Math.round(STAKE + (STAKE * STAKE_VALUE));
		boolean stop = true;
		stake = STAKE;
		while (stop == true) {
			winOrLoss();
			if (stake == lossAmount) {
				lostDays++;
				stop = false;
			}
			if (stake == winAmount) {
				wonDays++;
				stop = false;
			}
		}
		return stake;
	}

	public int getTotalAmountWonOrLoss() {
		int day_stake = 0;
		for (int day = 0; day <= 20; day++) {
			day_stake = resignStake(day);
			System.out.println("Day: " + day + " amount: " + day_stake);
			totalAmountEarned = totalAmountEarned + day_stake;
		}
		System.out.println("Total Amount Earned or Loss by Gambler at End of given period:- " + totalAmountEarned);
		return totalAmountEarned;
	}

	public int calculateForMonth() {
		getTotalAmountWonOrLoss();
		System.out.println("Days Won in  month: " + wonDays);
		System.out.println("Days Loss in  month: " + lostDays);
		return totalAmountEarned;
	}

	public static void main(String[] args) {
		System.out.println("WELCOME TO GAMBLER SIMULATION PROBLEM");
		GamblerSimulator game = new GamblerSimulator();
		game.calculateForMonth();
	}

}
