package com.blz.gambler.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GamblerSimulator {
	public static final int STAKE = 100;
	public static final int BET = 1;
	public static final float STAKE_VALUE = 0.5f;
	public int stake = 100, lossAmount, winAmount, totalAmountEarned = 0;
	public int lostDays, wonDays = 0;

	public String winOrLoss() {
		Random rand = new Random();
		int num = rand.nextInt(2);
		if (num == 1) {
			// System.out.println("***GAMBLER WON***");
			stake++;
			return "won the game";
		} else {
			// System.out.println("***GAMBLER LOST***");
			stake--;
			return "lost the game";

		}

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

	public void maxMinEarnedDays() {
		ArrayList<Integer> day_stake = new ArrayList<Integer>();
		for (int day = 1; day <= 20; day++) {
			day_stake.add(resignStake(day));
		}
		Collections.sort(day_stake);
		int size = day_stake.size();
		System.out.println("The luckiest day with maximum earning: " + day_stake.get(size - 1));
		System.out.println("The unluckiest day with minimum earning: " + day_stake.get(0));
	}

	public void continueGame() {
		String result = winOrLoss();
		Scanner scan = new Scanner(System.in);
		if (result.contains("won")) {
			System.out.println("if you want to continue...Choose Y/N");
			char res = scan.next().charAt(0);
			if (res == ('Y' | 'y')) {
				System.out.println("Continue for next month");
				game();
			} else if (res == ('N' | 'n')) {
				System.out.println("Thank you for playing");
			} else {
				System.out.println("Please enter right choice");
			}
		}
	}

	public void game() {
		winOrLoss();
		resignStake(20);
		getTotalAmountWonOrLoss();
		calculateForMonth();
		maxMinEarnedDays();
		continueGame();
	}

	public static void main(String[] args) {
		System.out.println("WELCOME TO GAMBLER SIMULATION PROBLEM");
		GamblerSimulator game1 = new GamblerSimulator();
		game1.continueGame();
	}

}
