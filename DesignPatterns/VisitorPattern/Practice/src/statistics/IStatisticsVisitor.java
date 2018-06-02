package statistics;

import normal.visitor.IVisitor;

public interface IStatisticsVisitor extends IVisitor {
    int getTotalPrice();
}
