package com.example.lpkkk.correxhelper;

/**
 * Created by lpkkk on 11/05/17.
 */

import java.util.ArrayList;

public class SearchEngine {
    ArrayList<Pallet> palletsArray;
    public SearchEngine(ArrayList<Pallet> newPalletsArray){
        this.palletsArray = newPalletsArray;
    }

    public ArrayList<Integer> getClosestWL(int toFindW,int toFindL, int maxGap){
        maxGap = maxGap * 2;
        ArrayList<Integer> closest = new ArrayList<Integer>();
        int closestDist = 10000;
        int i = 0;
        while (i<palletsArray.size()){
            int j = toFindW - palletsArray.get(i).getW();
            if(j < 0){
                j = -j;
            }
            //System.out.println("WChecking no:" + i + " " + palletsArray.get(i) + " j=" + j + " closestDist="+ closestDist);
            if(j < maxGap) {
                int k = toFindL - palletsArray.get(i).getL();
                if(k < 0){
                    k = -k;
                }
                if(k < maxGap) {
                    if (j < closestDist) {
                        closest = new ArrayList<Integer>();
                        closest.add(i);
                        closestDist = j;
                    } else if (j == closestDist) {
                        closest.add(i);
                    }
                }
            }

            i++;
        }
        System.out.println("WLClosest[" + toFindW + "," + toFindL + "]:" + closest.toString());
        return closest;
    }

    public ArrayList<Integer> getClosestW(int toFind, int maxGap){
        ArrayList<Integer> closest = new ArrayList<Integer>();
        int closestDist = 10000;
        int i = 0;
        while (i<palletsArray.size()){
            int j = toFind - palletsArray.get(i).getW();
            if(j < 0){
                j = -j;
            }
            //System.out.println("WChecking no:" + i + " " + palletsArray.get(i) + " j=" + j + " closestDist="+ closestDist);
            if(j < maxGap) {

                if (j < closestDist) {
                    closest = new ArrayList<Integer>();
                    closest.add(i);
                    closestDist = j;
                } else if (j == closestDist) {
                    closest.add(i);
                }

            }

            i++;
        }
        System.out.println("WClosest[" + toFind + "]:" + closest.toString());
        return closest;
    }
    public ArrayList<Integer> getClosestL(int toFind, int maxGap){
        ArrayList<Integer> closest = new ArrayList<Integer>();
        int closestDist = 10000;
        int i = 0;
        while (i<palletsArray.size()){
            int j = toFind - palletsArray.get(i).getL();
            if(j < 0){
                j = -j;
            }
            //System.out.println("HChecking no:" + i + " " + palletsArray.get(i) + " j=" + j + " closestDist="+ closestDist);
            if(j<maxGap) {
                if (j < closestDist) {
                    closest = new ArrayList<Integer>();
                    closest.add(i);
                    closestDist = j;
                } else if (j == closestDist) {
                    closest.add(i);
                }
            }

            i++;
        }
        System.out.println("LClosest[" + toFind + "]:" + closest.toString());
        return closest;
    }
}
