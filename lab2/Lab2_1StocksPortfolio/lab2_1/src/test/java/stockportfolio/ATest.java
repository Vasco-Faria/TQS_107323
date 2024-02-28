package stockportfolio;



import org.mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import net.bytebuddy.utility.nullability.MaybeNull;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ATest
{

    @Mock
    IStockmarketService market;

    @InjectMocks
    StocksPortfolio portfolio;



    @Test
    public void testTotalValue() {
        

        portfolio.addstock(new Stock("HUAWEI", 2));
        portfolio.addstock(new Stock("APPLE",5));
        portfolio.addstock(new Stock("OPPO",4));


        Mockito.when(market.lookupPrice("HUAWEI")).thenReturn(100.0);
        Mockito.when(market.lookupPrice("APPLE")).thenReturn(200.0);
        Mockito.when(market.lookupPrice("OPPO")).thenReturn(50.0);

        double result = portfolio.totalvalue();

        assertEquals(1400, result);

        Mockito.verify(market).lookupPrice("HUAWEI");
        Mockito.verify(market).lookupPrice("APPLE");
        Mockito.verify(market).lookupPrice("OPPO");
        Mockito.verifyNoMoreInteractions(market);

    }


}
