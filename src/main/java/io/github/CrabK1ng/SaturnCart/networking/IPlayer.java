package io.github.CrabK1ng.SaturnCart.networking;

import io.github.CrabK1ng.SaturnCart.RaceTrack;

public interface IPlayer {

    void setRacing(boolean isRacing);
    boolean isRacing();

    int getLap();
    void setLap(int laps);
    void addLap(int laps);

    boolean getcheckpoint1();
    void setcheckpoint1(boolean checkpoint1);

    boolean getcheckpoint2();
    void setcheckpoint2(boolean checkpoint2);

    void reset();
}
