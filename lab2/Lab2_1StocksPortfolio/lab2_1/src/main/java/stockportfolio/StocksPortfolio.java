package stockportfolio;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {


    private IStockmarketService stockmarket;
    private List<Stock> stocks = new ArrayList<Stock>();


    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addstock(Stock stock){
        stocks.add(stock);

    }

    public double totalvalue(){
        double totalvalue = 0;
        for (Stock stock : stocks) {
            totalvalue += stockmarket.lookupPrice(stock.getLabel()) * stock.getQuantity();
        }

        return totalvalue;
    }

}
