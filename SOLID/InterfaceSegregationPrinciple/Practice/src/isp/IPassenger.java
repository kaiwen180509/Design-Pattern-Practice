package isp;

public interface IPassenger {
    void pressingRing(IBusRing busRing);

    void board(ICar car);
    
    void leave(ICar car);
}
