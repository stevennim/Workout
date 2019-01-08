package com.hfad.workout;

public class Workout {
    private String name;
    private String description;

    public static final Workout[] workouts = {//list of workouts
            new Workout("The Limb Loosener", "5 handstand push-ups\n10 1-legged squats\n15 Pull-ups"),
            new Workout("Senpai Special", "10 Headpats\n25 Blushes\n12 Stammers\n0 Confessions"),
            new Workout("Horny on Main", "20 uwu\n20 owo\n10 Panty flashes\n3 Eroge scenes"),
            new Workout("Degen Double X", "50 Imoutos\n0 Non-blood related Imoutos"),
    };

    public Workout(String name, String desc){//constructor
        this.name = name;
        this.description = desc;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String toString(){
        return this.name;
    }
}
