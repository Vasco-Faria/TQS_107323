package stockportfolio;



import org.mockito.*;

import net.bytebuddy.utility.nullability.MaybeNull;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.notNullValue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;





@ExtendWith(MockitoExtension.class)
public class PortfolioTest 
{

    IStockmarketService market = Mockito.mock(IStockmarketService.class);

    StocksPortfolio portfolio = new StocksPortfolio(market);

    @Test
    public void testTotalValue() {

        portfolio.addstock(new Stock("EBAY", 2));
        portfolio.addstock(new Stock("AAPL",4));


        Mockito.when(market.lookupPrice("EBAY")).thenReturn(50.0);
        Mockito.when(market.lookupPrice("AAPL")).thenReturn(100.0);

        double result = portfolio.totalvalue();

        assertEquals(500, result);
        assertThat("EBAY",notNullValue());
        assertThat("AAPL",containsString("AA"));


        Mockito.verify(market).lookupPrice("EBAY");
        Mockito.verify(market).lookupPrice("AAPL");
        Mockito.verifyNoMoreInteractions(market);

    }


}
